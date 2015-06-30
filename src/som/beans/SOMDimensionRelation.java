/**
 * 
 * Bean Class to store SOM Node's Dimension Data
 * 
 */
package som.beans;

public class SOMDimensionRelation{
	
	public SOMDimensionRelation(int xPosition, int yPosition, Double distance) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.distance = distance;
	}
	
	private int xPosition;
	private int yPosition;
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	private Double distance;
}
