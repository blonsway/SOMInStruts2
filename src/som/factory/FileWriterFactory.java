/**
 * 
 * Class containing factory method to return the instance of FileType
 * 
 */
package som.factory;

import som.file.BLOutputFileParser;
import som.file.BestWordsFileGenerator;
import som.file.BestWordsFileParser;
import som.file.BestWordsTemplateVectorWriter;
import som.file.DWMFileWriter;
import som.file.IFileWritable;
import som.file.InputVectorFileWriter;
import som.file.SemanticOutputFileWriter;
import som.file.TemplateVectorFileWriter;
import som.file.UnitVectorFileWriter;
import som.file.WeightVectorFileWriter;
import som.file.VisualDataJSONWriter;
import som.file.DWMFileReader;

//static imports - Java is awesome
import static som.constants.IFileFactoryConstants.DWM_FILE;
import static som.constants.IFileFactoryConstants.UNIT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.WEIGHT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.INPUT_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE;
import static som.constants.IFileFactoryConstants.TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE;
import static som.constants.IFileFactoryConstants.BEST_WORDS_TEMPLATE_VECTOR_FILE;
import static som.constants.IFileFactoryConstants.VISUAL_DATA_JSON_FILE_WRITER;
import static som.constants.IFileFactoryConstants.DWM_FILE_READER;
import static som.constants.IFileFactoryConstants.BEST_WORDS_FILE_GENERATOR;

public class FileWriterFactory {

	public IFileWritable getFileWriter(String fileType){
		if(DWM_FILE.equalsIgnoreCase(fileType)){
			return new DWMFileWriter();
		}
		else if(UNIT_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new UnitVectorFileWriter();
		}
		else if(WEIGHT_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new WeightVectorFileWriter();
		}
		else if(INPUT_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new InputVectorFileWriter();
		}
		else if(CUSTOM_SOM_OUTPUT_FILE.equalsIgnoreCase(fileType)){
			return new SemanticOutputFileWriter();
		}
		else if(CUSTOM_SOM_PARSER_UNIT_OUTPUT_FILE.equalsIgnoreCase(fileType)){
			return new BLOutputFileParser();
		}
		else if(TEMPLATE_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new TemplateVectorFileWriter();
		}
		else if(BEST_WORDS_FILE.equalsIgnoreCase(fileType)){
			return new BestWordsFileParser();
		}
		else if(BEST_WORDS_TEMPLATE_VECTOR_FILE.equalsIgnoreCase(fileType)){
			return new BestWordsTemplateVectorWriter();
		}
		else if(VISUAL_DATA_JSON_FILE_WRITER.equalsIgnoreCase(fileType)){
			return new VisualDataJSONWriter();
		}
		else if(DWM_FILE_READER.equalsIgnoreCase(fileType)){
			return new DWMFileReader();
		}
		else if(BEST_WORDS_FILE_GENERATOR.equalsIgnoreCase(fileType)){
			return new BestWordsFileGenerator();
		}
		else return null;
		
	}
}
