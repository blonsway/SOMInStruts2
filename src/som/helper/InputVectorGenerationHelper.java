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
 * 		Class containing important utlity methods to generate input vectors data and also to 
 * 		generate Input Vector files, Template Vector Files and Semantic Parser File.
 * 
 * 		Also plays the role of Facade by calling the Python scripts executor API, stemmed File copy
 * 		code and best words file parser code to create input vectors.
 * 
 */

package som.helper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import som.adapter.FileOperationsAdapter;
import som.adapter.PythonAdapter;
import som.adapter.SOMToolboxAdapter;
import som.beans.VectorData;
import som.constants.ICommandLineConstants;
import som.constants.IGenericConstants;

//static import
import static som.constants.IGenericConstants.INPUT_VALUES_MAP;
import static som.constants.IGenericConstants.WORD_DICTIONARY;
import static som.constants.IGenericConstants.TEMPLATE_VECTOR_COUNTER_MAP;
import static som.constants.IGenericConstants.UNIQUE_WORD_LIST;
import static som.constants.IGenericConstants.BEST_WORDS_LIST;
import static som.constants.IGenericConstants.NEGLECTED_WORDS_LIST;
import static som.constants.IGenericConstants.TRIMMED_CHARACTERS_REGEX;
import static som.constants.IGenericConstants.TEMPLATE_VECTOR_FILE_CREATION_OPTION;
import static som.constants.IGenericConstants.BL_PARSER_FILE_OPTION;
import static som.constants.IBestWordsFileConstants.BEST_WORDS_CSV;
import static som.constants.IFileFactoryConstants.TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE_GENERATOR;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.STEMMED_DATA_FILE_READER;
import static som.constants.IFileFactoryConstants.FULLY_REDACTED_FILE_PARSER;

public class InputVectorGenerationHelper {

	/**
	 * adding unique words to dictionary
	 * @param wordsDiscovered
	 */
	private static void createWordDictionary(StringBuffer wordsDiscovered){

		// Java is Awesome. Arrays.asList operation is in O(1) time
		Set<String> items = new HashSet<String>(Arrays.asList(wordsDiscovered.toString().split("\\s+")));		
		System.out.println(" Unique words are "+items.size());
		int index = 0;

		for(String word : items){
			//doing two way mapping since  it can be extracted both ways.
			//wordDictionary.put(index+"", word);
			if(!NEGLECTED_WORDS_LIST.contains(word) && word.length() > 1){
				word = removeCharactersToBeTrimmed(word);
				WORD_DICTIONARY.put(word, index+"");
				if(!UNIQUE_WORD_LIST.contains(word)){
					UNIQUE_WORD_LIST.add(word);
				}
				index++;
			}

		}
	}

	/**
	 * removing the characters to be trimmed
	 * @param word
	 * @return
	 */
	private static String removeCharactersToBeTrimmed(String word){			
		return word.replaceAll(TRIMMED_CHARACTERS_REGEX,"");		
	}

	/**
	 * store data into VectorMap and templateVectorData
	 * @param iIndex
	 * @param jIndex
	 * @param vectorDataMap
	 */
	private static void setVectorMapData(String iIndex, String jIndex, Map<String,Integer> vectorDataMap){

		if(iIndex != null && iIndex != "" && jIndex != null && jIndex != ""){
			String key = "("+iIndex+","+jIndex+")";
			String keyReverse = "("+jIndex+","+iIndex+")";

			Integer vectorTf = vectorDataMap.get(key);
			if( vectorTf != null ){
				vectorDataMap.put(key, ++vectorTf);
				vectorDataMap.put(keyReverse, vectorTf);

			}
			else{

				vectorDataMap.put(key, 1);
				vectorDataMap.put(keyReverse, 1);

			}

			Integer templateVectorTf = TEMPLATE_VECTOR_COUNTER_MAP.get(key);
			if( vectorTf != null ){

				TEMPLATE_VECTOR_COUNTER_MAP.put(key, ++templateVectorTf);
				TEMPLATE_VECTOR_COUNTER_MAP.put(keyReverse, templateVectorTf);

			}
			else{

				TEMPLATE_VECTOR_COUNTER_MAP.put(key, 1);
				TEMPLATE_VECTOR_COUNTER_MAP.put(keyReverse,1);
			}

		}

	}

