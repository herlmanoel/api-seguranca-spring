package com.apiseguranca.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Este é um código de configuração de segurança em Java para um aplicativo Spring Boot.
 * Ele usa o Spring Security e o OAuth2 para criptografar senhas e gerenciar tokens de acesso JWT (JSON Web Tokens).
 */
@Configuration
public class AppConfigSecurity {

    /**
     * A chave de assinatura para tokens JWT, definida na propriedade jwt.secret do arquivo de configuração do aplicativo.
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Bean para converter tokens de acesso em tokens JWT e assiná-los com a chave definida na propriedade jwt.secret.
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtSecret);
        return tokenConverter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean para armazenar tokens JWT.
     * @return JwtTokenStore
     */
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
}