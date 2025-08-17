# Steps / Docs / Path

## Initial setup
- Added spring-web, and spring-security from start.spring.io for a web-app setup
- Started the application, and it generated a password in terminal which had to be used to send request, otherwise it gives 401 unauthorized error.
- Under `Authorization` in Postman, used `user` as username and application generated password in terminal as password.

## Adding mapping
- Need to add a request mapping to get the response
- Added a DemoController and called it from Postman using the authorization mentioned in "Initial setup" steps.

