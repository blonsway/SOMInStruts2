package som.file;

import java.io.FileOutputStream;
import java.io.PrintWriter;




import som.helper.GenericHelper;
//static import
import static som.constants.IVisualizationConstants.VISUAL_JSON_JS_FILE;
import static som.constants.IVisualizationConstants.bmuCoordinatesJSONArray;


public class VisualDataJSONWriter implements IFileWritable {

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter pw = new PrintWriter(new FileOutputStream(GenericHelper.getAbsolutePath()+VISUAL_JSON_JS_FILE,false));
			pw.print("var jsonCircles  = "+bmuCoordinatesJSONArray+";");
			pw.close();
		}
		catch(Exception e){
			System.out.println("class : VisualDataJSONWriter & method : writeToFileException while writing to JSON File"+e);
		}
	}

	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
