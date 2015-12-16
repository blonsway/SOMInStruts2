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
 * 		constants required for mapping excel sheet columns with data
 * 
 */

package som.constants;

public interface IExcelColumnMappingConstants extends IGenericConstants{

	public static final int INPUT_RANDOM_NAME_COLUMN_INDEX = 0;
	public static final int INPUT_MISSION_STATEMENT_COLUMN_INDEX = 1;
	public static final int INPUT_STATE_PROVINCE_COLUMN_INDEX = 2;
	public static final int INPUT_COUNTRY_COLUMN_INDEX = 3;
	public static final int INPUT_SOCIAL_SECTOR_COLUMN_INDEX = 4;
	public static final int INPUT_TECH_SECTOR_COLUMN_INDEX = 5;
	public static final int INPUT_SITUATION_DESCRIPTION_COLUMN_INDEX = 6;

	public static final int OUTPUT_RANDOM_NAME_COLUMN_INDEX = 0;
	public static final int OUTPUT_MISSION_STATEMENT_COLUMN_INDEX = 5;
	public static final int OUTPUT_STATE_PROVINCE_COLUMN_INDEX = 13;
	public static final int OUTPUT_COUNTRY_COLUMN_INDEX = 14;
	public static final int OUTPUT_SOCIAL_SECTOR_COLUMN_INDEX= 15;
	public static final int OUTPUT_TECH_SECTOR_COLUMN_INDEX = 16;
	public static final int OUTPUT_SITUATION_DESCRIPTION_COLUMN_INDEX = 18;
	
}
