Feature: Testing the given API

@Test_Id_1234
Scenario: testing the API
Given user loads the excel sheet "./Fixture/Test_API_Excel.xlsx"
And user validates status code for method "m001"
And user validates response for method "m001"