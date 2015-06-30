/**
 * 
 * calls the bat file and imports necessary files like unit vector, dwm and weight vector. The result you get
 * is SOMVioewer GUI
 * 
 */

package som.components;

import som.constants.ICommandLineConstants;
import som.notifier.SOMNotifier;
import som.observer.SOMObserver;

public class SOMGUIViewer extends SOMObserver{

	public SOMGUIViewer(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}

	@Override
	public void update() {
		try{

			String command = ICommandLineConstants.RUN_VISUAL_SOM_COMMAND;
			Runtime.getRuntime().exec(command);
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
