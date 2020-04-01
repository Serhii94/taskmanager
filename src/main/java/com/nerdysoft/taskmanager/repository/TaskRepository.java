package com.nerdysoft.taskmanager.repository;

import com.nerdysoft.taskmanager.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<Task> findById(String id);

//    // Return all documents having particular user email
//    @Query("{ 'emails' : 0? }")
//    List<Task> findAllByEmail(String email);
}
