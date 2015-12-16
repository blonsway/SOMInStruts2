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
 * 		Helper class containing utility method for computing  distance between the two vectors
 * 
 */


package som.helper;

import java.io.InputStream;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import som.beans.VectorCoordinate;
import som.constants.IGenericConstants;

//static import
import static som.constants.IGenericConstants.BEST_WORDS_FILE_OPTION_FOR_SITUATION_DESCRIPTION;
import static som.constants.IGenericConstants.BEST_WORDS_FILE_OPTION_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT;
import static som.constants.IGenericConstants.STEMMED_BEST_WORDS_FILE_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT;
import static som.constants.IGenericConstants.STEMMED_BEST_WORD_FILE_OPTION_FOR_SITUATION_DESCRIPTION;
import static som.constants.IVisualizationConstants.MIN_DIMENSION;
import static som.constants.IVisualizationConstants.MAX_DIMENSION;

public class GenericHelper {

	/**
	 * 
	 * @param coordinate1
	 * @param coordinate2
	 * @return the distance between two vectors using Euclidean distance formula
	 */
	public static double computeEuclideanDistanceForCoordinates(List<Integer> coordinate1, List<Integer> coordinate2)
	{
		double squareDistance = 0;
		double squareRootDistance = 0;
		for (int i = 0; i < coordinate1.size(); i++)
		{
			squareDistance += coordinate1.get(i) * coordinate2.get(i);
		}
		squareRootDistance = Math.sqrt(squareDistance);
		return squareRootDistance;
	}

	/**
	 * 
	 * @param secondOption
	 * @return whether the menu selection matches the best words option
	 */
	public static boolean isBestWordsOptionSelected(int secondOption){
		return secondOption == BEST_WORDS_FILE_OPTION_FOR_SITUATION_DESCRIPTION ||
				secondOption == BEST_WORDS_FILE_OPTION_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT ||
				secondOption == STEMMED_BEST_WORD_FILE_OPTION_FOR_SITUATION_DESCRIPTION ||
				secondOption == STEMMED_BEST_WORDS_FILE_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT;
	}

	/**
	 * 
	 * @param secondOption
	 * @return whether the menu selection matches the stemmed best words option
	 */
	public static boolean isStemmedBestWordsSelected(int secondOption){
		return secondOption == STEMMED_BEST_WORD_FILE_OPTION_FOR_SITUATION_DESCRIPTION ||
				secondOption == STEMMED_BEST_WORDS_FILE_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param dimensionList
	 * @return whether the point does not overlapp with one another
	 */
	public static boolean isDimensionCorrect(double x, double y, List<VectorCoordinate> dimensionList){

		boolean isDimensionCorrect = true;

		if(x <= MIN_DIMENSION || y<= MIN_DIMENSION || x >= MAX_DIMENSION || y >= MAX_DIMENSION){
			return false;
		}

		for(VectorCoordinate eachVectorCoordinate : dimensionList){
			if(eachVectorCoordinate.isOverlapping(x, y)){
				System.out.println(eachVectorCoordinate+ " is overalpping with x = "+x+" and y ="+y);
				isDimensionCorrect = false;
				break;
			}
		}

		return isDimensionCorrect;
	}

	/**
	 * 
	 * This function is added after migrating the code base to Struts2
	 * 
	 * gets the Context Path of the Server
	 * 
	 * @param resourcePath
	 * @return
	 */
	public static String getServerPath(){

		return ServletActionContext.getRequest().getServerName()+IGenericConstants.COLON+
				ServletActionContext.getRequest().getServerPort()+
				ServletActionContext.getServletContext().getContextPath();
	}

	/**
	 * 
	 * @param path
	 * @return the inputstream from Servlet Resource
	 */
	public static InputStream getFileFromServerUsingUrl(String path){
		InputStream inputStream = null;
		try{
			inputStream = ServletActionContext.getServletContext().getResourceAsStream(path);
			System.out.println("The File Url :"+inputStream.toString());

		} catch(Exception e){
			e.printStackTrace();			
		}

		return inputStream;
	}


	/**
	 * 
	 * @return whether the Operating system is Windows
	 */
	public static boolean isWindows()
	{

		String osName = System.getProperty("os.name");
		if(StringUtils.isNotBlank(osName)){
			return osName.startsWith("Windows");
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return the absolute path for files on the server
	 */
	public static String getAbsolutePath(){
		if(isWindows()){
			return IGenericConstants.WIN_PATH_FOR_GENERATED;
		} else {
			return IGenericConstants.LINUX_PATH_FOR_GENERATED;
		}
	}

}
