package plagiatssoftware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import edu.stanford.nlp.io.EncodingPrintWriter.out;


/**
 * Enth�lt Funktionen zum Suchen in der Suchmaschine <a href="http://blekko.com">Blekko</a>
 * 
 * @author Andreas
 */
public class BlekkoSearch
{
	private static final String	URL	           = "http://blekko.com/ws/?";
	private static final String	URL_ARG_JSON	= "+%2Fjson";
	private static final String	URL_ARG_SEARCH	= "q=";

	private static final String	CHARSET	       = "UTF-8";

	// Mit aktuellen URL Einstellungen werden 10 Links geliefert. MAX_URLS gibts an wieviele davon verwendet werden
	// sollen.
	private static final int	MAX_URLS	   = 5;

	private ArrayList<String>	_searchResults	= new ArrayList<String>();

	public BlekkoSearch()
	{

	}

	/**
	 * Sucht auf der Suchmaschine Blekko nach Treffern f�r den gegebenen Text
	 * 
	 * @param textToSearch
	 * @return ArrayList mit den Ergebnis-Links
	 */
	public ArrayList<String> search(String textToSearch)
	{
		ArrayList<String> result = null;

		try
		{
			textToSearch = URLEncoder.encode(textToSearch, CHARSET).replaceAll("[ \t\n\f\r]", "+");

			URL url = new URL(URL + URL_ARG_SEARCH + textToSearch + URL_ARG_JSON);
			InputStreamReader reader = new InputStreamReader(url.openStream(), CHARSET);

			BufferedReader bufferedReader = new BufferedReader(reader);

			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null)
			{
				stringBuilder.append(line);
				line = bufferedReader.readLine();
			}
			result = getUrlFromJson(stringBuilder.toString());
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Holt URLs aus json
	 * 
	 * @param strSearchLink
	 * @return Gibt Liste der URLs zurueck
	 */
	private ArrayList<String> getUrlFromJson(String strSearchLink)
	{
		ArrayList<String> alUrlList = new ArrayList<String>();
		// Matchpattern
		// Altes JSON
		Pattern patPattern = Pattern.compile("\"url\"\\s*?:\\s*?\"([^\"]+?)\"");
		// Neues JSON
		Pattern patPatternNew = Pattern.compile("\"displayUrl\"\\s*?:\\s*?\"([^\"]+?)\"");

		Matcher matMatcher;

		// Und schlie�lich in der for schleife//
		matMatcher = patPattern.matcher(strSearchLink);

		if (matMatcher.find())
		{
			// Falls matcher nicht leer ist
			matMatcher.reset();

			int numURL = 0;
			while (numURL < MAX_URLS && matMatcher.find())
			{
				numURL++;
				String strLink = cleanURL(Jsoup.parse(matMatcher.group(1)).text());
				// Falls Link bereits in _serchResults vorhanden nicht nochmal schicken
				if (!_searchResults.contains(strLink))
				{
					alUrlList.add(strLink);
					System.out.println(strLink);
				}
			}
		}
		else
		{
			matMatcher = patPatternNew.matcher(strSearchLink);
			matMatcher.reset();
			int numURL = 0;
			while (numURL < MAX_URLS && matMatcher.find())
			{
				numURL++;
				String strLink = cleanURL(Jsoup.parse(matMatcher.group(1)).text());
				// Falls Link bereits in _serchResults vorhanden nicht nochmal schicken
				if (!_searchResults.contains(strLink))
				{
					alUrlList.add(strLink);
					System.out.println(strLink);
				}
			}

		}
		_searchResults.addAll(alUrlList);
		return alUrlList;
	}

	private String cleanURL(String dirtyURL)
	{
		String result = "";
		dirtyURL = dirtyURL.replaceAll("www.", "");
		dirtyURL = dirtyURL.replaceAll("http://", "");
		result = "http://" + dirtyURL;
		return result;
	}

}
