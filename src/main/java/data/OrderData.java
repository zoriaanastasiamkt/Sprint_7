package data;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

public class OrderData {
    public static final String ORDER_ENDPOINT = "/api/v1/orders";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Генерация данных из фейковой библиотеки
    static Faker order = new Faker();
        public static final String FIRSTNAME = order.name().firstName() + order.regexify("[0-9]{5}");
        public static final String LASTNAME = order.name().lastName() + order.regexify("[0-9]{5}");
        public static final String ADDRESS = order.address().streetAddress();
        public static final String METROSTATION = order.regexify("[0-9]{1}");
        public static final String PHONE = order.phoneNumber().cellPhone();
        public static final int RENTTIME = order.number().numberBetween(1, 9);
        public static final String DELIVERYDATE = dateFormat.format(order.date().future(30, TimeUnit.DAYS));
        public static final String COMMENT = order.lorem().word();

    public static Collection<Object[]> colorTestCases() {
        return Arrays.asList(new Object[][] {
                // Без цвета (null)
                { "Без цвета", new String[]{} },

                // Один цвет - черный или серый
                { "Один цвет: BLACK", new String[]{"BLACK"} },
                { "Один цвет: GREY", new String[]{"GREY"} },

                // Два цвета - черный и серый
                { "Два цвета: BLACK,GREY", new String[]{"BLACK", "GREY"} }
        });
    }
}
