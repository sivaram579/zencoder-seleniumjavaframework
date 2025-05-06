# GitHub Actions Workflow

This directory contains the GitHub Actions workflow for automating the Selenium test execution process.

## Maven Test Workflow (maven-test.yml)

This workflow runs Selenium tests on every push to main/master branch, on pull requests, and can be triggered manually.

### Triggers
- Push to main/master branch
- Pull requests to main/master branch
- Manual trigger (workflow_dispatch)

### Workflow Steps
1. **Checkout code**: Retrieves the repository code
2. **Set up JDK 11**: Configures Java environment
3. **Setup Chrome**: Installs Chrome browser for testing
4. **Build with Maven**: Compiles the project
5. **Run Tests**: Executes the Selenium tests
6. **Generate Extent Reports**: Processes and lists the test reports
7. **Archive test results**: Saves reports and screenshots as artifacts
8. **Publish Test Report**: Creates a summary of test results in the workflow

### Key Features
- **Concurrency control**: Prevents multiple workflow runs from interfering with each other
- **Timeout limits**: Prevents workflows from running indefinitely (20 minutes max)
- **Artifact retention**: Keeps test reports for 14 days
- **Test summary**: Provides a quick overview of test results directly in GitHub
- **Error handling**: Robust handling of test failures and missing files

## Running the Workflow Manually

To manually trigger the workflow:
1. Go to the "Actions" tab in your GitHub repository
2. Select "Selenium Test Automation" workflow
3. Click "Run workflow"
4. Select the branch to run against
5. Click "Run workflow" button

## Viewing Test Reports

After the workflow runs, you can access the test reports in two ways:

1. **Artifacts**: 
   - Go to the workflow run
   - Scroll down to the "Artifacts" section
   - Download the "test-reports-{run_id}" artifact
   - Extract and open the HTML reports in a browser

2. **Workflow Summary**:
   - A summary of available reports is displayed directly in the workflow run
   - This provides a quick overview of the test execution

## Customizing the Workflow

You can customize this workflow by:
- Changing the trigger conditions
- Modifying the timeout value
- Adding additional build or test parameters
- Configuring different browser options
- Adding post-test actions like notifications

## Workflow Optimizations

The workflow includes several optimizations:
- **Concurrency limits**: Prevents parallel runs from interfering with each other
- **Caching**: Maven dependencies are cached to speed up builds
- **Conditional steps**: Some steps only run when needed
- **Unique artifact naming**: Prevents overwriting artifacts from different runs