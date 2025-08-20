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
We want to get our user details from database. To do so, in the database side, we have done the following:
- Created a schema with name "ss_lesson2"
- Created table "users" with "id" as primary key, "username", and "password" stored as pain text, for now

To make this work, we need 
- SecurityProvider to use our own custom implementation of `UserDetailsService`. For this, we have used `JpaUserDetailsService` which implements the `UserDetailsService` and its method `loadUserByUsername()` is provided body.
- Now this `loadUserByUsername()` gives us `username`. And we want to fetch the details from database using this `username`.
- To make the necessary configuration, we created an Entity class-- `User`
- To query from database we have created a repository interface which implements `JpaRepository<User, Integer>` and we provide our method in it.

> Notice that we don't require a @Repository annotation above our repository as we don't want to it to create a bean, as it cannot do so, since it is an interface and interface can't have object.

- By now, our database connection configuration is made, and we want our custom implementation of UserDetailsService to use this connection to fetch user details from database.
- In the `loadUserByUsername()` method, we call the `UserRepository` method, passing in the username. It returns an `Optional<User>` as the user may not belong in the database.
- By now, you will notice that `loadUserByUsername()` returns a UserDetails class, which contains all the necessary methods that spring security needs for a user.
- But we only have User class.
- We use the decorator patter, and create a new class `SecurityUser` of type `userDetails` which uses our `User` class to add some more capabilities to it, keeping both the classes separate but still working together as a wrapper.
- We then return this from our `loadUserByUsername()` method.

> We can remove the Bean of UserDetailsService from WebSecurityConfig and directly put @Service annotation over JpaUserDetailsService. This way, we don't need to pass in UserRepository as an argument to its constructor explicitly.

