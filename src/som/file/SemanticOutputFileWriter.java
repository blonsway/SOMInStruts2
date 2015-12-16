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
 * 		Creates custom unit output file 
 * 
 */



package som.file;

import java.io.PrintWriter;

import som.beans.VectorData;
import static som.constants.IMatrixConstants.DOCUMENT_MATRIX;
import static som.constants.IGenericConstants.INPUT_VALUES_MAP;



public class SemanticOutputFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter pw = new PrintWriter("custom_out.unit", "UTF-8");
			
			for(int i = 0 ; i < DOCUMENT_MATRIX.length;i++){
				for(int j =0 ; j< DOCUMENT_MATRIX[i].length ; j++){
					pw.println("bl_out_("+i+"/"+j+")");
					String docNoString = DOCUMENT_MATRIX[i][j];
					if(docNoString != null & docNoString != "" && docNoString.length() > 0){
						String[] docNos = docNoString.split(",");
						String distString = DOCUMENT_MATRIX[i][j];
						String[] distances = distString.split(",");
						pw.println("MAPPED VECTORS:");
						for(int k = 0 ; k < docNos.length ; k++){
							pw.println("Dcoument_Number_"+docNos[k]+" (dist: "+distances[k]+")");
							VectorData vectorData = INPUT_VALUES_MAP.get(Integer.parseInt(docNos[k]));
							pw.println("Project Title : "+vectorData.getProjectTitle());
							pw.println("Situation Description : "+vectorData.getSituationDescription());

						}
					}
					
				}
			}
			pw.close();
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
