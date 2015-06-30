package som.struts2;
import org.apache.commons.lang.StringUtils;

import som.adapter.Struts2Adapter;

import com.opensymphony.xwork2.ActionSupport;


public class SOMActionForVisualization extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		try{
			System.out.println("Calling execute method of SOMActionForVisualization");
			
			
			String status = Struts2Adapter.getVisualizationOptionStatus();
			if(StringUtils.equalsIgnoreCase(status,SUCCESS)){
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
}
