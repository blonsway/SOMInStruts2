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
 * 		Constants related to input file names, user preference mapping, vector map
 * 		and all other generic storage realated to SOm generation and visalization
 * 
 */

package som.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import som.beans.VectorData;

public interface IGenericConstants {

	// Random Number assuming that we do not have any word in the document occuring more than 10 times
	int MAX_RANDOM_NUMBER_LIMIT = 5; 

	//random value SOM map  <Position Number , Vector >
	Map<Integer, List<Integer>> RANDOM_SOM_VECTOR_MAP = new HashMap<Integer, List<Integer>>();

	//input vector generated from random value  <Vector Number, Vector>
	Map<Integer, VectorData> INPUT_VALUES_MAP = new HashMap<Integer, VectorData>();  

	//List to store combination
	List<String> BEST_WORDS_LIST = new ArrayList<String>();  

	// determines whether training is required
	boolean IS_TRAINING_REQUIRED = true;	

	//maximum number of combinations required for input vectors
	int MAX_NO_OF_OPTIONS = 10;

	//Default Option provided
	int DEFAULT_OPTION = 5;

	//Default Option provided
	int CUSTOM_COLUMN_OPTION = 6;

	//Visual Option provided
	int VISUAL_OPTION = 4;

	//Input vector generation option
	int INPUT_VECTOR_GENERATION_OPTION = 2;

	//Best Vector File Creation Option provided For Situation Description
	int BEST_WORDS_FILE_OPTION_FOR_SITUATION_DESCRIPTION = 7;

	//Best Vector File Creation Option provided For Situation Description and Mission Statement
	int BEST_WORDS_FILE_OPTION_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT = 8;

	//Stemmed Best Vector File Creation Option provided For Situation Description
	int STEMMED_BEST_WORD_FILE_OPTION_FOR_SITUATION_DESCRIPTION = 9;

	//Stemmed Best Vector File Creation Option provided For Situation Description and Mission Statement
	int STEMMED_BEST_WORDS_FILE_FOR_SITUATION_DESCRIPTION_AND_MISSION_STATEMENT = 10;

	//BL Parser File Creation Option
	int BL_PARSER_FILE_OPTION = 3;

	//Template Vector File Creation Option provided
	int TEMPLATE_VECTOR_FILE_CREATION_OPTION = 2;

	//option used for fetching data during visualization
	int VISUALIZATION_OPTION = 11;

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
	Map<String,Integer> TEMPLATE_VECTOR_COUNTER_MAP = new HashMap<String,Integer>();

	//column number to store stemmedData
	int STEMMED_DATA_COLUMN_NUMBER = 26;

	//stemmed File 
	String STEMMED_FILE = "stemmedcsv.csv";

	//windows path for generated files
	String WIN_PATH_FOR_GENERATED = "C:\\apache-tomcat-7.0.62\\webapps\\generated\\";

	//linux path for generated files
	String LINUX_PATH_FOR_GENERATED = "/var/lib/tomcat7/webapps/ROOT/generated/";

	//fully Redacted File Mapper
	String FULLY_REDACTED_FILE_NAME =  "fullyRedactedDocs.csv";


	// list of columns for case 1
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_1 = new ArrayList<Byte>(){
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
		}
	};

	// list of columns for case 2  
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_2 = new ArrayList<Byte>(){
		private static final long serialVersionUID = -5637014168989543047L;
		{	
			add((byte) 5);
			add((byte)17);
			add((byte)18);
		}
	};

	// list of columns for case 3
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_3 = new ArrayList<Byte>(){
		private static final long serialVersionUID = 274863993302385038L;
		{	
			add((byte) 0);
			add((byte) 5);
			add((byte) 10);
			add((byte) 11);
			add((byte)17);
			add((byte)18);
		}
	};

	// list of columns for case 4   
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_4 = new ArrayList<Byte>(){
		private static final long serialVersionUID = 7756504788805983078L;
		{	
			add((byte)9);
			add((byte)18);
			add((byte)20);
		}
	};

	// list of columns for case 5 - only situation description
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_5 = new ArrayList<Byte>(){
		private static final long serialVersionUID = 7656403497780591922L;
		{				
			add((byte)18);
		}
	};

	// list of columns for case 8   - only situation description and mission 
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_8 = new ArrayList<Byte>(){
		private static final long serialVersionUID = 5923539898694295466L;
		{				
			add((byte)18);
			add((byte)5);
		}
	};

	// list of columns for case 9 - all columns
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_9 = new ArrayList<Byte>(){
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
		}
	};

    /**
     * list of columns for case 10 -   required for visualization org_name,org_website,social_sector,
	 * tech_sector,situation_description,technical_scope
     */
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_10 = new ArrayList<Byte>(){	
		private static final long serialVersionUID = 1L;

		{
			add((byte)0);
			add((byte)4);
			add((byte)15);
			add((byte)16);
			add((byte)18);
			add((byte)21);
		}
	};


	//list of columns for case 11 -  columns required for stemmed data - Situation Description Only
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_11 = new ArrayList<Byte>(){
		private static final long serialVersionUID = 1L;
		{										
			add((byte)26);
		}
	};
	
	/**
	 * list of case 12 -  columns required for stemmed data - Situation Description 
	 * and mission statement
	 */
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_12 = new ArrayList<Byte>(){
		private static final long serialVersionUID = 1L;

		{										
			add((byte)26);
			add((byte)27);
		}
	};



	// Custom Columns Entered
	ArrayList<Byte> EXCEL_SHEET_MAPPER_CASE_CUSTOM = new ArrayList<Byte>();


	//list of Neglected words
	ArrayList<String> TRIMMED_WORD_LIST = new ArrayList<String>(){
		private static final long serialVersionUID = -8738283910303799120L;
		{
			add(",");
			add(".");
			add("/");
			add("(");
		}
	};

	//Regular Expression for Replaced words
	String TRIMMED_CHARACTERS_REGEX = "([.,/()'])";

	//mappings of option and List
	HashMap<Integer, List<Byte>> SHEET_MAPPER = new HashMap<Integer, List<Byte>>(){
		private static final long serialVersionUID = -1706437816912830507L;
		{
			put(1, EXCEL_SHEET_MAPPER_CASE_1);
			put(2, EXCEL_SHEET_MAPPER_CASE_2);
			put(3, EXCEL_SHEET_MAPPER_CASE_3);
			put(4, EXCEL_SHEET_MAPPER_CASE_4);
			put(5, EXCEL_SHEET_MAPPER_CASE_5);
			put(6, EXCEL_SHEET_MAPPER_CASE_CUSTOM);
			put(7, EXCEL_SHEET_MAPPER_CASE_5);
			put(8, EXCEL_SHEET_MAPPER_CASE_8);
			put(9, EXCEL_SHEET_MAPPER_CASE_11);
			put(10, EXCEL_SHEET_MAPPER_CASE_12);
			put(11, EXCEL_SHEET_MAPPER_CASE_10);

		}
	};

	//dictionary of words
	Map<String, String> WORD_DICTIONARY = new HashMap<String, String>();

	//List of unique words
	List<String> UNIQUE_WORD_LIST = new ArrayList<String>();


	//list of Neglected words
	ArrayList<String> NEGLECTED_WORDS_LIST = new ArrayList<String>(){
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

		}
	};
}
