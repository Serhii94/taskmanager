package com.nerdysoft.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ShareTaskInfo {
    private String providerId;
    private String providerUsername;
    private String receiverId;
    private String receiverUsername;
    private LocalDateTime time;
}
