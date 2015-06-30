/**
 * 
 * Constants for SOm Trainer Module
 * 
 */

package som.constants;

//static import
import static som.constants.IMatrixConstants.somMatrixColumnSize;
import static som.constants.IMatrixConstants.somMatrixRowSize;
import static java.lang.Math.max;
import static java.lang.Math.log;


public interface ITrainerConstants {

	//No of iterations
	int MAX_NO_ITERATIONS = 40;
	
	// initial Radius
	int INTIAL_RADIUS = max(somMatrixColumnSize, somMatrixRowSize)/2;
	
	// Time Constant 
	double TIME_CONSTANT = MAX_NO_ITERATIONS/log(INTIAL_RADIUS);
	
	//Starting Learning Rate
	double START_LEARN_RATE = 0.1;
	
}
