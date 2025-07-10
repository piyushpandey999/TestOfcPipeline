import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Test1 {

    @Test
    public void test1(){
        Response response = RestAssured.given()
                .header("x-api-key","reqres-free-v1")
                .queryParam("page",2)
                .when().get("https://reqres.in/api/users").then().extract().response();
        response.prettyPrint();
    }
}
