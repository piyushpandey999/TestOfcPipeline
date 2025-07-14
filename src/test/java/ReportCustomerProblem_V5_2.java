import Utils.IpProvider;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static Utils.APITestAssert2.*;

public class ReportCustomerProblem_V5_2 extends BaseSetup {

    @Test(dataProvider = "ipDataProvider2", dataProviderClass = IpProvider.class)
    public void test(String ip) {

        String body = "{\"reportCustomerProblemRequest\": " +
                "{\"ebmHeader\": {\"domain\": \"B2C\",\"lob\": \"AES\",\"consumerTransactionId\": \"202506301349198ioitest\",\"consumerName\": " +
                "\"Airtelworks\",\"programmeName\": \"Airtelworks\",\"customerMigrated\": \"false\"},\"dataArea\": {\"ext\": {\"attribute\":" +
                " {\"name\": \"SourceIdentifier\",\"value\": \"UpdateSR\"}},\"serviceRequestDetails\": {\"taskDetails\": {\"problem\": " +
                "{\"problemId\": \"38107476\",\"originatingSytem\": \"WORK\",\"taskId\": \"122679059\",\"category\": null,\"comments\": " +
                "\"ETA 2 HOURS \",\"activityStatus\": \"FE DETAILS UPDATE\",\"subCategory\": null,\"subSubCategory\": null,\"originatingSytemId\":" +
                " \"3770991858152744167\",\"resolutiontime\": \"30/06/2025 13:49\",                        \"status\": \"FE onsite\",\"createdBy\": \"\",\"attribute\": {\"name\": \"\",\"value\": \"\"}}},\"customer\": {\"identification\": {\"name\": \"NAVEEN KUMAR\"},\"telephone\": {\"contactNumber\": \"9632688301\"}},\"ext\": {\"attribute\": {\"name\": \"ATTRIBUTE1\",\"value\": \"30/06/2025 14:31 \"}}}}}}";


        RestAssured.baseURI = "http://"+ip;
        RestAssured.basePath = "/services/customerproblem/report/v5_2";

        Response res = RestAssured.given().log().all().when().body(body).post().then().extract().response();
        String ActualResponseCode = res.jsonPath().getString("soaFault.soaFaultCode");
        String expectedResponseCode = "reportCustomerProblem-3599-E";

        int actualHTTPCode = res.getStatusCode();
        int expectedHTTPCode = 409;

        validateApiResponse2(ActualResponseCode,expectedResponseCode,actualHTTPCode,expectedHTTPCode,ip);

        res.prettyPeek();

    }
}
