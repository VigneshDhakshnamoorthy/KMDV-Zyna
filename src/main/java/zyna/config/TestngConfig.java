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
		Object[] parameters = result.getParameters();
		String BName = null;
		WebDriver WebD = null;
		try {
			if (parameters != null && parameters.length > 0) {
				BName = parameters[0].toString();
				WebD = new BrowserConfig().getBrowser(BName);
				setSelenium(new SeleniumUtil(getExT(result.getName()).createNode(BName), WebD, BName, 15));
			} else {
				BName = "Edge";
				WebD = new BrowserConfig().getBrowser(BName);
				setSelenium(new SeleniumUtil(Ereport.createTest(result.getName()), WebD, BName, 15));
			}
		} catch (Exception e) {
			setSelenium(new SeleniumUtil(getExT(result.getName()).createNode(BName), 15));
			throw new SkipException(e.toString());

		}
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
		String ReportPath = new File(reportRoot, "Extent.html").getAbsolutePath();
		Ereport = new ExtentReports();
		Espark = new ExtentSparkReporter(ReportPath);
		Espark.config().setDocumentTitle(projectPathRoot.getName().toString());
		Espark.config().setReportName("KMDV Automation Framework");
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
