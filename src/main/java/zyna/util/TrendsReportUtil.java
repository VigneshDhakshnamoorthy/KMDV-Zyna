package zyna.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.JsonObject;
import java.util.Random;

public class TrendsReportUtil {
    public static void main(String[] args) {
        JsonObject jsonDataObject = new JsonObject();

        // Generate and add data for dates from 10th October to 29th October
        for (int day = 1; day <= 29; day++) {
            String date = String.format("%02dOct23-14_30", day);
            int pass = new Random().nextInt(50) + 1;  // Random pass value (1 to 50)
            int fail = new Random().nextInt(50) + 1;  // Random fail value (1 to 50)
            String link = "c:/" + date + ".html";

            JsonObject testCaseData = new JsonObject();
            testCaseData.addProperty("pass", pass);
            testCaseData.addProperty("fail", fail);
            testCaseData.addProperty("Link", link);

            jsonDataObject.add(date, testCaseData);
        }

        // Generate the HTML dashboard
        String htmlDashboard = generateHTMLDashboard(jsonDataObject);
        try {
            FileWriter fileWriter = new FileWriter("dashboard.html");
            fileWriter.write(htmlDashboard);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateHTMLDashboard(JsonObject data) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<title>Test Execution Dashboard</title>");
        html.append("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>");
        html.append("<style>");
        html.append("body { font-size: 16px; margin: 0; }");
        html.append(".header { background-color: lightgrey; border: 1px solid black; display: flex; justify-content: space-between; align-items: center; padding: 10px; }");
        html.append(".logo { max-width: 120px; }");
        html.append(".header-text { font-size: 24px; text-align: center; flex-grow: 1; margin: 0 auto; }");
        html.append(".date { text-align: right; flex-grow: 1; }");
        html.append("table { width: 100%; border-collapse: collapse; }");
        html.append("th, td { border: 1px solid black; padding: 8px; text-align: center; }");
        html.append(".table-container { overflow-y: scroll; max-height: 400px; }");
        html.append(".table-header { position: sticky; top: 0; background-color: lightgrey; border: 1px solid black; }");
        html.append(".container { display: flex; justify-content: space-between; padding: 10px; }");
        html.append(".title { flex-grow: 2; text-align: center; }");
        html.append(".chart { width: 100%; max-width: 800px; margin: 0 auto; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class='header'>");
        html.append("<div class='logo'><img src='logo.png' alt='Logo' style='max-width: 100%; height: auto;'></div>");
        html.append("<div class='header-text'>Zyna Trends Report</div>");
        html.append("<div class='date'>" + getCurrentDateTime() + "</div>");
        html.append("</div>");
        html.append("<div class='chart'>");
        html.append("<h3 class='chart'>Chart: Datewise Pass and Fail</h3>");
        html.append("<canvas id='myChart'></canvas>");
        html.append("</div>");
        html.append("<div class='table-container'>");
        html.append("<h3>Test Case Summaries</h3>");
        html.append("<table>");
        html.append("<tr class='table-header'><th>Date</th><th>Pass</th><th>Fail</th><th>Link</th></tr>");
        for (String date : data.keySet()) {
            JsonObject testCaseData = data.get(date).getAsJsonObject();
            int pass = testCaseData.get("pass").getAsInt();
            int fail = testCaseData.get("fail").getAsInt();
            String link = testCaseData.get("Link").getAsString();
            html.append("<tr>");
            html.append("<td>").append(date).append("</td>");
            html.append("<td>").append(pass).append("</td>");
            html.append("<td>").append(fail).append("</td>");
            html.append("<td><a href=\"").append(link).append("\" target=\"_blank\">View Report</a></td>");
            html.append("</tr>");
        }
        html.append("</table>");
        html.append("</div>");
        html.append("<script>");
        html.append("function createChart(data) {");
        html.append("var ctx = document.getElementById('myChart').getContext('2d');");
        html.append("var labels = Object.keys(data);");
        html.append("var passData = labels.map(function (date) { return data[date].pass; });");
        html.append("var failData = labels.map(function (date) { return data[date].fail; });");
        html.append("var chart = new Chart(ctx, {");
        html.append("type: 'bar',");
        html.append("data: {");
        html.append("labels: labels,");
        html.append("datasets: [{");
        html.append("label: 'Pass',");
        html.append("data: passData,");
        html.append("backgroundColor: 'green',");
        html.append("}, {");
        html.append("label: 'Fail',");
        html.append("data: failData,");
        html.append("backgroundColor: 'red',");
        html.append("}],");
        html.append("},");
        html.append("options: {");
        html.append("responsive: true,");
        html.append("maintainAspectRatio: false,");
        html.append("scales: {");
        html.append("x: { beginAtZero: true },");
        html.append("y: { beginAtZero: true },");
        html.append("},");
        html.append("});");
        html.append("}");
        html.append("createChart(" + data.toString() + ");");
        html.append("</script>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy - HH:mm");
        return sdf.format(new Date());
    }
}


