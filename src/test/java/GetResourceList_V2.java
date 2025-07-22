import Utils.IpProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.APITestAssert2.validateApiResponse2;

public class GetResourceList_V2 extends BaseSetup {


    @Test(dataProvider = "ipDataProvider2", dataProviderClass = IpProvider.class)
    public void PatternSearchTest(String ip) {

        String body = "{\n" +
                "    \"getResourceListReqMsg\": {\n" +
                "        \"ebmHeader\": {\n" +
                "            \"lob\": \"Mobility\",\n" +
                "            \"customerMigrated\": \"true\",\n" +
                "            \"consumerTransactionId\": \"ndiiendoedjd7we90659ksync1\"\n" +
                "        },\n" +
                "        \"dataArea\": {\n" +
                "            \"getResourceListRequest\": {\n" +
                "                \"party\": {\n" +
                "                    \"partyId\": \"-1996874\"\n" +
                "                },\n" +
                "                \"searchCriteria\": {\n" +
                "                    \"type\": \"4\",\n" +
                "                    \"value\": \"765%\",\n" +
                "                    \"count\": \"10\",\n" +
                "                    \"minPrice\": {\n" +
                "                        \"amount\": 0.0\n" +
                "                    },\n" +
                "                    \"maxPrice\": {\n" +
                "                        \"amount\": 0.0\n" +
                "                    }\n" +
                "                },\n" +
                "                \"geographicArea\": {\n" +
                "                    \"identification\": {\n" +
                "                        \"id\": \"123\"\n" +
                "                    }\n" +
                "                },\n" +
                "                \"logicalResource\": {\n" +
                "                    \"lrStatus\": \"8\",\n" +
                "                    \"type\": \"MSISDN\"\n" +
                "                },\n" +
                "                \"securityCodesRequired\": \"0\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        RestAssured.baseURI = "http://" + ip;
        RestAssured.basePath = "/services/customerproblem/report/v5_2";

        Response res = RestAssured.given().log().all().when().body(body).post().then().extract().response();
        String ActualResponseCode = res.jsonPath().getString("soaFault.soaFaultCode");
        String expectedResponseCode = "GetResourceList-3999-E";

        int actualHTTPCode = res.getStatusCode();
        int expectedHTTPCode = 200;

        validateApiResponse2(ActualResponseCode, expectedResponseCode, actualHTTPCode, expectedHTTPCode, ip);

        res.prettyPeek();

    }

}
