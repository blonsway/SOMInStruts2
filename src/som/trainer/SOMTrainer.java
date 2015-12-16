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
 * 		Adjust the weights of the SOM using decaying formula given in the following url
 * 		http://www.ai-junkie.com/ann/som/som1.html * 
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

import static som.constants.IMatrixConstants.SOM_MATRIX_COLUMN_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX_ROW_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX;
import static som.constants.IGenericConstants.RANDOM_SOM_VECTOR_MAP;




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
			if(!(i<0 || j<0 || i>=SOM_MATRIX_ROW_SIZE || j>=SOM_MATRIX_COLUMN_SIZE)){
				int vectorPosition = SOM_MATRIX[i][j];
				List<Integer> somVector = RANDOM_SOM_VECTOR_MAP.get(vectorPosition);
				List<Integer> somVectorCoordinate = new ArrayList<Integer>();
				somVectorCoordinate.add(i);
				somVectorCoordinate.add(j);
				double bmuDistanceFactor = getBMUDistanceFactor(
						GenericHelper.computeEuclideanDistanceForCoordinates(bmuCoordinate, somVectorCoordinate),
						this.neighbourRadius);
				List<Double> intermediateVector = getSubtractionOfTwoList(somVector, inputVector, 
						bmuDistanceFactor*this.learningRate);
				List<Integer> newSOMVector = getAdditionOfTwoList(somVector, intermediateVector);
				RANDOM_SOM_VECTOR_MAP.put(vectorPosition, newSOMVector);
			}

		}
	}



}