	/**
	 * gets the vector value of cooccurence into list of integers
	 * @param vectorDataMap
	 * @param size
	 * @return
	 */
	private static void storeVectorStringFromVectorDataMap(VectorData vectorData, 
			Map<String,Integer>  vectorDataMap, int size, int docNo,PrintWriter writer){
		try{

			int i,j;
			//List<Byte> vector = new ArrayList<Byte>();
			StringBuffer vectorString = new StringBuffer("");
			for( i = 0 ; i < size ; i ++){
				for( j  = 0  ; j < size ; j++){
					String key = "("+i+","+j+")";

					if(vectorDataMap.get(key) != null){
						//Byte val = (vectorDataMap.get(key).byteValue());
						//vector.add(val);
						vectorString.append(vectorDataMap.get(key)).append(" ");
					}
					else{
						//vector.add((byte)0);
						vectorString.append("0").append(" ");
					}
				}
			}
			vectorString.append("Document_number_"+docNo);
			writer.println(vectorString);
			//vectorData.setVectorByte(vector);

			// making it  eligible for garbage collection to avoid Heap Space Error
			vectorString = null;

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * gets the vector value of cooccurence into list of integers
	 * @param vectorDataMap
	 * @param size
	 * @return
	 */
	private static List<Integer> getVectorFromVectorDataMap(Map<String,Integer>  vectorDataMap, int size){
		int i,j;
		List<Integer> vector = new ArrayList<Integer>();
		for( i = 0 ; i < size ; i ++){
			for( j  = 0  ; j < size ; j++){
				String key = "("+i+","+j+")";

				if(vectorDataMap.get(key) != null){
					vector.add(vectorDataMap.get(key));
				}
				else{
					vector.add(0);

				}
			}
		}

		return vector;
	}


	/**
	 * count the number of words 
	 * 
	 * @param vectorDataList
	 * @return
	 */
	private static int getDocCount(List<VectorData> vectorDataList){
		int count = 0 ;
		for(int i  = 0 ; i < vectorDataList.size() ; i++){

			VectorData vectorData = vectorDataList.get(i);
			String vectorString = vectorData.toString();

			String[] vectorStringArray = vectorString.split("\\s+");

			if(vectorStringArray != null && vectorStringArray.length > 0 ){
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * return the removed unused words and trim operation
	 * 
	 * @param wordsVectorArray
	 * @return
	 */
	public static List<String> removeUnusedWordsNTrimWords(String[] wordsVectorArray){
		List<String> refinedInputWords = new ArrayList<String>();
		for(int i = 0 ; i < wordsVectorArray.length ; i++){
			if(!NEGLECTED_WORDS_LIST.contains(wordsVectorArray[i]) ){
				wordsVectorArray[i] = removeCharactersToBeTrimmed(wordsVectorArray[i]);
				if(wordsVectorArray[i] != null && wordsVectorArray[i] !="")
					refinedInputWords.add(wordsVectorArray[i]);
			}
		}

		return refinedInputWords;
	}


	/**
	 * Takes the vectordata from the list, and smartly generates cooccurence matrix without using Matrix data structure
	 * @param inputVectorMap
	 * @param vectorDataList
	 */
	@Deprecated
	private static void generateTfCooccurenceValuesIntoVectors(Map<Integer,VectorData> inputVectorMap, 
			List<VectorData> vectorDataList, int firstOption){
		PrintWriter writer = null;
		try{
			if(firstOption != 1){
				System.out.println(" Generating Vectors. Time consuming Process. Please wait ...");
				writer = new PrintWriter("MySOM.tfxidf", "UTF-8");
				writer.println("$TYPE vec_tfxidf");
				writer.println("$XDIM "+getDocCount(vectorDataList));
				writer.println("$YDIM 1");
				writer.println("$VEC_DIM "+ (UNIQUE_WORD_LIST.size()*UNIQUE_WORD_LIST.size()));
			}


			int dataCount = 0;
			for(int i  = 0 ; i < vectorDataList.size() ; i++){
				// this map will store key as (i,j) string and value as its cooccurence.
				// So this is the substitute for using Matrix. We are avoiding to  use matrix bcoz 
				// our matrix would be of size > 9000 * 9000
				Map<String,Integer> vectorDataMap = new HashMap<String, Integer>();
				VectorData vectorData = vectorDataList.get(i);
				String vectorString = vectorData.toString();

				String[] vectorStringArray = vectorString.split("\\s+");

				//remove neglected words and trim unused words
				List<String> vectorStringList = removeUnusedWordsNTrimWords(vectorStringArray);

				if(vectorStringList != null && vectorStringList.size() > 0 ){
					String prev = vectorStringList.get(0), cur = null, next = null;

					//iterating through each element of vector string array
					for(int j = 1 ; j < vectorStringList.size() ; j+=2){
						cur = vectorStringList.get(j);
						if(j+1 < vectorStringList.size()){
							next = vectorStringList.get(j+1);					
						}						



						if(prev!=null && prev != "" && cur!=null && cur != "" ){
							String iIndex = WORD_DICTIONARY.get(prev);
							String jIndex = WORD_DICTIONARY.get(cur);
							//System.out.println("Prev = "+prev+" iIndex="+iIndex+" , cur = "+cur+"  jIndex = "+jIndex);

							setVectorMapData(iIndex, jIndex, vectorDataMap);

						}
						if(next!=null && next != "" &&  cur!=null && cur != "" ){
							String iIndex = WORD_DICTIONARY.get(cur);
							String jIndex = WORD_DICTIONARY.get(next);
							//System.out.println("Cur = "+cur+" iIndex="+iIndex+" , Next = "+next+"  jIndex = "+jIndex);

							setVectorMapData(iIndex, jIndex, vectorDataMap);
						}
						prev = next;


					}

					//now we will browse like a map but will take matrix values from hashmap in O(1) time
					//List<Integer> vector = getVectorFromVectorDataMap(vectorDataMap, wordDictionary.size());
					//now we will browse like a map but will take matrix values from hashmap in O(1) time
					List<Integer> vector = getVectorFromVectorDataMap(vectorDataMap, WORD_DICTIONARY.size());
					vectorData.setVector(vector);
					//StringBuffer vectorStringBuffer = 
					if(firstOption != 1){
						storeVectorStringFromVectorDataMap(vectorData,vectorDataMap, WORD_DICTIONARY.size(), i,writer);
					}
					//vectorData.setVectorString(vectorStringBuffer);
					//System.out.println("The vector is "+vector);

					INPUT_VALUES_MAP.put(dataCount++, vectorData);

				}

			}

			System.out.println("Done With Generating the Input Vectors "+inputVectorMap.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(writer != null){
				writer.close();
			}
		}

	}

	/**
	 * Takes the vectordata from the list, and smartly generates cooccurence matrix without using Matrix data structure
	 * for the best words provided by Prof.Brian Lonsway in bestwords.txt
	 * @param inputVectorMap
	 * @param vectorDataList
	 */
	private static void generateTfCooccurenceValuesIntoVectorsForBestWords(Map<Integer,VectorData> inputVectorMap, 
			List<VectorData> vectorDataList, int firstOption){
		PrintWriter writer = null;
		try{
			if(firstOption != 1){
				System.out.println(" Generating Vectors. Time consuming Process. Please wait ...");
				writer = new PrintWriter(GenericHelper.getAbsolutePath()+"MySOM.tfxidf", "UTF-8");
				writer.println("$TYPE vec_tfxidf");
				writer.println("$XDIM "+getDocCount(vectorDataList));
				writer.println("$YDIM 1");
				writer.println("$VEC_DIM "+ (BEST_WORDS_LIST.size()));
			}

			int dataCount = 0;
			for(int i  = 0 ; i < vectorDataList.size() ; i++){
				// this map will store key as (i,j) string and value as its cooccurence.
				// So this is the substitute for using Matrix. We are avoiding to  use matrix bcoz 
				// our matrix would be of size > 9000 * 9000
				VectorData vectorData = vectorDataList.get(i);
				String vectorString = vectorData.toString();

				String[] vectorStringArray = vectorString.split("\\s+");

				//input vectors in array form for best words file
				int[] vector = new int[BEST_WORDS_LIST.size()];

				//input vectors in List form for best words file
				List<Integer> vectorList = new ArrayList<Integer>();


				//remove neglected words and trim unused words
				List<String> vectorStringList = removeUnusedWordsNTrimWords(vectorStringArray);

				if(vectorStringList != null && vectorStringList.size() > 0 ){
					String cur = null, next = null;

					//iterating through each element of vector string array
					for(int j = 0 ; j < vectorStringList.size() ; j++){
						cur = vectorStringList.get(j);
						if(j+1 < vectorStringList.size()){
							next = vectorStringList.get(j+1);					
						}						

						String coOccurence = cur+","+next;
						String coOccurenceReverse = next+","+cur;

						if(BEST_WORDS_LIST.contains(coOccurence) || BEST_WORDS_LIST.contains(coOccurenceReverse)){
							int index = BEST_WORDS_LIST.indexOf(coOccurence);
							if(index == -1 ){
								index = BEST_WORDS_LIST.indexOf(coOccurenceReverse);
							}

							if(index != -1){
								vector[index]++;									
							}
						}

					}

					//copy to List . Array.asList puts null value for 0 
					for(int j  = 0 ; j < vector.length ; j++){

						vectorList.add(vector[j]);

					}

					StringBuffer vectorCoOccurenceString = new StringBuffer("");

					vectorData.setVector(vectorList);


					for(Integer val : vectorList){

						vectorCoOccurenceString.append(val+ " ");

					}
					vectorCoOccurenceString.append("Document_number_"+i);
					if(firstOption != 1){
						writer.println(vectorCoOccurenceString);
					}

					INPUT_VALUES_MAP.put(dataCount++, vectorData);

				}

			}

			System.out.println("Done With Generating the Input Vectors "+INPUT_VALUES_MAP.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(writer != null){
				writer.close();
			}
		}

	}

	/**
	 *  stemmed file input  needs two preprocessing steps - first to create bestwords.txt file , second to add stemmed 
	 *  situation description and stemmed situation description + mission statement into the input data file
	 *  
	 * @param secondOption
	 * @param fileOperAdapter
	 */
	public static void doPreliminaryTaskForStemmedInput(int secondOption, FileOperationsAdapter fileOperAdapter){
		if(GenericHelper.isStemmedBestWordsSelected(secondOption)){
			fileOperAdapter.readFromFile(BEST_WORDS_FILE_GENERATOR,BEST_WORDS_CSV);			
		}
	}

	/**
	 * handles best words case, all words case and calls the appropriate function
	 * @param inputVectorMap
	 * @param option
	 */
	public static void createInputVectors(Map<Integer,VectorData> inputVectorMap, int firstOption, int secondOption, 
			List<String> columnList){
		
		//logic of calling the python script and generating the stemmed output goes here
		System.out.println("Logic of running the Python File starts here");
		//logic of running the python code    	
		new PythonAdapter().exceutePythonScripts(ICommandLineConstants.RUN_PYTHON_COMMAND_LINUX,
				columnList);
		System.out.println("Logic of running the Python File ends here");
		
		FileOperationsAdapter fileOperAdapter = new FileOperationsAdapter();		

		doPreliminaryTaskForStemmedInput(secondOption, fileOperAdapter);		

		List<VectorData> vectorDataList = fileOperAdapter.getVectorDataListFromExcelSheet(secondOption);
		StringBuffer wordsDiscovered = fileOperAdapter.getTotalNoOfWords();

		if(GenericHelper.isBestWordsOptionSelected(secondOption)){
			
			
			System.out.println("Logic of Reading into Stemmed Data and writing into Input File starts here");
			fileOperAdapter.readFromFile(STEMMED_DATA_FILE_READER,IGenericConstants.STEMMED_FILE);
			System.out.println("Logic of Reading into Stemmed Data and writing into Input File ends here");


			System.out.println("Logic of parsing and mapping columns from FullyRedactedDocs starts here");

			//logic of copying data from FullyRedactedDocs into Input sheet
			fileOperAdapter.readFromFile(FULLY_REDACTED_FILE_PARSER, IGenericConstants.FULLY_REDACTED_FILE_NAME);

			
			System.out.println("Logic of parsing the Best words File starts here");
			//prints the Best Words Vector file
			fileOperAdapter.readFromFile(BEST_WORDS_FILE);
			System.out.println("Logic of generating co-occurences starts here");
			//browser through every VectorData object and 
			generateTfCooccurenceValuesIntoVectorsForBestWords(inputVectorMap,vectorDataList, firstOption);
			System.out.println("Logic of generating co-occurences ends here");
			System.out.println("Logic of generating template vector file starts here");
			fileOperAdapter.writeToFile(BEST_WORDS_TEMPLATE_VECTOR_FILE);
			System.out.println("Logic of generating template vector file ends here");

			//executing the command to create DWM Files
			String command = ICommandLineConstants.WINDOWS_OUTPUT_GROWING_COMMAND_1+
					ICommandLineConstants.WINDOWS_OUTPUT_GROWING_COMMAND_2+
					ICommandLineConstants.RUN_GROWING_SOM_COMMAND_WINDOWS;
			
			if(!GenericHelper.isWindows()){
				command =  ICommandLineConstants.RUN_GROWING_SOM_COMMAND_LINUX;
			} 
			
			new SOMToolboxAdapter().executeGrowingSOMScript(command);
			System.out.println("Logic of running SOMToolbox Command ends here");

		}
		else{
			//adding unique words to dictionary
			createWordDictionary(wordsDiscovered);

			//browser through every VectorData object and 
			generateTfCooccurenceValuesIntoVectors(inputVectorMap,vectorDataList, firstOption);
		}

		if(firstOption == TEMPLATE_VECTOR_FILE_CREATION_OPTION && !GenericHelper.isBestWordsOptionSelected(secondOption) ){
			//prints the input Vector file
			fileOperAdapter.writeToFile(TEMPLATE_VECTOR_FILE);
		}


		if(firstOption == BL_PARSER_FILE_OPTION){
			fileOperAdapter.writeToFile(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE);
		}

		// Comment below line if you do not want to parse sample output file bl_out_unit
		//fileOperAdapter.writeToFile(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE);
	}

}
