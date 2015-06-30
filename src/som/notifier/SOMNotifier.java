/**
 * 
 * Oberserver pattern --> Class containing methods to attach Observers and accordingly notify them sequentially
 * 
 */

package som.notifier;

import java.util.ArrayList;
import java.util.List;

import som.observer.SOMObserver;


public class SOMNotifier {
	private List<SOMObserver> observers = new ArrayList<SOMObserver>();

	public void notifyObseversOnReceiveInputVectors() {
		//new RandomVectorGenerator().generateRandomVectorValues(inputVectorMap,inputVectorMap);
		notifyAllObservers();
	}

	public void attach(SOMObserver observer){
		observers.add(observer);		
	}

	public void notifyAllObservers(){
		for (SOMObserver observer : observers) {
			observer.update();
		}
	} 	
}
