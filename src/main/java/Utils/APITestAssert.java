package Utils;

import org.testng.Assert;

public class APITestAssert {

    public static void validateApiResponse(String actualApiCode, String expectedApiCode, int actualHttpCode, int expectedHttpCode) {

        ExtentReportListener.getTest().info("Expected API Code: " + expectedApiCode);
        ExtentReportListener.getTest().info("Received API Code: " + actualApiCode);

        ExtentReportListener.getTest().info("Expected HTTP Status Code: " + expectedHttpCode);
        ExtentReportListener.getTest().info("Actual HTTP Status Code: " + actualHttpCode);

        boolean testPassed = true;

        if (!expectedApiCode.equals(actualApiCode)) {
            ExtentReportListener.getTest().fail("API Code mismatch. Expected: " + expectedApiCode + ", Actual: " + actualApiCode);
            testPassed = false;
        } else {
            ExtentReportListener.getTest().pass("API Code matched: " + actualApiCode);
        }

        if (expectedHttpCode != actualHttpCode) {
            ExtentReportListener.getTest().fail("HTTP Status Code mismatch. Expected: " + expectedHttpCode + ", Actual: " + actualHttpCode);
            testPassed = false;
        } else {
            ExtentReportListener.getTest().pass("HTTP Status Code matched: " + actualHttpCode);
        }

        // If test failed, fail via TestNG to mark red in report
        if (!testPassed) {
            Assert.fail("API validation failed. See Extent Report for details.");
        }
    }
}

