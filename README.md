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
* **Test Data Management** - test data should be abstracted from the technical implementation so it is easier to update the data in future and avoid hard coding of data in implementation.
* **Reporting** - integrate third party reporting engine which ensures access to all relevant execution data which provide insights desired.
* **Scalability** - solution should be scalable on cloud like Headspin for Continuous Testing and tests should run parallel at scale.

## Setup & Usage
Follow below steps in order to setup project and execute the test(s).
* Clone https://github.com/mehulkagathara/infostretch-hs-hackathon.git repo or Download Zip and import as Maven project in Eclipse or IntelliJ.
* After importing project to Eclipse right click on project and navigate to `Maven -> Update Project` to resolve the dependencies.
* Make sure you've maven configured in your machine and then run below maven command to run tests.
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
* **Execution Manager** - this component is useful to ensure tests execution with TestNG integration and does support Parallel execution as well.
* **Reporting** - this component is useful to generate the execution report by collecting Selenium & Appium commands log.
### Folder Structure
* **`config`** - folder consists all TestNG run configuration xml files in which user can specify what to run. User can also override any properties from the configuration file as well by providing TestNG Parameter tag. Currently there is one configuration file called `testrun_config.xml` to run the tests. We can create environment specific configuration files and put inside this folder.
* **`resources`** - under this folder there are two sub folders & `application.properties` file.
    * **`platform`** - this folder contains the environment specific desired capabilities for both Android & iOS. So, if test is running on Android then `android/env.properties` will load and if it is running on iOS then `ios/env.properties` will load.
        ```properties
           driver.name=androidDriver
           android.capabilities.driverClass=io.appium.java_client.android.AndroidDriver
           android.capabilities.browserName=
           android.capabilities.platformName=Android
           android.capabilities.deviceName=aae87949
           android.capabilities.appPackage=com.makemytrip
           android.capabilities.appActivity=com.mmt.travel.app.home.ui.SplashActivity
        ```
    * **`locators`** - folder consists all application pages locators in separate individual page wise .properties file. A locator can be defined in key-value pair and throughout implementation we can use key to refer the elements. So if tomorrow there will be any change in locator we just need to update the properties file and no changes will be required at implementation level. 
    
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
            The user should be able to book a hotel into the MMT application.
                 
            Background:
              Given user launches application
              When login to the application with '${username}' and '${password}'
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
        * **`com.infostretch.hs.steps`** - consists implementation of steps for scenarios. For example,
            ```java
          /**
               * Login with provided username and password
               *
               * @param username
               * @param password
               */
              @QAFTestStep(description = "login to the application with {username} and {password}")
              public void login(String username, String password) {
                  CommonStep.sendKeys(username, "login.input.email");
                  CommonStep.click("login.btn.continue");
                  CommonStep.click("login.btn.via.password");
                  CommonStep.sendKeys(password, "login.input.password");
                  CommonStep.click("login.btn.submit");
                  
                  if ($("navigate.back.button").isPresent()) {
                      $("navigate.back.button").click();
                  }
              }
            ```
* **`test-results`** - execution report will generate inside this folder based on timestamp.
* **`dashboard`** - contains implementation of JavaScript & CSS for reporting. we did customization on top of third party QAF reporting and integrate with our solution.
### Locator Strategies Used
Selenium & Appius does support different locator strategies in order to access application elements like by `id`,`css`,`xpath` etc. We used `native` locator strategy as first priority throughout the implementation to get better performance. Also, keep locator key name same for both Android & iOS platform so in implementation we can use the same key to build Unified test and platform can be specified from `application.properties` file. Examples,   
```properties
login.input.email=-ios class chain=**/XCUIElementTypeTextField[`value CONTAINS[cd] "Email"`]
hotel.search.link.city=accessibility id=searchCity
```     
```properties
login.input.email=classname=android.widget.EditText
hotel.search.link.city=id=city
```
### Reusability
We kept reusability in mind while implementation and hence abstract the actual implementation away from the resources, test data and scenarios steps. Every steps can be reused in different scenarios by changing passing appropriate test data. Nothing is hard coded inside code. Also, components can be reused for different purposes like currently we used for Hotels booking but tomorrow same can be used for Flight booking too.

