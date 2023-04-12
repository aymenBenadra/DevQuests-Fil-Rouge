package com.sakamoto.frontend.view;

import com.sakamoto.frontend.data.model.Project;
import com.sakamoto.frontend.data.model.Submission;
import com.sakamoto.frontend.data.service.ProjectService;
import com.sakamoto.frontend.data.service.SubmissionService;
import com.sakamoto.frontend.layout.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;
import java.util.Objects;

@RolesAllowed("ADMIN")
@Route(value = "submissions", layout = MainLayout.class)
@PageTitle("DevQuests | Submissions")
public class SubmissionsView extends VerticalLayout {

    private final ComboBox<Project> projectsComboBox = new ComboBox<>();
    private final Grid<Submission> submissionsGrid = new Grid<>(Submission.class);
    private final ProjectService projectService;
    private final SubmissionService submissionService;

    public SubmissionsView(ProjectService ps, SubmissionService ss) {
        this.projectService = ps;
        this.submissionService = ss;

        setSizeFull();
        configureComboBox();
        configureGrid();
        add(projectsComboBox, submissionsGrid);
    }

    private void configureComboBox() {
        projectsComboBox.setLabel("Project");
        projectsComboBox.setItemLabelGenerator(Project::name);
        projectsComboBox.setClearButtonVisible(true);
        projectsComboBox.setItems(projectService.getProjects().collectList().block());
        projectsComboBox.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        if (Objects.isNull(projectsComboBox.getValue())) {
            return;
        }
        submissionsGrid.setItems(submissionService.getProjectSubmissions(projectsComboBox.getValue().id())
//                .sort((s1, s2) -> s2.createdAt().compareTo(s1.createdAt()))
                .collectList()
                .block());
    }

    private void configureGrid() {
        submissionsGrid.setSizeFull();
        submissionsGrid.addColumn(submission -> projectsComboBox.getValue().name()).setHeader("Project");
        submissionsGrid.addColumn(Submission::userId).setHeader("User ID");
        submissionsGrid.addColumn(Submission::createdAt).setHeader("Submission Date");
        submissionsGrid.addColumn(Submission::status).setHeader("Status");
        submissionsGrid.addColumn(Submission::githubLink).setHeader("Github Link");

        submissionsGrid.getColumns().forEach(col -> {
            col.setAutoWidth(true);
            col.setSortable(true);
        });
    }
}
