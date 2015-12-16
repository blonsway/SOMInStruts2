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
 * 		File Type constants used to passed into the File Factory to get Appropriate FileWriter 
 * 		FileReader Class Instance
 * 
 */


package som.constants;

/**
 * @author prashant
 *
 */
public interface IFileFactoryConstants {
  
	String UNIT_VECTOR_FILE = "UNIT_VECTOR_FILE";
	String WEIGHT_VECTOR_FILE = "WEIGHT_VECTOR_FILE";
	String DWM_FILE = "DWM_FILE";
	String INPUT_VECTOR_FILE = "INPUT_VECTOR_FILE";
	String TEMPLATE_VECTOR_FILE = "TEMPLATE_VECTOR_FILE";
	String BEST_WORDS_TEMPLATE_VECTOR_FILE = "BEST_WORDS_TEMPLATE_VECTOR_FILE";
	String CUSTOM_SOM_OUTPUT_FILE = "CUSTOM_SOM_OUTPUT_FILE";
	String CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE = "CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE";
	String BEST_WORDS_FILE = "BEST_WORDS_FILE";
	String VISUAL_DATA_JSON_FILE_WRITER = "VISUAL_DATA_JSON_FILE_WRITER";
	String DWM_FILE_READER = "DWM_FILE_READER";
	String BEST_WORDS_FILE_GENERATOR = "BEST_WORDS_FILE_GENERATOR";
	String STEMMED_DATA_FILE_READER = "STEMMED_DATA_FILE_READER";
	String FULLY_REDACTED_FILE_PARSER = "FULLY_REDACTED_FILE_PARSER";

}