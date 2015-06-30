/**
 * 
 * Providing an interface to communicate with SOM Training Utility
 * 
 */

package som.adapter;

import java.util.List;

import som.trainer.ISOMTrainer;
import som.trainer.SOMTrainer;

public class SOMTrainerAdapter {
	ISOMTrainer somTrainer;

	public SOMTrainerAdapter(){
		somTrainer = new SOMTrainer();
	}
	
	public SOMTrainerAdapter(SOMTrainer somTrainer){
		this.somTrainer = somTrainer;
	}

	public void adjustSOMWeights(int noOfIteration,int iPosOfBMU, int jPosOfBMU, List<Integer> inputVector){
		somTrainer.adjustTrainingWeights(noOfIteration, iPosOfBMU,  jPosOfBMU,  inputVector);
	}
}
