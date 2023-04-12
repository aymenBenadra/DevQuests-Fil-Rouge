package com.sakamoto.frontend.data.event;

import com.sakamoto.frontend.component.ProjectDetails;
import com.sakamoto.frontend.data.model.Project;

public class SubmitEvent extends ProjectFormEvent {
    public SubmitEvent(ProjectDetails source, Project project) {
        super(source, project);
    }
}