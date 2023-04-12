package com.sakamoto.frontend.data.event;

import com.sakamoto.frontend.component.ProjectDetails;

public class CloseEvent extends ProjectFormEvent {
    public CloseEvent(ProjectDetails source) {
        super(source, null);
    }
}
