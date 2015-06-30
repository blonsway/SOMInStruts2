/**
 * 
 * SOM Trainer inerface to adjust the weights
 * 
 */
package som.trainer;

import java.util.List;

public interface ISOMTrainer {
	void adjustTrainingWeights(int noOfIteration,int iPosOfBMU, int jPosOfBMU, List<Integer> inputVector);
}
