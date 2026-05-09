import models.CourierModel;
import org.junit.After;
import org.junit.Test;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;

public class LoginCourierTest extends BaseApiTest{

    //авторизация курьера - Запрос должен успешно проходить и возвращать id курьера
        @Test
        @DisplayName("Courier login success with returned ID")
        public void testLoginCourierSuccess() {
            CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

            createCourier(courier);
            loginCourier(courier)
                    .then()
                    .log().all()
                    .statusCode(HTTP_OK)
                    .body("id", notNullValue());
        }

    //авторизация курьера с неверным логином (=несуществующий пользователь) - Запрос должен вернуть ошибку
    @Test
    @DisplayName("Courier login with wrong login")
    public void testLoginCourierWrongLoginFail() {
        String wrongLogin = LOGIN + "_" + System.currentTimeMillis();
        CourierModel courier = new CourierModel(wrongLogin, PASSWORD, FIRSTNAME);

        loginCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    //авторизация курьера с неверным паролем - Запрос должен вернуть ошибку
    @Test
    @DisplayName("Courier login with wrong password")
    public void testLoginCourierWrongPasswordFail() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

        createCourier(courier);

        Faker faker = new Faker();
        String wrongPassword = faker.number().digits(4);

        CourierModel wrongcourier = new CourierModel(LOGIN, wrongPassword, FIRSTNAME);

        loginCourier(wrongcourier)
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    //авторизация курьера без обязательного поля login - запрос должен возвращать ошибку
    @Test
    @DisplayName("Courier login without login strim")
    public void testLoginCourierWithoutLoginFail() {
        CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);

        loginCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    //авторизация курьера без обязательного поля password - запрос должен возвращать ошибку
    @Test
    @DisplayName("Courier login without password strim")
    public void testLoginCourierWithoutPasswordFail() {
        CourierModel courier = new CourierModel(LOGIN, null, FIRSTNAME);

        loginCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @After
    public void cleanUp() {}
        }

