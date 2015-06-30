/**
 * 
 * Class for reating input vector considering in  String form
 * 
 */
package som.file;

import java.io.PrintWriter;


import java.util.List;
import java.util.Map;

import som.beans.VectorData;
import som.helper.VectorHelper;
//static import
import static som.constants.IGenericConstants.inputValuesMap;

public class InputVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.tfxidf", "UTF-8");
			writer.println("$TYPE vec_tfxidf");
			writer.println("$XDIM "+inputValuesMap.size());
			writer.println("$YDIM 1");
			writer.println("$VEC_DIM "+ (VectorHelper.getVectorDimension()));

			for(Map.Entry<Integer, VectorData> vectorEntry : inputValuesMap.entrySet()){
				int documentNumber = vectorEntry.getKey();
				VectorData inputVector = vectorEntry.getValue();
				if(inputVector != null && inputVector.getVector() != null && ! inputVector.getVector().isEmpty()){
					List<Integer> vector = inputVector.getVector();
					for(Integer i : vector){
						writer.print(i+" ");
					}
					writer.print("Document_Number_"+documentNumber);
					writer.println();
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
