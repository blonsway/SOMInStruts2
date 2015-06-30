/**
 * 
 * Class for creating Template Vector Files
 * 
 */
package som.file;

import static som.constants.IGenericConstants.uniqueWordsList;




import java.io.PrintWriter;


public class TemplateVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.tv", "UTF-8");
			writer.println("$TYPE template");
			writer.println("$XDIM 2");
			
			writer.println("$VEC_DIM "+ (uniqueWordsList.size()*uniqueWordsList.size()));

			int i,j;
			int count = 0;
			for( i = 0 ; i < uniqueWordsList.size(); i++){
				for(j = 0; j <uniqueWordsList.size() ; j++){
					String word1 = uniqueWordsList.get(i);
					String word2 = uniqueWordsList.get(j);
					writer.println(count+ " "+word1+"-"+word2);
				}
			}
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
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
