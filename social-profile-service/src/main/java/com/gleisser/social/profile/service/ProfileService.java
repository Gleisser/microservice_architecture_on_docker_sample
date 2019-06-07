package com.gleisser.social.profile.service;

import java.util.List;

import com.gleisser.social.profile.domain.Profile;

/**
 * Service Interface for managing Profile.
 */
public interface ProfileService {
	
	/**
     * Save a profile.
     *
     * @param profile the entity to save
     * @return the persisted entity
     */
    Profile save(Profile profile);

    /**
     * Get all the profiles.
     *
     * @return the list of entities
     */
    List<Profile> findAll();

    /**
     * Get the "id" profile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Profile findOne(String id);

    /**
     * Delete the "id" profile.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Find profile by User Id
     * @param id
     * @return
     */
    Profile findByUserId(String id);
    
}
