/**
 * 
 * Class to map SOM nodes with Documents
 * 
 * 
 */

package som.components;

import som.constants.IMatrixConstants;
import som.notifier.SOMNotifier;
import som.observer.SOMObserver;

public class SOMMappedDocumentPrinter extends SOMObserver{
	
	public SOMMappedDocumentPrinter(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}

	@Override
	public void update() {
		// prints the document matrix
		System.out.println("The Mapped Documents to SOM Matrix Positions are : ");
		for (int i = 0; i <IMatrixConstants.somMatrixRowSize; i++)
		{
			for (int j = 0; j <IMatrixConstants.somMatrixColumnSize; j++)
			{
				System.out.println("[" + i + "," + j + "] = "+IMatrixConstants.documentMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
