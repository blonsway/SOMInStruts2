/**
 * 
 * Constants used while working on Top 100 Words used for Creating Input Vector Files
 * 
 */
package som.constants;

import som.helper.GenericHelper;

/**
 * @author prashant
 *
 */
public interface IBestWordsFileConstants extends IGenericConstants {
	String fileName = "bestwords.txt";
	String CSV_WITH_SITUATION_DESC =  "output_cooccurrOfmergedSitDescOnly.csv";
	String CSV_WITH_SITUATION_DESC_MISSION_TEXT = "output_cooccurrOfmergedSitDescMission.csv";
	String BEST_WORDS_CSV = "stemmedOutput.csv";
}
