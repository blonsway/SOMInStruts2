package som.adapter;

//static imports
import static som.constants.IGenericConstants.inputValuesMap;
import static som.constants.IGenericConstants.INPUT_VECTOR_GENERATION_OPTION;

import java.util.List;

import som.constants.IGenericConstants;
import som.helper.GenericHelper;
import som.helper.MenuDrivenVectorGenerator;

public class Struts2Adapter {

	/**
	 * Calls the SOM old code process to generate the input vectors
	 * 
	 * @param secondOption
	 * @return
	 */
	public static String getInputVectorFileGenerationStatus(List<String> columnList){		
		
		return MenuDrivenVectorGenerator.createInputVectorFilesForStruts2(inputValuesMap, INPUT_VECTOR_GENERATION_OPTION
				, IGenericConstants.stemmendBestWordFileOptionForSituationDescription, columnList);
	}
	
	/**
	 * Calls the SOM old code process to visualize  the SOM output
	 * 
	 * @return
	 */
	public static String getVisualizationOptionStatus(){		
		
		return MenuDrivenVectorGenerator.createJsonDataForVisualization();
	}
}
