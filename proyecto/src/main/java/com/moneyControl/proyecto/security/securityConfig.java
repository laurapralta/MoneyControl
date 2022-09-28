

package com.moneyControl.proyecto.security;


import com.moneyControl.proyecto.handler.customHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource dataSource;

    @Autowired
    customHandler custom;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select correo,password,estado from employee where correo=?")
                .authoritiesByUsernameQuery("select correo,rol from employee where correo=?");
    }
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/","VerEmpresas/**").hasRole("ADMIN")
               .antMatchers("/ViewEmployee/**").hasRole("ADMIN")
               .antMatchers("/Enterprise/**").hasRole("ADMIN")
               .antMatchers("/Employee/**").hasRole("ADMIN")
               .antMatchers("/ViewMovements/**").hasAnyRole("ADMIN","USER")
               .antMatchers("/addMovements/**").hasAnyRole("ADMIN","USER")
               .antMatchers("/EditMovements/**").hasAnyRole("ADMIN","USER")
               .antMatchers("/AddEnterprise/**").hasRole("ADMIN")
               .and().formLogin().successHandler(custom)
               .and().exceptionHandling().accessDeniedPage("/Denegado")
               .and().logout().permitAll();
    }
}
