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
 * 		constants required for executing scripts on command line
 * 
 */


package som.constants;

import som.helper.GenericHelper;

/**
 * @author prashant
 *
 */
public interface ICommandLineConstants {
	public String RUN_VISUAL_SOM_COMMAND = "cmd /c start  runSOM.bat";
	public String RUN_GROWING_SOM_COMMAND_LINUX = GenericHelper.getAbsolutePath()+"GrowingSOM.sh";
	public String LINUX_BASH_FILE_PATH = "/bin/bash";
	
	public String WINDOWS_CMD_PATH = "cmd";
	public String WINDOWS_OUTPUT_GROWING_COMMAND_1 = " /c";
	public String WINDOWS_OUTPUT_GROWING_COMMAND_2 = " start";
	public String RUN_GROWING_SOM_COMMAND_WINDOWS = GenericHelper.getAbsolutePath()+"GrowingSOM.bat";

	public String LINUX_PYTHON_SCRIPT_FILE = "sudo python3 /var/lib/tomcat7/webapps/ROOT/generated/parsegoodtech.py "
			+ "-f /var/lib/tomcat7/webapps/ROOT/generated/fullyRedactedDocs.csv -c ";
	public String RUN_PYTHON_COMMAND_LINUX = GenericHelper.getAbsolutePath()+"runscript.sh";
	

}
