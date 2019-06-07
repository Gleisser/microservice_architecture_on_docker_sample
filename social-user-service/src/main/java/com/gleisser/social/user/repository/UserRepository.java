package com.gleisser.social.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gleisser.social.user.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	Optional<User> findByUsername(String username);

}
