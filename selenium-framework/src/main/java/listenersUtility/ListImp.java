package listenersUtility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.BaseTest;

public class ListImp implements ITestListener, ISuiteListener {
	public ExtentReports reports;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ExtentSparkReporter spark = new ExtentSparkReporter("./target/adv-report.html");
		spark.config().setDocumentTitle("Document title");
		spark.config().setReportName("Report Name");
		spark.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("O.S.", "Windows 11");
		reports.setSystemInfo("Browser", "Chrome");

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		reports.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test = reports.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + " is passed !!!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		// TODO Auto-generated method stub
		TakesScreenshot tks = (TakesScreenshot) BaseTest.sdriver;
		String filepath = tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, "ErrorFile");
		test.log(Status.FAIL, methodName + " is failed !!!");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName + " is skipped !!!");

	}

}
