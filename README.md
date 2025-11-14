Hyperface Assignment – API Automation (Rest Assured + TestNG)

This project automates CRUD API testing using Rest Assured, TestNG, and Extent Reports.
It is configured as a Maven project and includes JSON schema validation, reporting, and reusable utilities.

🚀 Tech Stack

Java 17

Rest Assured

TestNG

Extent Reports

JSON Schema Validator

Maven

📁 Project Structure
hyperface-assignment-api/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       ├── endpoints/
│   │       ├── payloads/
│   │       └── utils/
│   │
│   └── test/
│       ├── java/
│       │   └── tests/UserCrudTest.java
│       │
│       └── resources/
│           ├── testng.xml
│           └── schemas/user_schema.json
│
├── pom.xml
└── README.md

📌 Features

✔ CRUD operations (Create, Read, Update, Delete)
✔ JSON schema validation
✔ Request/Response specifications
✔ Extent HTML reporting
✔ Modular design: BaseTest, Endpoints, Payloads, Utils
✔ Maven Surefire plugin integration

🛠️ How to Run Tests
1️⃣ Clean and run entire test suite
mvn clean test

2️⃣ Run a single test class
mvn -Dtest=UserCrudTest test

🧪 Test Reports

After execution, reports are generated at:

/reports/API-TestReport.html


and TestNG/Maven reports:

/target/surefire-reports/

📂 JSON Schema Location

Ensure schema file exists in:

src/test/resources/schemas/user_schema.json

📝 Test Covered
Test Case	Description
TC01	Create User
TC02	Get User
TC03	Update User
TC04	Delete User
⚙ Prerequisites

JDK 17 installed

Maven configured (mvn -version)

Internet access (API hits jsonplaceholder)