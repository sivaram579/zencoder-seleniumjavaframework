# Selenium TestNG Framework with Extent Reports

This is a robust test automation framework built with Selenium WebDriver, TestNG, and Extent Reports.

## Features

- Page Object Model (POM) design pattern
- TestNG for test execution and assertions
- WebDriverManager for browser driver management
- Extent Reports for detailed HTML test reports
- Screenshot capture on test failures
- Logging of test steps

## Project Structure

```
selenium-testng-project/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── orangehrm/
│   │               ├── pages/       # Page Object classes
│   │               └── utils/       # Utility classes
│   └── test/
│       └── java/
│           └── com/
│               └── orangehrm/
│                   ├── listeners/   # TestNG listeners
│                   └── tests/       # Test classes
├── test-output/                     # Test execution reports
├── pom.xml                          # Maven configuration
└── testng.xml                       # TestNG configuration
```

## Extent Reports Implementation

The framework includes comprehensive reporting using Extent Reports:

1. **ExtentReportManager**: Manages the creation and configuration of Extent Reports.
2. **TestListener**: TestNG listener that hooks into the test execution lifecycle to create and update reports.
3. **TestUtils**: Utility class for logging test steps and capturing screenshots.
4. **BaseTest**: Base class for all test classes, includes screenshot capture functionality.

### Report Features

- Detailed test execution logs
- Screenshots at key points during test execution
- Screenshots on test failures
- Test status (Pass/Fail/Skip)
- System information
- Timestamps for test execution

## Running Tests

To run the tests, use the following Maven command:

```
mvn clean test
```

The HTML reports will be generated in the `test-output/reports` directory.

## Viewing Reports

After test execution, open the HTML report in any web browser. The report includes:

- Test summary
- Detailed test steps
- Screenshots
- Test execution time
- Environment information

## Customizing Reports

You can customize the reports by modifying the `ExtentReportManager` class:

- Change the report theme
- Add additional system information
- Modify the report name format
- Change the report location