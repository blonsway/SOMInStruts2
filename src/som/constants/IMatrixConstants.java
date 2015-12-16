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
 * 		Constants related to SOM Matrix creation
 * 
 */


package som.constants;

public interface IMatrixConstants extends IGenericConstants{
	//SOM matrix X size
	int SOM_MATRIX_ROW_SIZE = 20;

	//SOM matrix Y size
	int SOM_MATRIX_COLUMN_SIZE = 14;

	// Its the Kohonen Map (Self- ORganizing Map ) which is the 2-dimensional matrix
	int[][] SOM_MATRIX = new int[SOM_MATRIX_ROW_SIZE][SOM_MATRIX_COLUMN_SIZE];

	// Its the Corresponding Document Matrix storing the documents Mapped for the above SOM
	String[][] DOCUMENT_MATRIX = new String[SOM_MATRIX_ROW_SIZE][SOM_MATRIX_COLUMN_SIZE];

	// Its stores the minimum distance of the documents mapped to each element in the SOM
	String[][] MIN_DISTANCE_MATRIX = new String[SOM_MATRIX_ROW_SIZE][SOM_MATRIX_COLUMN_SIZE];
}
