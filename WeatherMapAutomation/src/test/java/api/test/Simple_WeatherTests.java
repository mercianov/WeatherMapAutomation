package api.test;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.path.xml.XmlPath;

import api.endpoints.WeatherEndPoints;

import io.restassured.response.Response;


public class Simple_WeatherTests {public Logger logger;

	@Test(priority = 1, testName = "validate weather with given city Jakarta in imperial units")
	public void checkWeatherByCityName(){
		Response response= WeatherEndPoints.cityBasedWeather("jakarta","imperial","","");
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		AssertJUnit.assertEquals(jsonPathEvaluator.getDouble("coord.lon"),106.8451);
		AssertJUnit.assertEquals(jsonPathEvaluator.getDouble("coord.lat"),-6.2146);
		AssertJUnit.assertEquals(jsonPathEvaluator.get("sys.country"),"ID");
		AssertJUnit.assertEquals(jsonPathEvaluator.get("name"),"Jakarta");
		try {
			System.out.println("currently " + jsonPathEvaluator.get("name") + " has temperature of " + jsonPathEvaluator.get("main.temp")+" Fahrenheit");
			System.out.println(jsonPathEvaluator.get("name") + " feels like " + jsonPathEvaluator.get("main.feels_like") +" Fahrenheit");
			System.out.println(jsonPathEvaluator.get("name") + " has " + jsonPathEvaluator.get("weather.description"));
		} catch (JsonPathException e) {
			System.err.println("Error processing JSON response: " + e.getMessage());
		}
		response.then().log().all();
	}

	@Test(priority = 2, testName = "validate weather with given coordinate in metric units")
	public void checkWeatherByCoordinate(){
		Response response= WeatherEndPoints.coordinateBasedWeather("-6.174067","106.788627","metric","","");
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		response.then().log().all();
	}

	@Test(priority = 3, testName = "validate weather with given zipcode in france language")
	public void checkWeatherByZipId(){
		Response response= WeatherEndPoints.zipIdBasedWeather("94040","","","fr");
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("the weather description in France: "+ jsonPathEvaluator.get("weather.description") +", prends soin de toi, merci beaucoup");
		response.then().log().all();
	}
	@Test(priority = 4)
	public void checkWeatherByCityId(){
		Response response= WeatherEndPoints.cityIdBasedWeather("2172797","","","");
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		response.then().log().all();
	}

}
