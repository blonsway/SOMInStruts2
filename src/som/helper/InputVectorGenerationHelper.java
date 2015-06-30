/**
 * 
 * Class containing important utlity methods to generate input vectors data and also to 
 * generate Input Vector files, Template Vector Files and Semantic Parser File
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



//static import
import static som.constants.IGenericConstants.inputValuesMap;
import static som.constants.IGenericConstants.wordDictionary;
import static som.constants.IGenericConstants.templateVectorCounterMap;
import static som.constants.IGenericConstants.uniqueWordsList;
import static som.constants.IGenericConstants.bestWordsList;
import static som.constants.IGenericConstants.neglectedWordList;
import static som.constants.IGenericConstants.trimmedCharactersRegex;
import static som.constants.IGenericConstants.stemmendBestWordFileOptionForSituationDescription;
import static som.constants.IGenericConstants.templateVectorFileCreationOption;
import static som.constants.IGenericConstants.blParserFileOption;
import static som.constants.IBestWordsFileConstants.CSV_WITH_SITUATION_DESC;
import static som.constants.IBestWordsFileConstants.CSV_WITH_SITUATION_DESC_MISSION_TEXT;
import static som.constants.IFileFactoryConstants.TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE_GENERATOR;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE;



import som.adapter.FileOperationsAdapter;
import som.beans.VectorData;
import som.constants.ICommandLineConstants;

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
			if(!neglectedWordList.contains(word) && word.length() > 1){
				word = removeCharactersToBeTrimmed(word);
				wordDictionary.put(word, index+"");
				if(!uniqueWordsList.contains(word)){
					uniqueWordsList.add(word);
				}
				index++;
			}

		}
	}

	private static String removeCharactersToBeTrimmed(String word){			
		return word.replaceAll(trimmedCharactersRegex,"");		
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

			Integer templateVectorTf = templateVectorCounterMap.get(key);
			if( vectorTf != null ){

				templateVectorCounterMap.put(key, ++templateVectorTf);
				templateVectorCounterMap.put(keyReverse, templateVectorTf);

			}
			else{

				templateVectorCounterMap.put(key, 1);
				templateVectorCounterMap.put(keyReverse,1);
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
			if(!neglectedWordList.contains(wordsVectorArray[i]) ){
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
				writer.println("$VEC_DIM "+ (uniqueWordsList.size()*uniqueWordsList.size()));
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
							String iIndex = wordDictionary.get(prev);
							String jIndex = wordDictionary.get(cur);
							//System.out.println("Prev = "+prev+" iIndex="+iIndex+" , cur = "+cur+"  jIndex = "+jIndex);

							setVectorMapData(iIndex, jIndex, vectorDataMap);

						}
						if(next!=null && next != "" &&  cur!=null && cur != "" ){
							String iIndex = wordDictionary.get(cur);
							String jIndex = wordDictionary.get(next);
							//System.out.println("Cur = "+cur+" iIndex="+iIndex+" , Next = "+next+"  jIndex = "+jIndex);

							setVectorMapData(iIndex, jIndex, vectorDataMap);
						}
						prev = next;


					}

					//now we will browse like a map but will take matrix values from hashmap in O(1) time
					//List<Integer> vector = getVectorFromVectorDataMap(vectorDataMap, wordDictionary.size());
					//now we will browse like a map but will take matrix values from hashmap in O(1) time
					List<Integer> vector = getVectorFromVectorDataMap(vectorDataMap, wordDictionary.size());
					vectorData.setVector(vector);
					//StringBuffer vectorStringBuffer = 
					if(firstOption != 1){
						storeVectorStringFromVectorDataMap(vectorData,vectorDataMap, wordDictionary.size(), i,writer);
					}
					//vectorData.setVectorString(vectorStringBuffer);
					//System.out.println("The vector is "+vector);

					inputValuesMap.put(dataCount++, vectorData);

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
				writer = new PrintWriter("MySOM.tfxidf", "UTF-8");
				writer.println("$TYPE vec_tfxidf");
				writer.println("$XDIM "+getDocCount(vectorDataList));
				writer.println("$YDIM 1");
				writer.println("$VEC_DIM "+ (bestWordsList.size()));
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
				int[] vector = new int[bestWordsList.size()];

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

						if(bestWordsList.contains(coOccurence) || bestWordsList.contains(coOccurenceReverse)){
							int index = bestWordsList.indexOf(coOccurence);
							if(index == -1 ){
								index = bestWordsList.indexOf(coOccurenceReverse);
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

					inputValuesMap.put(dataCount++, vectorData);

				}

			}

			System.out.println("Done With Generating the Input Vectors "+inputValuesMap.size());
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

			if(secondOption == stemmendBestWordFileOptionForSituationDescription){
				fileOperAdapter.readFromFile(BEST_WORDS_FILE_GENERATOR,CSV_WITH_SITUATION_DESC );
			}
			else{
				fileOperAdapter.readFromFile(BEST_WORDS_FILE_GENERATOR,CSV_WITH_SITUATION_DESC_MISSION_TEXT );
			}
		}
	}

	/**
	 * handles best words case, all words case and calls the appropriate function
	 * @param inputVectorMap
	 * @param option
	 */
	public static void createInputVectors(Map<Integer,VectorData> inputVectorMap, int firstOption, int secondOption){
		FileOperationsAdapter fileOperAdapter = new FileOperationsAdapter();		

		doPreliminaryTaskForStemmedInput(secondOption, fileOperAdapter);		

		List<VectorData> vectorDataList = fileOperAdapter.getVectorDataListFromExcelSheet(secondOption);
		StringBuffer wordsDiscovered = fileOperAdapter.getTotalNoOfWords();

		if(GenericHelper.isBestWordsOptionSelected(secondOption)){
			//prints the Best Words Vector file
			fileOperAdapter.readFromFile(BEST_WORDS_FILE);
			//browser through every VectorData object and 
			generateTfCooccurenceValuesIntoVectorsForBestWords(inputVectorMap,vectorDataList, firstOption);
			fileOperAdapter.writeToFile(BEST_WORDS_TEMPLATE_VECTOR_FILE);
			
			//executing the command to create DWM Files
			try{
				String command = ICommandLineConstants.RUN_GROWING_SOM_COMMAND_WINDOWS;
				if(!GenericHelper.isWindows()){
					command =  ICommandLineConstants.RUN_GROWING_SOM_COMMAND_LINUX;
				}
				Process p = Runtime.getRuntime().exec(command);	
				p.waitFor();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		else{
			//adding unique words to dictionary
			createWordDictionary(wordsDiscovered);

			//browser through every VectorData object and 
			generateTfCooccurenceValuesIntoVectors(inputVectorMap,vectorDataList, firstOption);
		}

		if(firstOption == templateVectorFileCreationOption && !GenericHelper.isBestWordsOptionSelected(secondOption) ){
			//prints the input Vector file
			fileOperAdapter.writeToFile(TEMPLATE_VECTOR_FILE);
		}


		if(firstOption == blParserFileOption){
			fileOperAdapter.writeToFile(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE);
		}

		// Comment below line if you do not want to parse sample output file bl_out_unit
		//fileOperAdapter.writeToFile(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE);
	}

}
