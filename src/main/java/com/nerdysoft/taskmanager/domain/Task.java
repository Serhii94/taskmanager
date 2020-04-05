package com.nerdysoft.taskmanager.domain;

import com.nerdysoft.taskmanager.dto.ShareTaskInfo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String title;
    private String description;
    private List<ShareTaskInfo> shares;
}
