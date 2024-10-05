package com.indusTalk.config;

import java.util.*;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {
    public static String JWT_HEADER="Authorization"; 
   protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)throws ServletException,IOException
   {
   String jwt = request.getHeader(JwtConstant.JWT_HEADER);

   if(jwt!=null)
   {
    try{
    String email=JwtProvider.getEmailFromJwtToken(jwt);
    List<GrantedAuthority> authorities= new ArrayList<>();
    Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    catch(Exception e){
        throw new BadCredentialsException("invalid token ...");
    }

   }
   
   try{
 filterChain.doFilter(request, response);
   }
   catch(Exception e)
   {

   }
   
   } 
}


// Detailed Explanation of JwtProvider and jwtValidator Classes
// These two classes together implement JWT (JSON Web Token) handling for securing APIs in a Spring Boot application. Let’s break down the code and explain each component's role in detail.

// 1. JwtProvider
// The JwtProvider class is responsible for creating and parsing JWT tokens.

// Components:
// SecretKey key:

// This is the secret key used to sign the JWT. It's generated using Keys.hmacShaKeyFor(), which produces a secure key using the constant JwtConstant.SECRET_KEY.
// The key is used in both signing and verifying the JWT to ensure it's legitimate.
// generateToken(Authentication auth):

// This method generates a JWT token based on the user's authentication information (auth).
// Components of the token:
// setIssuer("codeWithShiva"): Sets the issuer of the token (in this case, "codeWithShiva").
// setIssuedAt(new Date()): Specifies when the token was issued.
// setExpiration(new Date(new Date().getTime()+86400000)): Sets the expiration of the token to 24 hours from the time it was issued. The value 86400000 is the number of milliseconds in a day.
// claim("email", auth.getName()): Adds a custom claim (in this case, the user’s email) to the token. auth.getName() typically returns the username (in this case, email).
// signWith(key): Signs the JWT using the secret key to ensure its integrity.
// .compact(): Finalizes the token creation and returns it as a compact string (the actual JWT).
// Purpose: This method is typically used when a user successfully authenticates, and the application generates a JWT token to return to the client.

// getEmailFromJwtToken(String jwt):

// This method extracts the email from a given JWT token.
// Since the token is prefixed with "Bearer " in HTTP requests, it removes the first 7 characters (i.e., the "Bearer " part) to extract the actual JWT.
// Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt):
// Parses the JWT string, verifies the signature using the secret key, and extracts the claims (the payload part of the token).
// claims.get("email"):
// Retrieves the email claim from the JWT.
// Purpose: This method is used to validate the JWT and extract the email claim from it, which is later used to authenticate the user.

// 2. jwtValidator
// The jwtValidator class is a filter that validates incoming JWT tokens and sets the user’s authentication in the Spring Security context.

// Components:
// OncePerRequestFilter:

// This class extends OncePerRequestFilter, meaning that the doFilterInternal() method will be executed once per HTTP request.
// This filter checks every incoming request to see if it contains a valid JWT token.
// doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain):

// This method is the core of the filter that processes each HTTP request.
// Steps inside the doFilterInternal method:
// String jwt = request.getHeader(JwtConstant.JWT_HEADER):

// Retrieves the JWT from the HTTP request header. The JWT is expected to be passed in the "Authorization" header as "Bearer <token>".
// Token Validation:

// if(jwt != null):
// If the JWT is not null, it attempts to validate the token.
// JwtProvider.getEmailFromJwtToken(jwt):
// Uses JwtProvider to extract the email from the token.
// List<GrantedAuthority> authorities = new ArrayList<>();:
// A placeholder for user roles or authorities. In this basic implementation, it’s left as an empty list, but in a real-world application, the roles/authorities would be parsed from the JWT and added here.
// Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities):
// Creates an Authentication object using the extracted email. The null in the second argument is for the password, as we only need to validate the JWT here.
// SecurityContextHolder.getContext().setAuthentication(authentication):
// Sets the authentication object in the SecurityContext. This allows Spring Security to treat the user as authenticated for the rest of the request lifecycle.
// Exception Handling:

// catch(Exception e):
// If there is any issue while parsing or validating the JWT (such as the token being malformed or expired), it throws a BadCredentialsException.
// This will stop the request from proceeding and result in an authentication failure.
// filterChain.doFilter(request, response):

// Passes the request to the next filter in the chain, continuing the request processing.
// Additional Exception Handling:

// There is a generic catch(Exception e) block around filterChain.doFilter() that catches any exception thrown while filtering the request.
// Purpose:

// This filter validates the JWT in every incoming request. If the JWT is valid, it sets the user’s authentication in the SecurityContextHolder, allowing other parts of the application to recognize the authenticated user.
// Summary:
// JwtProvider handles the creation and parsing of JWTs.
// jwtValidator acts as a filter, checking if the incoming request contains a valid JWT. If the token is valid, it authenticates the user for that request.
// This implementation ensures that only requests with valid tokens are processed, providing secure access to protected resources.