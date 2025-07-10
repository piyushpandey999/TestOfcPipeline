package Utils;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;

public class APITestAssert2 {

    public static void validateApiResponse2(String actualApiCode, String expectedApiCode, int actualHttpCode, int expectedHttpCode,String ip) {
        boolean testPassed = true;

        // Compare API Code
        boolean apiCodeMatch = expectedApiCode.equals(actualApiCode);
        boolean httpCodeMatch = expectedHttpCode == actualHttpCode;

        if (!apiCodeMatch || !httpCodeMatch) {
            testPassed = false;
        }

        // HTML table summary
        String statusText = testPassed ? "PASS" : "FAIL";
        ExtentColor color = testPassed ? ExtentColor.GREEN : ExtentColor.RED;

        String summaryTable =
                "<table border='1' style='border-collapse: collapse;'>" +
                        "<tr style='background-color:#f2f2f2'><th>Field</th><th>Expected</th><th>Actual</th></tr>" +
                        "<tr><td><b>API Code</b></td><td>" + expectedApiCode + "</td><td>" + actualApiCode + "</td></tr>" +
                        "<tr><td><b>HTTP Code</b></td><td>" + expectedHttpCode + "</td><td>" + actualHttpCode + "</td></tr>" +
                        "<tr><td><b>Status</b></td><td colspan='2'><b>" + statusText + "</b></td></tr>" +
                        "</table>";

        ExtentReportListener.getTest().info(MarkupHelper.createLabel("API Validation Summary", ExtentColor.BLUE));
        ExtentReportListener.getTest().info(summaryTable);



        ExtentReportListener.getTest().info("IP - "+ip);

        // Individual assertions
        if (!apiCodeMatch) {
            ExtentReportListener.getTest().fail("Mismatch in API Code: Expected [" + expectedApiCode + "], but got [" + actualApiCode + "]");
        } else {
            ExtentReportListener.getTest().pass("API Code matched: " + actualApiCode);
        }

        if (!httpCodeMatch) {
            ExtentReportListener.getTest().fail("Mismatch in HTTP Status Code: Expected [" + expectedHttpCode + "], but got [" + actualHttpCode + "]");
        } else {
            ExtentReportListener.getTest().pass("HTTP Code matched: " + actualHttpCode);
        }

        // Fail test if any mismatch
        if (!testPassed) {
            Assert.fail("API validation failed. Refer Extent Report summary table for details.");
        }
    }
}
