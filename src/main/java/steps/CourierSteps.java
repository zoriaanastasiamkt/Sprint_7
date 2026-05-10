package steps;

import data.CourierData;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CourierModel;
import static io.restassured.RestAssured.given;

public class CourierSteps extends CourierData {

    //создание курьера
    @Step("Send POST request to /api/v1/courier")
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

    //авторизация курьера в системе
    @Step("Send POST request to /api/v1/courier/login")
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

    //удаление курьера
    @Step("Send DELETE request to /api/v1/courier/:id")
    public static Response deleteCourierById(int id){
        return  given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(DELETE_COURIER_ENDPOINT + id)
                .then()
                .log().all()
                .extract().response();
    }
}
