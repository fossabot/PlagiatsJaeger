<?php
class Report {

	/**
	 * Saves a new report and starts the backend functionality.
	 * @param int $dID
	 * @param int $rTreshold
	 * @param int $rLevel
	 * @return boolean
	 */
	public static function createReport($dID, $rTreshold = 50, $rLevel = 1, $sID = 1, $rErrorCode = 100) {
		if (Validator::validate(VAL_INTEGER, $dID, true)) {
			$db = new db();
			if ($db -> insert('report', array('dID' => $dID, 'rDatetime' => date('Y-m-d H:m:s'), 'rTreshold' => $rTreshold, 'rLevel' => $rLevel, 'sID' => $sID, 'rErrorCode' => $rErrorCode))) {
				$lastReportID = $db -> lastInsertId();
				$result = file("http://192.168.4.28:8080/PlagiatsJaeger/ReportServlet?rID=" . $lastReportID);
				print_array($result);
				if ($result) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns all reports from given document id.
	 * @param int $dID
	 * @return array
	 */
	public static function getReportsFromDocumentID($dID) {
		if (Validator::validate(VAL_INTEGER, $dID, true)) {
			$db = new db();
			$db -> read("
				SELECT
					r.rID, r.rDatetime, r.rTreshold, r.rLevel, r.dID
				FROM
					report AS r
				WHERE
					r.dID = '$dID'
				ORDER BY
					r.rDatetime DESC");

			return $db -> linesAsArray();
		}
	}

	public static function createGraphicReport($uID) {
		// TODO: createGraphicReport not imp.
		throw new Exception('Not implemented');
	}

	public static function createShortReport($uID) {
		// TODO: createShortReport not imp.
		throw new Exception('Not implemented');
	}

	public static function createSearchReport($uID) {
		// TODO: createReport not imp.
		throw new Exception('Not implemented');
	}

	public static function showGraphicReport($uID) {
		// TODO: showGraphicReport not imp.
		throw new Exception('Not implemented');
	}

	public static function showShortReport($uID) {
		// TODO: showShortReport not imp.
		throw new Exception('Not implemented');
	}

	public static function showSearchReport($uID) {
		// TODO: showReport not imp.
		throw new Exception('Not implemented');
	}

}
?>