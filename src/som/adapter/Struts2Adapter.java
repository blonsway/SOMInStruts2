package som.adapter;

//static imports
import static som.constants.IGenericConstants.inputValuesMap;
import static som.constants.IGenericConstants.INPUT_VECTOR_GENERATION_OPTION;
import som.helper.GenericHelper;
import som.helper.MenuDrivenVectorGenerator;

public class Struts2Adapter {

	/**
	 * Calls the SOM old code process to generate the input vectors
	 * 
	 * @param secondOption
	 * @return
	 */
	public static String getInputVectorFileGenerationStatus( int secondOption){		
		
		return MenuDrivenVectorGenerator.createInputVectorFilesForStruts2(inputValuesMap, INPUT_VECTOR_GENERATION_OPTION
				, secondOption);
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
