/**
 * 
 */
package som.constants;

import java.util.ArrayList;

import org.json.JSONArray;

/**
 * @author prashant
 *
 */
public interface IVisualizationConstants {

	//constant responsible for triangular displacement
	double lamba = 25;
	
	//list of available svg colors
	ArrayList<String> svgColorList = new ArrayList<String>(){
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("red");
		add("orange");
		add("blue");
		add("yellow");
		add("indigo");
		add("lawngreen");
		add("violet");
		add("tomato");
		add("brown");
		add("darkcyan");
		add("hotpink");
		add("paleturquoise");
		add("slategray");
		add("green");
		add("darkkhaki");
		add("black");

	}};
	
	//json list of D3 Co-ordinates
	JSONArray bmuCoordinatesJSONArray = new JSONArray();
	
	//output json file name
	String VISUAL_JSON_JS_FILE = "visualJsonData.js";
	
	//displacement constant
	double INITIAL_DISPLACEMENT = 15;
	
	//max displacement factor
	double MAX_DISPLACEMENT_FACTOR = 200;
	
	//minimum dimension
	double MIN_DIMENSION = 0;
	
	//maximum dimension
	double MAX_DIMENSION = 1000;
}
