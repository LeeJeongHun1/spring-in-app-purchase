package com.springinapppurchase.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    public DecodedJWT GetDecodedJWT(String token) {
        return JWT.decode(token);
    }


}
