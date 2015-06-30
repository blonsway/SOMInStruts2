/**
 * 
 * Generate random vector values in absence of input vectors
 * 
 */

package som.randomization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;



import som.helper.VectorHelper;
//static imports
import static som.constants.IGenericConstants.maxRandomNumberLimit;

public class RandomVectorGenerator {
	 
	
	public void generateRandomVectorValues(Map<Integer, List<Integer>> valueMap, int noOfVectors)
	{
		int i, j;
		Random randomNumberGen = new Random();
		//System.out.println("No of Vectors "+noOfVectors);
		for ( i = 0; i < noOfVectors; i++)
		{
			List<Integer>  randomNumberList = new ArrayList<Integer>();
			for ( j = 0; j < VectorHelper.getVectorDimension(); j++)
			{                    
				int randomNumber = randomNumberGen.nextInt(maxRandomNumberLimit);
				randomNumberList.add(randomNumber);                    
			}
			valueMap.put(i, randomNumberList);
			//System.out.println(" Vector Dimension Size "+randomNumberList.size());
		}
	}
	
	
}
