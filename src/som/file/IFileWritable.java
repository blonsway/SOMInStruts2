/**
 * 
 * Interface which all File Reader and Writers needed in order to implement its methods
 * 
 */
package som.file;

public interface IFileWritable {
	public void writeToFile();
	public void readFromFile();
	public void readFromFile(String fileName);

}
