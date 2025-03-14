AutoSauceFramework 🚀  
**Selenium Automation Framework for [Saucedemo.com](https://www.saucedemo.com/)**  

🛠️ Tech Stack  
- **Language**: Java 17  
- **Frameworks**: Selenium 4, TestNG  
- **Tools**: Jenkins, Docker, Maven, Git  
- **Reporting**: ExtentReports, Log4j2  

✅ Key Features  
- Page Object Model (POM) Design  
- Data-Driven Testing with TestNG `DataProvider`  
- Cross-Browser Testing (Chrome/Firefox)  
- CI/CD Pipelines with Jenkins & Docker  
- Parallel Test Execution  

🚀 Getting Started  
Prerequisites
- Java 17+  
- Maven 3.8.6+

Installation  
1. Clone the repo:  
    git clone [https://github.com/PiyushAhir21/AutomationSauceLab.git](https://github.com/PiyushAhir21/SauceLabAutomation.git)

    cd AutomationSauceLab  
3.	Run tests:

    mvn clean test -Dbrowser=chrome  
4.	View reports:

    target/extent-report.html

📋 Test Scenarios

•	Login with valid/invalid users.

•	Product sorting (Price, Name).

•	Add/Remove items from cart.

•	Checkout flow validation.


📂 Project Structure
AutoSauceFramework  
├── src/test/java  
│   ├── pages        # Page Objects (LoginPage, CartPage, etc.)  
│   ├── tests        # Test Scripts  
│   └── utilities    # Helpers (Config, ReportManager)  
├── src/test/resources  
│   ├── config.properties  
│   └── testdata.xlsx  
├── Jenkinsfile      # CI/CD Pipeline  
├── Dockerfile       # Containerization  
└── pom.xml  


CI/CD Pipeline

•	Jenkins triggers tests on Git push.

•	Dockerized execution for consistency.


🤝 Contribute
1.	Fork the repo.
2.	Create a branch: git checkout -b feature/new-feature.
3.	Commit changes: git commit -m 'Add feature'.
4.	Push: git push origin feature/new-feature.
5.	Open a pull request.

   
📜 License
MIT License. See [LICENSE](https://github.com/PiyushAhir21/SauceLabAutomation/blob/main/LICENSE) for details.
