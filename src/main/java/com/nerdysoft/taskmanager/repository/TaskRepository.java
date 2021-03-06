package com.nerdysoft.taskmanager.repository;

import com.nerdysoft.taskmanager.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

}
