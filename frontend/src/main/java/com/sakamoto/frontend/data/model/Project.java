package com.sakamoto.frontend.data.model;

import java.util.List;

public record Project(String id, String name, String description, List<Task> tasks, Difficulty difficulty,
                      int estimatedTime) {
}
