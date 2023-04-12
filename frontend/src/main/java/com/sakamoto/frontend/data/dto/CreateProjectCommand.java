package com.sakamoto.frontend.data.dto;

import com.sakamoto.frontend.data.model.Difficulty;
import com.sakamoto.frontend.data.model.Task;

import java.util.List;

public record CreateProjectCommand(String name, String description, List<Task> tasks, Difficulty difficulty) {
}
