package com.retail.store.auth.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.retail.store.auth.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends MongoRepository<User, ObjectId>{

	public Optional<User> findByUsername(String username);

}
