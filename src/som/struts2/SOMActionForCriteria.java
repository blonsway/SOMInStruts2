package som.struts2;


import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import som.adapter.Struts2Adapter;

import com.opensymphony.xwork2.ActionSupport;


public class SOMActionForCriteria extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String criteriaOption;
	private boolean isVisualOptionSelected;

	public String execute() throws Exception {
		try{
			System.out.println("Calling execute method of SOMActionForCriteria");
			System.out.println(this.criteriaOption);
			if(criteriaOption != null && StringUtils.isNotBlank(criteriaOption)){
				String[] criteriaArray = StringUtils.split(criteriaOption, ",");
				List<String> columnList = Arrays.asList(criteriaArray);
				System.out.println(columnList);
				
				//call the adapter
				String status = Struts2Adapter.getInputVectorFileGenerationStatus(columnList);
				String statusOfViz = Struts2Adapter.getVisualizationOptionStatus();
				if(StringUtils.equalsIgnoreCase(status,SUCCESS) && StringUtils.equalsIgnoreCase(statusOfViz,SUCCESS)){
					return SUCCESS;
				}
				else{
					return ERROR;
				}
				
			} else {
				return ERROR;
			}
			
		}catch (NumberFormatException ne){
			return ERROR;
		} catch (Exception e){
			return ERROR;
		}


	}

	public String getCriteriaOption() {
		return criteriaOption;
	}

	public void setCriteriaOption(String criteriaOption) {
		this.criteriaOption = criteriaOption;
	}

	public boolean isVisualOptionSelected() {
		return isVisualOptionSelected;
	}

	public void setVisualOptionSelected(boolean isVisualOptionSelected) {
		this.isVisualOptionSelected = isVisualOptionSelected;
	}


}
