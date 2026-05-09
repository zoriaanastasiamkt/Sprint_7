package steps;

import data.CourierData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CourierModel;
import static io.restassured.RestAssured.given;

public class CourierSteps extends CourierData {

    public static Response createCourier(CourierModel courier){
        return  given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(CREATE_COURIER_ENDPOINT)
                .then()
                .extract().response();
    }

    public static Response loginCourier(CourierModel courier){
        return  given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(LOGIN_COURIER_ENDPOINT)
                .then()
                .extract().response();
    }
}
