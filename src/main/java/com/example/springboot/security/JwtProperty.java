package com.example.springboot.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class JwtProperty {
	
	public static final String SECRET = "SomeSecretForJWTGeneration";
	public static final int EXPIRATION_TIME = 864_000_000; // ms = 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
	
}

