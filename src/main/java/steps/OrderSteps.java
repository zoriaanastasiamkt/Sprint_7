package steps;

import data.OrderData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps extends OrderData {

    //Создание заказа
    public static Response createOrder(OrderModel order){
        return  given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDER_ENDPOINT)
                .then()
                .extract().response();
    }

    // Проверка списка заказов
    public static Response checkOrderList (OrderModel order){
        return  given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .get(ORDER_ENDPOINT)
                .then()
                .extract().response();
    }
}
