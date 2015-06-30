/**
 * 
 * Helper class containing utility method for computing  distance between the two vectors
 * 
 */
package som.helper;

import static som.constants.IGenericConstants.bestWordFileOptionForSituationDescription;
import static som.constants.IGenericConstants.bestWordFileOptionForSituationDescriptionAndMissionStatement;
import static som.constants.IGenericConstants.stemmedBestWordFileOptionForSituationDescriptionAndMissionStatement;
import static som.constants.IGenericConstants.stemmendBestWordFileOptionForSituationDescription;
import static som.constants.IVisualizationConstants.MIN_DIMENSION;
import static som.constants.IVisualizationConstants.MAX_DIMENSION;











import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import som.beans.VectorCoordinate;
import som.constants.IGenericConstants;

public class GenericHelper {
	
	public static double computeEuclideanDistanceForCoordinates(List<Integer> coordinate1, List<Integer> coordinate2)
	{
		double squareDistance = 0;
		double squareRootDistance = 0;
		for (int i = 0; i < coordinate1.size(); i++)
		{
			squareDistance += coordinate1.get(i) * coordinate2.get(i);
		}
		squareRootDistance = Math.sqrt(squareDistance);
		return squareRootDistance;
	}
	
	/**
	 * 
	 * @param secondOption
	 * @return whether the menu selection matches the best words option
	 */
	public static boolean isBestWordsOptionSelected(int secondOption){
		return secondOption == bestWordFileOptionForSituationDescription ||
				secondOption == bestWordFileOptionForSituationDescriptionAndMissionStatement ||
				secondOption == stemmendBestWordFileOptionForSituationDescription ||
				secondOption == stemmedBestWordFileOptionForSituationDescriptionAndMissionStatement;
	}
	
	/**
	 * 
	 * @param secondOption
	 * @return whether the menu selection matches the stemmed best words option
	 */
	public static boolean isStemmedBestWordsSelected(int secondOption){
		return secondOption == stemmendBestWordFileOptionForSituationDescription ||
				secondOption == stemmedBestWordFileOptionForSituationDescriptionAndMissionStatement;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param dimensionList
	 * @return whether the point does not overlapp with one another
	 */
	public static boolean isDimensionCorrect(double x, double y, List<VectorCoordinate> dimensionList){
				
		boolean isDimensionCorrect = true;
		
		if(x <= MIN_DIMENSION || y<= MIN_DIMENSION || x >= MAX_DIMENSION || y >= MAX_DIMENSION){
			return false;
		}
		
		for(VectorCoordinate eachVectorCoordinate : dimensionList){
			if(eachVectorCoordinate.isOverlapping(x, y)){
				System.out.println(eachVectorCoordinate+ " is overalpping with x = "+x+" and y ="+y);
				isDimensionCorrect = false;
				break;
			}
		}
		
		return isDimensionCorrect;
	}
	
	/**
	 * 
	 * This function is added after migrating the code base to Struts2
	 * 
	 * gets the Context Path of the Server
	 * 
	 * @param resourcePath
	 * @return
	 */
	public static String getServerPath(){
		
		return ServletActionContext.getRequest().getServerName()+IGenericConstants.COLON+
				ServletActionContext.getRequest().getServerPort()+
				ServletActionContext.getServletContext().getContextPath();
	}
	
	/**
	 * 
	 * @param path
	 * @return the inputstream from Servlet Resource
	 */
	public static InputStream getFileFromServerUsingUrl(String path){
		InputStream inputStream = null;
		try{
			inputStream = ServletActionContext.getServletContext().getResourceAsStream(path);
			System.out.println("The File Url :"+inputStream.toString());
			
		} catch(Exception e){
			e.printStackTrace();			
		}
		
		return inputStream;
	}

}
