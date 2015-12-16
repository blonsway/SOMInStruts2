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
 * 		Constants for SOM Trainer
 */


package som.constants;

//static import
import static som.constants.IMatrixConstants.SOM_MATRIX_COLUMN_SIZE;
import static som.constants.IMatrixConstants.SOM_MATRIX_ROW_SIZE;
import static java.lang.Math.max;
import static java.lang.Math.log;


public interface ITrainerConstants {

	//No of iterations
	int MAX_NO_ITERATIONS = 40;
	
	// initial Radius
	int INTIAL_RADIUS = max(SOM_MATRIX_COLUMN_SIZE, SOM_MATRIX_ROW_SIZE)/2;
	
	// Time Constant 
	double TIME_CONSTANT = MAX_NO_ITERATIONS/log(INTIAL_RADIUS);
	
	//Starting Learning Rate
	double START_LEARN_RATE = 0.1;
	
}
