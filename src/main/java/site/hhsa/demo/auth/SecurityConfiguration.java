package site.hhsa.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
//                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/orgs","/orgs/{org_name}","/vols/{username}","/register","/vols/{username}/register", "{username}/orgs/register") // anyone can see the home and the ads pages
                .permitAll()
                .antMatchers(HttpMethod.POST,"/register","/vols/{username}/register", "{username}/orgs/register") // anyone can see the home and the ads pages
                .permitAll()
                /* Pages that require athentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/orgs/{org_name}/dash", "/vols/{username}/dash","/orgs/{org_dash}/favorites","/orgs/{org_name}/follow" // only authenticated users can view

                )
                .authenticated()
        ;
    }


}
