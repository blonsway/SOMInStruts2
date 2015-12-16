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
 *   Writes a fixed to  set of predefined files required by the SOM toolbox 
*/


package som.components;


import som.adapter.FileOperationsAdapter;
import som.notifier.SOMNotifier;
import som.observer.SOMObserver;

//static import
import static som.constants.IFileFactoryConstants.DWM_FILE;
import static som.constants.IFileFactoryConstants.UNIT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.WEIGHT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.INPUT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE;


public class SOMFileWriter extends SOMObserver{
	
	public SOMFileWriter(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}

	@Override
	public void update() {
		//calls the Adapter File Writer class to extend file writer functionality of the SOM
		FileOperationsAdapter fileWriterAdapter = new FileOperationsAdapter();
		fileWriterAdapter.writeToFile(INPUT_VECTOR_FILE);
		fileWriterAdapter.writeToFile(UNIT_VECTOR_FILE);
		fileWriterAdapter.writeToFile(WEIGHT_VECTOR_FILE);
		fileWriterAdapter.writeToFile(DWM_FILE);
		fileWriterAdapter.writeToFile(CUSTOM_SOM_OUTPUT_FILE);
		fileWriterAdapter.writeToFile(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE);
	}

}
