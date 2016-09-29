package org.vaadin.example.timerlabel;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.Label;


@JavaScript("timerextension.js")
public class TimerLabel extends Label {
	private static final long serialVersionUID = 1L;

	private static final String STYLE_NAME = "timerlabel";

	private static final long DEFAULT_ALERT_SEC = 10; // 10 sec left
	private static final long DEFAULT_FROM_SEC = 5*60; // 5 min
	private static final long DEFAULT_TO_SEC = 0; // to zero
	
	private long seconds;
	private TimerExtension timer;
	private long alertSeconds = DEFAULT_ALERT_SEC;
	private long fromSeconds = DEFAULT_FROM_SEC;
	private long toSeconds = DEFAULT_TO_SEC;
	
	public TimerLabel() {
		super();
		timer = new TimerExtension(this);
		super.setStyleName(STYLE_NAME);
		timer.reset(DEFAULT_FROM_SEC);		
	}
	
	public TimerLabel(int fromSec, int alertSec, int toSec) {
		this();
		this.reset(fromSec, alertSec, toSec, fromSec);
	}
	
    /*
     * Sets the component's style. Don't add a JavaDoc comment here, we use the
     * default documentation from implemented interface.
     */
    @Override
    public void setStyleName(String style) {
    	super.setStyleName(style);
    	super.addStyleName(STYLE_NAME);
    }

	public void syncSeconds(long seconds) {
		this.seconds = seconds;
		this.timer.reset(seconds);
	}

	public void reset(int fromSec, int alertSec, int toSec,int currentSec) {
		this.fromSeconds = fromSec;
		this.toSeconds = toSec;
		this.alertSeconds = alertSec;	
		timer.reset(currentSec);
	}

	public class TimerExtension extends AbstractJavaScriptExtension {
		private static final long serialVersionUID = 1L;

		private TimerExtension(Label label) {
			super(label);
		}		
	
		public void reset(long seconds) {
			callFunction("reset", seconds, fromSeconds, toSeconds, alertSeconds);
		}
	}
	
}