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
 * 		Writes the output data into DWM file.
 * 
 */

/**
 * 
 * Class forr writing into the DWM File
 * 
 */

package som.file;

import java.io.PrintWriter;
import java.util.Map;
import java.util.PriorityQueue;

import som.beans.SOMDimensionRelation;
import som.constants.IDWMFileConstants;
import som.constants.IGenericConstants;

public class DWMFileWriter implements IFileWritable {

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.dwm", "UTF-8");
			writer.println("$FILE_FORMAT_VERSION "+IDWMFileConstants.DWM_FILE_VERSION);
			writer.println("$NUM_WINNERS  "+IDWMFileConstants.NUMBER_OF_WINNERS);
			writer.println("$NUM_VECTORS "+IGenericConstants.INPUT_VALUES_MAP.size());
			writer.println("$METRIC "+IDWMFileConstants.MINIMUM_DISTANCE_COMPUTATION_METRIC);

			for(Map.Entry<Integer, PriorityQueue<SOMDimensionRelation>> entry : IDWMFileConstants.DWM_INFO_MAP.entrySet()){
				int documentNumber = entry.getKey();
				writer.println("Document Number : "+documentNumber);

				PriorityQueue<SOMDimensionRelation> dimensionInfoQueue  = entry.getValue();
				for(SOMDimensionRelation somDimensionInfo : dimensionInfoQueue){
					writer.print(somDimensionInfo.getxPosition()+" ");
					writer.print(somDimensionInfo.getyPosition()+" ");
					writer.print(somDimensionInfo.getDistance()+" ");
				}
				writer.println();
			}

			writer.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
