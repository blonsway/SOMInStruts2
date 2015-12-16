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
 * 		class to initialize SOM values with random values for its dimensions
 * 
 */


package som.components;

import som.notifier.SOMNotifier;
import som.observer.SOMObserver;
import som.randomization.RandomVectorGenerator;

//static imports
import static som.constants.IMatrixConstants.SOM_MATRIX_ROW_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX_COLUMN_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX;
import static som.constants.IGenericConstants.RANDOM_SOM_VECTOR_MAP;;


public class SOMMatrixCreater extends SOMObserver{

	public SOMMatrixCreater(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}
	
	
	@Override
	public void update() {
		
		//generate the Random vector values
		new RandomVectorGenerator().generateRandomVectorValues(
				RANDOM_SOM_VECTOR_MAP,SOM_MATRIX_COLUMN_SIZE*SOM_MATRIX_ROW_SIZE);
		
		//store the corresponding vector number position in the matrix row by row
		int position = 0 ;
		for (int i = 0; i < SOM_MATRIX_ROW_SIZE; i++)
		{
			for (int j = 0; j < SOM_MATRIX_COLUMN_SIZE; j++)
			{
				SOM_MATRIX[i][j] = position++;
			}
		}
	}

}
