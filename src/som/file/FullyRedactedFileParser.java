package som.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import au.com.bytecode.opencsv.CSVReader;


import som.constants.IExcelColumnMappingConstants;
import som.constants.IGenericConstants;
import som.helper.GenericHelper;

public class FullyRedactedFileParser implements IFileWritable{

	
	public static List<String> randomNameList = new ArrayList<String>();
	public static List<String> stemmedMissionStatementList = new ArrayList<String>();
	public static List<String> stateProvinceList = new ArrayList<String>();
	public static List<String> countryList = new ArrayList<String>();
	public static List<String> socialSectorList = new ArrayList<String>();
	public static List<String> techSectorList = new ArrayList<String>();
	public static List<String> stemmedSituationDescriptionList = new ArrayList<String>();
	
	
	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try
		{
			FileInputStream inp = new FileInputStream(new File(GenericHelper.getAbsolutePath()+
					IGenericConstants.REVISED_INPUT_SHEET_NAME));		

			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = (Sheet) wb.getSheetAt(0);

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

			FileOutputStream fileOut = new FileOutputStream("__150423_mergedData_readyForTraining - Copy.xlsx");
			wb.write(fileOut);
			fileOut.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e){
			e.printStackTrace();
		}
	}
	
	public static void createOutputList( Sheet sheet, int currentDocumentNumber, int columnIndex)
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
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillContents(Row row,int cellNumber,String cellValue)
	{
		Cell currentCellInExcel = row.getCell(cellNumber);
		currentCellInExcel.setCellType(Cell.CELL_TYPE_STRING);		
		currentCellInExcel.setCellValue(cellValue);
	}

}
