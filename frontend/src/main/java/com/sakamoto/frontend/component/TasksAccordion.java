package com.sakamoto.frontend.component;

import com.sakamoto.frontend.data.model.Task;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class TasksAccordion extends VerticalLayout {

    private final List<Task> tasks = new ArrayList<>();

    public TasksAccordion() {
        Accordion tasksAccordion = new Accordion();
        tasks.forEach(task -> {
            AccordionPanel taskAccordion = tasksAccordion.add(task.name(), new Text(task.description()));
        });
        add(tasksAccordion);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }
}
