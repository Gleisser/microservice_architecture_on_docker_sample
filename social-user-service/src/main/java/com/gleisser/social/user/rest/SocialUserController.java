package com.gleisser.social.user.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gleisser.social.user.HeaderUtil;
import com.gleisser.social.user.domain.User;
import com.gleisser.social.user.service.UserService;

/**
 * REST controller for managing User.
 */

@RestController
@RequestMapping("/user")
public class SocialUserController {

	private final Logger log = LoggerFactory.getLogger(SocialUserController.class);

	private static final String ENTITY_NAME = "user";

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	/**
	 * POST /users : Create a new user.
	 *
	 * @param user the user to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         user, or with status 400 (Bad Request) if the user has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
		log.debug("REST request to save User : {}", user);
		if (user.getId() != null) {
			log.error("A new profile cannot already have an ID {} idexists", ENTITY_NAME);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User result = userService.save(user);
		return ResponseEntity.created(new URI("/api/users/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /users : Updates an existing user.
	 *
	 * @param user the user to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         user, or with status 400 (Bad Request) if the user is not valid, or
	 *         with status 500 (Internal Server Error) if the user couldn't be
	 *         updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws URISyntaxException {
		log.debug("REST request to update User : {}", user);
		if (user.getId() == null) {
			return createUser(user);
		}
		User result = userService.save(user);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, user.getId().toString()))
				.body(result);
	}

	/**
	 * GET /users : get all the users.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of users in body
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		log.debug("REST request to get all Users");
		return userService.findAll();
	}

	/**
	 * GET /users/:id : get the "id" user.
	 *
	 * @param id the id of the user to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the user, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		log.debug("REST request to get User : {}", id);
		User user = userService.findOne(id);
		return Optional.ofNullable(user).map(r -> ResponseEntity.ok().header(null).body(r))
				.orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
	}

	/**
	 * GET /isusernameavailable/:username : check if the username is not already
	 * taken
	 *
	 * @param username the username of the user to check
	 * @return the ResponseEntity with status 200 (OK) true if username is available
	 *         or false if not
	 */
	@GetMapping("/isusernameavailable")
	public ResponseEntity<Boolean> isUsernameAvailable(@RequestParam(name = "username") String username) {
		log.debug("REST request to check if the username is available : {}", username);
		User user = userService.findByUsername(username);
		return ResponseEntity.ok().body(user != null ? false : true);
	}

	/**
	 * DELETE /users/:id : delete the "id" user.
	 *
	 * @param id the id of the user to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		log.debug("REST request to delete User : {}", id);
		userService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}

}
