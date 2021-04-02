package com.example.test.security;

import com.example.test.views.LoginView;
import com.example.test.views.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component 
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> { 
			final UI ui = uiEvent.getUI();
			ui.addBeforeEnterListener(this::authenticateNavigation);
		});
	}

	private void authenticateNavigation(BeforeEnterEvent event) {
		if(RegisterView.class.equals(event.getNavigationTarget()) 
				&& !SecurityUtils.isUserLoggedIn()){
			event.rerouteTo(RegisterView.class);
		}
		else if (!LoginView.class.equals(event.getNavigationTarget())
		    && !SecurityUtils.isUserLoggedIn()) { 
			event.rerouteTo(LoginView.class);
		}
	}
}