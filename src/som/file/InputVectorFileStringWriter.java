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
 * 		Creates Input vector File
 * 
 */



package som.file;

import static som.constants.IGenericConstants.INPUT_VALUES_MAP;

import java.io.PrintWriter;
import java.util.Map;

import som.beans.VectorData;
import som.helper.VectorHelper;

public class InputVectorFileStringWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.tfxidf", "UTF-8");
			writer.println("$TYPE vec_tfxidf");
			writer.println("$XDIM "+INPUT_VALUES_MAP.size());
			writer.println("$YDIM 1");
			writer.println("$VEC_DIM "+ (VectorHelper.getVectorDimension()));

			for(Map.Entry<Integer, VectorData> vectorEntry : INPUT_VALUES_MAP.entrySet()){
				int documentNumber = vectorEntry.getKey();
				VectorData inputVector = vectorEntry.getValue();
				if(inputVector != null && inputVector.getVector() != null && ! inputVector.getVector().isEmpty()){
					StringBuffer vectorStringBuffer = inputVector.getVectorString();
					writer.print(vectorStringBuffer+" ");					
					writer.print("Document_Number_"+documentNumber);
					writer.println();
				}

			}
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
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
