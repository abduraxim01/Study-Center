package com.practice.StudyCenter.service.jwtService;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    final private int tokenLiveTime = 1000 * 60 * 60 * 24; // 1-day

    final private String secretKey = "LaudateomnesgenteslaudateMagnificatinseculaEtanimamealaudateMagnificatinseculaHappynationlivininahappynationAddthistothedependenciesblockinyourbuildLetmeknowifyouneedfurtherassistance";

    public  String encode(String username) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Created by: ", "www.abduraxim.uz");
        extraClaims.put("Username", username);

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSignInKey())
                .compact();
    }

    private  SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
