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
 * 		Runs the SOM weight neighbourhood displacement algorithm in order to position the 
 * 		documents within the node and avoid overlapping
 */

package som.visualization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.json.JSONArray;
import org.json.JSONException;

import som.beans.SOMDimensionRelation;
import som.constants.IGenericConstants;
import som.helper.DocumentVisualizationDetailsHelper;
import som.helper.GenericHelper;
import som.beans.VectorCoordinate;
//static imports
import static som.constants.IDWMFileConstants.DWM_INFO_MAP;
import static som.constants.IVisualizationConstants.LAMBDA;
import static som.constants.IVisualizationConstants.SVG_COLOR_LIST;
import static som.constants.IVisualizationConstants.BMU_COORDINATES_JSON_ARRAY;
import static som.constants.IVisualizationConstants.INITIAL_DISPLACEMENT;
import static som.constants.IVisualizationConstants.MAX_DISPLACEMENT_FACTOR;;




public  class DocumentPositionCalculator {

	public  void storeTraingularCoordinates(){		

		List<VectorCoordinate> dimensionsList = new ArrayList<VectorCoordinate>();
		System.out.println("Before Printing : Size of DWM Map : "+DWM_INFO_MAP.size());
		if(BMU_COORDINATES_JSON_ARRAY.length() > 0){
			for(int i = BMU_COORDINATES_JSON_ARRAY.length()-1 ; i >= 0; i--){
				BMU_COORDINATES_JSON_ARRAY.remove(i);
			}
		}
		System.out.println("Before Printing : Size of JSON Map : "+BMU_COORDINATES_JSON_ARRAY.length());
		for(Map.Entry<Integer, PriorityQueue<SOMDimensionRelation>> entry : DWM_INFO_MAP.entrySet()){
			int documentNumber = entry.getKey();
			PriorityQueue<SOMDimensionRelation> docQueue = entry.getValue();
			SOMDimensionRelation node1 =  docQueue.poll();
			SOMDimensionRelation node2 =  docQueue.poll();
			SOMDimensionRelation node3 =  docQueue.poll();
			SOMDimensionRelation node4 =  docQueue.poll();

			int node1X = node1.getxPosition();
			int node1Y = node1.getyPosition();
			double node1Distance = node1.getDistance();

			int node2X = node2.getxPosition();
			int node2Y = node2.getyPosition();
			double node2Distance = node2.getDistance();

			int node3X = node3.getxPosition();
			int node3Y = node3.getyPosition();
			double node3Distance = node3.getDistance();

			int node4X = node4.getxPosition();
			int node4Y = node4.getyPosition();
			double node4Distance = node4.getDistance();

			boolean isNode4Needed = false;
			//check if Node4 is needed
			if((node1X == node2X && node2X== node3X && node1X == node3X) ||
					(node1Y == node2Y && node2Y== node3Y && node1Y == node3Y)){
				isNode4Needed = true;
			}

			//calculating the relative force
			double force1= node1Distance/node2Distance;
			double force2= node1Distance/node3Distance;
			double force3 = (isNode4Needed) ? node1Distance/node4Distance : 0;

			//calculating the distance relation
			double force1X = (node1X == node2X)?0:(1/(node2X - node1X ));
			double force1Y = (node1Y == node2Y)?0:(1/(node2Y - node1Y ));

			double force2X = (node1X == node3X)?0:(1/(node3X - node1X ));			
			double force2Y = (node1Y == node3Y)?0:(1/(node3Y - node1Y ));

			double force3X = (node1X == node4X)?0:(1/(node4X - node1X ));
			double force3Y = (node1Y == node4Y)?0:(1/(node4Y - node1Y ));

			//calculating the final displacement Coordinate
			double bmuX = ((LAMBDA*force1*force1X) + (LAMBDA*force2*force2X) + (LAMBDA*force3*force3X))+(node1X*100+50);
			double bmuY = ((LAMBDA*force1*force1Y) + (LAMBDA*force2*force2Y) + (LAMBDA*force3*force3Y))+(node1Y*100+50);

			VectorCoordinate vectorCoordinate = new VectorCoordinate();
			vectorCoordinate.setX(bmuX);
			vectorCoordinate.setY(bmuY);	

			//for debudgging
			//System.out.print("Tentative Document Number :"+documentNumber);
			//System.out.print(" bmuX:"+ vectorCoordinate.getX());
			//System.out.println(" bmuY:"+vectorCoordinate.getY());

			if(!GenericHelper.isDimensionCorrect(vectorCoordinate.getX(), vectorCoordinate.getY(), dimensionsList)){
				//System.out.println(vectorCoordinate +" is not correct ");
				resolvePointCollision(dimensionsList,vectorCoordinate,INITIAL_DISPLACEMENT);
			}

			dimensionsList.add(vectorCoordinate);

			//selecting the random color for this document 
			int colorIndex = (int)(Math.random()*SVG_COLOR_LIST.size());

			Map<String,Object> visualDocumentsMap = new HashMap<String,Object>();
			visualDocumentsMap.put("x_axis", vectorCoordinate.getX());
			visualDocumentsMap.put("y_axis", vectorCoordinate.getY());
			visualDocumentsMap.put("radius", 5);
			visualDocumentsMap.put("color", SVG_COLOR_LIST.get(colorIndex));
			visualDocumentsMap.put("documentNumber", documentNumber);			
			Map<String,String> trainingDataFromSheetMap = DocumentVisualizationDetailsHelper.getSituationDescriptionFromExcelSheet(
					IGenericConstants.VISUALIZATION_OPTION, documentNumber);
			visualDocumentsMap.put("organization_name", trainingDataFromSheetMap.get("organization_name"));
			visualDocumentsMap.put("mission statement", trainingDataFromSheetMap.get("mission statement"));
			visualDocumentsMap.put("State/Province", trainingDataFromSheetMap.get("State/Province"));
			visualDocumentsMap.put("Country", trainingDataFromSheetMap.get("Country"));
			visualDocumentsMap.put("social_sector", trainingDataFromSheetMap.get("social_sector"));
			visualDocumentsMap.put("tech_sector", trainingDataFromSheetMap.get("tech_sector"));
			visualDocumentsMap.put("situation_description", trainingDataFromSheetMap.get("situation_description"));

			BMU_COORDINATES_JSON_ARRAY.put(visualDocumentsMap);
			//for debudgging
			//System.out.print("Final Document Number :"+documentNumber);
			//System.out.print(" bmuX:"+ vectorCoordinate.getX());
			//System.out.println(" bmuY:"+vectorCoordinate.getY());
		}

		//System.out.println(bmuCoordinatesJSONArray);
		//recalculateNewPositionToAvoidCollision();
		
		System.out.println("After Printing : Size of DWM Map : "+DWM_INFO_MAP.size());
		System.out.println("After Printing : Size of JSON Map : "+BMU_COORDINATES_JSON_ARRAY.length());

	}



