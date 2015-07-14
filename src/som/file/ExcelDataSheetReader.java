/**
 * 
 * This Class contains methods which will reads the excel sheet and accordingly store the input colukn data 
 * 
 */
package som.file;

//static import
import static som.constants.IGenericConstants.REVISED_INPUT_SHEET_NAME;
import static som.constants.IGenericConstants.sheetMapper;
import static som.constants.IGenericConstants.MAX_NO_OF_ROWS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import som.beans.VectorData;
import som.helper.GenericHelper;


public class ExcelDataSheetReader implements IFileWritable{
	private StringBuffer combinedWords = new StringBuffer("");
	
	
	public StringBuffer getCombinedWords() {
		return combinedWords;
	}


	public void setCombinedWords(StringBuffer combinedWords) {
		this.combinedWords = combinedWords;
	}


	/**
	 * 
	 * @param option
	 * @return
	 */
	public List<VectorData> getInputDataSheet(int option){
	
		List<VectorData> vectorDataList = new ArrayList<VectorData>();
		createVectorDataList(vectorDataList, option);
		return vectorDataList;
	}

	
	/**
	 * 
	 * @param vectorDataList
	 * @param option
	 */
	private void createVectorDataList(List<VectorData> vectorDataList, int option){
		List<Byte> columnList = sheetMapper.get(option);
		System.out.println("Selected Column Numbers are "+ columnList);
		populateValuesIntoListFromFileAndAdditToWordDictionary(vectorDataList, columnList);
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell){
		String cellValue = "";
		if(cell != null){
			switch(cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue()+"";

				break;
			case Cell.CELL_TYPE_NUMERIC:        	
				cellValue = cell.getNumericCellValue() + "";
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue() + "";
				break;
			}
		}
		return cellValue;
	}

	
	

	/**
	 * get VectorData Class object
	 * @param columnList
	 * @param row
	 * @return
	 */
	private VectorData getPopulatedVectorData(List<Byte> columnList, Row row){
		VectorData vectorData = new VectorData();
		
		//necessary values  for input
		Cell cellTitle = row.getCell(17);
		String cellValueTitle = getCellValue(cellTitle);
		vectorData.setProjectTitleForBLParser(cellValueTitle);
		
		Cell cellDesc = row.getCell(18);
		String cellValueDesc = getCellValue(cellDesc);
		vectorData.setSituationDescriptionForBLParser(cellValueDesc);
		
		for(byte pos : columnList){
			
			Cell cell = row.getCell(pos);
			String cellValue = getCellValue(cell);
			
			
			
			if(cellValue != "" && cellValue != null){				
				
				combinedWords.append(cellValue).append(" ");

				
				if(pos == 0){//A
					vectorData.setOrganizatioName(cellValue);
				}
				else if(pos == 1){//B
					vectorData.setOrganizationType(cellValue);
				}
				else if(pos == 2){//C
					vectorData.setFocusArea(cellValue);
				}
				else if(pos == 3){//D
					vectorData.setRandomName(cellValue);
				}
				else if(pos == 4){//E
					vectorData.setOrganizationWebsite(cellValue);
				}				
				else if(pos == 5){//F
					vectorData.setMissionStatement(cellValue);
				}
				else if(pos == 6){//G
					vectorData.setRedactedMissionStatement(cellValue);
				}				
				else if(pos == 7){//H
					vectorData.setSizeOfOrganization(cellValue);
				}				
				else if(pos == 8){//I
					vectorData.setOrientationReach(cellValue);
				}
				else if(pos == 9){//J
					vectorData.setReach(cellValue);
				}
				else if(pos == 10){//K
					vectorData.setGeographicalScope(cellValue);
				}				
				else if(pos == 11){//L
					vectorData.setTargetAudience(cellValue);
				}
				else if(pos == 12){//M
					vectorData.setCity(cellValue);
				}
				else if(pos == 13){//N
					vectorData.setState(cellValue);
				}
				else if(pos == 14){//O
					vectorData.setCountry(cellValue);
				}
				else if(pos == 15){//P
					vectorData.setSocialSector(cellValue);
				}
				else if(pos == 16){//Q
					vectorData.setTechSector(cellValue);
				}				
				else if(pos == 17){//R
					vectorData.setProjectTitle(cellValue);
				}
				else if(pos == 18){//S
					vectorData.setSituationDescription(cellValue);
				}				
				else if(pos == 19){//T
					vectorData.setRedactedSituationDescription(cellValue);
				}				
				else if(pos == 20){//U
					vectorData.setOnsite(cellValue);
				}				
				else if(pos == 21){//V
					vectorData.setTechnicalScope(cellValue);			
				}
				else if(pos == 22){//W
					vectorData.setSkillsNeeded(cellValue);
				}
				else if(pos == 23){//X
					vectorData.setPortalRelationship(cellValue);
				}
				else if(pos == 24){//Y
					vectorData.setEstimatedProjectHours(cellValue);
				}
				else if(pos == 25){//Z
					vectorData.setProjectOverView(cellValue);
				}
				else if(pos == 26){//AA
					vectorData.setStemmedSituationDescription(cellValue);
					System.out.println(cellValue);
				}
				else if(pos == 27){//AB
					vectorData.setStemmedSituationDescriptionAndMissionStatement(cellValue);
				}
				
				
			}
		}
		
		return vectorData;
	}
	
	/**
	 * 
	 * @param vectorDataList
	 * @param columnList
	 */
	private void populateValuesIntoListFromFileAndAdditToWordDictionary(List<VectorData> vectorDataList,
			List<Byte> columnList){
		XSSFWorkbook workbook = null;
		try {

			//InputStream file = GenericHelper.getFileFromServerUsingUrl(REVISED_INPUT_SHEET_NAME);
			
			FileInputStream file = new FileInputStream(new File(GenericHelper.getAbsolutePath()+REVISED_INPUT_SHEET_NAME));

			//Get the workbook instance for XLS file 
			workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);


			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			int rowCount = 0;
			
			while(rowIterator.hasNext() && rowCount < MAX_NO_OF_ROWS) {
				
				Row row = rowIterator.next(); 
				if(row.getRowNum() == 0){
					continue;
				}
				
				VectorData vectorData = getPopulatedVectorData(columnList, row);
				vectorDataList.add(vectorData);
				rowCount++;
				
			}
			
			
			file.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			
		}
	}



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
		
	}
}
