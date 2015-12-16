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
 * 		Creates Weight Vector file
 * 
 */


package som.file;

import java.io.PrintWriter;
import java.util.List;

import som.constants.IMatrixConstants;
import som.helper.VectorHelper;

public class WeightVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.wgt", "UTF-8");
			writer.println("$TYPE som");
			writer.println("$GRID_LAYOUT rectangular");
			writer.println("$GRID_TOPOLOGY planar");
			writer.println("$XDIM "+IMatrixConstants.SOM_MATRIX_ROW_SIZE);
			writer.println("$YDIM "+IMatrixConstants.SOM_MATRIX_COLUMN_SIZE);
			writer.println("$ZDIM 1");
			writer.println("$VEC_DIM "+VectorHelper.getVectorDimension());
			for (int i = 0; i <IMatrixConstants.SOM_MATRIX_ROW_SIZE; i++)
			{
				for (int j = 0; j <IMatrixConstants.SOM_MATRIX_COLUMN_SIZE; j++)
				{
					int position =IMatrixConstants.SOM_MATRIX[i][j];
					List<Integer>vector =IMatrixConstants.RANDOM_SOM_VECTOR_MAP.get(position);
					for(Integer distance : vector){
						writer.print(distance+" ");
					}
					writer.println("SOM_MAP_MySOM_("+i+"/"+j+"/0)");
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
