package com.test.demo.security.jwt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
@Component
public class TokenProvider {

@Value("${wso.jwt-decrypt}")
private String jwtDecrypt;
 
    public boolean validateToken(String authToken) {
        try {
        	
        	 RSAPublicKey publicKey = null;
            /* InputStream file = ClassLoader
                     .getSystemResourceAsStream("wso2carbon.jks");*/
             InputStream file =  new FileInputStream(new File(jwtDecrypt));
             KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
             keystore.load(file, "wso2carbon".toCharArray());
      
             String alias = "wso2carbon";
             String jwtToken = authToken;
             // Get certificate of public key
             Certificate cert = keystore.getCertificate(alias);
             // Get public key
             publicKey = (RSAPublicKey) cert.getPublicKey();
             
            
                       
             
            //Jwts.parser().setSigningKey("").parseClaimsJws(authToken);
            //JwtHelper.decodeAndVerify(jwtToken, new RsaVerifier(publicKey));
            
            
             SignedJWT signedJWT = SignedJWT.parse(jwtToken);
             
             JWSVerifier verifier = new RSASSAVerifier(publicKey);
      
           if (signedJWT.verify(verifier)) {
                System.out.println("Signature is Valid");
                return true;
            } else {
                System.out.println("Signature is NOT Valid");
                return false;
            }
           
           
        } catch (Exception e) {
        	System.out.println(e);
        }			
        return false;
    }
    
    public Authentication getAuthentication(String token) {
       /* Claims claims = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();
*/
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(token.toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User("admin", "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
