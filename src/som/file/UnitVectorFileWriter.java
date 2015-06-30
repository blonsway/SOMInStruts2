/**
 * 
 * Creates unit Vector File
 * 
 */
package som.file;

import java.io.PrintWriter;

import som.constants.IMatrixConstants;

public class UnitVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.unit", "UTF-8");
			writer.println("$TYPE som");
			writer.println("$GRID_LAYOUT rectangular");
			writer.println("$GRID_TOPOLOGY planar");
			writer.println("$FILE_FORMAT_VERSION 1.2");
			writer.println("$XDIM "+IMatrixConstants.somMatrixRowSize);
			writer.println("$YDIM "+IMatrixConstants.somMatrixColumnSize);
			for (int j = 0; j <IMatrixConstants.somMatrixColumnSize; j++)
			{
				for (int i = 0; i <IMatrixConstants.somMatrixRowSize; i++)
				{
					writer.println("$POS_X "+i);
					writer.println("$POS_Y "+j);
					if(IMatrixConstants.documentMatrix[i][j] != null){
						String[] mappedDocNumberString =IMatrixConstants.documentMatrix[i][j].split(",");
						String[] mappedVectorDistanceString =IMatrixConstants.minDistanceMatrix[i][j].split(",");

						if(mappedDocNumberString != null && mappedDocNumberString.length > 0 &&
								mappedVectorDistanceString != null && mappedVectorDistanceString.length > 0){
							writer.println("$NR_VEC_MAPPED "+mappedDocNumberString.length);
							writer.println("$MAPPED_VECS");
							for(String mappedDocNumber : mappedDocNumberString){
								writer.println("Document Number : "+mappedDocNumber);
							}
							writer.print("$MAPPED_VECS_DIST ");
							for(String mappedDistVector : mappedVectorDistanceString){
								writer.print(mappedDistVector+" ");
							}
							writer.println();
						}
					}
					else{
						writer.println("$NR_VEC_MAPPED 0");
					}

				}
			}

			writer.close();
		}
		catch(Exception e){
			System.out.println(e);
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
