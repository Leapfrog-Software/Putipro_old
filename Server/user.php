<?php

class UserData {
  public $id;
	public $name;
  public $age;
  public $gender;
  public $message;
  public $phone;
  public $interviewDate;

	static function initFromFileString($line) {
		$datas = explode(",", $line);
		if (count($datas) == 7) {
      $userData = new UserData();
      $userData->id = $datas[0];
			$userData->name = $datas[1];
      $userData->age = $datas[2];
      $userData->gender = $datas[3];
      $userData->message = $datas[4];
      $userData->phone = $datas[5];
      $userData->interviewDate = $datas[6];
			return $userData;
		}
		return null;
	}

  function toFileString() {
    $str = "";
    $str .= $this->id;
    $str .= ",";
    $str .= $this->name;
    $str .= ",";
    $str .= $this->age;
    $str .= ",";
    $str .= $this->gender;
    $str .= ",";
    $str .= $this->message;
    $str .= ",";
    $str .= $this->phone;
    $str .= ",";
    $str .= $this->interviewDate;
    $str .= "\n";
    return $str;
  }
}

class User {

  const FILE_NAME = "data/user.txt";

	static function readAll() {
		if (file_exists(User::FILE_NAME)) {
			$fileData = file_get_contents(User::FILE_NAME);
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

  static function create() {

    $maxUserId = -1;

    $userList = User::readAll();
    foreach ($userList as $userData) {
      $userId = intval($userData->id);
      if ($userId > $maxUserId) {
        $maxUserId = $userId;
      }
    }

    $nextUserId = strval($maxUserId + 1);

    $userData = new UserData();
    $userData->id = $nextUserId;
    $userData->name = "";
    $userData->age = "";
    $userData->gender = "";
    $userData->message = "";
    $userData->phone = "";
    $userData->interviewDate = "";

    if (file_put_contents(User::FILE_NAME, $userData->toFileString(), FILE_APPEND) !== FALSE) {
      return $nextUserId;
    } else {
      return null;
    }
  }

  static function updateWorkerProfile($id, $name, $age, $gender, $message, $phone, $interviewDate) {

    $userList = User::readAll();
    $find = false;

    foreach ($userList as &$userData) {
      if (strcmp($userData->id, $id) == 0) {
        $userData = new UserData();
        $userData->id = $id;
        $userData->name = $name;
        $userData->age = $age;
        $userData->gender = $gender;
        $userData->message = $message;
        $userData->phone = $phone;
        $userData->interviewDate = $interviewDate;

        $find = true;
        break;
      }
    }
    if (!$find) {
      return false;
    }

    $str = "";
    foreach($userList as $data) {
      $str .= $data->toFileString();
    }
    return file_put_contents(User::FILE_NAME, $str) !== false;
  }
}

?>
