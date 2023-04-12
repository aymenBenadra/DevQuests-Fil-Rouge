package com.sakamoto.frontend.data.event;

import com.sakamoto.frontend.component.ProjectDetails;
import com.sakamoto.frontend.data.model.Project;
import com.vaadin.flow.component.ComponentEvent;

public abstract class ProjectFormEvent extends ComponentEvent<ProjectDetails> {
        private final Project project;

        protected ProjectFormEvent(ProjectDetails source, Project project) {
            super(source, false);
            this.project = project;
        }

        public Project getProject() {
            return project;
        }
    }