package com.practice.StudyCenter.service.jwtService;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {

    final private int TOKEN_LIVE_TIME = 1000 * 60 * 60 * 24; // a day

    final private String SECRET_KEY = "LaudateomnesgenteslaudateMagnificatinseculaEtanimamealaudateMagnificatinseculaHappynationlivininahappynationAddthistothedependenciesblockinyourbuildLetmeknowifyouneedfurtherassistance";

    public String encode(String username, Collection<? extends GrantedAuthority> authorities) {
        authorities.removeIf(authority -> !authority.getAuthority().startsWith("ROLE_"));
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Created by: ", "https://abduraxim.uz");
        extraClaims.put("Role", authorities.toString().substring(6,authorities.toString().length()-1));

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_LIVE_TIME))
                .signWith(getSignInKey())
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractClaims(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
