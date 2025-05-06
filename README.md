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
- Parallel test execution
- Scheduled test runs

## Project Structure

```
selenium-testng-project/
├── .github/
│   └── workflows/                   # GitHub Actions workflow files
│       ├── maven-test.yml           # Standard test workflow
│       ├── parallel-tests.yml       # Parallel browser testing
│       ├── publish-reports.yml      # Report publishing workflow
│       ├── scheduled-tests.yml      # Scheduled test runs
│       └── README.md                # Workflows documentation
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

This framework includes GitHub Actions workflows for continuous integration and automated testing:

### Available Workflows

1. **Standard Test Workflow** (`maven-test.yml`)
   - Runs on every push to main/master branch and on pull requests
   - Executes tests on Chrome browser
   - Archives test reports and screenshots

2. **Scheduled Tests** (`scheduled-tests.yml`)
   - Runs daily at 2 AM UTC
   - Sends email notifications on failure
   - Archives test reports with longer retention

3. **Parallel Browser Testing** (`parallel-tests.yml`)
   - Runs tests in parallel on multiple browsers (Chrome, Firefox)
   - Uses matrix strategy for efficient execution
   - Archives browser-specific test reports

4. **Report Publishing** (`publish-reports.yml`)
   - Publishes test reports to GitHub Pages
   - Creates an index page for easy navigation
   - Triggered after test workflows complete

### Running Tests with Different Browsers

You can specify the browser for test execution using the system property:

```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Viewing GitHub Actions Reports

After GitHub Actions workflows run, you can:
1. Download test reports as artifacts from the workflow run
2. View published reports on GitHub Pages (if enabled)
3. Check the workflow logs for test execution details

For more details on GitHub Actions configuration, see the [workflows README](.github/workflows/README.md).