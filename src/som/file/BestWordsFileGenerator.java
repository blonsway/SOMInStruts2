package som.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;






import som.constants.IBestWordsFileConstants;
import som.helper.GenericHelper;

public class BestWordsFileGenerator implements IFileWritable{



	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub


	}


	/**
	 * creates the bests words text file
	 * @param wordList
	 */
	public void createVectorDimensionAndWriteIntoFile(List<String[]> wordList){
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(GenericHelper.getAbsolutePath()+IBestWordsFileConstants.fileName);
			for(String[]words : wordList){
				pw.println(words[0]+","+words[1]);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(pw != null){
				pw.close();
			}
		}
	}

	@Override
	public void readFromFile() {
		

	}

	/**
	 *  main function used for testing
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestWordsFileGenerator cf = new BestWordsFileGenerator();
		cf.readFromFile("output_cooccurrOfmergedSitDescOnly.csv");
		System.out.println("Execution terminated successfully");
	}


	@Override
	public void readFromFile(String fileName) {
		BufferedReader br = null;
		String line = "";
		List<String[]> wordList = new ArrayList<String[]>();
		try{ 
			// TODO Auto-generated method stub
			br = new BufferedReader(new FileReader(new File(GenericHelper.getAbsolutePath()+fileName)));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] words = line.split(",");
				wordList.add(words);
			}
			createVectorDimensionAndWriteIntoFile(wordList);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}



}