### Parallel Execution
Framework can handle parallel execution too with help of TestNG and QAF Library. Below is an example of `testrun_config.xml` to run same test suite on both Android & iOS platform.
```xml
<suite name="MakeMyTrip Suite" verbose="0" parallel="tests">
  <test name="MakeMyTrip Test">
    <parameter name="platform" value="android"></parameter>
    <classes>
      <class name="com.qmetry.qaf.automation.step.client.text.BDDTestFactory2"></class>
    </classes>
  </test>
  <test name="MakeMyTrip Test">
    <parameter name="platform" value="ios"></parameter>
    <classes>
      <class name="com.qmetry.qaf.automation.step.client.text.BDDTestFactory2"></class>
    </classes>
  </test>
</suite>
```
## Logging & Reporting

[Execution Report](https://mehulkagathara.github.io/infostretch-hs-hackathon/dashboard.htm)

If you open dashboard from local file system, your browser may have local file access restrictions. In that case, if you face any issue you can do following settings:
##### Firefox:
 - go to about:config
 - set security.fileuri.strict_origin_policy:false. 
##### Safari:
 - Click on the Develop menu in the menu bar. 
 - Select Disable Local File Restrictions.

 If develop menu is not available, Click on the Edit > Preferences > Advanced tab. Check "Show Develop menu in the menu bar.

##### chrome:
 - Close down your Chrome browser (make sure you close all instances if you have multiple windows open)
 - Go to Run and type the following command: chrome.exe --allow-file-access-from-file.
 - Hit enter.

## Headspin Integration
To run test on Headspin cloud devices or browsers, we just need to update the `env.properties` file under `resources/platform/{android/ios}` directory for respectively platform.
> We can specify all [headspin capabilities](https://ui-dev.headspin.io/docs/appium-capabilities) same as below. 
```properties
driver.name=iosDriver
remote.server=https://us-nyc.headspin.io:7002/v0/7efa88393c1746fdb35d18b9e3571e71/wd/hub
ios.capabilities.driverClass=io.appium.java_client.ios.IOSDriver
ios.capabilities.browserName=
ios.capabilities.platformName=iOS
ios.capabilities.deviceName=iPhone
ios.capabilities.bundleId=com.Iphone.MMT
ios.capabilities.platformVersion=13.5
ios.capabilities.udid=a9f2c856640d10454e2b19922bdaf50bc49aa061
ios.capabilities.automationName=XCUITest
ios.capabilities.useSimpleBuildTest=true
```
## Top Challenges
Below are the challenges we have faced while implementing the MMT problem and same we addressed in our implementation to handle such circumstances. 
* **Loading Search Result** - the performance of MMT mobile application is not good and taking more than expected time to load the search result and not giving better user experience. 
* **OTP** - since we are testing MMT application on production version, OTP was big challenge.
* **Calendar Control** - DOM of calendar component is not accessible in android, it was difficult to access the child elements of Calendar. 
* **Appium fails to get the DOM** - Appium fails to get the DOM of the current screen when there are many results for given search criteria's. e.g. Hotel Search List. Due to this, finding locators & dry executions was difficult.
* **Application Defects** - addition to the coding challenges, we have also observed some existing application defects on both Android & iOS platform and due to this implementation, finding locators & dry execution became very challenging. 
    * Price slider is not updating the price range value. 
    * Application is freeze when it has multiple records to load in the view.
    * Guidance overlay is coming 2nd time at any time even-though it was dismissed earlier.
    * Sometimes clicks for some element is not changing any UI as Application is in the idle state.
    * Even after applying filter, sometimes the filter tray is not shown at the top.
    * Sometimes even after login, "Login Successful" toast appears, but user is still at login page.   
