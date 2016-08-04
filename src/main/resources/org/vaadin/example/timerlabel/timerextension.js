window.org_vaadin_example_timerlabel_TimerLabel_TimerExtension = function() {
	
    "use strict";

    this.parentElement = this.getElement(this.getParentId());
    this.timerId = null;
    
    this.reset = function(current, fromSeconds, toSeconds, alertSeconds) {

    	var display = this.parentElement;
    	
    	var duration = Math.abs(toSeconds - fromSeconds); 
    	var countUp = toSeconds > fromSeconds;
    	    	    	
    	// alert time elapsed
    	var elapsedAlertTime = Math.abs(alertSeconds-fromSeconds);
    	
    	// Cancel previous timer function
    	if (this.timerId) {
    		clearInterval(this.timerId);
    	}    	
    	
    	// Save for reference
    	var syncTimeStampSec = current;
    	var syncElapsedSec = Math.abs(current-fromSeconds);
    	var syncTimeStampMs = Date.now();
    	
    	// Timer function
    	function timer() {    		
    		var now = Date.now();
    		var elapsed = Math.round(syncElapsedSec + Math.abs(now - syncTimeStampMs) / 1000);
        	var remaining = Math.round(duration-elapsed);
    			
    		var displayTime = countUp ? fromSeconds + elapsed : fromSeconds - elapsed;

    		// does the same job as parseInt truncates the float
    		var minutes = (displayTime / 60) | 0;
    		var seconds = (displayTime % 60) | 0;
    		var sign = minutes < 0  || seconds < 0? "-" : "";

    		// format leading zeros
    		minutes = Math.abs(minutes);
    		seconds = Math.abs(seconds);
    		seconds = seconds < 10 ? "0" + seconds : seconds;

    		// Update display
    		display.textContent = sign + minutes + ":" + seconds;
    		
    		// Overtime
    		var overtime = remaining < 0; 
    		var alert = elapsed > elapsedAlertTime; 
    		
    		if (overtime) {
        		// Add "overtime" style if needed
    			if (!display.classList.contains("overtime")) {
            		display.classList.add("overtime");    				
            		display.classList.remove("alert");    				
    			}    			    			
    		} else if (alert) {
        		// Add "alert" style if needed
    			if (!display.classList.contains("alert")) {
            		display.classList.remove("overtime");    				
            		display.classList.add("alert");    				
    			}
    		} else {
    			// No extra styles needed
        		display.classList.remove("alert");    				
        		display.classList.remove("overtime");    			
    		}
  
    	};
    	
    	// call once immediately
    	timer();
    	
    	// Set the timer on 1 sec intervals
    	this.timerId = setInterval(timer, 1000);    	
    };
 
}

