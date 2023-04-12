package com.sakamoto.frontend.layout;

import com.sakamoto.frontend.config.SecurityService;
import com.sakamoto.frontend.view.ProjectDirectoryView;
import com.sakamoto.frontend.view.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService service) {
        securityService = service;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("DevQuests | Projects Directory");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        var header = new HorizontalLayout(new DrawerToggle(), logo);

        if (securityService.isUserAuthenticated()) {
            String u = securityService.getAuthenticatedUser().getUsername();
            Button logout = new Button("Log out - " + u, e -> securityService.logout());
            header.add(logout);
        } else {
            Button login = new Button("Login", e -> UI.getCurrent().navigate(LoginView.class));
            header.add(login);
        }

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        VerticalLayout drawer = new VerticalLayout(
                new Button("Project Directory", e-> UI.getCurrent().navigate(ProjectDirectoryView.class))
        );
        if (securityService.isAdmin()) {
            drawer.add(new Button("Submissions", e-> UI.getCurrent().navigate("submissions")));
        }
        addToDrawer(drawer);
    }
}