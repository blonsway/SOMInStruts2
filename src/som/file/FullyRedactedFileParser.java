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
 * 		This Class contains methods which will read the Fully redacted excel sheet and 
 * 		accordingly store the data into the input  sheer
 * 
 */

package som.file;

import static som.constants.IGenericConstants.REVISED_INPUT_SHEET_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;


import som.constants.IExcelColumnMappingConstants;
import som.constants.IGenericConstants;
import som.helper.GenericHelper;

public class FullyRedactedFileParser implements IFileWritable{

	
	static public List<String> randomNameList = new ArrayList<String>();
	static public List<String> stemmedMissionStatementList = new ArrayList<String>();
	static public List<String> stateProvinceList = new ArrayList<String>();
	static public List<String> countryList = new ArrayList<String>();
	static public List<String> socialSectorList = new ArrayList<String>();
	static public List<String> techSectorList = new ArrayList<String>();
	static public List<String> stemmedSituationDescriptionList = new ArrayList<String>();
	
	
	@Override
	
	public void writeToFile() {
		try
		{
			FileInputStream inp = new FileInputStream(new File(GenericHelper.getAbsolutePath()+
					IGenericConstants.REVISED_INPUT_SHEET_NAME));
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			
			System.out.println(" count is "+stemmedMissionStatementList.size()); 			
			for (int currentDocumentNumber=1;currentDocumentNumber<stemmedMissionStatementList.size();currentDocumentNumber++)
			{
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_RANDOM_NAME_COLUMN_INDEX);
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_MISSION_STATEMENT_COLUMN_INDEX);
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_STATE_PROVINCE_COLUMN_INDEX);
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_COUNTRY_COLUMN_INDEX);
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_SOCIAL_SECTOR_COLUMN_INDEX);
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_TECH_SECTOR_COLUMN_INDEX);
				createOutputList(sheet,currentDocumentNumber,IExcelColumnMappingConstants.OUTPUT_SITUATION_DESCRIPTION_COLUMN_INDEX);
			}

			FileOutputStream fileOut = new FileOutputStream(GenericHelper.getAbsolutePath()+
					IGenericConstants.REVISED_INPUT_SHEET_NAME);
			wb.write(fileOut);
			fileOut.close();			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}
	
	public  void createOutputList( Sheet sheet, int currentDocumentNumber, int columnIndex)
	{
		Row row = sheet.getRow(currentDocumentNumber);

		switch (columnIndex)
		{
		case IExcelColumnMappingConstants.OUTPUT_RANDOM_NAME_COLUMN_INDEX:
			String randomName = randomNameList.get(currentDocumentNumber);   			
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_RANDOM_NAME_COLUMN_INDEX,randomName);
			break;

		case IExcelColumnMappingConstants.OUTPUT_MISSION_STATEMENT_COLUMN_INDEX:
			String missionStatement = stemmedMissionStatementList.get(currentDocumentNumber);
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_MISSION_STATEMENT_COLUMN_INDEX,missionStatement);
			break;

		case IExcelColumnMappingConstants.OUTPUT_STATE_PROVINCE_COLUMN_INDEX:   			
			String stateProvince = stateProvinceList.get(currentDocumentNumber);
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_STATE_PROVINCE_COLUMN_INDEX,stateProvince);
			break;

		case IExcelColumnMappingConstants.OUTPUT_COUNTRY_COLUMN_INDEX:   			
			String country = countryList.get(currentDocumentNumber);
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_COUNTRY_COLUMN_INDEX,country);
			break;

		case IExcelColumnMappingConstants.OUTPUT_SOCIAL_SECTOR_COLUMN_INDEX:   			
			String socialSector = socialSectorList.get(currentDocumentNumber);
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_SOCIAL_SECTOR_COLUMN_INDEX,socialSector);
			break;

		case IExcelColumnMappingConstants.OUTPUT_TECH_SECTOR_COLUMN_INDEX:
			String techSector = techSectorList.get(currentDocumentNumber);
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_TECH_SECTOR_COLUMN_INDEX,techSector);
			break;

		case IExcelColumnMappingConstants.OUTPUT_SITUATION_DESCRIPTION_COLUMN_INDEX:
			String situationDescriptionText = stemmedSituationDescriptionList.get(currentDocumentNumber);
			fillContents(row,IExcelColumnMappingConstants.OUTPUT_SITUATION_DESCRIPTION_COLUMN_INDEX,situationDescriptionText);   			
			break;
		default:
		}		
	}

	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		try
		{
			CSVReader inputFileReader = new CSVReader(new FileReader(GenericHelper.getAbsolutePath()+
					fileName));
			String [] nextLine;
			String inputFileName = GenericHelper.getAbsolutePath()+fileName;
			System.out.println("Input File path is "+inputFileName);	
			
			randomNameList.clear();
			stemmedMissionStatementList.clear();
			stateProvinceList.clear();
			countryList.clear();
			socialSectorList.clear();
			techSectorList.clear();
			stemmedSituationDescriptionList.clear();
			
			
			//nextLine = inputFileReader.readNext();
			while ((nextLine = inputFileReader.readNext()) != null)
			{	        
				randomNameList.add(nextLine[IExcelColumnMappingConstants.INPUT_RANDOM_NAME_COLUMN_INDEX]);
				stemmedMissionStatementList.add(nextLine[IExcelColumnMappingConstants.INPUT_MISSION_STATEMENT_COLUMN_INDEX]);
				stateProvinceList.add(nextLine[IExcelColumnMappingConstants.INPUT_STATE_PROVINCE_COLUMN_INDEX]);
				countryList.add(nextLine[IExcelColumnMappingConstants.INPUT_COUNTRY_COLUMN_INDEX]);
				socialSectorList.add(nextLine[IExcelColumnMappingConstants.INPUT_SOCIAL_SECTOR_COLUMN_INDEX]);
				techSectorList.add(nextLine[IExcelColumnMappingConstants.INPUT_TECH_SECTOR_COLUMN_INDEX]);
				stemmedSituationDescriptionList.add(nextLine[IExcelColumnMappingConstants.INPUT_SITUATION_DESCRIPTION_COLUMN_INDEX]);
			}

			inputFileReader.close();
			
			 writeToFile();
		}
		catch (Exception e)
		{
			System.out.println("Exception thrown"+e);
			e.printStackTrace();
		}		
	}
	
	public  void fillContents(Row row,int cellNumber,String cellValue)
	{
		//System.out.println("Fill contents cellnumber is "+cellNumber);
		
		try {		
		
		Cell currentCellInExcel = row.getCell(cellNumber);
		
		if (currentCellInExcel == null)
			return;
		currentCellInExcel.setCellType(Cell.CELL_TYPE_STRING);		
		currentCellInExcel.setCellValue(cellValue);
		}
		catch(Exception ex)
		{
		System.out.println("Exception throw"+ex);
		ex.printStackTrace();
		}
	}

}
