package info.plagiatsjaeger.interfaces;

import info.plagiatsjaeger.types.CompareResult;

import java.util.ArrayList;


/**
 * Callbackhandler fuer die Rueckmeldung bei gefundenen Suchergebnissen.
 * 
 * @see IComparer
 * @author Andreas
 */
public interface OnCompareFinishedListener
{
	/**
	 * Wird aufgerufen wenn der Vergleich mit einer Webseite beendet wurde.
	 * 
	 * @param compareResult
	 * @param link
	 */
	public void onCompareResultFound(ArrayList<CompareResult> compareResult, String link);

	/**
	 * searchResult Wird aufgerufen wenn der Vergleich mit einem anderen
	 * Dokument beendet wurde.
	 * 
	 * @param compareResult
	 * @param link
	 */
	public void onCompareResultFound(ArrayList<CompareResult> compareResult, int docId);
}
