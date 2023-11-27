package api.test;

import api.endpoints.WeatherEndPoints;
import api.utilities.DataProviders;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.path.xml.exception.XmlPathException;
import io.restassured.response.Response;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;


public class DataDriven_WeatherTests {

	@Test(priority = 1, dataProvider = "CityName", dataProviderClass = DataProviders.class)
	public void checkWeatherByCityName(String cityName, String units, String mode, String lang, String testCaseName, String lat, String lon, String country, String cityId, String city, String base){
		Response response= WeatherEndPoints.cityBasedWeather(cityName,units,mode,lang);
		JsonPath jsonPathEvaluator = response.jsonPath();
		XmlPath xmlPath = new XmlPath(response.asString());
		String unitCurrency;
		System.out.println(testCaseName);
		if(units.equalsIgnoreCase("metric")){
			unitCurrency= "Celsius";
		} else if (units.equalsIgnoreCase("imperial")) {
			unitCurrency="Fahrenheit";
		} else{
			unitCurrency="Kelvin";
		}

		if (cityName.equals("TestCityNameNotExist"))
		{
			AssertJUnit.assertEquals(404,response.getStatusCode());
			AssertJUnit.assertEquals("city not found",jsonPathEvaluator.get("message"));
			System.out.println("oops "+ jsonPathEvaluator.get("message"));
		} else
		{
			AssertJUnit.assertEquals(200,response.getStatusCode());
			if(mode.equalsIgnoreCase("xml")){
				AssertJUnit.assertEquals(city,xmlPath.getString("current.city.@name"));
				AssertJUnit.assertEquals(lat,xmlPath.getString("current.city.coord.@lat"));
				AssertJUnit.assertEquals(lon,xmlPath.getString("current.city.coord.@lon"));
				AssertJUnit.assertEquals(cityId,xmlPath.getString("current.city.@id"));
				AssertJUnit.assertEquals(country,xmlPath.getString("current.city.country"));
				try {
					System.out.println("currently " + xmlPath.getString("current.city.@name") + " has temperature of " + xmlPath.getString("current.temperature.@value")+" "  + unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " feels like " + xmlPath.getString("current.feels_like.@value") +" " +unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " has " + xmlPath.getString("current.wind.speed.@name")+ " wind");
				} catch (XmlPathException e) {
					System.err.println("Error processing XML response: " + e.getMessage());
				}
			} else{
				AssertJUnit.assertEquals(city,jsonPathEvaluator.get("name"));
				AssertJUnit.assertEquals(lat,jsonPathEvaluator.getDouble("coord.lat")+"");
				AssertJUnit.assertEquals(lon,jsonPathEvaluator.getDouble("coord.lon")+"");
				AssertJUnit.assertEquals(cityId,jsonPathEvaluator.getInt("id")+"");
				AssertJUnit.assertEquals(country,jsonPathEvaluator.get("sys.country"));
				AssertJUnit.assertEquals(base,jsonPathEvaluator.get("base"));
				try {
					System.out.println("currently " + cityName + " has temperature of " + jsonPathEvaluator.get("main.temp")+" "  + unitCurrency);
					System.out.println(cityName + " feels like " + jsonPathEvaluator.get("main.feels_like") +" " +unitCurrency);
					System.out.println(cityName + " has " + jsonPathEvaluator.get("weather.description"));
				} catch (JsonPathException e) {
					System.err.println("Error processing JSON response: " + e.getMessage());
				}
			}

		}
		response.then().log().all();

	}

