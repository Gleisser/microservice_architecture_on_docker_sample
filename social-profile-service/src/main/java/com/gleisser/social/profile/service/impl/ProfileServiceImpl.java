package com.gleisser.social.profile.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleisser.social.profile.domain.Profile;
import com.gleisser.social.profile.repository.ProfileRepository;
import com.gleisser.social.profile.service.ProfileService;

/**
 * Service Implementation for managing Profile.
 */

@Service
public class ProfileServiceImpl implements ProfileService {
	
	private final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);
	
	@Autowired
	private ProfileRepository repository;

	/**
     * Save a profile.
     *
     * @param profile the entity to save
     * @return the persisted entity
     */
    @Override
    public Profile save(Profile profile) {
        log.debug("Request to save Profile : {}", profile);
        return repository.save(profile);
    }

    /**
     * Get all the profiles.
     *
     * @return the list of entities
     */
    @Override
    public List<Profile> findAll() {
        log.debug("Request to get all Profiles");
        return repository.findAll();
    }

    /**
     * Get one profile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Profile findOne(String id) {
        log.debug("Request to get Profile : {}", id);
        return repository.findById(id).orElse(new Profile());
    }

    /**
     * Get one profile by user id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Profile findByUserId(String id) {
        log.debug("Request to get Profile by User id: {}", id);
        return repository.findByUserId(id).orElse(new Profile());
    }


    /**
     * Delete the profile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Profile : {}", id);
        repository.deleteById(id);
    }

}
