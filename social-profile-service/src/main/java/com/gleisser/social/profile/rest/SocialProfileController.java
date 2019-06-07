package com.gleisser.social.profile.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gleisser.social.profile.domain.Profile;
import com.gleisser.social.profile.external.services.UserServiceClient;
import com.gleisser.social.profile.service.ProfileService;
import com.gleisser.social.profile.utils.HeaderUtil;

@RestController
@RequestMapping("/profile")
public class SocialProfileController {

	private final Logger log = LoggerFactory.getLogger(SocialProfileController.class);

	private static final String ENTITY_NAME = "profile";

	@Autowired
	private ProfileService profileService;

	@Autowired
	private UserServiceClient serviceClient;

	@GetMapping("/hello")
	public String hello() {
		return "hello profile, Hello from User: " + serviceClient.hello();
	}

	/**
	 * POST /profiles : Create a new profile.
	 *
	 * @param profile the profile to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         profile, or with status 400 (Bad Request) if the profile has already
	 *         an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/profiles")
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) throws URISyntaxException {
		log.debug("REST request to save Profile : {}", profile);
		if (profile.getId() != null) {
			log.error("A new profile cannot already have an ID {} idexists", ENTITY_NAME);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Profile result = profileService.save(profile);
		return ResponseEntity.created(new URI("/api/profiles/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /profiles : Updates an existing profile.
	 *
	 * @param profile the profile to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         profile, or with status 400 (Bad Request) if the profile is not
	 *         valid, or with status 500 (Internal Server Error) if the profile
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/profiles")
	public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) throws URISyntaxException {
		log.debug("REST request to update Profile : {}", profile);
		if (profile.getId() == null) {
			return createProfile(profile);
		}
		Profile result = profileService.save(profile);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profile.getId().toString()))
				.body(result);
	}

	/**
	 * GET /profiles : get all the profiles.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of profiles in
	 *         body
	 */
	@GetMapping("/profiles")
	public List<Profile> getAllProfiles() {
		log.debug("REST request to get all Profiles");
		return profileService.findAll();
	}

	/**
	 * GET /profiles/:id : get the "id" profile.
	 *
	 * @param id the id of the profile to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the profile, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/profiles/{id}")
	public ResponseEntity<Profile> getProfile(@PathVariable String id) {
		log.debug("REST request to get Profile : {}", id);
		Profile profile = profileService.findByUserId(id);
		if (StringUtils.isBlank(profile.getId())) {
			profile = profileService.findOne(id);
		}
		return Optional.ofNullable(profile).map(r -> ResponseEntity.ok().header(null).body(r))
				.orElse(new ResponseEntity<Profile>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /profiles/:id : delete the "id" profile.
	 *
	 * @param id the id of the profile to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/profiles/{id}")
	public ResponseEntity<Void> deleteProfile(@PathVariable String id) {
		log.debug("REST request to delete Profile : {}", id);
		profileService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}

}
