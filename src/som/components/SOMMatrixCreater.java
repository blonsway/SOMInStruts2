/**
 * 
 * class to initialize SOM values with random values for its dimensions
 * 
 */

package som.components;

import som.notifier.SOMNotifier;
import som.observer.SOMObserver;
import som.randomization.RandomVectorGenerator;

//static imports
import static som.constants.IMatrixConstants.somMatrixRowSize;
import static som.constants.IMatrixConstants.somMatrixColumnSize;
import static som.constants.IMatrixConstants.somMatrix;
import static som.constants.IMatrixConstants.randomSOMVectorMap;


public class SOMMatrixCreater extends SOMObserver{

	public SOMMatrixCreater(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}
	
	
	@Override
	public void update() {
		
		//generate the Random vector values
		new RandomVectorGenerator().generateRandomVectorValues(
				randomSOMVectorMap,somMatrixColumnSize*somMatrixRowSize);
		
		//store the corresponding vector number position in the matrix row by row
		int position = 0 ;
		for (int i = 0; i < somMatrixRowSize; i++)
		{
			for (int j = 0; j < somMatrixColumnSize; j++)
			{
				somMatrix[i][j] = position++;
			}
		}
	}

}
