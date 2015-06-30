/**
 * 
 * SOMObserver interface which will be implement by Observer components who have got attached by the Notifier
 * 
 */

package som.observer;

import som.notifier.SOMNotifier;

public abstract class SOMObserver {
	protected SOMNotifier notifier;
	public abstract void update();
}
