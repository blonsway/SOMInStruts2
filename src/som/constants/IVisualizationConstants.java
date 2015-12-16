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
 * 		Constants for SOM Visualization
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
	double LAMBDA = 25;
	
	//list of available svg colors
	ArrayList<String> SVG_COLOR_LIST = new ArrayList<String>(){
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
		}
	};
	
	//json list of D3 Co-ordinates
	JSONArray BMU_COORDINATES_JSON_ARRAY = new JSONArray();
	
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
