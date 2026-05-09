import data.CourierData;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseApiTest extends CourierData {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }
}
