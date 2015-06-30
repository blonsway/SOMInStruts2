/**
 * 
 * Creates a Template Vector From the Best Words of Situation Description provided by Prof. Brian Lonsway
 * 
 */
package som.file;

import static som.constants.IGenericConstants.bestWordsList;



import java.io.PrintWriter;

/**
 * @author prashant
 *
 */
public class BestWordsTemplateVectorWriter implements IFileWritable{

	

	@Override
	public void writeToFile() {
		
		try{
			PrintWriter writer = new PrintWriter("MySOM.tv", "UTF-8");
			writer.println("$TYPE template");
			writer.println("$XDIM 2");
			
			writer.println("$VEC_DIM "+ (bestWordsList.size()));

			int i;
			int count = 0;
			for( i = 0 ; i < bestWordsList.size(); i++){				
					writer.println((count++)+ " "+bestWordsList.get(i));
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
