# Headspin Hackathon Challenge

## Overview
We as **Infostretch** team has built a Maven based Java project for MMT mobile app automation hackathon challenge organized by the Headspin. Source code has some dependency on third party open source libraries like TestNG, Appium etc. and details are mentioned in **Dependencies** section.

## Team - Infostretch
* Mehul Kagathara (Lead)
* Amit Bhoraniya
* Nishith Shah
* Shivam Gohel

## Our Approach
In our implementation, we have tried to demonstrate the idea how we can abstract the technical implementation away from the operational components to support shift-left development and easy maintenance of automation project.
### Below are the key considerations to solve given MMT problem:
* **Structure Approach** - abstract the technical implementation away from the operational components like configurations, locators & test data for easy maintenance.
* **Behaviour Driven Support** - integration with Cucumber for behaviour-driven test authoring make it easier to work together.
* **Unified Scripting Approach** - test or scenario should be unified and it should run across the platform (both Android, iOS & Web).
* **Parallel Support** - integration with TestNG + Cucumber to run feature file scenarios parallel at scale.
* **Reusable Test Assets** - highly maintainable and repeatable tests utilize reusable test assets, proper modularity and semantic structure.
* **Test Data Management** - test data should be abstracted from the technical implementation so it is easier to update the data in future and avoid harding of data in implementation.
* **Reporting** - integrate third party reporting engine which ensures access to all relevant execution data which provide insights desired.
* **Scalability** - solution should be scalable on cloud like Headspin for Continous Testing and tests should run parallel at scale.
## Setup & Usage
Following below steps in order to setup project and execute the test(s).
* Clone https://github.com/mehulkagathara/headspin-hackathon.git repo or Download Zip and import as Maven project in Eclipse or IntelliJ.
* After importing project to Eclipse right click on project and navigate to `Maven -> Update Project` to resolve the dependencies.
* Make sure you've maven configured in your machine and then run below maven command to run.
    ```shell
    mvn clean test
    ```
## Dependencies
Below are the maven dependencies used to build this project.
* [org.seleniumhq.selenium](https://mvnrepository.com/artifact/org.seleniumhq.selenium)
* [io.appium](https://mvnrepository.com/artifact/io.appium/java-client)
* [com.qmetry](https://mvnrepository.com/artifact/com.qmetry)
* [org.slf4j](https://mvnrepository.com/artifact/org.slf4j)
* [io.github.bonigarcia](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager)

## Framework Design
This automation framework has below core components which helps to drive the automated code execution and structured the code for easy maintenance.
* **Driver Manager** - this component is useful to initialize the Selenium/Appium driver and it's derived from the QAF library.
* **Configuration Manager** - this component is useful to abstract the technical implementation away from the operational components and helps to manage the locators & test data separately.
* **Reporting** - this component is useful to generate the execution report by collecting selenium & appium commands log.
### Folder Structure
* **`config`** - folder consists all TestNG run configuration xml files in which user can specify what to run. User can also override any properties from the configuration file as well by providing TestNG Parameter tag. Currently there is one configuration file called `testrun_config.xml` to run the tests. We can create environment specific configuration files and put inside this folder.
* **`resources`** - under this folder there are two sub folders & `application.properties` file.
    * **`locators`** - folder consists all application pages locators in separate individual page wise .properties file. A locator can be defined in key-value pair and through out implementation we can use key to refer the elements. So if tomorrow there will be any change in locator we just need to update the properties file and no changes will be required at implementation level. 
    
        ```properties
            login.input.email=-ios class chain=**/XCUIElementTypeTextField[`value CONTAINS[cd] "Email"`]
            login.input.password=-ios class chain=**/XCUIElementTypeSecureTextField
            login.btn.submit=accessibility id=SUBMIT
        ```
    * **`testdata`** - test data can be provided in .xml format and to consume the data at step level, user just need to provide the xml key to get the value.
   
        ```xml
        <root>
          <hotel>
            <booking>
              <username>hshackathon@mailinator.com</username>
              <password>******</password>
              <menu>Hotels</menu>
            </booking>
          </hotel>
        </root>
        ``` 
    * **`application.properties`** - consists all global application & driver related properties. like AUT url and desire capabilities etc.
    
        ```properties
           platform=android
           env.baseurl=https://www.makemytrip.com/
           env.resources=resources/testdata;resources/config/${platform};resources/locators/${platform}   
           step.provider.pkg=com.infostretch.hs.steps.common;com.infostretch.hs.steps.${platform}
           
           remote.server=http://localhost:4723/wd/hub
           selenium.wait.timeout=30000
           
           wd.command.listeners=com.infostretch.hs.utils.WebDriverListener
           webdriver.manage.auto=true
        ```
* **`scenarios`** - solution is integrated with Cucumber and all feature files and its scenarios reside inside this folder.
     ```gherkin
             Feature: Hotel Booking
               The user should be able to book a hotel into the Make My Trip application from given check-in, out & payment details.
             
               Background:
                 Given user launches application
                 And login to the application with '${username}' and '${password}'
                 Then verify user should logged into the application
             
               @key:hotel.booking
               Scenario: Hotel booking from Make My Trip Application
                 When user navigates to the '${menu}'
                 And select a location '${location}'
     ```  
* **`src`** -
    * **`main/java`**
        * **`com.infostretch.hs`** - this package consists all reusable code, listeners to manage the driver and perform some common mobile/web operations. 
    * **`test/java`**
        * **`com.infostretch.hs.components`** - we have introduced the concept called `Component` in framework to represent the specific section of application as a WebElement. For example, while searching hotels, it return list of result items and each item has some unique characteristic like Hotel Name, Price, etc. We can represent each of the result item as a component. Advantages is we don't need to write repetitive code to handle and select item from result and get child elements.
        * **`com.infostretch.hs.steps`** - consists implementation of steps for scenarios.
* **`test-results`** - execution report will generate inside this folder based on timestamp.
* **`dashboard`** - contains implementation of JavaScript & CSS for reporting. we did customization on top of third party QAF reporting and integrate with our solution.
## Reporting
[Exection Report](https://mehulkagathara.github.io/headspin-hackathon/dashboard.htm)
>Since reporting has javascript dependencies, if you're facing any issue viewing the report please follow below steps in order to work it properly.
* Go to `about:config`
* Set `security.fileuri.strict_origin_policy` to `false`

## Headspin Integration
## CI Integration
## Top Challenges


