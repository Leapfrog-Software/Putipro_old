<?php

require "user.php";
require "category.php";
require "work.php";
require "question.php";
require "estimate.php";

$command = $_POST["command"];

if (strcmp($command, "createUser") == 0) {
  createUser();
} else if (strcmp($command, "getUser") == 0) {
  getUser();
} else if (strcmp($command, "createWork") == 0) {
  createWork();
} else if (strcmp($command, "getCategory") == 0) {
  getCategory();
} else if (strcmp($command, "getWork") == 0) {
  getWork();
} else if (strcmp($command, "updateWorkerProfile") == 0) {
  updateWorkerProfile();
} else if (strcmp($command, "applyWork") == 0) {
  applyWork();
} else if (strcmp($command, "postQuestion") == 0) {
  postQuestion();
} else if (strcmp($command, "getQuestion") == 0) {
  getQuestion();
} else if (strcmp($command, "postEstimate") == 0) {
  postEstimate();
} else if (strcmp($command, "getEstimate") == 0) {
  getEstimate();
}
else {
  echo("unknown");
}

function createUser() {

  $userId = User::create();
  if (is_null($userId)) {
    echo(json_encode(Array("result" => "1")));
  } else {
    echo(json_encode(Array("result" => "0", "userId" => $userId)));
  }
}

function getUser() {

  $userList = User::readAll();
  $users = [];
  foreach ($userList as $userData) {
    $users[] = Array("id" => $userData->id,
                    "name" => $userData->name,
                    "age" => $userData->age,
                    "gender" => $userData->gender,
                    "message" => $userData->message,
                    "phone" => $userData->phone,
                    "interviewDate" => $userData->interviewDate);
  }
  echo(json_encode(Array("result" => "0", "users" => $users)));
}

function createWork() {

  $categoryId = $_POST["categoryId"];
  $description = $_POST["description"];
  $fee = $_POST["date"];
  $date = $_POST["date"];
  $ordererId = $_POST["ordererId"];
  $receiverId = $_POST["receiverId"];

  if (Work::create($categoryId, $description, $fee, $date, $ordererId, $receiverId)) {
    echo(json_encode(Array("result" => "0")));
  } else {
    echo(json_encode(Array("result" => "1")));
  }
}

function getCategory() {

  $categoryList = Category::readAll();
  $categories = [];
  foreach ($categoryList as $categoryData) {
    $categories[] = Array("id" => $categoryData->id,
                          "name" => $categoryData->name);
  }
  echo(json_encode(Array("result" => "0", "categories" => $categories)));
}

function getWork() {

  $workList = Work::readAll();
  $works = [];
  foreach ($workList as $workData) {
    $works[] = Array("id" => $workData->id,
                    "categoryId" => $workData->categoryId,
                    "description" => $workData->description,
                    "fee" => $workData->fee,
                    "date" => $workData->date,
                    "ordererId" => $workData->ordererId,
                    "receiverId" => $workData->receiverId);
  }
  echo(json_encode(Array("result" => "0", "works" => $works)));
}

function updateWorkerProfile() {

  $id = $_POST["id"];
  $name = $_POST["name"];
  $age = $_POST["age"];
  $gender = $_POST["gender"];
  $message = $_POST["message"];
  $phone = $_POST["phone"];
  $interviewDate = $_POST["interviewDate"];

  if (User::updateWorkerProfile($id, $name, $age, $gender, $message, $phone, $interviewDate)) {
    echo(json_encode(Array("result" => "0")));
  } else {
    echo(json_encode(Array("result" => "1")));
  }
}

function applyWork() {

  $userId = $_POST["userId"];
  $workId = $_POST["workId"];

  if (Work::apply($userId, $workId)) {
    echo(json_encode(Array("result" => "0")));
  } else {
    echo(json_encode(Array("result" => "1")));
  }
}

function postQuestion() {

  $userId = $_POST["userId"];
  $question = $_POST["question"];

  if (Question::create($userId, $question)) {
    echo(json_encode(Array("result" => "0")));
  } else {
    echo(json_encode(Array("result" => "1")));
  }
}

function getQuestion() {

  $questionList = Question::readAll();
  $questions = [];
  foreach ($questionList as $questionData) {
    $questions[] = Array("senderId" => $questionData->senderId,
                          "question" => $questionData->question,
                          "answer" => $questionData->answer);
  }
  echo(json_encode(Array("result" => "0", "questions" => $questions)));
}

function postEstimate() {
  
  $senderId = $_POST["senderId"];
  $receiverId = $_POST["receiverId"];
  $score = $_POST["score"];
  $comment = $_POST["comment"];

  if (Estimage::create($userId, $receiverId, $score, $comment)) {
    echo(json_encode(Array("result" => "0")));
  } else {
    echo(json_encode(Array("result" => "1")));
  }
}

function getEstimate() {
  
  $estimateList = Estimate::readAll();
  $estimates = [];
  foreach ($estimateList as $estimateData) {
    $estimates[] = Array("senderId" => $estimateData->senderId,
                          "receiverId" => $estimateData->receiverId,
                          "score" => $estimateData->score,
                          "comment" => $estimateData->comment);
  }
  echo(json_encode(Array("result" => "0", "estimates" => $estimates)));
}

function DebugSave($str){

	date_default_timezone_set('Asia/Tokyo');

	$d = date('Y-m-d H:i:s');
	$s = $d . " " . $str . "\r\n";
	file_put_contents("debug.txt", $s, FILE_APPEND);
}

?>
