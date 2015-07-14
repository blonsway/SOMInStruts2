package som.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import som.beans.VectorData;
import som.helper.GenericHelper;

public interface IGenericConstants {



	//Number of dimensions in the vector
	//int vectorDimensions = 296*296;

	// Random Number assuming that we do not have any word in the document occuring more than 10 times
	int maxRandomNumberLimit = 5; 

	//Total number of Input Vectors 
	//int totalInputVectors = 60;

	//random value SOM map  <Position Number , Vector >
	Map<Integer, List<Integer>> randomSOMVectorMap = new HashMap<Integer, List<Integer>>();

	//input vector generated from random value  <Vector Number, Vector>
	Map<Integer, VectorData> inputValuesMap = new HashMap<Integer, VectorData>();  

	//List to store combination
	List<String> bestWordsList = new ArrayList<String>();  

	// determines whether training is required
	boolean IS_TRAINING_REQUIRED = true;	

	//maximum number of combinations required for input vectors
	int maxNoOfOptions = 10;

	//Default Option provided
	int defaultOption = 5;

	//Default Option provided
	int customColumnOption = 6;

	//Visual Option provided
	int visualOption = 4;
	
	//Input vector generation option
	int INPUT_VECTOR_GENERATION_OPTION = 2;
	


	//Best Vector File Creation Option provided For Situation Description
	int bestWordFileOptionForSituationDescription = 7;

	//Best Vector File Creation Option provided For Situation Description and Mission Statement
	int bestWordFileOptionForSituationDescriptionAndMissionStatement = 8;

	//Stemmed Best Vector File Creation Option provided For Situation Description
	int stemmendBestWordFileOptionForSituationDescription = 9;

	//Stemmed Best Vector File Creation Option provided For Situation Description and Mission Statement
	int stemmedBestWordFileOptionForSituationDescriptionAndMissionStatement = 10;

	//BL Parser File Creation Option
	int blParserFileOption = 3;

	//Template Vector File Creation Option provided
	int templateVectorFileCreationOption = 2;

	//option used for fetching data during visualization
	int visualizationOption = 11;

	//name of input data sheet
	@Deprecated
	String INPUT_SHEET_NAME = "__141106_mergedDataInFinalTax_active.xlsx";
	
	//forward slash
	String FORWARD_SLASH = "/";
	
	//colon
	String COLON = ":";

	//name of input data sheet
	String REVISED_INPUT_SHEET_NAME = "__150423_mergedData_readyForTraining.xlsx";

	// max no. of rows in sheet
	int MAX_NO_OF_ROWS = 167;

	//map for template vectors
	Map<String,Integer> templateVectorCounterMap = new HashMap<String,Integer>();
	
	//column number to store stemmedData
	int STEMMED_DATA_COLUMN_NUMBER = 26;
	
	//stemmed File 
	String STEMMED_FILE = "stemmedcsv.csv";
	
	//windows path for generated files
	String WIN_PATH_FOR_GENERATED = "C:\\apache-tomcat-7.0.62\\webapps\\generated\\";
	
	//linux path for generated files
	String LINUX_PATH_FOR_GENERATED = "/var/lib/tomcat7/webapps/generated/";



