package com.gleisser.social.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleisser.social.user.domain.User;
import com.gleisser.social.user.repository.UserRepository;
import com.gleisser.social.user.service.UserService;

/**
 * Service Implementation for managing User.
 */
@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository repository;
	
	/**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    @Override
    public User save(User user) {
        log.debug("Request to save User : {}", user);
        return repository.save(user);
    }

    /**
     * Get all the users.
     *
     * @return the list of entities
     */
    @Override
    public List<User> findAll() {
        log.debug("Request to get all Users");
        return repository.findAll();
    }

    /**
     * Get one user by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public User findOne(String id) {
        log.debug("Request to get User : {}", id);
        return repository.findById(id).orElse(new User());
    }

    /**
     * Delete the user by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete User : {}", id);
        repository.deleteById(id);
    }

    /**
     * Get one user by username / email.
     *
     * @param user name or email of the entity
     * @return the entity
     */
    @Override
    public User findByUsername(String username) {
        log.debug("Request to get User by the email : {}", username);
        return repository.findByUsername(username).orElse(new User());
    }

}
