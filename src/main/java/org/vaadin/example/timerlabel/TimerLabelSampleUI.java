package org.vaadin.example.timerlabel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
@Theme("sampletheme")
public class TimerLabelSampleUI extends UI {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		VerticalLayout content = new VerticalLayout();	
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);
		
		content.addComponent(new Label("TimerLabel from 0s to 10s with 5s alert:"));
		TimerLabel timerLabel = new TimerLabel(0, 5, 10);
		timerLabel.setStyleName(ValoTheme.LABEL_H2);
		content.addComponent(timerLabel);

		content.addComponent(new Label("TimerLabel from 10s to 0s with 5s alert:"));
		TimerLabel timerLabel2 = new TimerLabel(10, 5, 0);
		timerLabel2.setStyleName(ValoTheme.LABEL_H2);
		content.addComponent(timerLabel2);

		content.addComponent(new Label("TimerLabel from 30s to 2s  with 10s alert:"));
		TimerLabel timerLabel3 = new TimerLabel(30, 10, 2);
		timerLabel3.setStyleName(ValoTheme.LABEL_H2);
		content.addComponent(timerLabel3);

		content.addComponent(new Label("Controls for the last label:"));
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);		
		content.addComponent(buttons);
		
		buttons.addComponent(new Button("Sync to 30", e ->{ timerLabel3.syncSeconds(30);}));
		buttons.addComponent(new Button("Sync to 0 in 2 seconds", e -> { 			
			 Executors.newSingleThreadScheduledExecutor()
			 .schedule(() -> { syncSeconds(timerLabel3, 0); }, 2, TimeUnit.SECONDS);			
		}));
		buttons.addComponent(new Button("Sync to 15 in 2 seconds", e -> { 			
			 Executors.newSingleThreadScheduledExecutor()
			 .schedule(() -> { syncSeconds(timerLabel3, 15); }, 2, TimeUnit.SECONDS);			
		}));
		buttons.addComponent(new Button("Sync to 30 in 2 seconds", e -> { 			
			 Executors.newSingleThreadScheduledExecutor()
			 .schedule(() -> { syncSeconds(timerLabel3, 30); }, 2, TimeUnit.SECONDS);			
		}));
		buttons.addComponent(new Button("Pause", e -> timerLabel3.pause()));
		buttons.addComponent(new Button("Resume", e -> timerLabel3.resume()));

	}
	
	/// We need lock the UI using access(...) when modifying it from background thread
	private void syncSeconds(TimerLabel timerLabel, int seconds) {
		this.access(() -> {timerLabel.syncSeconds(seconds);});		
	}

	@WebServlet(urlPatterns = "/*", name = "TimerLabelSampleUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = TimerLabelSampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}
	