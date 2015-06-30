/**
 * 
 * Creates Weight Vector Files
 * 
 */
package som.file;

import java.io.PrintWriter;
import java.util.List;

import som.constants.IMatrixConstants;
import som.helper.VectorHelper;

public class WeightVectorFileWriter implements IFileWritable{

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.wgt", "UTF-8");
			writer.println("$TYPE som");
			writer.println("$GRID_LAYOUT rectangular");
			writer.println("$GRID_TOPOLOGY planar");
			writer.println("$XDIM "+IMatrixConstants.somMatrixRowSize);
			writer.println("$YDIM "+IMatrixConstants.somMatrixColumnSize);
			writer.println("$ZDIM 1");
			writer.println("$VEC_DIM "+VectorHelper.getVectorDimension());
			for (int i = 0; i <IMatrixConstants.somMatrixRowSize; i++)
			{
				for (int j = 0; j <IMatrixConstants.somMatrixColumnSize; j++)
				{
					int position =IMatrixConstants.somMatrix[i][j];
					List<Integer>vector =IMatrixConstants.randomSOMVectorMap.get(position);
					for(Integer distance : vector){
						writer.print(distance+" ");
					}
					writer.println("SOM_MAP_MySOM_("+i+"/"+j+"/0)");
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
