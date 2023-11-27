package api.test;

import api.endpoints.AirPollutionEndPoints;
import api.endpoints.WeatherEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Simple_AirPollutionTests {public Logger logger;

	public static String appId="026cf5ea3b1902c32c87663eff83852a";


	@Test(priority = 1, testName = "validate pollution with given exist coordinate")
	public void currentPollutionBasedOnCoordinate(){
		Response response= AirPollutionEndPoints.currentPollution("-6.174067","106.788627",appId);
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		int aqi= jsonPathEvaluator.getInt("list[0].main.aqi");
		System.out.println("the air quality index is "+ aqi);
		System.out.println("the pollution components compositions are:  "+jsonPathEvaluator.get("list[0].components") );
		response.then().log().all();
	}

	@Test(priority = 2, testName = "validate pollution response when latitude and longitude is not found")
	public void currentPollutionWithCoordinateNotExist(){
		Response response= AirPollutionEndPoints.currentPollution("2132132222222","1111",appId);
		JsonPath jsonPathEvaluator = response.jsonPath();
		AssertJUnit.assertEquals(400,response.getStatusCode());
		AssertJUnit.assertEquals("wrong latitude",jsonPathEvaluator.get("message"));
		System.out.println("oops "+ jsonPathEvaluator.get("message"));
		response.then().log().all();
	}
	@Test(priority = 3, testName = "validate pollution response when longitude is not found")
	public void currentPollutionWitLongitudeNotExist(){
		Response response= AirPollutionEndPoints.currentPollution("-6.174067","1111",appId);
		JsonPath jsonPathEvaluator = response.jsonPath();
		AssertJUnit.assertEquals(400,response.getStatusCode());
		AssertJUnit.assertEquals("wrong longitude",jsonPathEvaluator.get("message"));
		System.out.println("oops "+ jsonPathEvaluator.get("message"));
		response.then().log().all();
	}

	@Test(priority = 4, testName = "validate pollution response when latitude is not found")
	public void currentPollutionWitLatitudeNotExist(){
		Response response= AirPollutionEndPoints.currentPollution("21321322","106.788627",appId);
		JsonPath jsonPathEvaluator = response.jsonPath();
		AssertJUnit.assertEquals(400,response.getStatusCode());
		AssertJUnit.assertEquals("wrong latitude",jsonPathEvaluator.get("message"));
		System.out.println("oops "+ jsonPathEvaluator.get("message"));
		response.then().log().all();
	}

	@Test(priority = 5, testName = "validate pollution given coordinate with wrong appId")
	public void pollutionWithWrongAppId(){
		Response response= AirPollutionEndPoints.currentPollution("21321322","106.788627","12345");
		JsonPath jsonPathEvaluator = response.jsonPath();
		AssertJUnit.assertEquals(401,response.getStatusCode());
		AssertJUnit.assertEquals("Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.",jsonPathEvaluator.get("message"));
		System.out.println("oops "+ jsonPathEvaluator.get("message"));
		response.then().log().all();
	}
	@Test(priority = 6, testName = "validate pollution given coordinate with empty appId")
	public void pollutionWithEmptyAppId(){
		Response response= AirPollutionEndPoints.currentPollution("21321322","106.788627","");
		JsonPath jsonPathEvaluator = response.jsonPath();
		AssertJUnit.assertEquals(401,response.getStatusCode());
		AssertJUnit.assertEquals("Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.",jsonPathEvaluator.get("message"));
		System.out.println("oops "+ jsonPathEvaluator.get("message"));
		response.then().log().all();
	}
}
