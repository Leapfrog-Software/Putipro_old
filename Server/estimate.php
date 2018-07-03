<?php

class EstimateData {
 
 	public $senderId;
	public $receiverId;
	public $score;
	public $comment;

	static function initFromFileString($line) {
		$datas = explode(",", $line);
		if (count($datas) == 4) {
      $estimateData = new EstimateData();
      $estimateData->senderId = $datas[0];
			$estimateData->receiverId = $datas[1];
      $estimateData->score = $datas[2];
      $estimateData->comment = $datas[3];
			return $estimateData;
		}
		return null;
	}

  function toFileString() {
    $str = "";
    $str .= $this->senderId;
    $str .= ",";
    $str .= $this->receiverId;
    $str .= ",";
    $str .= $this->score;
        $str .= ",";
    $str .= $this->comment;
    $str .= "\n";
    return $str;
  }
}

class Estimate {

  const FILE_NAME = "data/estimate.txt";

	static function readAll() {
		if (file_exists(Estimate::FILE_NAME)) {
			$fileData = file_get_contents(Estimate::FILE_NAME);
			if ($fileData !== false) {
				$dataList = [];
				$lines = explode("\n", $fileData);
				for ($i = 0; $i < count($lines); $i++) {
					$data = Estimate::initFromFileString($lines[$i]);
					if (!is_null($data)) {
						$dataList[] = $data;
					}
				}
				return $dataList;
			}
		}
		return [];
	}

  static function create($senderId, $receiverId, $score, $comment) {

    $estimateData = new EstimateData();
    $estimateData->senderId = $senderId;
    $estimateData->receiverId = $receiverId;
    $estimateData->score = $score;
    $estimateData->comment = $comment;

    return file_put_contents(Estimate::FILE_NAME, $questionData->toFileString(), FILE_APPEND) !== FALSE;
  }
}

?>
