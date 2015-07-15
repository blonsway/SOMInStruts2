/**
 * 
 */
package som.constants;

/**
 * @author prashant
 *
 */
public interface ICommandLineConstants {
	public String RUN_VISUAL_SOM_COMMAND = "cmd /c start  runSOM.bat";
	public String RUN_GROWING_SOM_COMMAND_WINDOWS = "cmd /c start  C:\\apache-tomcat-7.0.62\\webapps\\generated\\";
	public String RUN_GROWING_SOM_COMMAND_LINUX = "/bin/bash /var/lib/tomcat7/webapps/ROOT/generated/GrowingSOM.sh";
	public String RUN_PYTHON_COMMAND_LINUX = "/bin/bash /var/lib/tomcat7/webapps/ROOT/generated/runscript.sh";

}
