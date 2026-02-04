package thesis.Graduation.thesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_PATHS = {
            "/", "/login", "/favicon.ico",
            "/css/**", "/js/**", "/images/**", "/webjars/**",
            "/cars/**", "/car/**", "/search/**", "/salon/**", "/models"
    };

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(this::configureAuthorization)
                .formLogin(this::configureFormLogin)
                .logout(this::configureLogout)
                .userDetailsService(userDetailsService)
                .build();
    }

    private void configureAuthorization(
            org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                .requestMatchers(PUBLIC_PATHS).permitAll()
                .requestMatchers("/director/**").hasRole("DIRECTOR")
                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "DIRECTOR")
                .requestMatchers("/employee/**").hasAnyRole("SELLER", "MANAGER", "DIRECTOR")
                .anyRequest().authenticated();
    }

    private void configureFormLogin(
            org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer<HttpSecurity> form) {
        form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll();
    }

    private void configureLogout(
            org.springframework.security.config.annotation.web.configurers.LogoutConfigurer<HttpSecurity> logout) {
        logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

