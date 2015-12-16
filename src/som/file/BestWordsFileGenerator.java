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
 *     Generates the best words formatted file
 *
 * 
 */

package som.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;






import som.constants.IBestWordsFileConstants;
import som.helper.GenericHelper;

public class BestWordsFileGenerator implements IFileWritable{



	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub


	}


	/**
	 * creates the bests words text file
	 * @param wordList
	 */
	public void createVectorDimensionAndWriteIntoFile(List<String[]> wordList){
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(GenericHelper.getAbsolutePath()+IBestWordsFileConstants.FILENAME);
			for(String[]words : wordList){
				pw.println(words[0]+","+words[1]);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(pw != null){
				pw.close();
			}
		}
	}

	@Override
	public void readFromFile() {
		

	}

	/**
	 *  main function used for testing
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestWordsFileGenerator cf = new BestWordsFileGenerator();
		cf.readFromFile("output_cooccurrOfmergedSitDescOnly.csv");
		System.out.println("Execution terminated successfully");
	}


	@Override
	public void readFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		List<String[]> wordList = new ArrayList<String[]>();
		try{ 
			// TODO Auto-generated method stub
			br = new BufferedReader(new FileReader(new File(GenericHelper.getAbsolutePath()+fileName)));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] words = line.split(",");
				wordList.add(words);
			}
			createVectorDimensionAndWriteIntoFile(wordList);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}



}
