/**
 * 
 * Java Comparator class to compare SOM Node's Distance with other SOM Node
 * and can be used in Collections.sort() to sort List of SOMDimension objects
 * 
 */
package som.comparators;

import java.util.Comparator;

import som.beans.SOMDimensionRelation;

public class SOMDistanceComparator implements Comparator<SOMDimensionRelation>{

	@Override
	public int compare(SOMDimensionRelation object1, SOMDimensionRelation object2) {
		// TODO Auto-generated method stub
		return object1.getDistance().compareTo(object2.getDistance());
	}

}
