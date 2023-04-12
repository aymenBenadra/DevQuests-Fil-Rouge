package com.sakamoto.frontend.view;

import com.sakamoto.frontend.component.ProjectDetails;
import com.sakamoto.frontend.data.dto.CreateSubmissionCommand;
import com.sakamoto.frontend.data.event.SubmitEvent;
import com.sakamoto.frontend.data.model.Project;
import com.sakamoto.frontend.data.service.ProjectService;
import com.sakamoto.frontend.data.service.SubmissionService;
import com.sakamoto.frontend.layout.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.Arrays;
import java.util.stream.Collectors;


@PermitAll
@Route(value = "projects", layout = MainLayout.class)
@PageTitle("Project Directory")
public class ProjectDirectoryView extends VerticalLayout {
    Grid<Project> grid = new Grid<>(Project.class);
    ProjectDetails projectDetails;
    ProjectService projectService;
    SubmissionService submissionService;

    public ProjectDirectoryView(ProjectService projectService, SubmissionService submissionService) {
        this.projectService = projectService;
        this.submissionService = submissionService;

        setSizeFull();
        setSpacing(true);
        setPadding(true);

        configureGrid();
        configureDetails();

        add(getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, projectDetails);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, projectDetails);
        content.setSizeFull();
        return content;
    }

    private void configureDetails() {
        projectDetails = new ProjectDetails();
        projectDetails.setWidth("25em");
        projectDetails.addSubmitListener(this::submitProject);
        projectDetails.addCloseListener(e -> closeEditor());
    }

    private void submitProject(SubmitEvent event) {
        submissionService
                .saveSubmission(new CreateSubmissionCommand("user", event.getProject().id()))
                .subscribe(submission -> Notification.show("Submission Added successfully!"));
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(Project::name).setHeader("Title");
        grid.addColumn(Project::difficulty).setHeader("Difficulty");
        grid.addColumn(Project::estimatedTime).setHeader("Estimated Time (in Hours)");
        grid.addColumn(project -> String.join(" ", project.description().split(" ", 10)) + "...").setHeader("Description");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> showProject(event.getValue()));
    }

    private void showProject(Project value) {
        if (value == null) {
            closeEditor();
        } else {
            projectDetails.setProject(value);
            projectDetails.setVisible(true);
        }
    }

    private void closeEditor() {
        projectDetails.setProject(null);
        projectDetails.setVisible(false);
    }

    private void updateList() {
        grid.setItems(projectService.getProjects().collectList().block());
    }
}
