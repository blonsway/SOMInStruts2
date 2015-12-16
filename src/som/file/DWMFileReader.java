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
 * 		reads the DWM File and determine the relationship between the documents by storing the info
 * 		into SOM Dimension bean object
 * 
 */

package som.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import som.comparators.SOMDistanceComparator;
import som.helper.GenericHelper;
import som.beans.SOMDimensionRelation;


//static import
import static som.constants.IDWMFileConstants.VISUAL_FILE_NAME;
import static som.constants.IDWMFileConstants.DWM_INFO_MAP;



public class DWMFileReader implements IFileWritable {

	

	/**
	 * parses the BufferedReader and store the information into dwm info hashmap
	 * @param br
	 */
	public  void storeDocumentsDistanceInfo(BufferedReader br){
		try{
			String documentLine = "";
			while(( documentLine = br.readLine()) != null){
				//System.out.println(documentLine);
				
				
				if(documentLine.indexOf("Document_number_") != -1){
					String[] documentNumberLineSplit = documentLine.split("_");
					int documentNumber = Integer.parseInt(documentNumberLineSplit[2]);

					String bmuLine = br.readLine();

					String[] bmuLineSplit = bmuLine.split(" ");

					SOMDistanceComparator comparator = new SOMDistanceComparator();
					PriorityQueue<SOMDimensionRelation> documentQueue = new PriorityQueue<SOMDimensionRelation>(10,comparator);

					int bmuLineIndex = 0;

					while(bmuLineIndex < bmuLineSplit.length){
						SOMDimensionRelation somDimensionRelation = new SOMDimensionRelation(
								Integer.parseInt(bmuLineSplit[bmuLineIndex++]), 
								Integer.parseInt(bmuLineSplit[bmuLineIndex++]),
								Double.parseDouble(bmuLineSplit[bmuLineIndex++]));
						documentQueue.add(somDimensionRelation);
					}

					DWM_INFO_MAP.put(documentNumber, documentQueue);

				}

			}	

		}
		catch(Exception e){
			System.out.println(" class DWMFileReader : method storeDocumentsDistanceInfo(BufferedReader br) :"
					+ "Exception while dealing with getting dwm info : "+e);
		}
	}

	/**
	 * reads the dwm file and store the information needed for visualization
	 */
	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		try{
			FileReader isr = new FileReader(new File(GenericHelper.getAbsolutePath()+(VISUAL_FILE_NAME)));
			BufferedReader br = new BufferedReader(isr);
			storeDocumentsDistanceInfo(br);
			
			br.close();
		}
		catch(Exception e){
			System.out.println(" class DWMFileReader : method readFromFile() : "
					+ "Exception while dealing with file : "+e);
		}

	}
	
	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	
	



}
