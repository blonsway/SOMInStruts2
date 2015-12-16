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
 * Creates a Template Vector From the Best Words of Situation Description provided by Prof. Brian Lonsway
 *
 * 
 */


package som.file;

import static som.constants.IGenericConstants.BEST_WORDS_LIST;




import java.io.PrintWriter;

import som.helper.GenericHelper;

/**
 * @author prashant
 *
 */
public class BestWordsTemplateVectorWriter implements IFileWritable{

	

	@Override
	public void writeToFile() {
		
		try{
			PrintWriter writer = new PrintWriter(GenericHelper.getAbsolutePath()+"MySOM.tv", "UTF-8");
			writer.println("$TYPE template");
			writer.println("$XDIM 2");
			
			writer.println("$VEC_DIM "+ (BEST_WORDS_LIST.size()));

			int i;
			int count = 0;
			for( i = 0 ; i < BEST_WORDS_LIST.size(); i++){				
					writer.println((count++)+ " "+BEST_WORDS_LIST.get(i));
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
