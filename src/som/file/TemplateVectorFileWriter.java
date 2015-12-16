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
 * 		Creates Template Vector file
 * 
 */


package som.file;

import static som.constants.IGenericConstants.UNIQUE_WORD_LIST;




import java.io.PrintWriter;


public class TemplateVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.tv", "UTF-8");
			writer.println("$TYPE template");
			writer.println("$XDIM 2");
			
			writer.println("$VEC_DIM "+ (UNIQUE_WORD_LIST.size()*UNIQUE_WORD_LIST.size()));

			int i,j;
			int count = 0;
			for( i = 0 ; i < UNIQUE_WORD_LIST.size(); i++){
				for(j = 0; j <UNIQUE_WORD_LIST.size() ; j++){
					String word1 = UNIQUE_WORD_LIST.get(i);
					String word2 = UNIQUE_WORD_LIST.get(j);
					writer.println(count+ " "+word1+"-"+word2);
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
