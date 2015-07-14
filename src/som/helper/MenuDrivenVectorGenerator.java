/**
 * 
 * Helper class for Menu Drive utility
 * 
 */

package som.helper;




import static som.constants.IFileFactoryConstants.DWM_FILE_READER;
import static som.constants.IFileFactoryConstants.VISUAL_DATA_JSON_FILE_WRITER;
import static som.constants.IGenericConstants.inputValuesMap;
import static som.constants.IGenericConstants.maxNoOfOptions;
import static som.constants.IGenericConstants.customColumnOption;
import static som.constants.IGenericConstants.visualOption;
import static som.constants.IGenericConstants.excelSheetMapperListCaseCustom;









import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;




import com.opensymphony.xwork2.ActionSupport;

import som.adapter.FileOperationsAdapter;
import som.beans.VectorData;
import som.visualization.DocumentPositionCalculator;

public class MenuDrivenVectorGenerator extends ActionSupport{

	private static void displaySecondMenu(){
		System.out.println("Enter 1 to train SOM by  vectors of organization name, "
				+ "organization focus area, mission statement, geographical scope, "
				+ "target audience, city+state, project title, situation description, technical scope");
		System.out.println("Enter 2 to train SOM by  vectors of mission statement, "
				+ "project title, situation description");
		System.out.println("Enter 3 to train SOM by vectors of organization name, city+state, "
				+ "mission statement, project title, situation description");
		System.out.println("Enter 4 to train SOM by vectors of Situation Description, Target Audience and Technical Scope");
		System.out.println("Enter 5 to train SOM by vectors of Situation Description");
		System.out.println("Enter 6 to train SOM by vectors of your Custom Column Nos");
		System.out.println("Enter 7 to train SOM by best 100 words Provided for Situation Description");
		System.out.println("Enter 8 to train SOM by best 100 words Provided for Situation Description"
				+ "and Mission Statement");
		System.out.println("Enter 9 to train SOM by best stemmed words Provided for Situation Description");
		System.out.println("Enter 10 to train SOM by best stemmed words Provided for Situation Description"
				+ "and Mission Statement");


	}

	private static void displayFirstMenu(){
		System.out.println("Enter 1 to run Our Customized SOM Algorithm. If you are selecting this option, then please"
				+ " make sure to avoid selecting large data. This option will automatically trigger SOM Viewer");
		System.out.println("Enter 2 to get Input Vector Files For SOM Toolbox");
		System.out.println("Enter 3 to generate semantic Output File.");	
		System.out.println("Enter 4 to view the visualized output based on DWM File (stored in project as visual.dwm)");


	}

	/**
	 * 
	 * @return the Integer input from the console
	 */
	private static int getIntegerInput(){
		int input = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String s = br.readLine();
			input = Integer.parseInt(s);
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		return input;
	}

	/**
	 * 
	 * @return the String input from the console
	 */
	private static String getStringInput(){
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			input  = br.readLine();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		return input;
	}



	/**
	 * 
	 * @param input
	 */
	public static void createInputVectorsBasedOnOption(int firstOption, int secondOption , List<String> columnList){
		if(secondOption > maxNoOfOptions || secondOption < 0){
			System.out.println("Option not present in the list");		
		}
		else{
			InputVectorGenerationHelper.createInputVectors(inputValuesMap, firstOption,secondOption, columnList);
		}
	}

	/**
	 * 
	 * store column numbers as Custom Input
	 * 
	 * @param customColumnInput
	 */
	public static void handleCustomCoulumnInput(int  inputForSecondMenu){
		try{
			if(inputForSecondMenu == customColumnOption){
				System.out.println(" Enter Comma Separated Columns : ");
				String customColumnInputString = getStringInput();
				String[] customColumns = customColumnInputString.split(",");

				for(String s : customColumns){
					if(s != null && s != ""){
						Byte columnNo = Byte.parseByte(s);
						excelSheetMapperListCaseCustom.add(columnNo);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("Invalid Input : "+e);
		}
	}

	/**
	 * 
	 * @param inputValuesMap
	 * @return
	 */
	public static int storeInputVectorByMenuSelectionAndReturnTrainingOption(Map<Integer, VectorData> inputValuesMap){		
		displayFirstMenu();
		int inputForFirstMenu = getIntegerInput();
		int inputForSecondMenu = -1;

		if(inputForFirstMenu != visualOption){
			displaySecondMenu();
			inputForSecondMenu = getIntegerInput();

			handleCustomCoulumnInput(inputForSecondMenu);

			createInputVectorsBasedOnOption(inputForFirstMenu,inputForSecondMenu,null);

		}

		return inputForFirstMenu;
	}
	
	
	/**
	 * helper exposed for Struts2 files to enable input vector files generation via Web interface
	 * 
	 * @param inputValuesMap
	 * @param inputForFirstMenu
	 * @param inputForSecondMenu
	 * @return
	 */
	public static String createInputVectorFilesForStruts2(Map<Integer, VectorData> inputValuesMap,int inputForFirstMenu,
			int inputForSecondMenu, List<String> columnList){
		if(inputForFirstMenu != visualOption){
			
			handleCustomCoulumnInput(inputForSecondMenu);

			createInputVectorsBasedOnOption(inputForFirstMenu,inputForSecondMenu, columnList);

		}

		return SUCCESS;
	}
	
	/**
	 * helper exposed for Struts2 files to Visual SOM output via Web interface
	 * 
	 * @return
	 */
	public static String createJsonDataForVisualization(){
		System.out.println("Inside createJsonDataForVisualization method of MenuDrivenVectorGenerator");
		 new FileOperationsAdapter().readFromFile(DWM_FILE_READER);
		 new DocumentPositionCalculator().storeTraingularCoordinates();
		 new FileOperationsAdapter().writeToFile(VISUAL_DATA_JSON_FILE_WRITER);
		 return SUCCESS;
	}
}