	/**
	 * This method resolves the collision using the angular diagonal displacement method
	 * 
	 * 
	 * @param dimensionList
	 * @param vectorCoordinate
	 * @param displacementFactor
	 */
	public static void resolvePointCollision(List<VectorCoordinate> dimensionList, 
			VectorCoordinate vectorCoordinate,double displacementFactor){
		//System.out.println("Resolve Collosion starts for "+vectorCoordinate+" with factor "+displacementFactor);
		if(displacementFactor > MAX_DISPLACEMENT_FACTOR){
			return;
		}
		else {
			double newX = vectorCoordinate.getX() + displacementFactor;
			double newY  = vectorCoordinate.getY() + displacementFactor;

			boolean success = false;

			if(!GenericHelper.isDimensionCorrect(newX, newY, dimensionList)){
				newX = vectorCoordinate.getX() - displacementFactor;
				newY  = vectorCoordinate.getY() - displacementFactor;			
				if(!GenericHelper.isDimensionCorrect(newX, newY, dimensionList)){
					newX = vectorCoordinate.getX() + displacementFactor;
					newY  = vectorCoordinate.getY() - displacementFactor;
					if(!GenericHelper.isDimensionCorrect(newX, newY, dimensionList)){
						newX = vectorCoordinate.getX() - displacementFactor;
						newY  = vectorCoordinate.getY() + displacementFactor;
						if(!GenericHelper.isDimensionCorrect(newX, newY, dimensionList)){
							resolvePointCollision(dimensionList, vectorCoordinate, displacementFactor+INITIAL_DISPLACEMENT);
						}else{
							success = true;
						}
					}else{
						success = true;
					}
				}else{
					success = true;
				}
			}else{
				success = true;
			}

			if(success){
				vectorCoordinate.setX(newX);
				vectorCoordinate.setY(newY);
				//System.out.println("Collosion Resolved with "+vectorCoordinate+" and with factor "+displacementFactor);

			}


			return;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		data.put( "name", "Mars" );
		data.put( "age", 32 );
		data.put( "city", "NY" );

		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put( "name", "palebt" );
		data2.put( "age", 21 );
		data2.put( "city", "NY" );

		System.out.println(DocumentVisualizationDetailsHelper.getSituationDescriptionFromExcelSheet(9, 0));


		JSONArray list = new JSONArray();
		list.put(data);
		list.put(data2);

		//System.out.printf( "JSON: %s", list ); 

		// System.out.println();
		//DWMFileReader.readFromFileStatic();
		// storeTraingularCoordinates();



	}

}
