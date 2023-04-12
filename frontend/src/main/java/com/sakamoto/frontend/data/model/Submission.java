package com.sakamoto.frontend.data.model;

public record Submission(String id, String githubLink, Status status, String userId, String projectId,
                         String createdAt) {
}
