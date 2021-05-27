package br.com.zupacademy.maxley.mercadolivre.config.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidaToken {

    @Value("{forum.jwt.secret}")
    private String secret;

    public boolean validar(String token) {

        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch(Exception ex){
            return false;
        }

    }
}
