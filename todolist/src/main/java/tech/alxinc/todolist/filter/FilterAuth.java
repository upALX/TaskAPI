package tech.alxinc.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterAuth extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authorization = request.getHeader("Authorization");



        System.out.println("Authorization:");
        System.out.println(authorization);

        var AuthEncoded = authorization.substring("Basic".length()).trim();

        byte[] ByteArrayAuthDecode = Base64.getDecoder().decode(AuthEncoded);

        var AuthDecoded = new String(ByteArrayAuthDecode);

        String[] credentials = AuthDecoded.split(":");

        String username = credentials[0];

        System.out.println("username:");
        System.out.println(username);

        String password = credentials[1];

        System.out.println("password:");
        System.out.println(password);

        filterChain.doFilter(request, response);
    }
    
}

