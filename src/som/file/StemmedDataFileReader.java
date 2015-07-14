package som.file;

import static som.constants.IBestWordsFileConstants.fileName;
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

		System.out.println(stemmedRowsList);

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