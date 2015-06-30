/**
 * 
 * Constants used to generate DWM Mapping File
 * 
 */

package som.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


import som.beans.SOMDimensionRelation;

public interface IDWMFileConstants extends IMatrixConstants{
	//for dwm file <Vector Number, SOM Matrix Element Relation info with this vector number>
	Map<Integer, PriorityQueue<SOMDimensionRelation>> dwmInfoMap = new HashMap<Integer, PriorityQueue<SOMDimensionRelation>>();	

	//Number of Winners in DWM File
	int numberOfWinners = somMatrixColumnSize * somMatrixRowSize;	

	// File format version of DWM File
	String dwmFileVersion = "1.1";

	//metric used to computer Minimum distance 
	String minimumDistanceComputationMetric = "at.tuwien.ifs.somtoolbox.layers.metrics.L2Metric";
	
	//Filename to be used for read for visualization
	String VISUAL_FILE_NAME = "output/MySOM.dwm";
	
	

}
