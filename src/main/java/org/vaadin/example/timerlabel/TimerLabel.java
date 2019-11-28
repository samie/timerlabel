package org.vaadin.example.timerlabel;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.shared.JavaScriptExtensionState;
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
	private long startTime;

	public TimerLabel() {
		super();
		timer = new TimerExtension(this);
		super.setStyleName(STYLE_NAME);
		timer.getState().fromSeconds = DEFAULT_FROM_SEC;
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
    	timer.getState().current = seconds;
	}

	public void reset(int fromSec, int alertSec, int toSec,int currentSec) {
		TimerLabelState state = timer.getState();
		state.fromSeconds = fromSec;
		state.toSeconds = toSec;
		state.alertSeconds = alertSec;
		state.current = currentSec;
		this.startTime = System.currentTimeMillis(); // Stored for calculations
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			// We synchronize the clock back to the time that has been elapsed since start based on RTC
			long elapsedTimeMs = System.currentTimeMillis() - startTime;
			TimerLabelState state = timer.getState();
			syncSeconds(state.fromSeconds < state.toSeconds?
					state.fromSeconds+elapsedTimeMs/1000 :
					state.fromSeconds-elapsedTimeMs/1000);
		}
	}

	public void pause() {
		timer.pause();
	}

	public void resume() {
		timer.resume();
	}

	public class TimerExtension extends AbstractJavaScriptExtension {
		private static final long serialVersionUID = 1L;

		private TimerExtension(Label label) {
			super(label);
		}		

		public void pause() {
			getState().paused = true;
		}

		public void resume() { getState().paused = false; }


		@Override
		protected TimerLabelState getState() {
			return (TimerLabelState) super.getState();
		}

	}

	public static class TimerLabelState extends JavaScriptExtensionState {

    	public boolean paused, visible = true;

		public long current, fromSeconds, toSeconds, alertSeconds;


	}
}