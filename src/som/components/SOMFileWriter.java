/**
 * 
 * Writes a fixed to  set of predefined files to be able to View SOMViewer
 * 
 */
package som.components;


import som.adapter.FileOperationsAdapter;
import som.notifier.SOMNotifier;
import som.observer.SOMObserver;

//static import
import static som.constants.IFileFactoryConstants.DWM_FILE;
import static som.constants.IFileFactoryConstants.UNIT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.WEIGHT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.INPUT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE;;





public class SOMFileWriter extends SOMObserver{
	
	public SOMFileWriter(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}

	@Override
	public void update() {
		//calls the Adapter File Writer class to extend file writer functionality of the SOM
		FileOperationsAdapter fileWriterAdapter = new FileOperationsAdapter();
		fileWriterAdapter.writeToFile(INPUT_VECTOR_FILE);
		fileWriterAdapter.writeToFile(UNIT_VECTOR_FILE);
		fileWriterAdapter.writeToFile(WEIGHT_VECTOR_FILE);
		fileWriterAdapter.writeToFile(DWM_FILE);
		fileWriterAdapter.writeToFile(CUSTOM_SOM_OUTPUT_FILE);
		fileWriterAdapter.writeToFile(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE);
	}

}