	// list of case 1
	ArrayList<Byte> excelSheetMapperListCase1 = new ArrayList<Byte>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{	
			add((byte) 0);
			add((byte)2);
			add((byte)4);
			add((byte)8);
			add((byte)9);
			add((byte)10);
			add((byte)11);
			add((byte)17);
			add((byte)18);
			add((byte)20);

		}};


		// list of case 2  5,18,19
		ArrayList<Byte> excelSheetMapperListCase2 = new ArrayList<Byte>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -5637014168989543047L;

			{	
				add((byte) 5);
				add((byte)17);
				add((byte)18);


			}};

			// list of case 3
			ArrayList<Byte> excelSheetMapperListCase3 = new ArrayList<Byte>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 274863993302385038L;

				{	
					add((byte) 0);
					add((byte) 5);
					add((byte) 10);
					add((byte) 11);
					add((byte)17);
					add((byte)18);

				}};

				// list of case 4   18  case 7 : ,9,18,20   case 6 : 4,9,20 
				ArrayList<Byte> excelSheetMapperListCase4 = new ArrayList<Byte>(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 7756504788805983078L;

					{	
						//add((byte)4);
						add((byte)9);
						//add((byte)17);
						add((byte)18);
						add((byte)20);
					}};

					// list of case 5 (default case)  18  - only situation description
					ArrayList<Byte> excelSheetMapperListCase5 = new ArrayList<Byte>(){
						/**
						 * 
						 */
						private static final long serialVersionUID = 7656403497780591922L;

						{				
							add((byte)18);
						}};

						// list of case 7   - only situation description
						ArrayList<Byte> excelSheetMapperListCase7 = new ArrayList<Byte>(){
							/**
							 * 
							 */
							private static final long serialVersionUID = 5923539898694295466L;

							{				
								add((byte)18);
							}};

							// list of case 7   - only situation description and mission 
							ArrayList<Byte> excelSheetMapperListCase8 = new ArrayList<Byte>(){
								/**
								 * 
								 */
								private static final long serialVersionUID = 5923539898694295466L;
								{				
									add((byte)18);
									add((byte)5);
								}};

								// list of case 8 - all columns
								ArrayList<Byte> excelSheetMapperListCase9 = new ArrayList<Byte>(){
									/**
									 * 
									 */
									private static final long serialVersionUID = 5923539898694295466L;

									{	
										add((byte)0);
										add((byte)1);
										add((byte)2);
										add((byte)3);
										add((byte)4);
										add((byte)5);
										add((byte)6);
										add((byte)7);
										add((byte)8);
										add((byte)9);
										add((byte)10);
										add((byte)11);
										add((byte)12);
										add((byte)13);
										add((byte)14);
										add((byte)15);
										add((byte)15);
										add((byte)16);
										add((byte)17);
										add((byte)18);
									}};


									//list of case 10 -  columns required for visualization org_name,org_website,social_sector,
									//tech_sector,situation_description,technical_scope
									ArrayList<Byte> excelSheetMapperListCase10 = new ArrayList<Byte>(){/**
									 * 
									 */
										private static final long serialVersionUID = 1L;

										{
											add((byte)0);
											add((byte)4);
											add((byte)15);
											add((byte)16);
											add((byte)18);
											add((byte)21);
										}};


										//list of case 11 -  columns required for stemmed data - Situation Description Only
										ArrayList<Byte> excelSheetMapperListCase11 = new ArrayList<Byte>(){/**
										 * 
										 */
											private static final long serialVersionUID = 1L;

											{										
												add((byte)26);
											}};


											//list of case 12 -  columns required for stemmed data - Situation Description 
											// and mission statement
											ArrayList<Byte> excelSheetMapperListCase12 = new ArrayList<Byte>(){/**
											 * 
											 */
												private static final long serialVersionUID = 1L;

												{										
													add((byte)26);
													add((byte)27);
												}};



												// Custom Columns Entered
												ArrayList<Byte> excelSheetMapperListCaseCustom = new ArrayList<Byte>();


												//list of Neglected words
												ArrayList<String> trimedWordList = new ArrayList<String>(){
													/**
													 * 
													 */
													private static final long serialVersionUID = -8738283910303799120L;

													{
														add(",");
														add(".");
														add("/");
														add("(");	

													}};

													//Regular Expression for Replaced words
													String trimmedCharactersRegex = "([.,/()'])";



													//mappings of option and List
													HashMap<Integer, List<Byte>> sheetMapper = new HashMap<Integer, List<Byte>>(){
														/**
														 * 
														 */
														private static final long serialVersionUID = -1706437816912830507L;

														{
															put(1, excelSheetMapperListCase1);
															put(2, excelSheetMapperListCase2);
															put(3, excelSheetMapperListCase3);
															put(4, excelSheetMapperListCase4);
															put(5, excelSheetMapperListCase5);
															put(6, excelSheetMapperListCaseCustom);
															put(7, excelSheetMapperListCase7);
															put(8, excelSheetMapperListCase8);
															put(9, excelSheetMapperListCase11);
															put(10, excelSheetMapperListCase12);
															put(11, excelSheetMapperListCase10);

														}};

														//dictionary of words
														Map<String, String> wordDictionary = new HashMap<String, String>();

														//List of unique words
														List<String> uniqueWordsList = new ArrayList<String>();


														//list of Neglected words
														ArrayList<String> neglectedWordList = new ArrayList<String>(){
															/**
															 * 
															 */
															private static final long serialVersionUID = 4056783685635726115L;

															{
																add(".");
																add("/");
																add("(");
																add("a");
																add("about");
																add("above");
																add("after");
																add("again");
																add("against");
																add("all");
																add("am");
																add("an");
																add("and");
																add("any");
																add("are");
																add("aren't");
																add("as");
																add("at");
																add("be");
																add("because");
																add("been");
																add("before");
																add("being");
																add("below");
																add("between");
																add("both");
																add("but");
																add("by");
																add("can't");
																add("cannot");
																add("could");
																add("couldn't");
																add("did");
																add("didn't");
																add("do");
																add("does");
																add("doesn't");
																add("doing");
																add("don't");
																add("down");
																add("during");
																add("each");
																add("few");
																add("for");
																add("from");
																add("further");
																add("had");
																add("hadn't");
																add("has");
																add("hasn't");
																add("have");
																add("haven't");
																add("having");
																add("he");
																add("he'd");
																add("he'll");
																add("he's");
																add("her");
																add("here");
																add("here's");
																add("hers");
																add("herself");
																add("him");
																add("himself");
																add("his");
																add("how");
																add("how's");
																add("i");
																add("i'd");
																add("i'll");
																add("i'm");
																add("i've");
																add("if");
																add("in");
																add("into");
																add("is");
																add("isn't");
																add("it");
																add("it's");
																add("its");
																add("itself");
																add("let's");
																add("me");
																add("more");
																add("most");
																add("mustn't");
																add("my");
																add("myself");
																add("no");
																add("nor");
																add("not");
																add("of");
																add("off");
																add("on");
																add("once");
																add("only");
																add("or");
																add("other");
																add("ought");
																add("our");
																add("ours");
																add("ourselves");
																add("out");
																add("over");
																add("own");
																add("same");
																add("shan't");
																add("she");
																add("she'd");
																add("she'll");
																add("she's");
																add("should");
																add("shouldn't");
																add("so");
																add("some");
																add("such");
																add("than");
																add("that");
																add("that's");
																add("the");
																add("their");
																add("theirs");
																add("them");
																add("themselves");
																add("then");
																add("there");
																add("there's");
																add("these");
																add("they");
																add("they'd");
																add("they'll");
																add("they're");
																add("they've");
																add("this");
																add("those");
																add("through");
																add("to");
																add("too");
																add("under");
																add("until");
																add("up");
																add("very");
																add("was");
																add("wasn't");
																add("we");
																add("we'd");
																add("we'll");
																add("we're");
																add("we've");
																add("were");
																add("weren't");
																add("what");
																add("what's");
																add("when");
																add("when's");
																add("where");
																add("where's");
																add("which");
																add("while");
																add("who");
																add("who's");
																add("whom");
																add("why");
																add("why's");
																add("with");
																add("won't");
																add("would");
																add("wouldn't");
																add("you");
																add("you'd");
																add("you'll");
																add("you're");
																add("you've");
																add("your");
																add("yours");
																add("yourself");
																add("yourselves");

															}};
}
