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
 * Parse the File Containing Top 100 Words and accordingly includes the info into Appropriate data structure
 *
 * 
 */



package som.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;



import java.io.InputStreamReader;

import som.helper.GenericHelper;
//static import
import static som.constants.IBestWordsFileConstants.FILENAME;
import static som.constants.IGenericConstants.UNIQUE_WORD_LIST;
import static som.constants.IGenericConstants.BEST_WORDS_LIST;


public class BestWordsFileParser implements IFileWritable{

	

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		
	}
	
	public  void readFromFile(){
		String currentLine = null;
		BufferedReader br = null;
		try{
			if(BEST_WORDS_LIST.size() > 0 ){
				BEST_WORDS_LIST.clear();
			}
			br = new BufferedReader(new FileReader(new File(GenericHelper.getAbsolutePath()+FILENAME)));
			while((currentLine = br.readLine()) != null){
				currentLine = currentLine.trim();
				//if(!bestWordsList.contains(currentLine)){
					BEST_WORDS_LIST.add(currentLine);
				//}
				String[] wordSplitArray = currentLine.split(",");
				for(String s : wordSplitArray){
					if(!UNIQUE_WORD_LIST.contains(s)){
						UNIQUE_WORD_LIST.add(s);
					}
				}
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
