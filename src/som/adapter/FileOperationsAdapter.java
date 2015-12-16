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
 *   This file provides an interface to communicate with File Handling classes 
 *   via Factory method
*/

package som.adapter;

import java.util.List;

import som.beans.VectorData;
import som.factory.FileWriterFactory;
import som.file.ExcelDataSheetReader;
import som.file.IFileWritable;

public class FileOperationsAdapter {
	//handle to hold instances performing File operation 
	IFileWritable fileWriter;
	//handle to hold instances performing column mapping operation from Excel shet
	ExcelDataSheetReader excelDataSheetReader;
	
	
	public FileOperationsAdapter() {
		
	}

	//writes data into the File required for SOM generation
	public void writeToFile(String fileType){
		FileWriterFactory fileFactory = new FileWriterFactory();
		fileWriter = fileFactory.getFileWriter(fileType);
		fileWriter.writeToFile();
	}
	
	//
	public List<VectorData> getVectorDataListFromExcelSheet(int option){
		this.excelDataSheetReader = new ExcelDataSheetReader();
		return this.excelDataSheetReader.getInputDataSheet(option) ;
	}
	
	public StringBuffer getTotalNoOfWords(){
		return this.excelDataSheetReader.getCombinedWords();
	}
	
	public void readFromFile(String fileType){
		FileWriterFactory fileFactory = new FileWriterFactory();
		fileWriter = fileFactory.getFileWriter(fileType);
		fileWriter.readFromFile();
	}
	
	public void readFromFile(String fileType, String fileName){
		FileWriterFactory fileFactory = new FileWriterFactory();
		fileWriter = fileFactory.getFileWriter(fileType);
		fileWriter.readFromFile(fileName);
	}
	
	
}
