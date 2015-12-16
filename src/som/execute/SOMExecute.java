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
 * 
 * This is the entry point of the program. The Design is using Observer pattern which notifies 
 * its observers on receiving the inputs
 * 
 */


package som.execute;

//project packages imports

import som.components.SOMFileWriter;
import som.components.SOMGUIViewer;
import som.components.SOMMappedDocumentPrinter;
import som.components.SOMMatrixCreater;
import som.components.SOMVectorDocumentMapper;
import som.helper.MenuDrivenVectorGenerator;
import som.notifier.SOMNotifier;
import som.adapter.FileOperationsAdapter;
import som.visualization.DocumentPositionCalculator;
//static imports
import static som.constants.IGenericConstants.INPUT_VALUES_MAP;
import static som.constants.IGenericConstants.VISUAL_OPTION;
import static som.constants.IGenericConstants.IS_TRAINING_REQUIRED;
import static som.constants.IFileFactoryConstants.VISUAL_DATA_JSON_FILE_WRITER;
import static som.constants.IFileFactoryConstants.DWM_FILE_READER;



public class SOMExecute{


	public static void main(String[] args)
	{
		
		// Notifier object which is responsible for notifying the observers
		SOMNotifier somNotifier = new SOMNotifier();
		
		// logic to generate InputValues
		int firstOption =MenuDrivenVectorGenerator.storeInputVectorByMenuSelectionAndReturnTrainingOption(INPUT_VALUES_MAP);

		 //option to run our own custom Algorithm
		if(firstOption == 1){
			//register the observers to noNotifier
			new SOMMatrixCreater(somNotifier);
			new SOMVectorDocumentMapper(somNotifier,IS_TRAINING_REQUIRED);
			new SOMMappedDocumentPrinter(somNotifier);
			new SOMFileWriter(somNotifier);
			new SOMGUIViewer(somNotifier);
			
			somNotifier.notifyObseversOnReceiveInputVectors();
		}
		
		if(firstOption == VISUAL_OPTION){
			 new FileOperationsAdapter().readFromFile(DWM_FILE_READER);
			 new DocumentPositionCalculator().storeTraingularCoordinates();
			 new FileOperationsAdapter().writeToFile(VISUAL_DATA_JSON_FILE_WRITER);

		}

	}


}

