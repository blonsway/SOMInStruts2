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
 * 		Struts2 handler for calling the entire SOM generation and training process and
 * 		visualization 
 * 
 */

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
