package com.example.demo_restfulapi.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;

@EntityScan
public class Signature {
    private String cert;
    private String privateKey;

    private String alias;

    public Signature(Certificate cert, PrivateKey privateKey, String alias) throws CertificateEncodingException {
        this.cert = Base64.getEncoder().encodeToString(cert.getEncoded());
        this.privateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        this.alias = alias;
    }

    public String getCert() {
        return cert;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getAlias() {
        return alias;
    }

}
