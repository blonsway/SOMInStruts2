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
 * 		Creates unit Vector file
 * 
 */


package som.file;

import java.io.PrintWriter;

import som.constants.IMatrixConstants;

public class UnitVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.unit", "UTF-8");
			writer.println("$TYPE som");
			writer.println("$GRID_LAYOUT rectangular");
			writer.println("$GRID_TOPOLOGY planar");
			writer.println("$FILE_FORMAT_VERSION 1.2");
			writer.println("$XDIM "+IMatrixConstants.SOM_MATRIX_ROW_SIZE);
			writer.println("$YDIM "+IMatrixConstants.SOM_MATRIX_COLUMN_SIZE);
			for (int j = 0; j <IMatrixConstants.SOM_MATRIX_COLUMN_SIZE; j++)
			{
				for (int i = 0; i <IMatrixConstants.SOM_MATRIX_ROW_SIZE; i++)
				{
					writer.println("$POS_X "+i);
					writer.println("$POS_Y "+j);
					if(IMatrixConstants.DOCUMENT_MATRIX[i][j] != null){
						String[] mappedDocNumberString =IMatrixConstants.DOCUMENT_MATRIX[i][j].split(",");
						String[] mappedVectorDistanceString =IMatrixConstants.MIN_DISTANCE_MATRIX[i][j].split(",");

						if(mappedDocNumberString != null && mappedDocNumberString.length > 0 &&
								mappedVectorDistanceString != null && mappedVectorDistanceString.length > 0){
							writer.println("$NR_VEC_MAPPED "+mappedDocNumberString.length);
							writer.println("$MAPPED_VECS");
							for(String mappedDocNumber : mappedDocNumberString){
								writer.println("Document Number : "+mappedDocNumber);
							}
							writer.print("$MAPPED_VECS_DIST ");
							for(String mappedDistVector : mappedVectorDistanceString){
								writer.print(mappedDistVector+" ");
							}
							writer.println();
						}
					}
					else{
						writer.println("$NR_VEC_MAPPED 0");
					}

				}
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
