/**
 * 
 * Gets the Distance between two vectors
 * 
 */

package som.bmufinder;

import java.util.List;

import som.helper.VectorHelper;

public class SOMBestMatchingUnitFinder {
	
	public double computeEuclideanDistance(List<Integer> inputVector, List<Integer> SOMVector)
	{
		double squareDistance = 0;
		double squareRootDistance = 0;


		for (int i = 0; i < VectorHelper.getVectorDimension(); i++)
		{
			

			squareDistance += inputVector.get(i) * SOMVector.get(i);
		}
		squareRootDistance = Math.sqrt(squareDistance);
		return squareRootDistance;
	}
	
	
}
