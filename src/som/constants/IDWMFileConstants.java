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
 * 		constants used to generate DWM Mapping file
 * 
 */


package som.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


import som.beans.SOMDimensionRelation;

public interface IDWMFileConstants extends IMatrixConstants{
	//for dwm file <Vector Number, SOM Matrix Element Relation info with this vector number>
	Map<Integer, PriorityQueue<SOMDimensionRelation>> DWM_INFO_MAP = new HashMap<Integer, PriorityQueue<SOMDimensionRelation>>();	

	//Number of Winners in DWM File
	int NUMBER_OF_WINNERS = SOM_MATRIX_COLUMN_SIZE * SOM_MATRIX_ROW_SIZE;	

	// File format version of DWM File
	String DWM_FILE_VERSION = "1.1";

	//metric used to computer Minimum distance 
	String MINIMUM_DISTANCE_COMPUTATION_METRIC = "at.tuwien.ifs.somtoolbox.layers.metrics.L2Metric";
	
	//Filename to be used for read for visualization
	String VISUAL_FILE_NAME = "output/MySOM.dwm";
	
	

}
