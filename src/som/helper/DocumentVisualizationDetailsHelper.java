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
 * 		Store the visualization metrics to be displayed on the GUI
 * 
 */

package som.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import som.adapter.FileOperationsAdapter;
import som.beans.VectorData;

public class DocumentVisualizationDetailsHelper {
	
	/**
	 * This function will call the File Adapter API which is responsible to interact with File Handler module
	 * to fetch the required column data from the the Excel sheet
	 * 
	 * @param option : This parameter selects the combination of columns to be displayed
	 * @param rowNumber
	 * @return
	 */
	public static Map<String,String> getSituationDescriptionFromExcelSheet(int option, int rowNumber){
		
		Map<String,String> visualDocSheetMap = new HashMap<String, String>();
		
		FileOperationsAdapter fileOperAdapter = new FileOperationsAdapter();
		
		List<VectorData> vectorDataList = fileOperAdapter.getVectorDataListFromExcelSheet(option);
		
		VectorData vectorData =  vectorDataList.get(rowNumber);
		
		visualDocSheetMap.put("organization_name", StringUtils.isNotBlank(vectorData.getOrganizatioName()) ?
				vectorData.getOrganizatioName().toUpperCase() : "");
		visualDocSheetMap.put("mission statement", vectorData.getMissionStatement());
		visualDocSheetMap.put("State/Province", vectorData.getState());
		visualDocSheetMap.put("Country", vectorData.getCountry());
		visualDocSheetMap.put("social_sector",StringUtils.isNotBlank( vectorData.getSocialSector()) && 
				"x".equalsIgnoreCase(vectorData.getSocialSector()) ? "1" : "0");
		visualDocSheetMap.put("tech_sector",StringUtils.isNotBlank( vectorData.getTechSector()) && 
				"x".equalsIgnoreCase(vectorData.getTechSector()) ? "1" : "0");
		visualDocSheetMap.put("situation_description", vectorData.getSituationDescription());
		
		return visualDocSheetMap;
	}
	
	/**
	 * Test stub to test correct column data mapped with the visualization data
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("class DocumentVisualizationDetailsHelper : Main function");
		System.out.println(getSituationDescriptionFromExcelSheet(7, 0));
	}

}
