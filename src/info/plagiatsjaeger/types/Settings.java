package info.plagiatsjaeger.types;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Datentyp fuer die Einstellungen.
 * 
 * @author Andreas
 */
public class Settings
{

	private double							_threshold;
	// TODO: Default Settings setzen eventuell ueber Config File
	private int								_searchSentenceLength	= 10;
	private int								_searchJumpLength		= 10;
	private int								_compareSentenceLength	= 10;
	private int								_compareJumpLength		= 10;
	private boolean							_checkWWW				= true;
	private ArrayList<Integer>				_localFolders			= new ArrayList<Integer>();

	private String							_searchURL				= "http://blekko.com/ws/?";
	private String							_searchSearchArg		= "q=";
	private String							_searchAuthArg;
	private String							_searchURLArgs			= "+%2Fjson";

	private static Settings					_Instance				= new Settings();

	private final ReentrantReadWriteLock	_readWriteLock			= new ReentrantReadWriteLock();
	private final Lock						_read					= _readWriteLock.readLock();
	private final Lock						_write					= _readWriteLock.writeLock();

	public static Settings getInstance()
	{
		return _Instance;
	}

	private Settings()
	{
	}

	public void putSettings(int threshold, int searchSentenceLength, int searchJumpLength, int compareSentenceLength, int compareJumpLength, boolean checkWWW, ArrayList<Integer> localFolders)
	{
		_write.lock();
		try
		{
			_threshold = threshold;
			_searchSentenceLength = searchSentenceLength;
			_searchJumpLength = searchJumpLength;
			_compareSentenceLength = compareSentenceLength;
			_compareJumpLength = compareJumpLength;
			_checkWWW = checkWWW;
			if (localFolders != null)
			{
				_localFolders = localFolders;
			}
		}
		finally
		{
			_write.unlock();
		}
	}

	public void putSettings(int threshold, int searchSentenceLength, int searchJumpLength, int compareSentenceLength, int compareJumpLength, boolean checkWWW, ArrayList<Integer> localFolders, String searchUrl, String searchSearchArg, String searchAuthArg, String searchUrlArgs)
	{
		putSettings(threshold, searchSentenceLength, searchJumpLength, compareSentenceLength, compareJumpLength, checkWWW, localFolders);
		_write.lock();
		try
		{
			_searchURL = searchUrl;
			_searchSearchArg = searchSearchArg;
			_searchAuthArg = searchAuthArg;
			_searchURLArgs = searchUrlArgs;
		}
		finally
		{
			_write.unlock();
		}
	}

	public double getThreshold()
	{
		_read.lock();
		try
		{
			return _threshold;
		}
		finally
		{
			_read.unlock();
		}
	}

	public int getSearchSentenceLength()
	{
		_read.lock();
		try
		{
			return _searchSentenceLength;
		}
		finally
		{
			_read.unlock();
		}
	}

	public int getSearchJumpLength()
	{
		_read.lock();
		try
		{
			return _searchJumpLength;
		}
		finally
		{
			_read.unlock();
		}
	}

	public int getCompareSentenceLength()
	{
		_read.lock();
		try
		{
			return _compareSentenceLength;
		}
		finally
		{
			_read.unlock();
		}
	}

	public int getCompareJumpLength()
	{
		_read.lock();
		try
		{
			return _compareJumpLength;
		}
		finally
		{
			_read.unlock();
		}
	}

	public boolean getCheckWWW()
	{
		_read.lock();
		try
		{
			return _checkWWW;
		}
		finally
		{
			_read.unlock();
		}
	}

	public ArrayList<Integer> getLocalFolders()
	{
		_read.lock();
		try
		{
			return _localFolders;
		}
		finally
		{
			_read.unlock();
		}
	}

	public String getSearchURL()
	{
		_read.lock();
		try
		{
			return _searchURL;
		}
		finally
		{
			_read.unlock();
		}
	}

	public String getSearchAuthArg()
	{
		_read.lock();
		try
		{
			return _searchAuthArg;
		}
		finally
		{
			_read.unlock();
		}
	}

	public String getSearchSearchArg()
	{
		_read.lock();
		try
		{
			return _searchSearchArg;
		}
		finally
		{
			_read.unlock();
		}
	}

	public String getSearchURLArgs()
	{
		_read.lock();
		try
		{
			return _searchURLArgs;
		}
		finally
		{
			_read.unlock();
		}
	}

}