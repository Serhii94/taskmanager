package com.nerdysoft.taskmanager.domain;

import com.nerdysoft.taskmanager.dto.TaskAccess;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    // List representing user' tasks.
    private List<String> taskIds;
    private List<TaskAccess> sharedBy;
}
