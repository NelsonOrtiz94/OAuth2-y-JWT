package com.lta.security.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//Vinculamos propiedades de configuracion con campos de la clase RsaKeysConfig
@ConfigurationProperties(prefix = "rsa")
public record RsaKeysConfig(String publicKey, String privateKey) {}