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
 * 		Helper class for Menu Driven utility
 * 
 */


package som.helper;




import static som.constants.IFileFactoryConstants.DWM_FILE_READER;
import static som.constants.IFileFactoryConstants.VISUAL_DATA_JSON_FILE_WRITER;
import static som.constants.IGenericConstants.INPUT_VALUES_MAP;
import static som.constants.IGenericConstants.MAX_NO_OF_OPTIONS;
import static som.constants.IGenericConstants.CUSTOM_COLUMN_OPTION;
import static som.constants.IGenericConstants.VISUAL_OPTION;
import static som.constants.IGenericConstants.EXCEL_SHEET_MAPPER_CASE_CUSTOM;









import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;




import com.opensymphony.xwork2.ActionSupport;

import som.adapter.FileOperationsAdapter;
import som.beans.VectorData;
import som.visualization.DocumentPositionCalculator;

public class MenuDrivenVectorGenerator extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private static void displaySecondMenu(){
		System.out.println("Enter 1 to train SOM by  vectors of organization name, "
				+ "organization focus area, mission statement, geographical scope, "
				+ "target audience, city+state, project title, situation description, technical scope");
		System.out.println("Enter 2 to train SOM by  vectors of mission statement, "
				+ "project title, situation description");
		System.out.println("Enter 3 to train SOM by vectors of organization name, city+state, "
				+ "mission statement, project title, situation description");
		System.out.println("Enter 4 to train SOM by vectors of Situation Description, Target Audience and Technical Scope");
		System.out.println("Enter 5 to train SOM by vectors of Situation Description");
		System.out.println("Enter 6 to train SOM by vectors of your Custom Column Nos");
		System.out.println("Enter 7 to train SOM by best 100 words Provided for Situation Description");
		System.out.println("Enter 8 to train SOM by best 100 words Provided for Situation Description"
				+ "and Mission Statement");
		System.out.println("Enter 9 to train SOM by best stemmed words Provided for Situation Description");
		System.out.println("Enter 10 to train SOM by best stemmed words Provided for Situation Description"
				+ "and Mission Statement");
	}

	private static void displayFirstMenu(){
		System.out.println("Enter 1 to run Our Customized SOM Algorithm. If you are selecting this option, then please"
				+ " make sure to avoid selecting large data. This option will automatically trigger SOM Viewer");
		System.out.println("Enter 2 to get Input Vector Files For SOM Toolbox");
		System.out.println("Enter 3 to generate semantic Output File.");	
		System.out.println("Enter 4 to view the visualized output based on DWM File (stored in project as visual.dwm)");


	}

	/**
	 * 
	 * @return the Integer input from the console
	 */
	private static int getIntegerInput(){
		int input = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String s = br.readLine();
			input = Integer.parseInt(s);
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		return input;
	}

	/**
	 * 
	 * @return the String input from the console
	 */
	private static String getStringInput(){
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			input  = br.readLine();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		return input;
	}



	/**
	 * 
	 * @param input
	 */
	public static void createInputVectorsBasedOnOption(int firstOption, int secondOption , List<String> columnList){
		if(secondOption > MAX_NO_OF_OPTIONS || secondOption < 0){
			System.out.println("Option not present in the list");		
		}
		else{
			if(INPUT_VALUES_MAP.size() > 0){
				INPUT_VALUES_MAP.clear();
			}
			InputVectorGenerationHelper.createInputVectors(INPUT_VALUES_MAP, firstOption,secondOption, columnList);
		}
	}

	/**
	 * 
	 * store column numbers as Custom Input
	 * 
	 * @param customColumnInput
	 */
	public static void handleCustomCoulumnInput(int  inputForSecondMenu){
		try{
			if(inputForSecondMenu == CUSTOM_COLUMN_OPTION){
				System.out.println(" Enter Comma Separated Columns : ");
				String customColumnInputString = getStringInput();
				String[] customColumns = customColumnInputString.split(",");

				for(String s : customColumns){
					if(s != null && s != ""){
						Byte columnNo = Byte.parseByte(s);
						EXCEL_SHEET_MAPPER_CASE_CUSTOM.add(columnNo);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("Invalid Input : "+e);
		}
	}

	/**
	 * 
	 * @param inputValuesMap
	 * @return
	 */
	public static int storeInputVectorByMenuSelectionAndReturnTrainingOption(Map<Integer, VectorData> inputValuesMap){		
		displayFirstMenu();
		int inputForFirstMenu = getIntegerInput();
		int inputForSecondMenu = -1;

		if(inputForFirstMenu != VISUAL_OPTION){
			displaySecondMenu();
			inputForSecondMenu = getIntegerInput();

			handleCustomCoulumnInput(inputForSecondMenu);

			createInputVectorsBasedOnOption(inputForFirstMenu,inputForSecondMenu,null);

		}

		return inputForFirstMenu;
	}
	
	
	/**
	 * helper exposed for Struts2 files to enable input vector files generation via Web interface
	 * 
	 * @param inputValuesMap
	 * @param inputForFirstMenu
	 * @param inputForSecondMenu
	 * @return
	 */
	public static String createInputVectorFilesForStruts2(Map<Integer, VectorData> inputValuesMap,int inputForFirstMenu,
			int inputForSecondMenu, List<String> columnList){
		if(inputForFirstMenu != VISUAL_OPTION){
			
			handleCustomCoulumnInput(inputForSecondMenu);

			createInputVectorsBasedOnOption(inputForFirstMenu,inputForSecondMenu, columnList);

		}

		return SUCCESS;
	}
	
	/**
	 * helper exposed for Struts2 files to Visual SOM output via Web interface
	 * 
	 * @return
	 */
	public static String createJsonDataForVisualization(){
		System.out.println("Inside createJsonDataForVisualization method of MenuDrivenVectorGenerator");
		 new FileOperationsAdapter().readFromFile(DWM_FILE_READER);
		 new DocumentPositionCalculator().storeTraingularCoordinates();
		 new FileOperationsAdapter().writeToFile(VISUAL_DATA_JSON_FILE_WRITER);
		 return SUCCESS;
	}
}
