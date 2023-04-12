package com.sakamoto.frontend.view;

import com.sakamoto.frontend.config.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.awt.*;

@AnonymousAllowed
@Route(value = "")
@PageTitle("DevQuests | Home")
public class HomeView extends VerticalLayout {

    public HomeView(SecurityService securityService) {

        Image image = new Image("images/devquests.png", "DevQuests Logo");

        image.setWidth("300px");
        image.setHeight("300px");

        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setSpacing(true);
        setPadding(true);
        getStyle().set("background-color", Color.LIGHT_GRAY.toString());

        add(image,
                securityService.isUserAuthenticated()
                        ? new H1("Welcome back " + securityService.getAuthenticatedUser().getUsername())
                        : new H1("Welcome to DevQuests!"),
                new H2("A platform for developers to learn and grow their skills."),
                securityService.isUserAuthenticated()
                        ? new Button("Get Started!", e -> UI.getCurrent().navigate(ProjectDirectoryView.class))
                        : new Button("Login to Get Started!", e -> UI.getCurrent().navigate(LoginView.class)));
    }
}
