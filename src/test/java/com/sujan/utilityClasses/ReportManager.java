package com.sujan.utilityClasses;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {

    public static class Status {
        public static final String PASS = "PASS";
        public static final String FAIL = "FAIL";

        private Status() {
        }
    }

    private static PrintWriter reportFile = null;
    private static WebDriver driver = null;
    private static int stepCounter = 1;
    private static boolean reportInitialized = false;

    private static final String basePath = System.getProperty("user.dir");
    private static Path screenshotExecutionFolder = null;

    public ReportManager() {
    }

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static void initializeReport() {

        if (reportInitialized) {
            return;
        }

        try {
            String executionTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMMdd_HH_mm_ss"));

            Path reportsDir = Paths.get(basePath, "reports");
            Files.createDirectories(reportsDir);

            screenshotExecutionFolder = reportsDir.resolve("screenshots").resolve(executionTime);
            Files.createDirectories(screenshotExecutionFolder);

            Path reportPath = reportsDir.resolve("TestReport_" + executionTime + ".html");

            reportFile = new PrintWriter(new FileWriter(reportPath.toFile(), false));

            reportFile.write("""
                    <html>
                    <head>
                    <title>Automation Report</title>

                    <style>

                    body {font-family: Arial; background:#fafafa;}

                    table {border-collapse: collapse; width: 100%;}

                    th, td {
                        border: 1px solid #ddd;
                        padding: 8px;
                        text-align: center;
                    }

                    th {background:#f2f2f2;}

                    .PASS {color: green; font-weight:bold;}
                    .FAIL {color: red; font-weight:bold;}

                    img {
                        width:120px;
                        border:1px solid #ccc;
                    }

                    </style>

                    </head>

                    <body>

                    <h2>Automation Execution Report</h2>

                    <table>

                    <tr>
                    <th>Sl. No</th>
                    <th>Step</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Date & Time</th>
                    <th>Screenshot</th>
                    </tr>
                    """);

            reportInitialized = true;

        } catch (IOException e) {
            System.out.println("[Exception in initializeReport] " + e.getMessage());
        }
    }

    public static void updateTestLog(String step, String description, String status) {

        if (!reportInitialized) {
            initializeReport();
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String screenshotName = "step_" + stepCounter + ".png";

        File screenshotAbsolute = screenshotExecutionFolder.resolve(screenshotName).toFile();

        String relativeFolder = screenshotExecutionFolder.getFileName().toString();

        String screenshotRelative = "screenshots/" + relativeFolder + "/" + screenshotName;

        if (driver != null) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(src.toPath(), screenshotAbsolute.toPath());
            } catch (IOException e) {
                System.out.println("[Exception saving screenshot] " + e.getMessage());
            }
        }

        String statusClass = Status.PASS.equals(status) ? "PASS" : "FAIL";

        reportFile.write(String.format("""
                <tr>
                <td>%d</td>
                <td>%s</td>
                <td>%s</td>
                <td class="%s">%s</td>
                <td>%s</td>
                <td>
                <a href="%s" target="_blank">
                <img src="%s">
                </a>
                </td>
                </tr>
                """,
                stepCounter, step, description, statusClass, status,
                timestamp, screenshotRelative, screenshotRelative));

        stepCounter++;
    }

    public static void closeReport() {

        if (reportFile != null) {

            reportFile.write("""
                    </table>
                    </body>
                    </html>
                    """);

            reportFile.flush();
            reportFile.close();

            reportFile = null;
            reportInitialized = false;
            screenshotExecutionFolder = null;
            stepCounter = 1;
        }
    }
}
