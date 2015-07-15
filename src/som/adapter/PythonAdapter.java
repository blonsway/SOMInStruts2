/**
 * 
 * @author prashant
 *
 *Adpater class to support executing python scripts
 *
 */
package som.adapter;

import som.python.PythonExecutor;

public class PythonAdapter {
	PythonExecutor pythonExecutor;
	
	public PythonAdapter() {
		// TODO Auto-generated constructor stub
		pythonExecutor = new PythonExecutor();
	}
	
	public void exceutePythonScripts(String command){
		pythonExecutor.runPythonScriptsUsingShellScripting(command);
	}
}
