# Steps / Docs / Path

## Initial setup
- Added spring-web, and spring-security from start.spring.io for a web-app setup
- Started the application, and it generated a password in terminal which had to be used to send request, otherwise it gives 401 unauthorized error.
- Spring boot's convention over configuration provides us with a running application which has spring security enabled. This uses 'Basic Auth' for authorization.
- Under `Authorization` in Postman, used `user` as username and application generated password in terminal as password.

## Adding mapping
- Need to add a request mapping to get the response
- Added a DemoController and called it from Postman using the authorization mentioned in "Initial setup" steps.

## Adding a user
- We used a configuration class to add beans to spring context. To be precise, to override the beans that the spring security added to the context.
- When a request is made, it goes into the **security filter chain** -- BasicAuthenticationFilter in our case, as it's the default when nothing is provided.
- Filter asks AuthenticationManager to check username / password
- AuthenticationManager delegates to one or more AuthenticationProvider
- AuthenticationProvider uses the UserDetailsService to get the username, and PasswordEncoder to check the password.
- We have overridden the UserDetailsService and PasswordEncoder Bean and hence our implementation will be called rather than the default one.

## Better understanding of spring security flow
- Request -> Filter chain -> delegates to -> AuthenticationManager -> delegates to -> AuthenticationProvider -> UserDetailService & PasswordEncoder
- AuthenticationManager chooses one of the appropriate AuthenticationProvider and looks if the username exist or not. If it does, it will populate the UserDetails object, and validate the password using the provided PasswordEncoder
- After all the filter chains have been executed, the result will go to the dispatcher servlet--part of Spring MVC and then our controller will be executed--part of Spring web.

## Lesson 2: Managing User
We want to get our user details from database.