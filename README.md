TestProject for testing REST API https://jsonplaceholder.typicode.com/

To run the tests use: 
    mvn clean test
To run the reporter use: 
    mvn allure:serve

Test Framework: TestNG
    - tests organized into testng.xml suites
    - some assertions
    - parallel execution
    - data providers

Rest client: RestAssured
    - REST API client
    - some assertions
    - logs
    
POJO models: lombok, jackson

TestData: Yaml

Reporter: Allure

Test implemented 
    - for Contract testing: com.typicode.jsonplaceholder.contract
    - for Functional testing: com.typicode.jsonplaceholder.endpoints
