# WeatherMapAutomation

How to run:
1. Open project folder name: WeatherMapAutomation
2. For test scenario using excel input is from directory: testData/WeatherData.xlsx
3. Open src/test/java/api/test/
4. Run DataDriven_WeatherTests.java for testing using data driven using point number 2 above
5. For test scenario using direct code Weather API,
6. Run Simple_WeatherTest.java
7. For test scenario using direct code Air Pollution API
8. Run Simple_AirPollutionTest


Test cases using Excel WeatherData.xlsx for API https://api.openweathermap.org/data/2.5/weather

template 1:
>>As a QA Engineer I want to validate the weather of a given location
>>Given a city_name
>>When I pass the <city_name>,<units>,<mode>,<lang>  to my test script / method
>>Then the script prints the current weather details of that location in <unit_measurements>

1. Validate weather of Singapore in standard with description in English using JSON mode
Given a city name
When I pass the Singapore, -, -, - to my test script
Then the script prints
currently Singapore has temperature of 86.43 Fahrenheit
Singapore feels like 99.03 Fahrenheit
Singapore has moderate rain

2.Validate weather of Jakarta in standard with description in Korean using JSON mode
Given a city name
When I pass the Jakarta, standard, -, Kr to my test script
Then the script prints
currently Jakarta has temperature of 86.43 Fahrenheit
Jakarta feels like 99.03 Fahrenheit
Jakarta has 보통 비

3.Validate weather of Medan in metric with description in English using JSON mode
Given a city name
When I pass the Medan, metric, -, - to my test script
Then the script prints
currently Medan has temperature of 32.1 Celcius
Medan feels like 33.03 Celcius
Medan has few clouds

4.Validate weather of Kuala Lumpur in imperial with description in Vietnamese using JSON mode
Given a city name
When I pass the Kuala Lumpur, imperial , -, Vi to my test script
Then the script prints
currently Kuala Lumpur has temperature of 87.84 Fahrenheit
Kuala Lumpur  feels like 88.84 Fahrenheit
Kuala Lumpur  has mây thưa

5.Validate weather of Singapore in metric with description in English using XML mode
Given a city name
When I pass the Singapore, metric , XML, -  to my test script
Then the script prints
currently Singapore has temperature of 28.73 Celsius
Singapore feels like 33.04 Celsius
Singapore has Light breeze wind

6.Validate weather of Jakarta in imperial with description in Indonesian using XML mode
Given a city name
When I pass the Jakarta, imperial , XML, id  to my test script
Then the script prints
currently Jakarta has temperature of 86.4 Fahrenheit
Jakarta feels like 98.94 Fahrenheit
Jakarta has awan hujan

7. Validate weather of Jakarta in standard units with description in default language using default mode (JSON)
Given a city name
When I pass the Jakarta, - , -, -  to my test script
Then the script prints
currently Jakarta has temperature of 303.37 Kelvin
Jakarta feels like 310.34 Kelvin
Jakarta has [moderate rain]

8.Validate response when city name not found
Given a city name
When I pass the ABCD, - , -, -  to my test script
Then the script prints
Oops city not found

template 2:
>>As a QA Engineer I want to validate the weather of a given coordinate

>>Given a coordinate
>>When I pass the <latitude>,<longitude>,<units>,<mode>,<lang>  to my test script 
>>Then the script prints the current weather details of that location in <unit_measurements>

9. validate given coordinate using units standard
Given a coordinate
When I pass 1.357, 103.819,standard,-,-  to my test script 
Then the script prints
currently Bright Hill Crescent has temperature of 301.89 Kelvin
Bright Hill Crescent feels like 306.21 Kelvin
Bright Hill Crescent has [light intensity shower rain]

10. validate given coordinate using units metric
Given a coordinate
When I pass -6.174,106.788,metric,-,-  to my test script 
Then the script prints
currently Cideng has temperature of 29.92 Celsius
Cideng feels like 36.92 Celsius
Cideng has [moderate rain]

11. validate given coordinate using units imperial and in language France
Given a coordinate
When I pass 1.344,103.801,imperial,-,fr  to my test script 
Then the script prints
currently Chinese Gardens has temperature of 83.8 Fahrenheit
Chinese Gardens feels like 91.35 Fahrenheit
Chinese Gardens has [petites averses]

12.validate given coordinate using units metric and in XML mode
Given a coordinate
When I pass -6.174,106.788,metric,xml-,-  to my test script 
Then the script prints
currently Cideng has temperature of 29.92 Celsius
Cideng feels like 36.92 Celsius
Cideng has Light breeze wind

13.validate given coordinate using units imperial and in XML mode
Given a coordinate
When I pass 1.344, 103.801, ,imperial,xml,-  to my test script 
Then the script prints
currently Chinese Gardens has temperature of 83.8 Fahrenheit
Chinese Gardens feels like 91.35 Fahrenheit
Chinese Gardens has Light breeze wind

