package api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AirPollutionEndPoints {



	public static Response currentPollution(String lat,String lon, String appId)
	{
		Response response=given()
				.queryParam("lat",lat)
				.queryParam("lon",lon)
				.queryParam("appid",appId)
				.when()
				.get(Routes.air_pollution_url);
		response.then().log().all();
		return response;

	}
	public static Response forecastPollution(String lat,String lon, String appId)
	{
		Response response=given()
				.queryParam("lat",lat)
				.queryParam("lon",lon)
				.queryParam("appid",appId)
				.when()
				.get(Routes.air_pollution_url);
		response.then().log().all();
		return response;

	}

	public static Response historyPollution(String lat,String lon, String start, String end, String appId)
	{
		Response response=given()
				.queryParam("lat",lat)
				.queryParam("lon",lon)
				.queryParam("appid",appId)
				.queryParam("start",start)
				.queryParam("end",end)
				.when()
				.get(Routes.air_pollution_url);
		response.then().log().all();
		return response;

	}


	
}
