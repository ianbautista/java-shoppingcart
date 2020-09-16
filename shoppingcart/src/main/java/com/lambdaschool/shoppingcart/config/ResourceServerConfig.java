package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    // this controls who has access to what
    public void configure(HttpSecurity http) throws Exception
    {
        // cross site request forgery (csrf) tokens
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();

        // who has access to what. we use antMatchers(list of endpoints/routes)
        // .antMatchers("/users/users").hasAnyRole("admin")
        http.authorizeRequests()
                .antMatchers("/", "/h2-console/**", "/login").permitAll()
                .antMatchers("/users/**").authenticated()
                .antMatchers("/products/**").authenticated()
                .antMatchers("/carts/user").authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
