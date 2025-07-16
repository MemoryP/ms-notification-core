package com.ocbc.ms.notification.core.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.constant.Constants;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;

import static com.ocbc.ms.notification.core.constant.Constants.NOTIFICATION_INBOX_KEY_USER_ID;
import static com.ocbc.ms.notification.core.enums.ExceptionEnum.*;

/**
 * @author Allen Huang
 * @date 2020/5/8
 */
@Slf4j
@Service
public class JwtEncryptionService {

    @Getter
    private PrivateKey authPrivateKey;

    @Getter
    private PublicKey publicKey;

    @Value("${com.ocbc.ms.jws.key-store}")
    private String jwtSigningKeyStore;

    @Value("${com.ocbc.ms.jws.private-key.alias}")
    private String alias;

    @Value("${com.ocbc.ms.jws.store-password}")
    private String privateKeyPassword;

//    @PostConstruct
//    public synchronized void preConstructPrivateKey() {
//        RSAKey rsaKey = loadRSAKey(jwtSigningKeyStore, alias, privateKeyPassword);
//        try {
//            authPrivateKey = rsaKey.toPrivateKey();
//            publicKey = rsaKey.toPublicKey();
//        } catch (JOSEException e) {
//            log.error("Exception in rsaKey toPrivateKey & toPublicKey:{}", e.getMessage());
//        }
//    }

    /**
     * Get lanId from JWT.
     *
     * @param headers {@link HttpHeaders}
     * @return {@link String}
     */
    public String getLanIdFromJWT(HttpHeaders headers) {
        JWTClaimsSet jwtClaimsSet = parseJwtFromHeader(headers);
        return jwtClaimsSet.getSubject();
    }

    public synchronized RSAKey loadRSAKey(String filepath, String alias, String password) {
        RSAKey key;
        File file = new File(filepath);
        try (FileInputStream is = new FileInputStream(file)) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(is, password.toCharArray());
            key = RSAKey.load(keyStore, alias, password.toCharArray());
            //SHA-1 thumbprint is deprecated, so should manual digest it.
            MessageDigest sha1 = MessageDigest.getInstance("SHA-256");
            Certificate cert = keyStore.getCertificate(alias);
            if (key != null) {
                key = new RSAKey.Builder(key).x509CertSHA256Thumbprint(Base64URL.encode(sha1.digest(cert.getEncoded())))
                    .build();
            } else {
                throw new NullPointerException("RSA key is null,cert is null or not a valid X509 Cert.");
            }
        } catch (Exception e) {
            log.error("Exception in loadRSAKey:{}", e.getMessage());
            throw new BizException(RSA_KEY_ERROR);
        }
        return key;
    }


    public JWTClaimsSet parseJwtFromHeader(HttpHeaders headers) {
        String jwt = headers.getFirst(Constants.X_ACC_JWT);
        if (StringUtils.isBlank(jwt)) {
            log.error("JWT can not be blank!");
            throw new BizException(JWT_IS_BLANK);
        }
        return parseJWT(jwt, this.publicKey);
    }

    /**
     * Parse JWT and return user id;
     *
     * @param jwt {@link String}
     * @return {@link JWTClaimsSet}
     */
    public JWTClaimsSet parseJWT(String jwt, PublicKey publicKey) {
        JWTClaimsSet jwtClaimsSet;
        SignedJWT signedJwt;
        try {
            signedJwt = SignedJWT.parse(jwt);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
            signedJwt.verify(verifier);
            jwtClaimsSet = signedJwt.getJWTClaimsSet();
        } catch (Exception e) {
            log.error("Exception in parse JWT:{}", e.getMessage());
            throw new BizException(PARSE_JWT_FAILED);
        }
        Date currentDate = new Date();
        if (currentDate.after(jwtClaimsSet.getExpirationTime())) {
            throw new BizException(JWT_EXPIRED);
        }
        return jwtClaimsSet;
    }

    public String resolveUserIdFromJWT(HttpHeaders headers){
        JWTClaimsSet jwtClaimsSet = parseJwtFromHeader(headers);
        Object customObj = jwtClaimsSet.getClaims().get(Constants.CUSTOM);
        if (!(customObj instanceof Map)) {
            throw new BizException(WRONG_CUSTOM_FORMAT_IN_JWT);
        }
        Map<String, String> customMap = (Map<String, String>) customObj;
        String userId;
        userId = customMap.get(NOTIFICATION_INBOX_KEY_USER_ID);
        if (userId == null) {
            throw new BizException(NO_USER_ID_IN_CUSTOM);
        }
        return userId;
    }

}
