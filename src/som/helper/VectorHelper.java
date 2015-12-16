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
 * 		Helper class to be used by input vectors to get its dimensions
 *
 * 
 */


package som.helper;

import static som.constants.IGenericConstants.RANDOM_SOM_VECTOR_MAP;
import static som.constants.IMatrixConstants.SOM_MATRIX;

import java.util.List;

import som.beans.VectorData;
import som.constants.IMatrixConstants;

public class VectorHelper {
	
	
	public static int getVectorDimension(){
		int vectorDimensions = 0;
		if(IMatrixConstants.INPUT_VALUES_MAP.get(0) != null){
			VectorData vectorData = IMatrixConstants.INPUT_VALUES_MAP.get(0);
			List<Integer> dimensions = vectorData.getVector();
			vectorDimensions = dimensions.size();
		}
		return vectorDimensions;
	}
	
	public static List<Integer> getSOMVectorByPosition(int iPos, int jPos){
		int vectorPosition = SOM_MATRIX[iPos][jPos];
		List<Integer> somVector = RANDOM_SOM_VECTOR_MAP.get(vectorPosition);
		return somVector;
	}
}
