package com.cybersoft.uniclub.filter;

import com.cybersoft.uniclub.payload.respond.RoleRespond;
import com.cybersoft.uniclub.utils.JWTUtils;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomJwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerAuthen = request.getHeader("Authorization");
        if(headerAuthen != null && headerAuthen.trim().length() > 0){
            String token = headerAuthen.substring(7);
            System.out.println("Kiem tra token: " + token);
            //Giải mã token
            String data = jwtUtils.decryptToken(token);
            System.out.println("Kiem tra data: " + data);
            if (data != null){


                RoleRespond role =  gson.fromJson(data, RoleRespond.class);
                System.out.println("Kiểm tra jwt data " + data + "-" + role.getName());

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
                authorityList.add(simpleGrantedAuthority);

                //Tạo chứng thực cho Security biết là đã hợp lệ bypass được tất cả các filter của security
                UsernamePasswordAuthenticationToken authen = new
                        UsernamePasswordAuthenticationToken("","",authorityList);


                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authen);
            }
            System.out.println("Kiểm tra jwt " + data);
        }


        filterChain.doFilter(request,response);//cho phép đi vào link đang gọi
    }
}
