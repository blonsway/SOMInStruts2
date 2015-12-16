/*
 *  
 *	Tech For Good Portal 
 *	Proof of Concept
 *	J P Morgan Chase Technology Center at Syracuse University
 * 
 *	Authored by: 
 *	Last Revision: 1.0
 *	Last Revised by: Prashant Patel
 *
 *	Version 1.0
 *
 *  	Principal Investigators
 *		Kathleen Brandt
 *		Brian Lonsway
 *		Steve Masiclat
 *
 * 	Contributors
 *		Lead Java Developer & Research Assistant: Prashant Patel
 *		Java Developer & Research Assistant: Ravi Nagendra
 *		Python Developer: Brian Lonsway
 * 
 *	This document is a part of the source code and related artifacts
 * 	for the Tech For Good Portal, an open source proof of concept developed
 *	for J P Morgan Chase.
 *
 * 	Copyright © 2015, jointly held by 
 *		Kathleen Brandt, Brian Lonsway, and Steve Masiclat; 
 *		Syracuse University; and
 *		J P Morgan Chase.
 *
 *   	This file is part of TechForGoodPortal.
 *
 *    	TechForGoodPortal is free software: you can redistribute it and/or modify
 *    	it under the terms of the GNU General Public License version 3 as published by
 *    	the Free Software Foundation.
 *
 *    	TechForGoodPortal is distributed in the hope that it will be useful,
 *    	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    	GNU General Public License for more details.
 *
 *    	See <http://www.gnu.org/licenses/> for a copy of the GNU General Public License.
 *    	
 *
 * 		Writes the SOM dimension data into JSON file in order to display those coordinates on the GUI
 * 		
 * 
 */

package som.file;

import java.io.FileOutputStream;
import java.io.PrintWriter;




import som.helper.GenericHelper;
//static import
import static som.constants.IVisualizationConstants.VISUAL_JSON_JS_FILE;
import static som.constants.IVisualizationConstants.BMU_COORDINATES_JSON_ARRAY;


public class VisualDataJSONWriter implements IFileWritable {

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		try{
			PrintWriter pw = new PrintWriter(new FileOutputStream(GenericHelper.getAbsolutePath()+VISUAL_JSON_JS_FILE,false));
			pw.print("var jsonCircles  = "+BMU_COORDINATES_JSON_ARRAY+";");
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
