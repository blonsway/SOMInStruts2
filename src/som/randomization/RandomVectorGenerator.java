/*
 *  
 *	Tech For Good Portal 
 *	Proof of Concept
 *	J P Morgan Chase Technology Center at Syracuse University
 * 
 *	Authored by: 
 *	Last Revision: 1.0
 *	Last Revised by: Prashant Patel
 *
 *	Version 1.0
 *
 *  	Principal Investigators
 *		Kathleen Brandt
 *		Brian Lonsway
 *		Steve Masiclat
 *
 * 	Contributors
 *		Lead Java Developer & Research Assistant: Prashant Patel
 *		Java Developer & Research Assistant: Ravi Nagendra
 *		Python Developer: Brian Lonsway
 * 
 *	This document is a part of the source code and related artifacts
 * 	for the Tech For Good Portal, an open source proof of concept developed
 *	for J P Morgan Chase.
 *
 * 	Copyright © 2015, jointly held by 
 *		Kathleen Brandt, Brian Lonsway, and Steve Masiclat; 
 *		Syracuse University; and
 *		J P Morgan Chase.
 *
 *   	This file is part of TechForGoodPortal.
 *
 *    	TechForGoodPortal is free software: you can redistribute it and/or modify
 *    	it under the terms of the GNU General Public License version 3 as published by
 *    	the Free Software Foundation.
 *
 *    	TechForGoodPortal is distributed in the hope that it will be useful,
 *    	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    	GNU General Public License for more details.
 *
 *    	See <http://www.gnu.org/licenses/> for a copy of the GNU General Public License.
 *    	
 *
 * 		Generate random vector values in absence of input vectors 
 * 
 */



package som.randomization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;



import som.helper.VectorHelper;
//static imports
import static som.constants.IGenericConstants.MAX_RANDOM_NUMBER_LIMIT;

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
				int randomNumber = randomNumberGen.nextInt(MAX_RANDOM_NUMBER_LIMIT);
				randomNumberList.add(randomNumber);                    
			}
			valueMap.put(i, randomNumberList);
			//System.out.println(" Vector Dimension Size "+randomNumberList.size());
		}
	}
	
	
}
