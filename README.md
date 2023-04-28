# Global Logic backend test

![](https://img.shields.io/badge/build-success-brightgreen.svg)

![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/h2-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)
![](https://img.shields.io/badge/gradle-✓-blue.svg)
-------------------
## Description

This application has the following capabilities:
- Create an user and manage logging session.
- Generate a jwt that allows the user to login

-------------------
## Database

This application use a h2.
```
-http://localhost:8080/h2-console/
-User: sa
-Password: gltest
```

-------------------

## How to run locally

The app runs with java 8 open-jdk, gradle and h2 (console).


Clone the repository from Gitlab

```
git clone https://gitlab.com/pablocolasso/global-logic-challenge.git
```

Install gradle dependencies 

```
  ./gradlew build
```

To run the app please run the class GlobalLogicChallengeApplication


----------

## Swagger
To test the application use de following swagger link. First you have to complete the sing-up and you will get a jwt. Then try with the endpoint to login using the jwt e.g. "Baerer token".
```
  http://localhost:8080/swagger-ui/#/
```
