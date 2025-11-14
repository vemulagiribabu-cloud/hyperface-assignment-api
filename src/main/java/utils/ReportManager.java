package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports(String reportPath) {

        if (extent == null) {
            ExtentHtmlReporter html = new ExtentHtmlReporter(reportPath);
            html.config().setDocumentTitle("API Automation Report");
            html.config().setReportName("CRUD API Test Execution");
            html.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(html);
        }
        return extent;
    }
}
