/**
 * 
 * This is the entry point of the program. The Design is using Observer pattern which notifies 
 * its observers on receiving the inputs
 * 
 */
package som.execute;

//project packages imports

import som.components.SOMFileWriter;
import som.components.SOMGUIViewer;
import som.components.SOMMappedDocumentPrinter;
import som.components.SOMMatrixCreater;
import som.components.SOMVectorDocumentMapper;
import som.helper.MenuDrivenVectorGenerator;
import som.notifier.SOMNotifier;
import som.adapter.FileOperationsAdapter;
import som.visualization.DocumentPositionCalculator;
//static imports
import static som.constants.IGenericConstants.inputValuesMap;
import static som.constants.IGenericConstants.visualOption;
import static som.constants.IGenericConstants.IS_TRAINING_REQUIRED;
import static som.constants.IFileFactoryConstants.VISUAL_DATA_JSON_FILE_WRITER;
import static som.constants.IFileFactoryConstants.DWM_FILE_READER;



public class SOMExecute{


	public static void main(String[] args)
	{
		
		// Notifier object which is responsible for notifying the observers
		SOMNotifier somNotifier = new SOMNotifier();
		
		// logic to generate InputValues
		int firstOption =MenuDrivenVectorGenerator.storeInputVectorByMenuSelectionAndReturnTrainingOption(inputValuesMap);

		 //option to run our own custom Algorithm
		if(firstOption == 1){
			//register the observers to noNotifier
			new SOMMatrixCreater(somNotifier);
			new SOMVectorDocumentMapper(somNotifier,IS_TRAINING_REQUIRED);
			new SOMMappedDocumentPrinter(somNotifier);
			new SOMFileWriter(somNotifier);
			new SOMGUIViewer(somNotifier);
			
			somNotifier.notifyObseversOnReceiveInputVectors();
		}
		
		if(firstOption == visualOption){
			 new FileOperationsAdapter().readFromFile(DWM_FILE_READER);
			 new DocumentPositionCalculator().storeTraingularCoordinates();
			 new FileOperationsAdapter().writeToFile(VISUAL_DATA_JSON_FILE_WRITER);

		}

	}


}

