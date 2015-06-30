/**
 * 
 * Helper class containing all necessary utilities for training SOM Nodes
 * 
 */
package som.helper;

//static import
import static som.constants.ITrainerConstants.TIME_CONSTANT;
import static som.constants.ITrainerConstants.START_LEARN_RATE;
import static som.constants.ITrainerConstants.INTIAL_RADIUS;
import static som.constants.IMatrixConstants.somMatrixColumnSize;
import static som.constants.IMatrixConstants.somMatrixRowSize;

import static java.lang.Math.exp;


import java.util.ArrayList;
import java.util.List;

import som.beans.SOMDimensionRelation;

public class SOMTrainerHelper {

	public static double getExponentialComponent(int noOfIteration){
		return exp(-noOfIteration/TIME_CONSTANT);
	}
	
	public static double getNeighbourhoodRadius(int noOfIteration){
		return INTIAL_RADIUS * getExponentialComponent(noOfIteration);
	}
	
	//it is the theta
	public static double getBMUDistanceFactor(double dist, double radius){
		return exp(-dist*dist/(2*radius*radius));
	}
	
	public static double getLearningRate(int noOfIteration){
		return START_LEARN_RATE * getExponentialComponent(noOfIteration);
	}
	
	public static List<SOMDimensionRelation> getListofSOMVectorsUnderRadius(int radius, int iPosOfBMU, int jPosOfBMU){
		List<SOMDimensionRelation> dimensionList = new ArrayList<SOMDimensionRelation>();
		
		for(int start = 1; start <= radius; start++){
			storePosSpirally(iPosOfBMU-start, jPosOfBMU-start, iPosOfBMU+start, jPosOfBMU+start, dimensionList);
		}
		return dimensionList;
	}
	
	
	
	public static void storePosSpirally(int minRow, int minCol, int maxRow, int maxCol,List<SOMDimensionRelation> dimensionList){
		int i = minRow;
		int j = minCol;
		while(j<=maxCol){
			if(j < somMatrixColumnSize){
				addDimensionToList(i, j, dimensionList);
				System.out.print("("+i+","+j+") ");
			}
			j++;
		}
		j--;
		i++;
		while(i <= maxRow){
			if(i < somMatrixRowSize){
				addDimensionToList(i, j, dimensionList);
				System.out.print("("+i+","+j+") ");
			}
			i++;
		}
		i--;
		j--;
		while(j >= minCol){
			if(j >= 0){
				addDimensionToList(i, j, dimensionList);
				System.out.print("("+i+","+j+") ");
			}
			j--;
		}
		j++;
		i--;
		while(i >= minRow){
			if(i >= 0){
				addDimensionToList(i, j, dimensionList);
				System.out.print("("+i+","+j+") ");
			}
			i--;
		}
		System.out.println();
	}
	
	public static void addDimensionToList(int i , int j, List<SOMDimensionRelation> dimensionList){
		SOMDimensionRelation dimension = new SOMDimensionRelation(i, j, 0.0);
		dimensionList.add(dimension);
	}
	
	public static List<Integer> getAdditionOfTwoList(List<Integer> list1, List<Double> list2){
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i = 0; i<list1.size(); i++){
			int value = (int) ((list1.get(i)+list2.get(i)));
			resultList.add(i, value );
		}
		return resultList;
	}
	
	public static List<Double> getSubtractionOfTwoList(List<Integer> list1, List<Integer> list2, double multiplicationFactor){
		List<Double> resultList = new ArrayList<Double>();
		for(int i = 0; i<list1.size(); i++){
			Double value = (double) ((list1.get(i)-list2.get(i))*multiplicationFactor);
			resultList.add(i, value );
		}
		return resultList;
	}
}
