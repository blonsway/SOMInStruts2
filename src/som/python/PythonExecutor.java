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
 * 		Executes python scripts to generated the stemmed file and best words file.
 *
 * 
 */

package som.python;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import som.constants.ICommandLineConstants;
import som.helper.GenericHelper;

public class PythonExecutor {
	
	public void createAndWriteIntoPythonShellScriptFile(String path,List<String> commands){
		try{
			String contents = "";
			if(!commands.isEmpty()){
				
				for(int i = 0 ; i < commands.size(); i++){
					String trimmedCommand = commands.get(i).trim();
					if(i == commands.size() - 1){
						//Making sure that , is not at the last
						contents+=trimmedCommand;
					} else{
						contents+=trimmedCommand + ",";
					}
				}
				
				
			} else {
				contents = "7";
			}
			
			
			PrintWriter pw = new PrintWriter(new File(path));
			pw.println(ICommandLineConstants.LINUX_PYTHON_SCRIPT_FILE+contents);
			
			pw.close();
		} catch (Exception e) {
			System.out.println("Exception : "+e);
			e.printStackTrace();
		}
	}

	public void runPythonScriptsUsingShellScripting(String shellScriptCommand,
			List<String> columnNumbers){
		try {
			//Create Shell Script file to enter the column numbers dynamically
			createAndWriteIntoPythonShellScriptFile(shellScriptCommand,columnNumbers );
			
			System.setSecurityManager(null);
			System.out.println(" executing Python script");
			File executorDirectory = new File(GenericHelper.getAbsolutePath());       
			System.out.println("shell command for python is : "+shellScriptCommand);
			String executorPath = ICommandLineConstants.WINDOWS_CMD_PATH;
			if(!GenericHelper.isWindows()){
				executorPath = ICommandLineConstants.LINUX_BASH_FILE_PATH;
			}
			ProcessBuilder processBuilder = new ProcessBuilder(executorPath,shellScriptCommand);  
			processBuilder.directory(executorDirectory); 
			processBuilder.redirectErrorStream(true);

			System.out.println("starting shell script for python");
			Process process = processBuilder.start();
			int shellExitStatus = process.waitFor();  
			if (shellExitStatus != 0) {  
				System.out.println("Successfully executed the shell script for python");  
			} 
			System.out.println("No Error in the command for python");			
		}
		catch (Exception e) {
			System.out.println("Shell Script process for python is interrupted");
			e.printStackTrace();
			//System.exit(-1);
		}
	}

	public static void main(String args){
		List<String> columnNumbersList = new ArrayList<String>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			add("2");
			add("7");
		}};
		
		new PythonExecutor().runPythonScriptsUsingShellScripting(
				ICommandLineConstants.LINUX_BASH_FILE_PATH,columnNumbersList);
	}
}
