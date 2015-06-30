/**
 * 
 * Vector To create Custom unit output file
 * 
 */

package som.file;

import java.io.PrintWriter;

import som.beans.VectorData;
import static som.constants.IMatrixConstants.documentMatrix;
import static som.constants.IGenericConstants.inputValuesMap;



public class SemanticOutputFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter pw = new PrintWriter("custom_out.unit", "UTF-8");
			
			for(int i = 0 ; i < documentMatrix.length;i++){
				for(int j =0 ; j< documentMatrix[i].length ; j++){
					pw.println("bl_out_("+i+"/"+j+")");
					String docNoString = documentMatrix[i][j];
					if(docNoString != null & docNoString != "" && docNoString.length() > 0){
						String[] docNos = docNoString.split(",");
						String distString = documentMatrix[i][j];
						String[] distances = distString.split(",");
						pw.println("MAPPED VECTORS:");
						for(int k = 0 ; k < docNos.length ; k++){
							pw.println("Dcoument_Number_"+docNos[k]+" (dist: "+distances[k]+")");
							VectorData vectorData = inputValuesMap.get(Integer.parseInt(docNos[k]));
							pw.println("Project Title : "+vectorData.getProjectTitle());
							pw.println("Situation Description : "+vectorData.getSituationDescription());

						}
					}
					
				}
			}
			pw.close();
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
