package zyna.config;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import zyna.base.TestBase;
import zyna.util.PdfReportUtil;

public class TestngConfig extends TestBase implements ITestListener {


	@Override
	public void onTestStart(ITestResult result) {
		
		Object[] parameters = result.getParameters();
		String BName;
		if (parameters != null && parameters.length > 0) {
			BName = parameters[0].toString();
		}else {
			BName = "Edge";
		}
		try {
			pdf.set(new PdfReportUtil(new File(pdfReportsRoot, result.getName()+"-"+BName+".pdf").getAbsolutePath(), result.getName()+"-"+BName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		initWeb(result);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			pdf.get().saveDocument();
			pdf.get().closeDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			pdf.get().saveDocument();
			pdf.get().closeDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
