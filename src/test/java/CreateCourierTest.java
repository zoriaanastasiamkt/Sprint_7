import io.qameta.allure.Description;
import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.deleteCourierById;

import io.qameta.allure.junit4.DisplayName;

public class CreateCourierTest extends BaseApiTest{

    private int courierId = -1;

    //создание курьера - Запрос должен успешно проходить
        @Test
        @DisplayName("Succesed courier creation")
        @Description("Positive test for creating a new courier in the system")
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
    @Description("A negative test for creating two identical couriers in the system - the second request should not return a positive response")
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
        @Description("Negative test for creating a courier in the system - without filling in the required field")
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
    @Description("Negative test for creating a courier in the system - without filling in the required field")
    public void testCreateCourierWithoutPasswordFail() {
        CourierModel courier = new CourierModel(LOGIN, null, FIRSTNAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void cleanUp() {
        if (courierId > 0) {
             try {
                    deleteCourierById(courierId);
             } catch (Exception e) { }
        courierId = -1;
    }
}
        }

