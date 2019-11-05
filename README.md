# assurityassigment
REST API automation framework with BDD approach


This framework desgin is data driven framework in which we have leveraged REST assured and Cucumber frameworks.
Feature folder contains feature files which you want to run and Fixture contains excel sheet which would be read at run time as per the name you have provided in the feature file
# com.assurityassignment.steps.RestSteps
This class contains Steps for creating feature file which would load the excel sheet and read the content which should have headers in following order :-
- Test_Id (This is used to identify the particular Scenario)
- Method_Id (This is used to identify the particular method under test, method signifies a particular endpoint testing in E2E test of a particular scenario)
- Server_URL (URL signifies the server at which your Endpoint is exposed)
- Endpoint (Endpoint path on server)
- HeaderKey 
- HeaderValue
- RequestKey
- RequestValue
- ResponseKey
- ResponseValue
- ExpectedResponseStatus
- HTTPMethod
- QueryString

In case of nested api Testing( Meaning in whole E2E testing one api response value you have provide to request of other API), then you can provide it in RequesdKey the key object whose value you want to change then provide the RequestValue as "${<method id from which you want the value>.response.<key object>}" for example:- ${m002.response.name.[0].firstname}
  
  
  # com.assurityassignment.runner.RunnerClass
  This class contains all cucumberoptions  used for running a particular test. Under tags set of cucumber options, you have to provide the tag as "@Test_Id_<your test id you have mentioned in excel sheet>". right click on it and run it as a JUnit test.
  
  
  There are utility classes present like ExcelUtil (contains functions for Excel Reading), JSONUtils(contains functions for JSON file reading), RestAssuredUtils (Contains functions for creating rest client and triggering the request for a particular endpoint).
  
  JSONUtils class contains functions like parseJSONObjecttoMap which will convert your JSON string into key objects.
 For Example:-
 {\"CategoryId\":6327,\"Name\":\"Carbon credits\",\"Path\":\"/Business-farming-industry/Carbon-credits\",\"CanListAuctions\":true,\"CanListClassifieds\":true,\"CanRelist\":true,\"LegalNotice\":\"Compliance Declaration\\nI confirm that these carbon credits are certified and registered with a carbon credits registry. \",\"DefaultDuration\":14,\"AllowedDurations\":[2,3,4,5,6,7,10,14],\"Fees\":{\"Bundle\":15,\"EndDate\":0.25,\"Feature\":10,\"Gallery\":2,\"Listing\":29,\"Reserve\":0.25,\"Subtitle\":0.55,\"TenDays\":0.25,\"ListingFeeTiers\":[{\"MinimumTierPrice\":0,\"FixedFee\":29}],\"SecondCategory\":0.99},\"FreePhotoCount\":20,\"MaximumPhotoCount\":20,\"IsFreeToRelist\":true,\"Promotions\":[{\"Id\":1,\"Name\":\"Basic\",\"Description\":\"Lowest position in category\",\"Price\":0,\"MinimumPhotoCount\":0},{\"Id\":2,\"Name\":\"Gallery\",\"Description\":\"Good position in category \\n2x larger image in desktop site search results\",\"Price\":2,\"OriginalPrice\":2,\"MinimumPhotoCount\":0},{\"Id\":3,\"Name\":\"Feature\",\"Description\":\"Better position in category \\n4x larger image in desktop site search results\",\"Price\":10,\"OriginalPrice\":10,\"Recommended\":true,\"MinimumPhotoCount\":0},{\"Id\":4,\"Name\":\"Feature Combo\",\"Description\":\"Best position in category \\nIncludes benefits of Feature \\nHighlights listing in search results\",\"Price\":15,\"OriginalPrice\":15,\"MinimumPhotoCount\":0}],\"EmbeddedContentOptions\":[],\"MaximumTitleLength\":80,\"AreaOfBusiness\":1,\"DefaultRelistDuration\":7}
 
 into key - value pair:-
 CategoryId=======>6327
Path=======>/Business-farming-industry/Carbon-credits
Promotions.[0].MinimumPhotoCount=======>0
Promotions.[0].Description=======>Lowest position in category
Promotions.[0].Price=======>0
Promotions.[0].Id=======>1
Promotions.[0].Name=======>Basic
Promotions.[1].MinimumPhotoCount=======>0
Promotions.[1].Description=======>Good position in category 
2x larger image in desktop site search results
Promotions.[1].OriginalPrice=======>2
Promotions.[1].Price=======>2
Promotions.[1].Id=======>2
Promotions.[1].Name=======>Gallery
Promotions.[2].MinimumPhotoCount=======>0
Promotions.[2].Description=======>Better position in category 
4x larger image in desktop site search results
Promotions.[2].OriginalPrice=======>10
Promotions.[2].Price=======>10
Promotions.[2].Recommended=======>true
Promotions.[2].Id=======>3
Promotions.[2].Name=======>Feature
Promotions.[3].MinimumPhotoCount=======>0
Promotions.[3].Description=======>Best position in category 
Includes benefits of Feature 
Highlights listing in search results
Promotions.[3].OriginalPrice=======>15
Promotions.[3].Price=======>15
Promotions.[3].Id=======>4
Promotions.[3].Name=======>Feature Combo
AllowedDurations.[0]=======>2
AllowedDurations.[1]=======>3
AllowedDurations.[2]=======>4
AllowedDurations.[3]=======>5
AllowedDurations.[4]=======>6
AllowedDurations.[5]=======>7
AllowedDurations.[6]=======>10
AllowedDurations.[7]=======>14
IsFreeToRelist=======>true
CanListAuctions=======>true
Fees.Reserve=======>0.25
Fees.Subtitle=======>0.55
Fees.ListingFeeTiers.[0].FixedFee=======>29
Fees.ListingFeeTiers.[0].MinimumTierPrice=======>0
Fees.Listing=======>29
Fees.Bundle=======>15
Fees.Gallery=======>2
Fees.TenDays=======>0.25
Fees.EndDate=======>0.25
Fees.Feature=======>10
Fees.SecondCategory=======>0.99
Name=======>Carbon credits
CanListClassifieds=======>true
AreaOfBusiness=======>1
DefaultRelistDuration=======>7
MaximumPhotoCount=======>20
CanRelist=======>true
LegalNotice=======>Compliance Declaration
I confirm that these carbon credits are certified and registered with a carbon credits registry. 
DefaultDuration=======>14
MaximumTitleLength=======>80
FreePhotoCount=======>20
