package som.struts2;


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

			int criteriaOption = Integer.parseInt(this.criteriaOption);
			String status = Struts2Adapter.getInputVectorFileGenerationStatus(criteriaOption);
			String statusOfViz = Struts2Adapter.getVisualizationOptionStatus();
			if(StringUtils.equalsIgnoreCase(status,SUCCESS) && StringUtils.equalsIgnoreCase(statusOfViz,SUCCESS)){
				return SUCCESS;
			}
			else{
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
