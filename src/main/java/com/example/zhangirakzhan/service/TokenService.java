package com.example.zhangirakzhan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@Repository
public class TokenService {

    @Autowired
    private final JwtEncoder encoder;

    @Autowired
    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication auth) {
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(30L * 24L * 60L * 60L);
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(auth.getName())
                .claim("scope", scope)
                .claim("username", (auth.getName()))
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();    }
}
