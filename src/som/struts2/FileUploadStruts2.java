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
 * 		Struts2 handler for uploading input file and saving it with the name - fullyRedactedDocs.csv 
 * 
 */

package som.struts2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import som.constants.IGenericConstants;
import som.helper.GenericHelper;

public class FileUploadStruts2 {
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;

	public String execute()
	{
		/* Copy file to a safe location */
		destPath = GenericHelper.getAbsolutePath();

		try{
			System.out.println("Src File name: " + myFile);
			System.out.println("Dst File name: " + myFileFileName);
			setMyFileFileName(IGenericConstants.FULLY_REDACTED_FILE_NAME);
			File destFile  = new File(destPath, myFileFileName);
			FileUtils.copyFile(myFile, destFile);

		}catch(IOException e){
			e.printStackTrace();
			return "error";
		}

		return "success";
	}
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getMyFileContentType() {
		return myFileContentType;
	}
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
}
