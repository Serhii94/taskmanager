package com.nerdysoft.taskmanager.repository;

import com.nerdysoft.taskmanager.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'tasks' : ?0 }")
    List<User> findAllByTaskId(String taskId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
