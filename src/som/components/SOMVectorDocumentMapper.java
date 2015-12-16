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
 * 		Class responsible for comparing the vectors with their respective SOM Nodes, and calling
 * 		SOM Trainer module for weight adjustments
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
import static som.constants.IGenericConstants.INPUT_VALUES_MAP;
import static som.constants.IDWMFileConstants.DWM_INFO_MAP;
import static som.constants.IMatrixConstants.DOCUMENT_MATRIX;
import static som.constants.IMatrixConstants.MIN_DISTANCE_MATRIX;
import static som.constants.IMatrixConstants.SOM_MATRIX_COLUMN_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX_ROW_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX;
import static som.constants.IGenericConstants.RANDOM_SOM_VECTOR_MAP;




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
		for(Map.Entry<Integer, VectorData> entry : INPUT_VALUES_MAP.entrySet())
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
		for (int i = 0; i < SOM_MATRIX_ROW_SIZE; i++)
		{
			for (int j = 0; j < SOM_MATRIX_COLUMN_SIZE; j++)
			{
				double distanceValue = documentArray[i][j];
				if(distanceValue < minimum){
					minimum = distanceValue;
					iPos = i;
					jPos = j;
				}

			}
		}
		if (DOCUMENT_MATRIX[iPos][jPos] == null)
		{
			DOCUMENT_MATRIX[iPos][jPos] = documentNumber +"";
			MIN_DISTANCE_MATRIX[iPos][jPos] = minimum +" ";
		}
		else
		{
			DOCUMENT_MATRIX[iPos][jPos] += ","+ documentNumber;
			MIN_DISTANCE_MATRIX[iPos][jPos] += ","+ minimum;

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
		double[][] documentArray = new double[SOM_MATRIX_ROW_SIZE][SOM_MATRIX_COLUMN_SIZE];

		for (int i = 0; i <SOM_MATRIX_ROW_SIZE; i++)
		{
			for (int j = 0; j <SOM_MATRIX_COLUMN_SIZE; j++)
			{
				int somPosition = SOM_MATRIX[i][j];
				documentArray[i][j] =  new SOMBestMatchingUnitFinder().computeEuclideanDistance(
						inputVector,RANDOM_SOM_VECTOR_MAP.get(somPosition));
				SOMDimensionRelation somDimension = new SOMDimensionRelation(i, j, documentArray[i][j]);
				somDimensionQueue.add(somDimension);
			}
		}
		DWM_INFO_MAP.put(documentNumber, somDimensionQueue);
		Map<String,Integer> minPositionInfo = getMinimumPositionAndStoreInDocumentMatrix(documentNumber, documentArray);
		if(somTrainerAdapter != null){
			this.noOfIteration++;
			somTrainerAdapter.adjustSOMWeights(this.noOfIteration, minPositionInfo.get("iPos"),minPositionInfo.get("jPos"),inputVector);
		}
		
		System.out.println();
	}


}
