/**
 * 
 * Parse the File Containing Top 100 Words and accordingly includes the info into Appropriate data structure
 * 
 */

package som.file;

import java.io.BufferedReader;
import java.io.FileReader;



import java.io.InputStreamReader;

import som.helper.GenericHelper;
//static import
import static som.constants.IBestWordsFileConstants.fileName;
import static som.constants.IGenericConstants.uniqueWordsList;
import static som.constants.IGenericConstants.bestWordsList;


public class BestWordsFileParser implements IFileWritable{

	

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		
	}
	
	public  void readFromFile(){
		String currentLine = null;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(GenericHelper.getFileFromServerUsingUrl(fileName)));
			while((currentLine = br.readLine()) != null){
				currentLine = currentLine.trim();
				//if(!bestWordsList.contains(currentLine)){
					bestWordsList.add(currentLine);
				//}
				String[] wordSplitArray = currentLine.split(",");
				for(String s : wordSplitArray){
					if(!uniqueWordsList.contains(s)){
						uniqueWordsList.add(s);
					}
				}
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