	@Test(priority = 2, dataProvider = "Coordinate", dataProviderClass = DataProviders.class)
	public void checkWeatherByCoordinate(String lat, String lon, String units, String mode, String lang, String testCaseName,  String country, String cityId, String city, String base){
		Response response= WeatherEndPoints.coordinateBasedWeather(lat,lon,units,mode, lang);
		JsonPath jsonPathEvaluator = response.jsonPath();
		XmlPath xmlPath = new XmlPath(response.asString());
		String unitCurrency;
		System.out.println(testCaseName);
		if(units.equalsIgnoreCase("metric")){
			unitCurrency= "Celsius";
		} else if (units.equalsIgnoreCase("imperial")) {
			unitCurrency="Fahrenheit";
		} else{
			unitCurrency="Kelvin";
		}

		if (lat.equals("100000"))
		{
			AssertJUnit.assertEquals(400,response.getStatusCode());
			AssertJUnit.assertEquals("wrong latitude",jsonPathEvaluator.get("message"));
			System.out.println("oops "+ jsonPathEvaluator.get("message"));
		} else
		{
			AssertJUnit.assertEquals(200,response.getStatusCode());
			if(mode.equalsIgnoreCase("xml")){
				AssertJUnit.assertEquals(city,xmlPath.getString("current.city.@name"));
				AssertJUnit.assertEquals(lat,xmlPath.getString("current.city.coord.@lat"));
				AssertJUnit.assertEquals(lon,xmlPath.getString("current.city.coord.@lon"));
				AssertJUnit.assertEquals(cityId,xmlPath.getString("current.city.@id"));
				AssertJUnit.assertEquals(country,xmlPath.getString("current.city.country"));
				try {
					System.out.println("currently " + xmlPath.getString("current.city.@name") + " has temperature of " + xmlPath.getString("current.temperature.@value")+" "  + unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " feels like " + xmlPath.getString("current.feels_like.@value") +" " +unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " has " + xmlPath.getString("current.wind.speed.@name")+ " wind");
				} catch (XmlPathException e) {
					System.err.println("Error processing XML response: " + e.getMessage());
				}
			} else{
				AssertJUnit.assertEquals(city,jsonPathEvaluator.get("name"));
				AssertJUnit.assertEquals(lat,jsonPathEvaluator.getDouble("coord.lat")+"");
				AssertJUnit.assertEquals(lon,jsonPathEvaluator.getDouble("coord.lon")+"");
				AssertJUnit.assertEquals(cityId,jsonPathEvaluator.getInt("id")+"");
				AssertJUnit.assertEquals(country,jsonPathEvaluator.get("sys.country"));
				AssertJUnit.assertEquals(base,jsonPathEvaluator.get("base"));
				try {
					System.out.println("currently " + jsonPathEvaluator.get("name") + " has temperature of " + jsonPathEvaluator.get("main.temp")+" "  + unitCurrency);
					System.out.println(jsonPathEvaluator.get("name") + " feels like " + jsonPathEvaluator.get("main.feels_like") +" " +unitCurrency);
					System.out.println(jsonPathEvaluator.get("name") + " has " + jsonPathEvaluator.get("weather.description"));
				} catch (JsonPathException e) {
					System.err.println("Error processing JSON response: " + e.getMessage());
				}
			}

		}

		response.then().log().all();
	}

    @Test(priority = 3, dataProvider = "CityID", dataProviderClass = DataProviders.class)
    public void checkWeatherByCityId(String cityID, String units, String mode, String lang, String testCaseName,  String country, String cityId, String city, String base){
        Response response= WeatherEndPoints.cityIdBasedWeather(cityID,units,mode,lang);
		JsonPath jsonPathEvaluator = response.jsonPath();
		XmlPath xmlPath = new XmlPath(response.asString());
		String unitCurrency;
		System.out.println(testCaseName);
		if(units.equalsIgnoreCase("metric")){
			unitCurrency= "Celsius";
		} else if (units.equalsIgnoreCase("imperial")) {
			unitCurrency="Fahrenheit";
		} else{
			unitCurrency="Kelvin";
		}

		if (cityID.equals("1880701222"))
		{
			AssertJUnit.assertEquals(404,response.getStatusCode());
			AssertJUnit.assertEquals("city not found",jsonPathEvaluator.get("message"));
			System.out.println("oops "+ jsonPathEvaluator.get("message"));
		} else
		{
			AssertJUnit.assertEquals(200,response.getStatusCode());
			if(mode.equalsIgnoreCase("xml")){
				AssertJUnit.assertEquals(city,xmlPath.getString("current.city.@name"));
				AssertJUnit.assertEquals(cityId,xmlPath.getString("current.city.@id"));
				AssertJUnit.assertEquals(country,xmlPath.getString("current.city.country"));
				try {
					System.out.println("currently " + xmlPath.getString("current.city.@name") + " has temperature of " + xmlPath.getString("current.temperature.@value")+" "  + unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " feels like " + xmlPath.getString("current.feels_like.@value") +" " +unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " has " + xmlPath.getString("current.wind.speed.@name")+ " wind");
				} catch (XmlPathException e) {
					System.err.println("Error processing XML response: " + e.getMessage());
				}
			} else{
				AssertJUnit.assertEquals(city,jsonPathEvaluator.get("name"));
				AssertJUnit.assertEquals(cityId,jsonPathEvaluator.getInt("id")+"");
				AssertJUnit.assertEquals(country,jsonPathEvaluator.get("sys.country"));
				AssertJUnit.assertEquals(base,jsonPathEvaluator.get("base"));
				try {
					System.out.println("currently " + jsonPathEvaluator.get("name") + " has temperature of " + jsonPathEvaluator.get("main.temp")+" "  + unitCurrency);
					System.out.println(jsonPathEvaluator.get("name") + " feels like " + jsonPathEvaluator.get("main.feels_like") +" " +unitCurrency);
					System.out.println(jsonPathEvaluator.get("name") + " has " + jsonPathEvaluator.get("weather.description"));
				} catch (JsonPathException e) {
					System.err.println("Error processing JSON response: " + e.getMessage());
				}
			}

		}

        response.then().log().all();
    }

