/*
 *  
 *	Tech For Good Portal 
 *	Proof of Concept
 *	J P Morgan Chase Technology Center at Syracuse University
 * 
 *	Authored by: 
 *	Last Revision: 1.0
 *	Last Revised by: Prashant Patel
 *
 *	Version 1.0
 *
 *  	Principal Investigators
 *		Kathleen Brandt
 *		Brian Lonsway
 *		Steve Masiclat
 *
 * 	Contributors
 *		Lead Java Developer & Research Assistant: Prashant Patel
 *		Java Developer & Research Assistant: Ravi Nagendra
 *		Python Developer: Brian Lonsway
 * 
 *	This document is a part of the source code and related artifacts
 * 	for the Tech For Good Portal, an open source proof of concept developed
 *	for J P Morgan Chase.
 *
 * 	Copyright © 2015, jointly held by 
 *		Kathleen Brandt, Brian Lonsway, and Steve Masiclat; 
 *		Syracuse University; and
 *		J P Morgan Chase.
 *
 *   	This file is part of TechForGoodPortal.
 *
 *    	TechForGoodPortal is free software: you can redistribute it and/or modify
 *    	it under the terms of the GNU General Public License version 3 as published by
 *    	the Free Software Foundation.
 *
 *    	TechForGoodPortal is distributed in the hope that it will be useful,
 *    	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    	GNU General Public License for more details.
 *
 *    	See <http://www.gnu.org/licenses/> for a copy of the GNU General Public License.
 *    	
 *
 * 		Helper class containing all necessary utilities for training SOM Nodes
 * 
 */


package som.helper;

//static import
import static som.constants.ITrainerConstants.TIME_CONSTANT;
import static som.constants.ITrainerConstants.START_LEARN_RATE;
import static som.constants.ITrainerConstants.INTIAL_RADIUS;
import static som.constants.IMatrixConstants.SOM_MATRIX_COLUMN_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX_ROW_SIZE;

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
			if(j < SOM_MATRIX_COLUMN_SIZE){
				addDimensionToList(i, j, dimensionList);
				System.out.print("("+i+","+j+") ");
			}
			j++;
		}
		j--;
		i++;
		while(i <= maxRow){
			if(i < SOM_MATRIX_ROW_SIZE){
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
