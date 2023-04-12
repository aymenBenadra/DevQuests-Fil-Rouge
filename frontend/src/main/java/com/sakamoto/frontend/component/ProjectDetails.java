package com.sakamoto.frontend.component;

import com.sakamoto.frontend.data.event.CloseEvent;
import com.sakamoto.frontend.data.event.SubmitEvent;
import com.sakamoto.frontend.data.model.Difficulty;
import com.sakamoto.frontend.data.model.Project;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ProjectDetails extends VerticalLayout {
    TextField name = new TextField("Title", "Project Name");
    ComboBox<Difficulty> difficulty = new ComboBox<>("Difficulty");
    TextField estimatedTime = new TextField("Estimated Time");
    TextArea description = new TextArea("Description");
    TasksAccordion tasksAccordion = new TasksAccordion();
    TextField githubLink = new TextField("Github Link", "https://github.com/username/repo");
    Button start = new Button("Start!");
    FormLayout form = new FormLayout(githubLink, start);
    Button submit = new Button("Submit");
    Button close = new Button("Close");
    Binder<Project> binder = new Binder<>(Project.class);

    public ProjectDetails() {
        binder.bindReadOnly(name, Project::name);
        binder.bindReadOnly(difficulty, Project::difficulty);
        binder.bindReadOnly(estimatedTime, project -> project.estimatedTime() + " Hours");
        binder.bindReadOnly(description, Project::description);

        name.setReadOnly(true);
        difficulty.setReadOnly(true);
        estimatedTime.setReadOnly(true);
        description.setReadOnly(true);

        difficulty.setItemLabelGenerator(Difficulty::name);
        difficulty.setItems(Difficulty.values());

        form.setVisible(false);

        binder.addValueChangeListener(event -> showTasks((Project) event.getValue()));

        add(name, new HorizontalLayout(difficulty, estimatedTime), description, tasksAccordion, form, createButtonsLayout());
    }

    private void showTasks(Project project) {
        if (project == null) {
            tasksAccordion.setTasks(List.of());
        } else {
            tasksAccordion.setTasks(project.tasks());
        }
    }

    private Component createButtonsLayout() {
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        submit.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        submit.addClickListener(event -> fireEvent(new SubmitEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        start.addClickListener(event -> {
            start.setVisible(false);
            form.setVisible(true);
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(start, close);

        return buttons;
    }

    public void setProject(Project project) {
        binder.setBean(project);
    }

    public Registration addSubmitListener(ComponentEventListener<SubmitEvent> listener) {
        return addListener(SubmitEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}

