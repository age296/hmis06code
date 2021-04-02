package com.example.test.views;


import com.example.test.conection.userconection;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value ="register", layout=LoginRegisterLayout.class) 
@CssImport("./styles/views/login/login-view.css")
public class RegisterView extends Composite {


    public RegisterView() {
        setId("register-view");

    }

    @Override
    protected Component initContent() {
        TextField username = new TextField("Usuario");
        PasswordField password1 = new PasswordField("Contraseña");
        PasswordField password2 = new PasswordField("Confirma contraseña");
        TextField email = new TextField("Correo electrónico");
        Button enviar = new Button("Enviar", event -> register(
                username.getValue(),
                password1.getValue(),
                password2.getValue(),
                email.getValue()));
        
        enviar.addThemeVariants(ButtonVariant.LUMO_SMALL,
                ButtonVariant.LUMO_PRIMARY);
        return new VerticalLayout(
                new H2("Registro"),
                username,
                password1,
                password2,
                email,
                enviar
        );
    }

    private void register(String username, String password1, String password2,String email) {
    	userconection uc = new userconection();
    	if (username.trim().isEmpty()) {
            Notification.show("Introduce un usuario");
        }else if (uc.userExists(username)) {
            Notification.show("Usuario existente");
        }else if (password1.isEmpty()) {
            Notification.show("Introducuce una contraseña");
        } else if (!password1.equals(password2)) {
            Notification.show("Las contraseñas introducidas no coinciden");
        } else if (!email.contains("@")) {
            Notification.show("Ha intruducido un correo erroneo");
        }
        else {
        	
            boolean flag = uc.addUser(username, email, password1);
            if(flag) {
            	Notification.show("Se ha reistrado con éxito");
            }else
            	Notification.show("Se ha producido un error, intentelo más tarde");
        }
    }
}
