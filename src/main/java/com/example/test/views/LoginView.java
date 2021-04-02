package com.example.test.views;


import com.example.test.conection.Customer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
 import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.annotation.EnableVaadin;


@Route(value="login", layout=LoginRegisterLayout.class) 
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private LoginForm login = new LoginForm(); 

	public LoginView(){
		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER); 
		//setJustifyContentMode(JustifyContentMode.CENTER);
		
		login.setI18n(createI18n());
		login.setForgotPasswordButtonVisible(false);
		//login.setI18n(createPortugueseI18n());
		login.setAction("login");  
	
		Button register = new Button("register");
		register.addClickListener( event ->{
    		//new RouterLink("login", LoginView.class);
    		UI.getCurrent().navigate("register");
    	});
		add(login);
		
    
	}

	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if(beforeEnterEvent.getLocation() 
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
		
	}
	private LoginI18n createI18n() {
	   LoginI18n i18n = LoginI18n.createDefault();
	   	
	    i18n.setHeader(new LoginI18n.Header());
	    i18n.getHeader().setTitle("Iniciar sesión");
	    i18n.getForm().setUsername("Usuario");
	    i18n.getForm().setTitle("Iniciar sesión");
	    i18n.getForm().setSubmit("Entrar");
	    i18n.getForm().setPassword("Contraseña");
	    i18n.getErrorMessage().setTitle("Usuario o contraseña no validos");
	    i18n.getErrorMessage()
	        .setMessage("Compruebe que el usuario o contraseña esten escritos correctamente");
	    i18n.setAdditionalInformation(
	       "WeebApp HMIS06");
	    return i18n;
	}
}
