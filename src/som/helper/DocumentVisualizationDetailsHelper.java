package som.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import som.adapter.FileOperationsAdapter;
import som.beans.VectorData;

public class DocumentVisualizationDetailsHelper {
	
	/**
	 * This function will call the File Adapter API which is responsible to interact with File Handler module
	 * to fetch the required column data from the the Excel sheet
	 * 
	 * @param option : This parameter selects the combination of columns to be displayed
	 * @param rowNumber
	 * @return
	 */
	public static Map<String,String> getSituationDescriptionFromExcelSheet(int option, int rowNumber){
		
		Map<String,String> visualDocSheetMap = new HashMap<String, String>();
		
		FileOperationsAdapter fileOperAdapter = new FileOperationsAdapter();
		
		List<VectorData> vectorDataList = fileOperAdapter.getVectorDataListFromExcelSheet(option);
		
		VectorData vectorData =  vectorDataList.get(rowNumber);
		
		visualDocSheetMap.put("organization_name", StringUtils.isNotBlank(vectorData.getOrganizatioName()) ?
				vectorData.getOrganizatioName().toUpperCase() : "");
		visualDocSheetMap.put("mission statement", vectorData.getMissionStatement());
		visualDocSheetMap.put("State/Province", vectorData.getState());
		visualDocSheetMap.put("Country", vectorData.getCountry());
		visualDocSheetMap.put("social_sector",StringUtils.isNotBlank( vectorData.getSocialSector()) && 
				"x".equalsIgnoreCase(vectorData.getSocialSector()) ? "1" : "0");
		visualDocSheetMap.put("tech_sector",StringUtils.isNotBlank( vectorData.getTechSector()) && 
				"x".equalsIgnoreCase(vectorData.getTechSector()) ? "1" : "0");
		visualDocSheetMap.put("situation_description", vectorData.getSituationDescription());
		
		return visualDocSheetMap;
	}
	
	
	public static void main(String[] args) {
		System.out.println("class DocumentVisualizationDetailsHelper : Main function");
		System.out.println(getSituationDescriptionFromExcelSheet(7, 0));
	}

}
