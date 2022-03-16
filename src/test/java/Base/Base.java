package Base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;


public class Base {
    public static RequestSpecification httpRequest;
    public static Response response;
    Logger log = LogManager.getLogger(Base.class);


    @After
    public void checkStatusCode() {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        log.info("After Method is Complete");
    }

    public String random(String no) {
        char[] sayi = no.toCharArray();
        for (int a = sayi.length - 1; a >= 0; a--) {
            if (sayi[a] == 'z') {
                sayi[a] = 'a';
            } else if (sayi[a] == '9') {
                sayi[a] = '0';
            } else {
                sayi[a]++;
                break;
            }
        }
        return String.valueOf(sayi);
    }


}
