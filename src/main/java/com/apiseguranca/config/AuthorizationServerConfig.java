package com.apiseguranca.config;

import java.util.Arrays;

import com.apiseguranca.components.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${jwt.duration}")
    private Integer jwtDuration;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // Configura as permissões de acesso às chaves do token e à verificação do token
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // Configura os detalhes do cliente OAuth2 em memória
        clients.inMemory()
                .withClient(clientId) // Define o ID do cliente
                .secret(passwordEncoder.encode(clientSecret)) // Define o segredo do cliente criptografado
                .scopes("read", "write") // Define os escopos permitidos
                .authorizedGrantTypes("password") // Define os tipos de concessão autorizados
                .accessTokenValiditySeconds(jwtDuration); // Define a validade do token de acesso em segundos
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // Cria uma cadeia de melhoradores de token com o conversor de token de acesso e o melhorador de token personalizado
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Arrays.asList(accessTokenConverter, tokenEnhancer));

        // Configura os pontos finais do servidor de autorização
        endpoints.authenticationManager(authenticationManager) // Define o gerenciador de autenticação
                .tokenStore(tokenStore) // Define o armazenamento de tokens
                .accessTokenConverter(accessTokenConverter) // Define o conversor de token de acesso
                .tokenEnhancer(chain); // Define a cadeia de melhoradores de token
    }
}