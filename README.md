# Selenium TestNG Framework with Extent Reports

This is a robust test automation framework built with Selenium WebDriver, TestNG, and Extent Reports, with CI/CD integration via GitHub Actions.

## Features

- Page Object Model (POM) design pattern
- TestNG for test execution and assertions
- WebDriverManager for browser driver management
- Extent Reports for detailed HTML test reports
- Screenshot capture on test failures
- Logging of test steps
- Cross-browser testing support (Chrome, Firefox, Edge)
- CI/CD integration with GitHub Actions

## Project Structure

```
selenium-testng-project/
├── .github/
│   └── workflows/                   # GitHub Actions workflow files
│       ├── maven-test.yml           # Test automation workflow
│       └── README.md                # Workflow documentation
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
│   ├── reports/                     # Extent Reports
│   └── screenshots/                 # Test screenshots
├── pom.xml                          # Maven configuration
├── README.md                        # Project documentation
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

## GitHub Actions Integration

This framework includes a GitHub Actions workflow for continuous integration and automated testing:

### Maven Test Workflow

The `maven-test.yml` workflow automates the testing process:

- **Triggers**: Runs on pushes to main/master, pull requests, and manual triggers
- **Environment**: Uses Ubuntu with JDK 11 and Chrome browser
- **Process**: Builds the project, runs tests, and generates reports
- **Artifacts**: Saves test reports and screenshots for 14 days
- **Summary**: Provides a test execution summary directly in GitHub

### Running Tests with Different Browsers

You can specify the browser for local test execution using the system property:

```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Viewing Test Reports

After the GitHub Actions workflow runs, you can access test reports in two ways:

1. **Download Artifacts**: 
   - Go to the workflow run in GitHub Actions
   - Download the "test-reports-{run_id}" artifact
   - Extract and open the HTML reports in a browser

2. **Workflow Summary**:
   - A summary of test results is displayed directly in the workflow run
   - This provides a quick overview without downloading artifacts

### Workflow Features

- **Concurrency control**: Prevents multiple workflow runs from interfering
- **Timeout limits**: Prevents workflows from running indefinitely
- **Caching**: Maven dependencies are cached to speed up builds
- **Error handling**: Robust handling of test failures

For more details on GitHub Actions configuration, see the [workflows README](.github/workflows/README.md).