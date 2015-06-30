package som.beans;

//static import
import static som.constants.IVisualizationConstants.INITIAL_DISPLACEMENT;

public class VectorCoordinate {
	private double x;
	private double y;	
	
	
	
	public boolean isOverlapping(double x, double y){
		if(Math.abs(this.x - x ) < INITIAL_DISPLACEMENT && Math.abs(this.y - y ) < INITIAL_DISPLACEMENT ){
			return true;
		}
		else{
			return false;
		}
	}



	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	@Override
	public String toString() {
		return "VectorCoordinate [x=" + x + ", y=" + y + "]";
	}
	
}
