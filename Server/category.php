<?php

class CategoryData {
  public $id;
	public $name;

	static function initFromFileString($line) {
		$datas = explode(",", $line);
		if (count($datas) == 2) {
      $categoryData = new CategoryData();
      $categoryData->id = $datas[0];
			$categoryData->name = $datas[1];
			return $categoryData;
		}
		return null;
	}

  function toFileString() {
    $str = "";
    $str .= $this->id;
    $str .= ",";
    $str .= $this->name;
    $str .= "\n";
    return $str;
  }
}

class Category {

  const FILE_NAME = "data/category.txt";

	static function readAll() {
		if (file_exists(Category::FILE_NAME)) {
			$fileData = file_get_contents(Category::FILE_NAME);
			if ($fileData !== false) {
				$dataList = [];
				$lines = explode("\n", $fileData);
				for ($i = 0; $i < count($lines); $i++) {
					$data = CategoryData::initFromFileString($lines[$i]);
					if (!is_null($data)) {
						$dataList[] = $data;
					}
				}
				return $dataList;
			}
		}
		return [];
	}
}

?>
