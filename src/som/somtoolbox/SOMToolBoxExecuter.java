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
 * 		Executes the command line scripts for SOMToolbox to generate the dwm file. 
 * 
 */

package som.somtoolbox;

import java.io.File;

import som.constants.ICommandLineConstants;
import som.helper.GenericHelper;

public class SOMToolBoxExecuter {
	public void runSOMToolBoxGrowingSOMExecutorScript(String command){
		try{
			System.out.println("Logic of running SOMToolbox Command starts here");				

			File executorDirectory = new File(GenericHelper.getAbsolutePath());       
			String shellScript = command;
			 
			System.out.println("shell command is"+shellScript);
			String executorPath = ICommandLineConstants.WINDOWS_CMD_PATH;
			if(!GenericHelper.isWindows()){
				executorPath = ICommandLineConstants.LINUX_BASH_FILE_PATH;
			}
			ProcessBuilder processBuilder = new ProcessBuilder(executorPath,command);  
			processBuilder.directory(executorDirectory); 
			processBuilder.redirectErrorStream(true);

			System.out.println("starting shell script for SOM Tool box");
			Process process = processBuilder.start();
			int shellExitStatus = process.waitFor();  
			if (shellExitStatus != 0) {  
				System.out.println("Successfully executed the shell script for SOM Tool box");  
			} 	 
		}catch(Exception e){
			System.out.println(e);
		}

	}
}