	@Test(priority = 4, dataProvider = "ZipCode", dataProviderClass = DataProviders.class)
	public void checkWeatherByZipId(String zipId, String units, String mode, String lang, String testCaseName,  String country, String cityId, String city, String base){
		Response response= WeatherEndPoints.zipIdBasedWeather(zipId,units,mode,lang);
		JsonPath jsonPathEvaluator = response.jsonPath();
		XmlPath xmlPath = new XmlPath(response.asString());
		String unitCurrency;
		System.out.println(testCaseName);
		if(units.equalsIgnoreCase("metric")){
			unitCurrency= "Celsius";
		} else if (units.equalsIgnoreCase("imperial")) {
			unitCurrency="Fahrenheit";
		} else{
			unitCurrency="Kelvin";
		}

		if (zipId.equals("94040111"))
		{
			AssertJUnit.assertEquals(404,response.getStatusCode());
			AssertJUnit.assertEquals("city not found",jsonPathEvaluator.get("message"));
			System.out.println("oops "+ jsonPathEvaluator.get("message"));
		} else
		{
			AssertJUnit.assertEquals(200,response.getStatusCode());
			if(mode.equalsIgnoreCase("xml")){
				AssertJUnit.assertEquals(city,xmlPath.getString("current.city.@name"));
				AssertJUnit.assertEquals(cityId,xmlPath.getString("current.city.@id"));
				AssertJUnit.assertEquals(country,xmlPath.getString("current.city.country"));
				try {
					System.out.println("currently " + xmlPath.getString("current.city.@name") + " has temperature of " + xmlPath.getString("current.temperature.@value")+" "  + unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " feels like " + xmlPath.getString("current.feels_like.@value") +" " +unitCurrency);
					System.out.println(xmlPath.getString("current.city.@name") + " has " + xmlPath.getString("current.wind.speed.@name")+ " wind");
				} catch (XmlPathException e) {
					System.err.println("Error processing XML response: " + e.getMessage());
				}
			} else{
				AssertJUnit.assertEquals(city,jsonPathEvaluator.get("name"));
				AssertJUnit.assertEquals(cityId,jsonPathEvaluator.getInt("id")+"");
				AssertJUnit.assertEquals(country,jsonPathEvaluator.get("sys.country"));
				AssertJUnit.assertEquals(base,jsonPathEvaluator.get("base"));
				try {
					System.out.println("currently " + jsonPathEvaluator.get("name") + " has temperature of " + jsonPathEvaluator.get("main.temp")+" "  + unitCurrency);
					System.out.println(jsonPathEvaluator.get("name") + " feels like " + jsonPathEvaluator.get("main.feels_like") +" " +unitCurrency);
					System.out.println(jsonPathEvaluator.get("name") + " has " + jsonPathEvaluator.get("weather.description"));
				} catch (JsonPathException e) {
					System.err.println("Error processing JSON response: " + e.getMessage());
				}
			}

		}
		response.then().log().all();
	}
}
