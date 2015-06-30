/**
 * 
 * reads bl_out.unit file, parses it and store it in semantic manner in 
 *  bl_parser_out.unit manner
 * 
 */

package som.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



import som.beans.VectorData;
//static import in java. Java is awesome
import static som.constants.IGenericConstants.inputValuesMap;


public class BLOutputFileParser implements IFileWritable{

	
	private void printDimensions(String currentLine, PrintWriter writer){	
		String[] dimensionArray = currentLine.split("\\s+");		
		if(dimensionArray.length > 0 ){
			writer.println(dimensionArray[1]);
		}
	}

	/**
	 * move the buffered Reader pointer to n line relative to the current line and return the String holding the next Line
	 * @param br
	 * @param n
	 */
	private String moveBufferedReadtoLineNo(BufferedReader br, int n){
		String currentLine = null;
		for(int i = 0 ; i<= n;i++){
			try{
				currentLine = br.readLine();
			}

			catch(Exception e){
				e.printStackTrace();
			}
		}
		return currentLine;
	}

	/**
	 * writes the output of Document Number information to the File
	 * @param writer
	 * @param documentNumberList
	 * @param documentDistanceList
	 */
	private void writeDocumentInformationIntoFile(PrintWriter writer , List<Integer> documentNumberList, List<Double> documentDistanceList){
		for(int i = 0 ; i < documentNumberList.size() ; i++){
			int docNo = documentNumberList.get(i);
			Double distance = documentDistanceList.get(i);
			VectorData vectorData = inputValuesMap.get(docNo);
			writer.println("Document_Number_"+docNo+ " (dist:"+distance+")");
			if(vectorData != null){
				writer.println(" Project Title: "+vectorData.getProjectTitleForBLParser());
				writer.println(" Project Descriptipon: "+vectorData.getSituationDescriptionForBLParser());
			}
			else{
				writer.println(" Project Title: ");
				writer.println(" Project Descriptipon: ");
			}

		}
	}

	/**
	 * store Mapped Vectors And its distance info into the  list and calls writeDocumentInformationIntoFile() method
	 * @param noOfMappedVectors
	 * @param br
	 */
	private void storeMappedVectorsAndWriteIntoOutputFile(int noOfMappedVectors, BufferedReader br, PrintWriter writer ){
		try{
			// list containing the document Number 
			List<Integer> documentNumberList = new ArrayList<Integer>();
			String documentNumberLine = null;

			// now go on reading the next line to get the document number information till 
			// the no of mapped vectors
			for(int i = 0 ; i < noOfMappedVectors ; i++){
				documentNumberLine = br.readLine();
		//		System.out.println("LINE WITH DOCUMENT NUMBER : "+documentNumberLine);
				if(documentNumberLine != null){
					String[] lineSplitDoc = documentNumberLine.split("_");

					//check if the document number information is present in the line
					if(lineSplitDoc != null && lineSplitDoc.length >= 3){
						documentNumberList.add(Integer.parseInt(lineSplitDoc[2]));										
					}
				}
			}
			//check in next Line to get distance information
			String distanceLineInfo = br.readLine();	

	//		System.out.println("LINE WITH DISTANCE  : "+distanceLineInfo);

			String[] lineSplitDistance = distanceLineInfo.split("\\s+");

			// list containing the distance information
			List<Double> documentDistanceList = new ArrayList<Double>();

			//validate the distance array i.e. distances == noOfDocuments
			if(lineSplitDistance!= null && lineSplitDistance.length > noOfMappedVectors){
				for(int j = 1; j < lineSplitDistance.length ; j++){
					documentDistanceList.add(Double.parseDouble(lineSplitDistance[j]));
				}
			}

			// writes the Document Information to the File
			writeDocumentInformationIntoFile(writer, documentNumberList, documentDistanceList);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * parse the Mapped Vector Details
	 * @param mappedVectorLine
	 * @param br
	 * @param writer
	 */
	private void parseMappedVectorDetailsInfo(String mappedVectorLine, BufferedReader br, PrintWriter writer){
		try{
			String[] lineSpilt = mappedVectorLine.split("\\s+");

	//		System.out.println("NO OF MAPPED VECTOR LINE"+mappedVectorLine);

			// check the lenght of the line split to avoid processing time
			if(lineSpilt != null && lineSpilt.length >= 2){
				int noOfMappedVectors =  Integer.parseInt(lineSpilt[1]);

				// check number of mapped vectors contains valid info
				if(noOfMappedVectors > 0){
					
					writer.println("MAPPED VECTORS : "+noOfMappedVectors);

					moveBufferedReadtoLineNo(br, 0);

				//	System.out.println("next Line After No of Mapped Vectors : "+nextLineAfterMapped);

					// now go on reading the next line to get the document number information till 
					// the no of mapped vectors
					storeMappedVectorsAndWriteIntoOutputFile(noOfMappedVectors, br, writer);
				}else{
					// found number of mapped vectors less than 1
					writer.println("MAPPED VECTORS : 0");
				}
			}else{
				// found the Mapped Vector numbers line is missing with information
				writer.println("MAPPED VECTORS : 0");
			}

		}
		catch(Exception e){

		}

	}

	/**
	 * parse each line of the File to look for Matrix element info, document info and distance info
	 * @param currentLine
	 * @param writer
	 * @param br
	 */
	private void parseMatrixElementInfo(String currentLine, PrintWriter writer, BufferedReader br ){
		try{
			String mappedVectorLine = null;
			// first parse the line of Matrix Element Information
			if(currentLine.indexOf("bl_out") != -1){
				printDimensions(currentLine, writer);

		//		System.out.println("Bl_OUT_LINE" + currentLine);

				mappedVectorLine = moveBufferedReadtoLineNo(br, 2);

				// now look for getting the no of mapped vector information
				if(mappedVectorLine != null && mappedVectorLine.indexOf("$NR_VEC_MAPPED") != -1){
					parseMappedVectorDetailsInfo(mappedVectorLine, br, writer);
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Overriden method which reads bl_out.unit file, parses it and store it in semantic manner in 
	 * bl_parser_out.unit manner
	 */
	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		PrintWriter writer = null;
		BufferedReader br = null;
		String currentLine = null;
	//	System.out.println("Parsing Started ");
		try{
			writer = new PrintWriter("bl_parser_out.unit");
			br = new BufferedReader(new FileReader("bl_out.unit"));
			
			while((currentLine = br.readLine()) != null){
				// first parse the line of Matrix Element Information
				parseMatrixElementInfo(currentLine, writer, br);

			}
			br.close();
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

}
