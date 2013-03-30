<?php
class Result {

	public static function getResultsFromReportID($rID) {
		if (Validator::validate(VAL_INTEGER, $rID, true)) {
			$db = new db();
			$db -> read("
				SELECT
					rt.rtID, rt.rtSequence, rt.rtText, rt.rtSourceLink, rt.rtSourceText, rt.rID
				FROM
					result AS rt
				WHERE
					rt.rID = '$rID'
				ORDER BY
					rt.rtSequence ASC");

			return $db -> linesAsArray();
		}
	}
}
?>