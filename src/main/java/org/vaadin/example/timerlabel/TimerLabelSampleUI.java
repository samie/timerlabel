package org.vaadin.example.timerlabel;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Push
public class TimerLabelSampleUI extends UI {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);
		
		// Create a TimerLabel from 0 to 10  with 5s alert
		TimerLabel l = new TimerLabel(0, 10, 5);
		l.setStyleName(ValoTheme.LABEL_H1);
		content.addComponent(l);

		
		// Create a TimerLabel from 10 to 0  with 5s alert
		TimerLabel l2 = new TimerLabel(10, 0, 5);
		l2.setStyleName(ValoTheme.LABEL_H1);
		content.addComponent(l2);

		// Create a TimerLabel from 10 to 0  with 5s alert
		TimerLabel l3 = new TimerLabel(30, 10, 2);
		l3.setStyleName(ValoTheme.LABEL_H1);
		content.addComponent(l3);
		
		content.addComponent(new Button("Reset to 30", e ->{ l3.setSeconds(30);}));

	}
	
	@WebServlet(urlPatterns = "/*", name = "TimerLabelSampleUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = TimerLabelSampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}
	