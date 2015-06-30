/**
 * Helper class to be used by input vectors to get its dimensiona
 * 
 */
package som.helper;

import static som.constants.IGenericConstants.randomSOMVectorMap;
import static som.constants.IMatrixConstants.somMatrix;

import java.util.List;

import som.beans.VectorData;
import som.constants.IMatrixConstants;

public class VectorHelper {
	
	
	public static int getVectorDimension(){
		int vectorDimensions = 0;
		if(IMatrixConstants.inputValuesMap.get(0) != null){
			VectorData vectorData = IMatrixConstants.inputValuesMap.get(0);
			List<Integer> dimensions = vectorData.getVector();
			vectorDimensions = dimensions.size();
		}
		return vectorDimensions;
	}
	
	public static List<Integer> getSOMVectorByPosition(int iPos, int jPos){
		int vectorPosition = somMatrix[iPos][jPos];
		List<Integer> somVector = randomSOMVectorMap.get(vectorPosition);
		return somVector;
	}
}
