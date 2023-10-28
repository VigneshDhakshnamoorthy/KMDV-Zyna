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

public class TestngConfig implements ITestListener {

	private ExtentReports Ereport;
	private ExtentSparkReporter Espark;
	private File projectPathRoot = new File(System.getProperty("user.dir"));
	private File reportRoot = new File(projectPathRoot, "Report");
	private HashMap<String, ExtentTest> ExT = new HashMap<String, ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		Object[] parameters = result.getParameters();
		if (parameters != null && parameters.length > 0) {
			if(ExT.containsKey(result.getName())){
				ExT.get(result.getName()).createNode(parameters[0].toString());
			}else {
				ExT.put(result.getName(),Ereport.createTest(result.getName()));
				ExT.get(result.getName()).createNode(parameters[0].toString());
			}
		}else {
			Ereport.createTest(result.getName());
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
