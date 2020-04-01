package com.nerdysoft.taskmanager.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ShareTaskData {
    @NonNull private String taskId;
    @NonNull private String receiverEmail;
}
