package som.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonExecutor {

	public void runPythonScriptsUsingShellScripting(String shellScriptCommand){
		String s = null;

		try {

			Process p = Runtime.getRuntime().exec(shellScriptCommand);

			BufferedReader stdInput = new BufferedReader(new
					InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new
					InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("error of the command \n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}

			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("exception thrown");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
