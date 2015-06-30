/**
 * 
 * Adjust the weights of the SOM using decaying formula given in the following url
 * http://www.ai-junkie.com/ann/som/som1.html
 * 
 */
package som.trainer;

//static import

import static som.constants.ITrainerConstants.MAX_NO_ITERATIONS;
import static som.helper.SOMTrainerHelper.getNeighbourhoodRadius;
import static som.helper.SOMTrainerHelper.getLearningRate;
import static som.helper.SOMTrainerHelper.getListofSOMVectorsUnderRadius;
import static som.helper.SOMTrainerHelper.getSubtractionOfTwoList;
import static som.helper.SOMTrainerHelper.getAdditionOfTwoList;
import static som.helper.SOMTrainerHelper.getBMUDistanceFactor;

import static som.constants.IMatrixConstants.somMatrixColumnSize;
import static som.constants.IMatrixConstants.somMatrixRowSize;
import static som.constants.IMatrixConstants.somMatrix;
import static som.constants.IGenericConstants.randomSOMVectorMap;




// standard java library static import
import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;


import som.beans.SOMDimensionRelation;
import som.helper.GenericHelper;



public class SOMTrainer implements ISOMTrainer{
	private int neighbourRadius = -1;
	private double learningRate = -1;


	public void adjustTrainingWeights(int noOfIteration, int iPosOfBMU, int jPosOfBMU, List<Integer> inputVector){
		if(noOfIteration <= MAX_NO_ITERATIONS){
			calculateDependentValues(noOfIteration);
			List<SOMDimensionRelation> dimensionRelationList = getListofSOMVectorsUnderRadius(
					this.neighbourRadius, iPosOfBMU, jPosOfBMU);
			List<Integer> bmuCoordinate = new ArrayList<Integer>();
			bmuCoordinate.add(iPosOfBMU);
			bmuCoordinate.add(jPosOfBMU);
			storeNewWeightOfNeighbourhoodVectors(dimensionRelationList,inputVector,bmuCoordinate);

		}
	}

	public void calculateDependentValues(int noOfIteration){
		this.neighbourRadius = (int) abs(getNeighbourhoodRadius(noOfIteration));
		this.learningRate = getLearningRate(noOfIteration);
		System.out.println("Neighbourhood Radius at iternation "+noOfIteration+" : "+neighbourRadius);
		System.out.println("Learning Rate at iternation "+noOfIteration+" : "+learningRate);
	}

	public void storeNewWeightOfNeighbourhoodVectors(List<SOMDimensionRelation> dimensionRelationList,
			List<Integer> inputVector,List<Integer> bmuCoordinate){

		for(SOMDimensionRelation dimension : dimensionRelationList){
			int i = dimension.getxPosition();
			int j = dimension.getyPosition();
			if(!(i<0 || j<0 || i>=somMatrixRowSize || j>=somMatrixColumnSize)){
				int vectorPosition = somMatrix[i][j];
				List<Integer> somVector = randomSOMVectorMap.get(vectorPosition);
				List<Integer> somVectorCoordinate = new ArrayList<Integer>();
				somVectorCoordinate.add(i);
				somVectorCoordinate.add(j);
				double bmuDistanceFactor = getBMUDistanceFactor(
						GenericHelper.computeEuclideanDistanceForCoordinates(bmuCoordinate, somVectorCoordinate),
						this.neighbourRadius);
				List<Double> intermediateVector = getSubtractionOfTwoList(somVector, inputVector, 
						bmuDistanceFactor*this.learningRate);
				List<Integer> newSOMVector = getAdditionOfTwoList(somVector, intermediateVector);
				randomSOMVectorMap.put(vectorPosition, newSOMVector);
			}

		}
	}



}
