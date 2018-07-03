<?php

class WorkData {
  public $id;
	public $categoryId;
  public $description;
  public $fee;
  public $date;
  public $ordererId;
  public $candidateId;
  public $receiverId;

	static function initFromFileString($line) {
		$datas = explode(",", $line);
		if (count($datas) == 8) {
      $workData = new WorkData();
      $workData->id = $datas[0];
			$workData->categoryId = $datas[1];
      $workData->description = $datas[2];
      $workData->fee = $datas[3];
      $workData->date = $datas[4];
      $workData->ordererId = $datas[5];
      $workData->candidateId = $datas[6];
      $workData->receiverId = $datas[7];
			return $workData;
		}
		return null;
	}

  function toFileString() {
    $str = "";
    $str .= $this->id;
    $str .= ",";
    $str .= $this->categoryId;
    $str .= ",";
    $str .= $this->description;
    $str .= ",";
    $str .= $this->fee;
    $str .= ",";
    $str .= $this->date;
    $str .= ",";
    $str .= $this->ordererId;
    $str .= ",";
    $str .= $this->candidateId;
    $str .= ",";
    $str .= $this->receiverId;
    $str .= "\n";
    return $str;
  }
}

class Work {

  const FILE_NAME = "data/work.txt";

	static function readAll() {
		if (file_exists(Work::FILE_NAME)) {
			$fileData = file_get_contents(Work::FILE_NAME);
			if ($fileData !== false) {
				$dataList = [];
				$lines = explode("\n", $fileData);
				for ($i = 0; $i < count($lines); $i++) {
					$data = UserData::initFromFileString($lines[$i]);
					if (!is_null($data)) {
						$dataList[] = $data;
					}
				}
				return $dataList;
			}
		}
		return [];
	}

  static function create($categoryId, $description, $fee, $date, $ordererId, $candidateId) {

    $maxWorkId = -1;

    $workList = Work::readAll();
    foreach ($workList as $workData) {
      $workId = intval($workData->id);
      if ($workId > $maxWorkId) {
        $maxWorkId = $workId;
      }
    }

    $nextWorkId = strval($maxWorkId + 1);

    $workData = new WorkData();
    $workData->id = $nextWorkId;
    $workData->categoryId = $categoryId;
    $workData->description = $description;
    $workData->fee = $fee;
    $workData->date = $date;
    $workData->ordererId = $ordererId;
    $workData->candidateId = $candidateId;
    $workData->receiverId = "";

    return file_put_contents(Work::FILE_NAME, $workData->toFileString(), FILE_APPEND) !== FALSE;
  }

  static function apply($userId, $workId) {

    $find = false;

    $workList = Work::readAll();
    foreach ($workList as &$workData) {
      if (strcmp($workData->id, $workId) == 0) {
        $workData->receiverId = $userId;
        $find = true;
        break;
      }
    }

    $str = "";
    foreach($workList as $data) {
      $str .= $data->toFileString();
    }
    return file_put_contents(Work::FILE_NAME, $str) !== false;
  }
}

?>