14.validate response when given wrong latitude and longitude
Given a coordinate
When I pass 100000, 100200,-,-,-  to my test script 
Then the script prints
Oops wrong latitude

template 3
>>As a QA Engineer I want to validate the weather of a given city ID
>>Given a city ID
>When I pass the <city_ID>,<units>,<mode>,<lang>  to my test script / method
>>Then the script / method prints the current weather details of that location in <unit_measurements>

15.validate given cityID in imperial units and default language
Given a city id
When I pass 1880251,imperial,-,-  to my test script
Then the script prints
currently Singapore has temperature of 83.91 Fahrenheit
Singapore feels like 91.6 Fahrenheit
Singapore has [light intensity shower rain]


16.validate given cityID in standard units and language Japan
// some language like JP and many more registered language on the API doc turn out doesn’t return description weather in the referred language, instead still using English, so I will write as it is the response return. It’s not that JP not listed on the API doc. JP is listed.

Given a city id
When I pass 1880252,standard,-,jp  to my test script
Then the script prints
currently Singapore has temperature of 301.88 Kelvin
Singapore feels like 306.19 Kelvin
Singapore has [light intensity shower rain]


17.validate given cityID in metric units and language Indonesian
Given a city id
When I pass 1880294,metric,-,id  to my test script
Then the script prints
currently Seletar has temperature of 29 Celsius
Seletar feels like 33.69 Celsius
Seletar has [hujan sesaat berintensitas rendah]


18.validate given cityID in imperial units and default language using XML mode
Given a city id
When I pass 1880574,imperial,xml,-  to my test script
Then the script prints
currently Kampong Pasir Ris has temperature of 83.57 Fahrenheit
Kampong Pasir Ris feels like 91.13 Fahrenheit
Kampong Pasir Ris has Light breeze wind


19.validate given cityID in standard units and Indonesian language using XML mode
Given a city id
When I pass 1880701,-,xml,id  to my test script
Then the script prints
currently Chye Kay has temperature of 302.17 Kelvin
Chye Kay feels like 306.69 Kelvin
Chye Kay has Light breeze wind


20.validate response when cityID is not found
Given a city id
When I pass 1880701222,-,-,-  to my test script
Then the script prints
Oops city not found

template 4
>>As a QA Engineer I want to validate the weather of a given zipcode
>>Given a zip ID
>>When I pass the <zip_Id>,<units>,<mode>,<lang>  to my test script / method
>>Then the script / method prints the current weather details of that location in <unit_measurements>

21. Validate given zipID in imperial units using default JSON mode and default language English
Given a zip ID
When I pass 94040,imperial,-,-
Then the script prints
currently Mountain View has temperature of 48.58 Fahrenheit
Mountain View feels like 48.58 Fahrenheit
Mountain View has [clear sky]

22.Validate given zipID in imperial units using XML mode and default language English
Given a zip ID
When I pass 94040,imperial,-,-
Then the script prints
currently Mountain View has temperature of 48.58 Fahrenheit
Mountain View feels like 48.58 Fahrenheit
Mountain View has Setting wind

23.Validate response when zipID is not found
Given a zip ID
When I pass 94040111,-,xml,-
Then the script prints
Oops city not found

******
We can have many test cases by checking unto those combinations. 
Also the other negative test cases like:
-Not exist city name/ zip id/ city id/ lat/ lon
-Invalid API Key
-Not inputting one or more of the mandatory request parameters 
*****


Test cases for API http://api.openweathermap.org/data/2.5/air_pollution?

1. validate pollution with given exist coordinate
Given a coordinate 
When user input the coordinate to the test method currentPollutionBasedOnCoordinate
Then user will get output
  the air quality index is <aqi>
  the pollution components compositions are: {co2:12.12, co:2.1…}


2.validate pollution response when latitude and longitude is not found
Given a coordinate 
When user input the coordinate to the test method currentPollutionWithCoordinateNotExist
Then user will get output:
  oops wrong latitude

3. validate pollution response when longitude is not found
Given a coordinate 
When user input the coordinate to the test method currentPollutionWitLongitudeNotExist
Then user will get output
  oops wrong longitude

4. validate pollution response when latitude is not found
Given a coordinate 
When user input the coordinate to the test method  currentPollutionWitLatitudeNotExist
Then user will get output
  oops wrong latitude

5. validate pollution given coordinate with wrong appId
Given a coordinate 
When user input the coordinate to the test method pollutionWithWrongAppId
Then user will get output
  oops Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.

6. validate pollution given coordinate with empty appId
Given a coordinate 
When user input the coordinate to the test method pollutionWithEmptyAppId
Then user will get output
  oops Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.


