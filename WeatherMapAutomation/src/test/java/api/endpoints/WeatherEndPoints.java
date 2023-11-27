package api.endpoints;

import static io.restassured.RestAssured.given;


import io.restassured.response.Response;

public class WeatherEndPoints {
	public static String appId="026cf5ea3b1902c32c87663eff83852a";

	public static Response cityBasedWeather(String cityName, String units, String mode, String lang)
	{
		Response response=given()
			.queryParam("q",cityName)
			.queryParam("appid",appId)
			.queryParam("units",units)
			.queryParam("mode",mode)
			.queryParam("lang",lang)
		.when()
			.get(Routes.weather_url);
		response.then().log().all();
		return response;

	}

	public static Response coordinateBasedWeather(String lat,String lon, String units, String mode, String lang)
	{
		Response response=given()
				.queryParam("lat",lat)
				.queryParam("lon",lon)
				.queryParam("appid",appId)
				.queryParam("units",units)
				.queryParam("mode",mode)
				.queryParam("lang",lang)
				.when()
				.get(Routes.weather_url);
		response.then().log().all();
		return response;

	}
	public static Response zipIdBasedWeather(String zipId, String units, String mode, String lang)
	{
		Response response=given()
				.queryParam("zip",zipId)
				.queryParam("appid",appId)
				.queryParam("units",units)
				.queryParam("mode",mode)
				.queryParam("lang",lang)
				.when()
				.get(Routes.weather_url);
		response.then().log().all();
		return response;

	}

	public static Response cityIdBasedWeather(String cityId, String units, String mode, String lang)
	{
		Response response=given()
				.queryParam("id",cityId)
				.queryParam("appid",appId)
				.queryParam("units",units)
				.queryParam("mode",mode)
				.queryParam("lang",lang)
				.when()
				.get(Routes.weather_url);
		response.then().log().all();
		return response;

	}

	
}
