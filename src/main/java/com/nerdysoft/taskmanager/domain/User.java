package com.nerdysoft.taskmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private boolean isAdmin;
    private List<String> tasks;

}
