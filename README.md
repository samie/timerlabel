TimerLabel component for Vaadin
==============

This is a special Label component that displays time in minutes and seconds. The component can count up or down:

		// Create a TimerLabel from 0s to 10s with 5s alert
		TimerLabel timerLabel = new TimerLabel(0, 5, 10);
		content.addComponent(timerLabel2);
		
		
Special style class names are applied for alert and overtime, that you can apply in your theme:

		/* Default styles for the TimeLabel */
		.timerlabel {
		}

		/* Alert time styles for the TimeLabel */
		.timerlabel.alert { 
			color: orange; 
		}
  
		/* Overtime styles for the TimeLabel */
		.timerlabel.overtime {
			color: red;
		}
		


Online demo
=======

Demo is available at [sami.app.fi/timerlabel](http://sami.app.fi/timerlabel)


Running the demo locally
=======

To run the demo application, run "mvn jetty:run" and open http://localhost:8080/ .

		git clone https://github.com/samie/timerlabel.git
		cd timerlabel
		mvn package jetty:run

