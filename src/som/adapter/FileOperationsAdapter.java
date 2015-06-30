/**
 * 
 * Providing an interface to communicate with File Handling classes and getting Factory instance
 * 
 */
package som.adapter;

import java.util.List;

import som.beans.VectorData;
import som.factory.FileWriterFactory;
import som.file.ExcelDataSheetReader;
import som.file.IFileWritable;

public class FileOperationsAdapter {
	IFileWritable fileWriter;
	ExcelDataSheetReader excelDataSheetReader;
	


	public FileOperationsAdapter() {
		
	}


	public void writeToFile(String fileType){
		FileWriterFactory fileFactory = new FileWriterFactory();
		fileWriter = fileFactory.getFileWriter(fileType);
		fileWriter.writeToFile();
	}
	
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
