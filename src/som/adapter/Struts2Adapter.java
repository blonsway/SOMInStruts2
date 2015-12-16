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
 *   provide the interface to support communication between Struts2 target class handler and
 *   SOM generation and visualization process
*/

package som.adapter;

//static imports
import static som.constants.IGenericConstants.INPUT_VALUES_MAP;
import static som.constants.IGenericConstants.INPUT_VECTOR_GENERATION_OPTION;

import java.util.List;

import som.constants.IGenericConstants;
import som.helper.GenericHelper;
import som.helper.MenuDrivenVectorGenerator;

public class Struts2Adapter {

	/**
	 * Calls the SOM old code process to generate the input vectors
	 * 
	 * @param secondOption
	 * @return
	 */
	public static String getInputVectorFileGenerationStatus(List<String> columnList){		
		
		return MenuDrivenVectorGenerator.createInputVectorFilesForStruts2(INPUT_VALUES_MAP, INPUT_VECTOR_GENERATION_OPTION
				, IGenericConstants.STEMMED_BEST_WORD_FILE_OPTION_FOR_SITUATION_DESCRIPTION, columnList);
	}
	
	/**
	 * Calls the SOM old code process to visualize  the SOM output
	 * 
	 * @return
	 */
	public static String getVisualizationOptionStatus(){		
		
		return MenuDrivenVectorGenerator.createJsonDataForVisualization();
	}
}
