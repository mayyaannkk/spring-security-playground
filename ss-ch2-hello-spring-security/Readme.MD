## Spring security ch-2: Hello Spring Security

1. Create at least one user who has a set of credentials (username and password) 
2. Add the user to be managed by our implementation of UserDetailsService
3. Define a bean of the type PasswordEncoder that our application can use to verify a given password with the one stored and managed by UserDetailsService

When building the instance, we have to provide the username, the password, and at least one authority. The authority is an action allowed for that user, and we can use any string for this.

When using the default UserDetailsService, a PasswordEncoder is also autoconfigured. Because we overrode UserDetailsService, we also have to declare a PasswordEncoder. Trying the example now, you’ll see an exception when you call the endpoint. When trying to do the authentication, Spring Security realizes it doesn’t know how to manage the password and fails.

> The NoOpPasswordEncoder instance treats passwords as plain text. It doesn’t encrypt or hash them. For matching, NoOpPasswordEncoder only compares the strings using the underlying equals(Object o) method of the String class. You shouldn’t use this type of PasswordEncoder in a production-ready app. NoOpPasswordEncoder is a good option for examples where you don’t want to focus on the hashing algorithm of the password. Therefore, the developers of the class marked it as @Deprecated, and your development environment will show its name with a strikethrough.

## WebSecurityConfigureAdapter
HTTP Basic authentication doesn’t fit into most application architectures. Sometimes we’d like to change it to match our application. Similarly, not all endpoints of an application need to be secured, and for those that do, we might need to choose different authorization rules. To make such changes, we start by extending the WebSecurityConfigurerAdapter class. Extending this class allows us to override the configure(HttpSecurity http) method
