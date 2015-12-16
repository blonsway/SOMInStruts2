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
 * 		Reads in the input file and places the stemmed data into appropriate column
 * 
 */

package som.file;

import static som.constants.IBestWordsFileConstants.FILENAME;
import static som.constants.IGenericConstants.REVISED_INPUT_SHEET_NAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import som.constants.IGenericConstants;
import som.helper.GenericHelper;

public class StemmedDataFileReader implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String line = "";
		List<String> stemmedRowsList = new ArrayList<String>();
		try{
			// Fetching the stemmed File
			br = new BufferedReader(new InputStreamReader(GenericHelper.getFileFromServerUsingUrl(fileName)));
			while ((line = br.readLine()) != null) {	 
				line = StringUtils.replace(line, "|", " ");
				stemmedRowsList.add(line);	 
			}
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		} 

		//System.out.println(stemmedRowsList);

		writeIntoInputDataSheet(stemmedRowsList);

	}

	public void writeIntoInputDataSheet( List<String> wordsList){
		XSSFWorkbook workbook = null;
		try{
			System.out.println("Writing into Input File");
			FileInputStream file = new FileInputStream(new File(GenericHelper.getAbsolutePath()+REVISED_INPUT_SHEET_NAME));
			//FileInputStream file = new FileInputStream(new File(REVISED_INPUT_SHEET_NAME));
			//File file = new File(REVISED_INPUT_SHEET_NAME);
			
			//Get the workbook instance for XLS file 
			workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			while(rowIterator.hasNext()){
				Row row = rowIterator.next(); 
				if(row.getRowNum() == 0){
					continue;
				}

				Cell cell = row.getCell(IGenericConstants.STEMMED_DATA_COLUMN_NUMBER);
				if(wordsList.size() > row.getRowNum()){
					cell.setCellValue(wordsList.get( row.getRowNum()));
				}				
			}

			file.close();		     

			System.out.println("Writing to the file on the server");
			FileOutputStream outFile =new FileOutputStream(new File(GenericHelper.getAbsolutePath()+
					REVISED_INPUT_SHEET_NAME));
			workbook.write(outFile);
			System.out.println("File Writing Complete");
			outFile.close();

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		StemmedDataFileReader stemmedDataFileReader = new StemmedDataFileReader();
		stemmedDataFileReader.readFromFile("stemmedcsv.csv");		
	}

}