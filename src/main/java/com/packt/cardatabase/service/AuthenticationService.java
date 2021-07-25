package com.packt.cardatabase.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {

	static final long EXPIRATION_TIME = 86_400_000;
	static final String SIGNING_KEY = "SecretKey";
	static final String PREFIX = "Bearer";

	// Add token to Authorization header in response
	static public void addToken(HttpServletResponse resp, String username) {
		String jwtToken = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY).compact();

		resp.setHeader("Authorization", PREFIX + " " + jwtToken);
		resp.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	// Get token from Authorization header in request
	static public Authentication getAuthentication(HttpServletRequest req) {
		String token = req.getHeader("Authorization");

		if (token != null) {
			String user = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token.replace(PREFIX, "")).getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}

		return null;
	}
}
