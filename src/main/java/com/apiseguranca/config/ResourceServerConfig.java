package com.apiseguranca.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };

    private static final String[] OPERATOR_OR_ADMIN = { "/products/**", "/categories/**" };

    private static final String[] ADMIN = { "/users/**" };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // Configura o armazenamento de tokens do servidor de recursos
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Desativa as opções de quadro para o banco de dados H2 no perfil de teste
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        // Configura as permissões de acesso aos recursos
        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll() // Permite acesso público aos recursos listados em PUBLIC
                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() // Permite acesso GET aos recursos listados em OPERATOR_OR_ADMIN
                .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN") // Permite acesso aos recursos listados em OPERATOR_OR_ADMIN para usuários com função OPERATOR ou ADMIN
                .antMatchers(ADMIN).hasRole("ADMIN") // Permite acesso aos recursos listados em ADMIN para usuários com função ADMIN
                .anyRequest().authenticated(); // Exige autenticação para qualquer outro recurso
    }
}