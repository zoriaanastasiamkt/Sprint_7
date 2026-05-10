package steps;

import data.OrderData;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.OrderModel;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.anyOf;

public class OrderSteps extends OrderData {

    private static Object track;

    //Создание заказа
    @Step("Send POST request to /api/v1/orders")
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
    @Step("Send GET request to /api/v1/orders")
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

    // Отмена заказа
    @Step("Send PUT request to /api/v1/orders/cancel")
    public static void cancelOrderByTrack (Integer track){
           if (track == null) return;
           given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("track", track)
                .when()
                .put(CANCEL_ORDER_ENDPOINT)
                .then()
                .log().all()
                .statusCode(anyOf(equalTo(200), equalTo(400), equalTo(404)));
    
    }
}
