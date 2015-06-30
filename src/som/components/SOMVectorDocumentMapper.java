/**
 * 
 * Class responsible for comparing the vectors with their respective SOM Nodes, and calling
 * SOM Trainer module for weight adjustments
 * 
 */

package som.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import som.adapter.SOMTrainerAdapter;
import som.beans.SOMDimensionRelation;
import som.beans.VectorData;
import som.bmufinder.SOMBestMatchingUnitFinder;
import som.comparators.SOMDistanceComparator;
import som.notifier.SOMNotifier;
import som.observer.SOMObserver;

//static imports
import static som.constants.IGenericConstants.inputValuesMap;
import static som.constants.IDWMFileConstants.dwmInfoMap;
import static som.constants.IMatrixConstants.documentMatrix;
import static som.constants.IMatrixConstants.minDistanceMatrix;
import static som.constants.IMatrixConstants.somMatrixColumnSize;
import static som.constants.IMatrixConstants.somMatrixRowSize;
import static som.constants.IMatrixConstants.somMatrix;
import static som.constants.IGenericConstants.randomSOMVectorMap;




public class SOMVectorDocumentMapper extends SOMObserver {
	private boolean isTrainingRequired;
	private int noOfIteration = -1;
	
	public SOMVectorDocumentMapper(SOMNotifier notifier, boolean isTrainingRequired) {
		this.notifier = notifier;
		this.notifier.attach(this);
		this.isTrainingRequired = isTrainingRequired;
		this.noOfIteration = 0;
	}
	
	@Override
	public void update() {
		SOMTrainerAdapter somTrainerAdapter = null;
		if(isTrainingRequired){
			somTrainerAdapter = new SOMTrainerAdapter();
		}
		for(Map.Entry<Integer, VectorData> entry : inputValuesMap.entrySet())
		{
			int documentNumber = entry.getKey();
			VectorData inputVector = entry.getValue();
			if(inputVector != null && inputVector.getVector() != null && !inputVector.getVector().isEmpty()){
				List<Integer> vectorList = inputVector.getVector();
				findTargetVector(documentNumber, vectorList,somTrainerAdapter);

			}
		}
	}
	
	
	/**
	 * 
	 * @param documentNumber
	 * @param documentArray
	 * @return
	 */
	private Map<String,Integer> getMinimumPositionAndStoreInDocumentMatrix(int documentNumber,double[][] documentArray)
	{
		Map<String,Integer> minPositionInfo = new HashMap<String, Integer>();
		double minimum = documentArray[0][0] ;
		int iPos = 0, jPos = 0;
		for (int i = 0; i < somMatrixRowSize; i++)
		{
			for (int j = 0; j < somMatrixColumnSize; j++)
			{
				double distanceValue = documentArray[i][j];
				if(distanceValue < minimum){
					minimum = distanceValue;
					iPos = i;
					jPos = j;
				}

			}
		}
		if (documentMatrix[iPos][jPos] == null)
		{
			documentMatrix[iPos][jPos] = documentNumber +"";
			minDistanceMatrix[iPos][jPos] = minimum +" ";
		}
		else
		{
			documentMatrix[iPos][jPos] += ","+ documentNumber;
			minDistanceMatrix[iPos][jPos] += ","+ minimum;

		}
		System.out.println("Shortest distance is " + minimum + " found at documentMatrix[" + iPos + "," + jPos + "]");
		minPositionInfo.put("iPos", iPos);
		minPositionInfo.put("jPos", jPos);
		return minPositionInfo;
	}

	/**
	 * 
	 * @param documentNumber
	 * @param inputVector
	 * @param somTrainerAdapter
	 */
	private void findTargetVector(int documentNumber, List<Integer> inputVector, SOMTrainerAdapter somTrainerAdapter)
	{
		PriorityQueue<SOMDimensionRelation> somDimensionQueue = new PriorityQueue<SOMDimensionRelation>(
				90000, new SOMDistanceComparator());
		double[][] documentArray = new double[somMatrixRowSize][somMatrixColumnSize];

		for (int i = 0; i <somMatrixRowSize; i++)
		{
			for (int j = 0; j <somMatrixColumnSize; j++)
			{
				int somPosition = somMatrix[i][j];
				documentArray[i][j] =  new SOMBestMatchingUnitFinder().computeEuclideanDistance(
						inputVector,randomSOMVectorMap.get(somPosition));
				SOMDimensionRelation somDimension = new SOMDimensionRelation(i, j, documentArray[i][j]);
				somDimensionQueue.add(somDimension);
			}
		}
		dwmInfoMap.put(documentNumber, somDimensionQueue);
		Map<String,Integer> minPositionInfo = getMinimumPositionAndStoreInDocumentMatrix(documentNumber, documentArray);
		if(somTrainerAdapter != null){
			this.noOfIteration++;
			somTrainerAdapter.adjustSOMWeights(this.noOfIteration, minPositionInfo.get("iPos"),minPositionInfo.get("jPos"),inputVector);
		}
		
		System.out.println();
	}


}
