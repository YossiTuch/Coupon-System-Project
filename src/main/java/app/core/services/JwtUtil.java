package app.core.services;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private String encodedSecretKey = "I+have+good+knowledge+skill+and+motivation+is+smaller+then+I+have+good+connections+two+times";
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), signatureAlgorithm);

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", userDetails.email);
        claims.put("userType", userDetails.userType);
        return createToken(claims, userDetails.id);
    }
    public String createToken(Map<String, Object> claims, long subject){
        Instant now = Instant.now();
        return Jwts.builder().setClaims(claims)
                .setSubject(String.valueOf(subject))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS))).signWith(decodedSecretKey)
                .signWith(decodedSecretKey)
                .compact();
    }
    public Claims extractAllClaims(String token) throws JwtException {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token).getBody();

    }
    public long extractId(String token) throws JwtException{
        return Long.parseLong(extractAllClaims(token).getSubject());
    }
    public String extractEmail(String token) throws JwtException{
        return extractAllClaims(token).get("userEmail").toString();
    }
    public String extractUserType(String token) throws JwtException{
        return extractAllClaims(token).get("userType").toString();
    }
    public Date extractExpirationDate(String token) throws JwtException{
        return extractAllClaims(token).getExpiration();
    }
    public boolean isTokenExpired(String token) throws JwtException{
        try{
            extractAllClaims(token);
            return false;
        }catch (ExpiredJwtException e){
            return true;
        }
    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String email = extractEmail(token);
        return (email.equals(userDetails.email) && !isTokenExpired(token));
    }
    public static class UserDetails{
        public long id;
        public String email;
        public ClientType.CType userType;

        public UserDetails(long id, String email, ClientType.CType userType) {
            this.id = id;
            this.email = email;
            this.userType = userType;
        }
    }
}
