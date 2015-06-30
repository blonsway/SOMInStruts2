package som.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		visualDocSheetMap.put("organization_name", vectorData.getOrganizatioName());
		visualDocSheetMap.put("organization_website", vectorData.getOrganizationWebsite());
		visualDocSheetMap.put("social_sector", vectorData.getSocialSector()!= null && 
				"x".equalsIgnoreCase(vectorData.getSituationDescription()) ? "Yes" : "No");
		visualDocSheetMap.put("tech_sector", vectorData.getSocialSector()!= null && 
				"x".equalsIgnoreCase(vectorData.getSituationDescription()) ? "Yes" : "No");
		visualDocSheetMap.put("situation_description", vectorData.getSituationDescription());
		visualDocSheetMap.put("technical_scope", vectorData.getTechnicalScope());
		
		return visualDocSheetMap;
	}
	
	
	public static void main(String[] args) {
		System.out.println("class DocumentVisualizationDetailsHelper : Main function");
		System.out.println(getSituationDescriptionFromExcelSheet(7, 0));
	}

}
