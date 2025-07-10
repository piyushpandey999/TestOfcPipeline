import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static Utils.APITestAssert.*;
import static Utils.APITestAssert2.*;

public class Test3 extends BaseSetup{


    @Test
    public void test(){

        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soapenv:Body>\n" +
                "        <reportCustomerProblemRequest xmlns=\"http://www.airtel.com/aim/schema/reportcustomerproblem_V5\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "            <ebmHeader xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/ebm_header\">\n" +
                "                <domain>B2B</domain>\n" +
                "                <lob>AES</lob>\n" +
                "                <consumerTransactionId>37965579</consumerTransactionId>\n" +
                "                <consumerName>Siebel</consumerName>\n" +
                "                <programmeName>CreateUpdateSR</programmeName>\n" +
                "                <customerMigrated>true</customerMigrated>\n" +
                "            </ebmHeader>\n" +
                "            <dataArea>\n" +
                "                <ext xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/attributes\">\n" +
                "                    <attribute>\n" +
                "                        <name>SourceIdentifier</name>\n" +
                "                        <value>CreateUpdateSR</value>\n" +
                "                    </attribute>\n" +
                "                </ext>\n" +
                "                <serviceRequestDetails>\n" +
                "                    <logicalResource xmlns=\"http://www.airtel.com/aim/schema/abe/resource/resource/logicalResource/v1\">\n" +
                "                        <identification>\n" +
                "                            <id xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">7042148513</id>\n" +
                "                        </identification>\n" +
                "                        <identification>\n" +
                "                            <name xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">LSINumber</name>\n" +
                "                        </identification>\n" +
                "                        <identification>\n" +
                "                            <name xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">SINumber</name>\n" +
                "                            <value xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">testing-23423423</value>\n" +
                "                        </identification>\n" +
                "                        <type>MSISDN</type>\n" +
                "                        <circuitId>14173925</circuitId>\n" +
                "                    </logicalResource>\n" +
                "                    <customer xmlns=\"http://www.airtel.com/aim/schema/abe/customer/customer/v2\">\n" +
                "                        <customerAccount>\n" +
                "                            <identification>\n" +
                "                                <id xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">testing-1-81401805365</id>\n" +
                "                            </identification>\n" +
                "                        </customerAccount>\n" +
                "                        <customerSegment>testing-Prime</customerSegment>\n" +
                "                        <email>\n" +
                "                            <eMailAddress xmlns=\"http://www.airtel.com/aim/schema/abe/engagedParty/party/contact/v1\">testing-abc@gmail.com</eMailAddress>\n" +
                "                        </email>\n" +
                "                        <telephone>\n" +
                "                            <contactNumber xmlns=\"http://www.airtel.com/aim/schema/abe/engagedParty/party/contact/v1\">8126251984</contactNumber>\n" +
                "                        </telephone>\n" +
                "                        <organizationName>C-EDGE TECHNOLOGIES LIMITED</organizationName>\n" +
                "                        <individual>\n" +
                "                            <individualName xmlns=\"http://www.airtel.com/aim/schema/abe/engagedParty/party/v1\">\n" +
                "                                <givenName>Pankaj</givenName>\n" +
                "                            </individualName>\n" +
                "                        </individual>\n" +
                "                    </customer>\n" +
                "                    <serviceproblem xmlns=\"http://www.airtel.com/aim/schema/abe/service/serviceproblem/v1\">\n" +
                "                        <problem xmlns=\"http://www.airtel.com/aim/schema/abe/common/problem/v1\">\n" +
                "                            <problemId>37965579</problemId>\n" +
                "                            <priority>Severity 2</priority>\n" +
                "                            <impactPattterns>Service Impacting</impactPattterns>\n" +
                "                            <createdDate>2025-06-09T02:27:16</createdDate>\n" +
                "                            <taskId>122140879</taskId>\n" +
                "                            <status>Open</status>\n" +
                "                            <type>NEW</type>\n" +
                "                            <creationChannel>Siebel</creationChannel>\n" +
                "                            <group>ENOC_CEDGE</group>\n" +
                "                            <caseType>testing-nkjkhkj</caseType>\n" +
                "                            <createdBy>A1UUNC75</createdBy>\n" +
                "                            <attribute>\n" +
                "                                <name>BusinessCircle</name>\n" +
                "                                <value>testing-Billing Circle</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>productType</name>\n" +
                "                                <value>SD WAN-MPLS</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>SRSubsubtype</name>\n" +
                "                                <value>Primary Link Down</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>TaskName</name>\n" +
                "                                <value>BRC Task</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>ImplementationCircle</name>\n" +
                "                                <value>testing-HR</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>existingBandwidth</name>\n" +
                "                                <value>12</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>state</name>\n" +
                "                                <value>Maharashtra</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>Region</name>\n" +
                "                                <value>testing-abc</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>SRType</name>\n" +
                "                                <value>Network &amp; Connectivity Related</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>SRSubtype</name>\n" +
                "                                <value>Link Down</value>\n" +
                "                            </attribute>\n" +
                "                            <attribute>\n" +
                "                                <name>SerSegment</name>\n" +
                "                                <value>testing-ABC</value>\n" +
                "                            </attribute>\n" +
                "                            <summary>ok</summary>\n" +
                "                        </problem>\n" +
                "                        <externalIdentification>\n" +
                "                            <name xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">organisationProcessPath</name>\n" +
                "                            <value xmlns=\"http://www.airtel.com/aim/schema/eol/common/v1/identification\">AIRTEL.B2B.SERVICE_ASSURANCE.IWAN</value>\n" +
                "                        </externalIdentification>\n" +
                "                    </serviceproblem>\n" +
                "                </serviceRequestDetails>\n" +
                "            </dataArea>\n" +
                "        </reportCustomerProblemRequest>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        RestAssured.baseURI = "http://10.222.184.238:8000/services/reportcustomerproblem/v5_2";
       Response response = RestAssured.given().body(body).auth().basic("siebel","dNa97mWw").post().then().extract().response();

       String actualCode = response.xmlPath().getString("Envelope.Body.Fault.detail.reportCustomerProblemFault.soaFault.soaFaultCode");
        System.out.println(actualCode);

        String expectedCode = "reportCustomerProblem-3599-E";

        int actualHTTpCode = response.getStatusCode();
        int expectedHttpCode = 200;

//        validateApiResponse2(actualCode,expectedCode,actualHTTpCode,expectedHttpCode);
    }
}
