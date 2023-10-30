package zyna.config;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import zyna.base.TestBase;
import zyna.util.SeleniumUtil;

public class TestngConfig extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		initWeb(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		String ReportPath = new File(extentReportRoot, "Extent.html").getAbsolutePath();
		Ereport = new ExtentReports();
		Espark = new ExtentSparkReporter(ReportPath);
		Espark.config().setDocumentTitle(projectPathRoot.getName().toString());
		Espark.config().setReportName("Zyna Automation Framework");
		Espark.config().setTimelineEnabled(false);
		Espark.config().setEncoding("utf-8");
		Espark.config().setTimeStampFormat("dd MMM yyyy hh:mm:ss a");
		Ereport.attachReporter(Espark);
	}

	@Override
	public void onFinish(ITestContext context) {
		Ereport.flush();
		
	}

}
