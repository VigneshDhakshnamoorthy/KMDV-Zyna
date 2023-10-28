package zyna.config;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import zyna.base.TestBase;
import zyna.common.SeleniumUtil;

public class TestngConfig extends TestBase implements ITestListener {

	private ExtentReports Ereport;
	private ExtentSparkReporter Espark;
	private File projectPathRoot = new File(System.getProperty("user.dir"));
	private File reportRoot = new File(projectPathRoot, "Report");
	private HashMap<String, ExtentTest> ExT = new HashMap<String, ExtentTest>();

	@Override
	public  void onTestStart(ITestResult result) {
		Object[] parameters = result.getParameters();
		if (parameters != null && parameters.length > 0) {
	
			setSelenium(new SeleniumUtil(parameters[0].toString(),
					getExT(result.getName()).createNode(parameters[0].toString()), 15));
		} else {

			setSelenium(new SeleniumUtil(parameters[0].toString(), Ereport.createTest(result.getName()), 15));
		}

		getSelenium().Log(result.getName());

	}
	
	public synchronized ExtentTest getExT(String resultName) {
		if (!ExT.containsKey(resultName)) {
			ExT.put(resultName, Ereport.createTest(resultName));
		}
		return ExT.get(resultName);
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
