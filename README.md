
# ms-notification-core

This service is refactored from CN ms-cust.

## Getting Started

Checkout this application from bitbucket and import it as an existing maven project. 

Perform maven update to resolve all dependencies.

## Prerequisites

Below are the prerequisites:
 
- Java 8 or higher version required. To be specific (jdk1.8.0_161).


## Build All modules 
````
   mvn clean install
````

    
### [Optional] <i>If want only build module for specified country</i>
* Active the module profile and the country profile
    * Build API module for SG
    ````
       mvn clean install -P api,sg
    ````
    * Build Job module for SG
    ````
       mvn clean install -P job,sg
    ````
    * Build API module for HK
    ````
       mvn clean install -P api,hk
    ````
    * Build Job module for SG
    ````
       mvn clean install -P job,hk
    ````
## Run application
* Run API in local with maven plugin for HK
```
    mvn clean install spring-boot:run -P api,hk
```
* Run API in local with maven plugin for SG
```
    mvn clean install spring-boot:run -P api,sg
```

Please follow Maven versioning <Major.Minor.Version> standard.

## Import Notes
* For the generic implementation bean injection
    * <b>Have to declare with @Configuration files</b>
        * This is due to the spring injection framework, @Configuration have low priority to inject.
    * Add <b>@ConditionalOnMissingBean</b> annotation  
* For the country specified implementation bean injection
    * The country specified implementation class must define in related country module
    * Can use @Component, @Service to define the bean injection along with <b>@ConditionalOnProperty</b>
## Health Checking
* Actuator
    * Beans   
    ```
    http://127.0.0.1:8080/actuator/beans
    ```
    * Health
    ```
    http://127.0.0.1:8080/actuator/health
    ```
    ```
     http://127.0.0.1:8080/health/alive
     ```
    * Info
    ```
    http://127.0.0.1:8080/health/info
    ```

## Swagger-ui
* Swagger
  * development: http://127.0.0.1:8888/swagger-ui.html
  
## Happy Coding :)
