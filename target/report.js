$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("API_Test.feature");
formatter.feature({
  "line": 1,
  "name": "Testing the given API",
  "description": "",
  "id": "testing-the-given-api",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "testing the API",
  "description": "",
  "id": "testing-the-given-api;testing-the-api",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@Test_Id_1234"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "user loads the excel sheet \"./Fixture/Test_API_Excel.xlsx\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "user validates status code for method \"m001\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "user validates response for method \"m001\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "./Fixture/Test_API_Excel.xlsx",
      "offset": 28
    }
  ],
  "location": "RestSteps.user_loads_the_excel_sheet_something(String)"
});
formatter.result({
  "duration": 1988894300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "m001",
      "offset": 39
    }
  ],
  "location": "RestSteps.user_validates_status_code_for_method_something(String)"
});
formatter.result({
  "duration": 6957231900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "m001",
      "offset": 36
    }
  ],
  "location": "RestSteps.user_validates_response_for_method_something(String)"
});
formatter.result({
  "duration": 55505600,
  "status": "passed"
});
});