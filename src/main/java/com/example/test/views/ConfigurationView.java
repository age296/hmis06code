package com.example.test.views;




import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.test.conection.Customer;
import com.example.test.conection.userconection;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route(value ="", layout = MainLayout.class) 
@CssImport("./styles/views/login/login-view.css")

public class ConfigurationView extends VerticalLayout {
    
	public ConfigurationView(){
		addClassName("conf-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER); 
		
		userconection uc =new userconection();
		Customer c  = uc.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Text mensajeInicial = new Text("Hola, \n"
				+c.getFirstName()+", aquí puedes cambiar tu email: "
				+c.getEmail()+" o tu cotraseña: ******");
		add(mensajeInicial);
	//	add(new Text("Hola, "+c.getFirstName()));
		//add(new Text(", aquí puedes cambiar tu email: "));
	//	add(new Text(c.getEmail()+" o tu cotraseña: ******")); 
		
		TextField email = new TextField("Nuevo email");
        PasswordField password = new PasswordField("Nueva contraseña");
        Button cambiarEmail = new Button("Cambiar email", event -> cambiarEmail(
        		email.getValue()
        ));
        
    
        Button cambiarClave = new Button("Cambiar contraseña", event -> cambiarClave(
        		password.getValue()
        ));
        cambiarEmail.addThemeVariants(ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_PRIMARY);
  
        cambiarClave.addThemeVariants(ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_PRIMARY);
        email.setWidth("40%");
        cambiarEmail.setWidth("40%");
        password.setWidth("40%");
        cambiarClave.setWidth("40%");
        add(email,cambiarEmail, password,cambiarClave);
    	
	}

	private void cambiarClave(String value) {
		userconection uc = new userconection();
		if(value.isEmpty()) {
			Notification.show("Ha intruducido una contraseña erronea");
		}else if(uc.updatePassword(SecurityContextHolder.getContext().getAuthentication().getName(), value)) {
			Notification.show("Contraseña cambiada correctamente");
		}else {
			Notification.show("No se ha la contraseña");
		}
		
    }


	private void cambiarEmail(String value) {
		userconection uc = new userconection();
		Customer c  = uc.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		if(!value.contains("@")) {
			Notification.show("Ha intruducido un correo erroneo");
		}else if(uc.updateEmail(SecurityContextHolder.getContext().getAuthentication().getName(), value)) {
			Notification.show("Email cambiado correctamente");
		}else {
			Notification.show("No se ha podido cambiar el email");
		}
	}
}