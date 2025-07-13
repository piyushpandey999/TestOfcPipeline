package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import org.testng.*;

import java.io.File;

public class ExtentReportListener implements ITestListener, ISuiteListener {
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        // Initialize ExtentReports and attach reporter

//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss").format(new Date());
//        String reportName = "APITestReport-" + timeStamp + ".html";
        String reportName = "TestResults"+".html";
        String reportPath = System.getProperty("user.dir") + "\\Reports\\" + reportName;
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(new File(reportPath));

        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("API Test Report");
        spark.config().setReportName("RestAssured Framework");

        extent.attachReporter(spark);

        extent.setSystemInfo("Environment", "QA");
//            extent.setSystemInfo("User", System.getProperty("user.name"));

        // Add RestAssured filter globally
        RestAssured.filters(new ReportLoggingFilter());
    }

    @Override

    public void onStart(ITestContext context) {

        // Log test suite start

        System.out.println("Test Suite Started: " + context.getName());

    }


    @Override
    public void onTestStart(ITestResult result) {
        // Create test entry in Extent Report
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logDetails(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logDetails(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logDetails(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        logDetails(Status.PASS, "Test Passed with Success Percentage: " + result.getMethod().getMethodName());

    }


    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        // Additional cleanup if needed
        if (extent != null) {
            extent.flush();
        }
    }

    private void logDetails(Status status, String message) {
        ExtentTest test = extentTest.get();

        // Check if test is null and handle gracefully
        if (test == null) {
            System.err.println("ExtentTest is null, cannot log message: " + message);
            return;
        }

        try {
            test.log(status, message);

            // Log Request/Response details
            ReportLoggingFilter.RequestDetails request = ReportLoggingFilter.getRequestDetails();
            ReportLoggingFilter.ResponseDetails response = ReportLoggingFilter.getResponseDetails();

            if (request != null) {
                test.info(MarkupHelper.createLabel("REQUEST", ExtentColor.BLUE));
                test.info("Method: " + request.getMethod());
                test.info("URI: " + request.getUri());
                test.info("Headers: " + formatHeaders(request.getHeaders()));
                test.info("Body: " + request.getBody());
            }

            if (response != null) {
                test.info(MarkupHelper.createLabel("RESPONSE", ExtentColor.GREEN));
                test.info("Status: " + response.getStatusCode());
                test.info("Body: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error logging to ExtentTest: " + e.getMessage());
        } finally {
            // Clear ThreadLocal data after logging
            ReportLoggingFilter.clear();
            // Don't remove the test from ThreadLocal here, let TestNG handle the lifecycle
        }
    }

    private String formatHeaders(Headers headers) {
        if (headers == null) {
            return "No headers";
        }
        return headers.toString().replace(",", "<br>"); // Format for HTML report
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void removeTest() {
        extentTest.remove();
    }

//    @Override
//    public void onFinish(ISuite suite) {
//        extent.flush(); // Save report @theking999
//    }
}
