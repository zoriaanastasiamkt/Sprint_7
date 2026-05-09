import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.createCourier;
import io.qameta.allure.junit4.DisplayName;

public class CreateCourierTest extends BaseApiTest{

    //создание курьера - Запрос должен успешно проходить
        @Test
        @DisplayName("Succesed courier creation")
        public void testCreateCourierSuccess() {
            CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

            createCourier(courier)
                    .then()
                    .log().all()
                    .statusCode(HTTP_CREATED)
                    .body("ok", equalTo(true));
        }

    //создание 2х одинаковых курьеров - запрос должен возвращать ошибку
    @Test
    @DisplayName("Creation of two the same couriers")
    public void testCreateTwoIdenticalCouriersFail() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

        createCourier(courier);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }

        //создание курьера без обязательного поля login - запрос должен возвращать ошибку
        @Test
        @DisplayName("Creation of courier without login strim")
        public void testCreateCourierWithoutLoginFail() {
            CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);

            createCourier(courier)
                    .then()
                    .log().all()
                    .statusCode(HTTP_BAD_REQUEST)
                    .body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }

    //создание курьера без обязательного поля password - запрос должен возвращать ошибку
    @Test
    @DisplayName("Creation of courier without password strim")
    public void testCreateCourierWithoutPasswordFail() {
        CourierModel courier = new CourierModel(LOGIN, null, FIRSTNAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void cleanUp() {}
        }

