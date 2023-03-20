package katachi.todo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;
//    
//    private CorsConfigurationSource corsConfigurationSource() {
//    	CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
//        corsConfiguration.setAllowCredentials(true);
//        
//    	  UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
//          corsSource.registerCorsConfiguration("/login", corsConfiguration);
//
//          return corsSource;
//    }
//    // ログイン後は/homeに遷移させる
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
        http
    		.csrf().disable();
        http.headers(header -> {
            header.frameOptions().disable();
        });
    		//	.ignoringAntMatchers("/login")
//    		http
//    			.cors()
//    				.configurationSource( corsConfigurationSource());
        http.authorizeHttpRequests(authz -> authz
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .mvcMatchers("/").permitAll()
            .mvcMatchers("/general").hasRole("GENERAL")
            .mvcMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
        );
        http.formLogin(form -> form
    		.loginProcessingUrl("/login")
        	.loginPage("/login")
            .defaultSuccessUrl("/home")
            .failureUrl("/login?error")
            .permitAll()
        );
        
       
        
        http.logout(logout -> logout
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/login")
        
        );
        return http.build();
    }
////    @Bean
////    public UserDetailsManager userDetailsService(){
////        JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
////        // データベースにuser1/userというレコードが追加されます
////        UserDetails user = User.withUsername("user1")
////                .password(
////                PasswordEncoderFactories
////                        .createDelegatingPasswordEncoder()
////                        .encode("user"))
////                .roles("USER")
////               .build();
////        users.createUser(user);
////        return users;
////    }
//    // user1/userでログインする
    @Bean
  public UserDetailsManager userDetailsService(){
        /*  独自テーブルではない場合はこちら
         *  既存User : user1/user
         *  JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
         */
    	String USERQUERY = "select username,password,true from users where username = ?";
    	String AUTHQUERY = "select username,'GENERAL' from users where username = ?";;
    	JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
    	users.setUsersByUsernameQuery(USERQUERY);
    	users.setAuthoritiesByUsernameQuery(AUTHQUERY);
    	return users;
    }

}