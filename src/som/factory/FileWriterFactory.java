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
 * Class containing factory method to return the instance of FileType
 *
 * 
 */


package som.factory;

import som.file.BLOutputFileParser;
import som.file.BestWordsFileGenerator;
import som.file.BestWordsFileParser;
import som.file.BestWordsTemplateVectorWriter;
import som.file.DWMFileWriter;
import som.file.FullyRedactedFileParser;
import som.file.IFileWritable;
import som.file.InputVectorFileWriter;
import som.file.SemanticOutputFileWriter;
import som.file.StemmedDataFileReader;
import som.file.TemplateVectorFileWriter;
import som.file.UnitVectorFileWriter;
import som.file.WeightVectorFileWriter;
import som.file.VisualDataJSONWriter;
import som.file.DWMFileReader;

//static imports - Java is awesome
import static som.constants.IFileFactoryConstants.DWM_FILE;
import static som.constants.IFileFactoryConstants.UNIT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.WEIGHT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.INPUT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.VISUAL_DATA_JSON_FILE_WRITER;
import static som.constants.IFileFactoryConstants.DWM_FILE_READER;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE_GENERATOR;
import static som.constants.IFileFactoryConstants.STEMMED_DATA_FILE_READER;
import static som.constants.IFileFactoryConstants.FULLY_REDACTED_FILE_PARSER;



public class FileWriterFactory {

	public IFileWritable getFileWriter(String fileType){
		if(DWM_FILE.equalsIgnoreCase(fileType)){
			return new DWMFileWriter();
		}
		else if(UNIT_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new UnitVectorFileWriter();
		}
		else if(WEIGHT_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new WeightVectorFileWriter();
		}
		else if(INPUT_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new InputVectorFileWriter();
		}
		else if(CUSTOM_SOM_OUTPUT_FILE.equalsIgnoreCase(fileType)){
			return new SemanticOutputFileWriter();
		}
		else if(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE.equalsIgnoreCase(fileType)){
			return new BLOutputFileParser();
		}
		else if(TEMPLATE_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new TemplateVectorFileWriter();
		}
		else if(BEST_WORDS_FILE.equalsIgnoreCase(fileType)){
			return new BestWordsFileParser();
		}
		else if(BEST_WORDS_TEMPLATE_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new BestWordsTemplateVectorWriter();
		}
		else if(VISUAL_DATA_JSON_FILE_WRITER.equalsIgnoreCase(fileType)){
			return new VisualDataJSONWriter();
		}
		else if(DWM_FILE_READER.equalsIgnoreCase(fileType)){
			return new DWMFileReader();
		}
		else if(BEST_WORDS_FILE_GENERATOR.equalsIgnoreCase(fileType)){
			return new BestWordsFileGenerator();
		}
		else if(STEMMED_DATA_FILE_READER.equalsIgnoreCase(fileType)){
			return new StemmedDataFileReader();
		} 
		else if(FULLY_REDACTED_FILE_PARSER.equalsIgnoreCase(fileType)){
			return new FullyRedactedFileParser();
		}
		else return null;
		
	}
}
