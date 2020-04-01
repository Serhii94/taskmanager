package com.nerdysoft.taskmanager.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class TaskAccess {
    @NonNull
    private String taskId;
    @NonNull
    private String providedBy;
}
