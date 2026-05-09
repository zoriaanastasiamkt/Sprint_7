
import models.OrderModel;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Test;
import static java.net.HttpURLConnection.*;
import static steps.OrderSteps.checkOrderList;
import io.qameta.allure.junit4.DisplayName;

public class OrderListTest extends BaseApiTest {

    //Проверка ручки списка заказов
    @Test
    @DisplayName("Check the list of orders can be displayed")
    public void testGetOrderListSuccess() {
        OrderModel order = new OrderModel();

        checkOrderList(order)
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .body(IsNull.notNullValue());
    }

    @After
    public void cleanUp() {}
}
