package com.example.test.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
public class LoginRegisterLayout extends AppLayout { 
    public LoginRegisterLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
    	H1 logo = new H1("Bienvenido a Equipo 03 WeebApp");
        logo.addClassName("logo");

    
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo); 
 
        setDrawerOpened(false);
        header.expand(logo); 
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("Inicio de sesión", LoginView.class); 
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 

        addToDrawer(new VerticalLayout(listLink)); 
        
        listLink = new RouterLink("Registrase", RegisterView.class); 
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 
        addToDrawer(new VerticalLayout(listLink)); 
    }
}
