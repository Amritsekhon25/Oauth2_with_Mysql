package com.example.oauth2.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

//**    This class is used to configure the Oauth2 configuration

    //*
    @Configuration
    @EnableAuthorizationServer
     class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    	
        @Autowired
        @Qualifier("authenticationManager")
        private AuthenticationManager authenticationManager;

        public DataSource dataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
                dataSourceBuilder.url("jdbc:mysql://localhost:3306/auth_db");
                dataSourceBuilder.username("root");
                dataSourceBuilder.password("root");
                return dataSourceBuilder.build();   
        }
        
        /**
        This method is used to configure clientID and client secret and grant type for Oauth server

        */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {      	
        	clients.jdbc(dataSource());
        }

       /**
        This method is used to configure check token endpoint permission.

        */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager)
            .tokenServices(tokenServices());   
                  }
        
        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource());

        }
        
        @Primary
        @Bean
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(tokenStore());
            tokenServices.setClientDetailsService(new JdbcClientDetailsService(dataSource()));
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            tokenServices.setTokenEnhancer(enhancerChain);

            return tokenServices;
        }
    }