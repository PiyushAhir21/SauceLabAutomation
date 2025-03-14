package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report.html");
            extent.attachReporter(spark);
        }
        return extent;
    }
}