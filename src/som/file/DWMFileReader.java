package som.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import som.comparators.SOMDistanceComparator;
import som.helper.GenericHelper;
import som.beans.SOMDimensionRelation;


//static import
import static som.constants.IDWMFileConstants.VISUAL_FILE_NAME;
import static som.constants.IDWMFileConstants.dwmInfoMap;



public class DWMFileReader implements IFileWritable {

	

	/**
	 * parses the BufferedReader and store the information into dwm info hashmap
	 * @param br
	 */
	public  void storeDocumentsDistanceInfo(BufferedReader br){
		try{
			String documentLine = "";
			while(( documentLine = br.readLine()) != null){
				//System.out.println(documentLine);
				
				
				if(documentLine.indexOf("Document_number_") != -1){
					String[] documentNumberLineSplit = documentLine.split("_");
					int documentNumber = Integer.parseInt(documentNumberLineSplit[2]);

					String bmuLine = br.readLine();

					String[] bmuLineSplit = bmuLine.split(" ");

					SOMDistanceComparator comparator = new SOMDistanceComparator();
					PriorityQueue<SOMDimensionRelation> documentQueue = new PriorityQueue<SOMDimensionRelation>(10,comparator);

					int bmuLineIndex = 0;

					while(bmuLineIndex < bmuLineSplit.length){
						SOMDimensionRelation somDimensionRelation = new SOMDimensionRelation(
								Integer.parseInt(bmuLineSplit[bmuLineIndex++]), 
								Integer.parseInt(bmuLineSplit[bmuLineIndex++]),
								Double.parseDouble(bmuLineSplit[bmuLineIndex++]));
						documentQueue.add(somDimensionRelation);
					}

					dwmInfoMap.put(documentNumber, documentQueue);

				}

			}
			//System.out.println(dwmInfoMap);
			/*PriorityQueue<SOMDimensionRelation> documentQueue = dwmInfoMap.get(51);
			for(SOMDimensionRelation s : documentQueue){
				System.out.println(s.getxPosition()+" "+s.getyPosition()+" "+s.getDistance());
			}
			System.out.println(dwmInfoMap.size());*/

		}
		catch(Exception e){
			System.out.println(" class DWMFileReader : method storeDocumentsDistanceInfo(BufferedReader br) :"
					+ "Exception while dealing with getting dwm info : "+e);
		}
	}

	/**
	 * reads the dwm file and store the information needed for visualization
	 */
	@Override
	public void readFromFile() {
		// TODO Auto-generated method stub
		try{
			InputStreamReader isr = new InputStreamReader(GenericHelper.getFileFromServerUsingUrl(VISUAL_FILE_NAME));
			BufferedReader br = new BufferedReader(isr);
			storeDocumentsDistanceInfo(br);
			
			br.close();
		}
		catch(Exception e){
			System.out.println(" class DWMFileReader : method readFromFile() : "
					+ "Exception while dealing with file : "+e);
		}

	}
	
	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	
	



}
