package com.mdsl.assessment.security.jwt;

import com.mdsl.assessment.user.UserEntity;
import com.mdsl.assessment.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        try
        {
            String jwt = jwtUtils.parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt))
            {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserEntity userEntity =
                        userServiceImpl.findByUserName(username).orElseThrow(() -> new RuntimeException("Error: User not found."));

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userEntity, null,
                        null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e)
        {
            log.error("Cannot set user authentication:{}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }


}