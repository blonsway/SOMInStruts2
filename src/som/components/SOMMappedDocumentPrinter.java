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
 * 		maps SOM nodes with Documents
 * 
 */


package som.components;

import som.constants.IMatrixConstants;
import som.notifier.SOMNotifier;
import som.observer.SOMObserver;

public class SOMMappedDocumentPrinter extends SOMObserver{
	
	public SOMMappedDocumentPrinter(SOMNotifier notifier) {
		this.notifier = notifier;
		this.notifier.attach(this);
	}

	@Override
	public void update() {
		// prints the document matrix
		System.out.println("The Mapped Documents to SOM Matrix Positions are : ");
		for (int i = 0; i <IMatrixConstants.SOM_MATRIX_ROW_SIZE; i++)
		{
			for (int j = 0; j <IMatrixConstants.SOM_MATRIX_COLUMN_SIZE; j++)
			{
				System.out.println("[" + i + "," + j + "] = "+IMatrixConstants.DOCUMENT_MATRIX[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
