import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Test2 {

    @Test
    public void getBookingIds() {
        Response response = RestAssured.given()
                .when().get("https://restful-booker.herokuapp.com/booking,")
                .then().extract().response();
        response.prettyPrint();
    }
}
