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
		setStyleName(STYLE_NAME);
		timer = new TimerExtension(this);
		timer.reset(DEFAULT_FROM_SEC);		
	}
	
	public TimerLabel(int fromSec, int toSec, int alertSec) {
		this();
		this.fromSeconds = fromSec;
		this.toSeconds = toSec;
		this.alertSeconds = alertSec;	
		timer.reset(fromSeconds);
	}

	public long getSeconds() {
		return seconds;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
		this.timer.reset(seconds);
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