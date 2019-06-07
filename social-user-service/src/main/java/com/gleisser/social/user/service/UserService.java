package com.gleisser.social.user.service;

import java.util.List;

import com.gleisser.social.user.domain.User;

/**
 * Service Interface for managing User.
 */

public interface UserService {
	
	/**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    User save(User user);

    /**
     * Get all the users.
     *
     * @return the list of entities
     */
    List<User> findAll();

    /**
     * Get the "id" user.
     *
     * @param id the id of the entity
     * @return the entity
     */
    User findOne(String id);

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    /**
     * Find an user by email / username
     * @param username or email of the entity
     *
     */
    User findByUsername(String username);
}
