# NopCommerce Cucumber Automation

Selenium + Cucumber BDD framework for NopCommerce admin testing.

## ✨ Features
- Login/Logout automation
- Customer management (Add/Search)
- Cross-browser support
- Data-driven testing

## 🏗️ Structure
```
src/test/
├── java/testRunner/         # Test runner
├── java/stepDefinitions/    # Step definitions  
├── resources/Features/      # Feature files
└── pageObjects/            # Page objects
```

## 🚀 Quick Start
```bash
git clone <repo-url>
mvn test
```

## 🎯 Run Tests
```bash
mvn test -Dcucumber.filter.tags="@sanity"      # Sanity tests
mvn test -Dcucumber.filter.tags="@regression"  # Regression tests
```

## 🔧 Tech Stack
Java | Selenium | Cucumber | Maven | JUnit

## 📊 Test Scenarios
```mermaid
graph TD
    A[NopCommerce Tests] --> B[Login Module]
    A --> C[Customer Module]
    B --> D[Valid Login - sanity]
    B --> E[Data Driven Login - regression]
    C --> F[Add Customer - sanity]
    C --> G[Search by Email - regression]
    C --> H[Search by Name - regression]
    D --> I[Dashboard Verification]
    E --> I
    F --> J[Success Message Check]
    G --> K[Email Found in Table]
    H --> L[Name Found in Table]
```

**Reports**: `target/cucumber-reports.html`
