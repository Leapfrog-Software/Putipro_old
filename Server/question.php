<?php

class QuestionData {
  public $senderId;
	public $question;
  public $answer;

	static function initFromFileString($line) {
		$datas = explode(",", $line);
		if (count($datas) == 3) {
      $questionData = new QuestionData();
      $questionData->id = $datas[0];
			$questionData->question = $datas[1];
      $questionData->answer = $datas[2];
			return $questionData;
		}
		return null;
	}

  function toFileString() {
    $str = "";
    $str .= $this->senderId;
    $str .= ",";
    $str .= $this->question;
    $str .= ",";
    $str .= $this->answer;
    $str .= "\n";
    return $str;
  }
}

class Question {

  const FILE_NAME = "data/question.txt";

	static function readAll() {
		if (file_exists(Question::FILE_NAME)) {
			$fileData = file_get_contents(Question::FILE_NAME);
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

  static function create($senderId, $question) {

    $questionData = new QuestionData();
    $questionData->senderId = $senderId;
    $questionData->question = $question;
    $questionData->answer = "";

    return file_put_contents(Question::FILE_NAME, $questionData->toFileString(), FILE_APPEND) !== FALSE;
  }
}

?>
