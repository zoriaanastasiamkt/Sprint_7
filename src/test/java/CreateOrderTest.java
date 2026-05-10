import io.qameta.allure.Description;
import models.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static data.OrderData.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.notNullValue;
import static steps.OrderSteps.createOrder;
import static steps.OrderSteps.cancelOrderByTrack;
import io.qameta.allure.junit4.DisplayName;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseApiTest {

    private final String scenarioName;
    private final String[] color;
    private Integer track = null;

    public CreateOrderTest(String scenarioName, String[] color) {
        this.scenarioName = scenarioName;
        this.color = color;
    }

    @Parameterized.Parameters(name = "{0} | цвет={1}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][]{
                // без цвета вообще
                {"Без цвета", new String[]{}},

                // один цвет - черный или серый
                {"Один цвет: BLACK", new String[]{"BLACK"}},
                {"Один цвет: GREY", new String[]{"GREY"}},

                // Два цвета
                {"Два цвета: BLACK,GREY", new String[]{"BLACK", "GREY"}}
        });
    }

    @Test
    @DisplayName("Order test with variants of colours - null, black OR grey, black AND grey")
    @Description("The test checks the ability to place an order with different options for filling in the OPTIONAL color field")
    public void testCreateOrderWithColorVariants() {
        OrderModel order = new OrderModel(FIRSTNAME, LASTNAME, ADDRESS, METROSTATION, PHONE, RENTTIME, DELIVERYDATE, COMMENT, color);

        track = createOrder(order)
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");
    }

    @After
    public void cleanUp() {
        if (track != null && track > 0) {
            try {
                cancelOrderByTrack(track);
            } catch (Exception e) {
            }
            track = null;
        }
    }
}