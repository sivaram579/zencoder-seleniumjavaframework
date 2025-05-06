# GitHub Actions Workflows

This directory contains GitHub Actions workflows for automating the testing process.

## Available Workflows

### 1. maven-test.yml
Runs Selenium tests on every push to main/master branch and on pull requests.

**Triggers:**
- Push to main/master
- Pull requests to main/master
- Manual trigger (workflow_dispatch)

**Actions:**
- Checks out code
- Sets up JDK 11
- Sets up Chrome
- Builds with Maven
- Runs tests
- Archives test reports and screenshots

### 2. scheduled-tests.yml
Runs tests on a schedule (daily at 2 AM UTC).

**Triggers:**
- Schedule (cron: '0 2 * * *')
- Manual trigger (workflow_dispatch)

**Actions:**
- Checks out code
- Sets up JDK 11
- Sets up Chrome
- Builds with Maven
- Runs tests
- Archives test reports and screenshots
- Sends email notification on failure (requires secrets configuration)

### 3. parallel-tests.yml
Runs tests in parallel on multiple browsers.

**Triggers:**
- Push to main/master (only when specific files change)
- Manual trigger (workflow_dispatch)

**Actions:**
- Checks out code
- Sets up JDK 11
- Sets up specified browser (Chrome or Firefox)
- Builds with Maven
- Runs tests with browser parameter
- Archives test reports and screenshots for each browser

### 4. publish-reports.yml
Publishes test reports to GitHub Pages.

**Triggers:**
- Completion of any test workflow

**Actions:**
- Downloads test report artifacts
- Creates an index page
- Publishes reports to GitHub Pages

## Required Secrets for Email Notifications

To enable email notifications for scheduled test failures, add these secrets to your repository:

- `MAIL_SERVER`: SMTP server address
- `MAIL_PORT`: SMTP server port
- `MAIL_USERNAME`: Email username
- `MAIL_PASSWORD`: Email password
- `NOTIFICATION_EMAIL`: Email address to receive notifications

## Running Workflows Manually

You can manually trigger any workflow from the "Actions" tab in your GitHub repository.

## Viewing Test Reports

After the workflows run, test reports will be:
1. Available as downloadable artifacts for each workflow run
2. Published to GitHub Pages (if the publish-reports workflow is enabled)

## Customizing Workflows

You can customize these workflows by:
- Changing the trigger conditions
- Modifying the browser matrix
- Adjusting the schedule
- Adding more test parameters