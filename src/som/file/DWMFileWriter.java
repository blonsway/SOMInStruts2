/**
 * 
 * Class forr writing into the DWM File
 * 
 */

package som.file;

import java.io.PrintWriter;
import java.util.Map;
import java.util.PriorityQueue;

import som.beans.SOMDimensionRelation;
import som.constants.IDWMFileConstants;
import som.constants.IGenericConstants;

public class DWMFileWriter implements IFileWritable {

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter writer = new PrintWriter("MySOM.dwm", "UTF-8");
			writer.println("$FILE_FORMAT_VERSION "+IDWMFileConstants.dwmFileVersion);
			writer.println("$NUM_WINNERS  "+IDWMFileConstants.numberOfWinners);
			writer.println("$NUM_VECTORS "+IGenericConstants.inputValuesMap.size());
			writer.println("$METRIC "+IDWMFileConstants.minimumDistanceComputationMetric);

			for(Map.Entry<Integer, PriorityQueue<SOMDimensionRelation>> entry : IDWMFileConstants.dwmInfoMap.entrySet()){
				int documentNumber = entry.getKey();
				writer.println("Document Number : "+documentNumber);

				PriorityQueue<SOMDimensionRelation> dimensionInfoQueue  = entry.getValue();
				for(SOMDimensionRelation somDimensionInfo : dimensionInfoQueue){
					writer.print(somDimensionInfo.getxPosition()+" ");
					writer.print(somDimensionInfo.getyPosition()+" ");
					writer.print(somDimensionInfo.getDistance()+" ");
				}
				writer.println();
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
