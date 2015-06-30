/**
 * 
 * Constants for SOM Matrix Creation
 * 
 */

package som.constants;

public interface IMatrixConstants extends IGenericConstants{
	//SOM matrix X size
	int somMatrixRowSize = 20;

	//SOM matrix Y size
	int somMatrixColumnSize = 14;

	// Its the Kohonen Map (Self- ORganizing Map ) which is the 2-dimensional matrix
	int[][] somMatrix = new int[somMatrixRowSize][somMatrixColumnSize];

	// Its the Corresponding Document Matrix storing the documents Mapped for the above SOM
	String[][] documentMatrix = new String[somMatrixRowSize][somMatrixColumnSize];

	// Its stores the minimum distance of the documents mapped to each element in the SOM
	String[][] minDistanceMatrix = new String[somMatrixRowSize][somMatrixColumnSize];
}
