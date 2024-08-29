package com.mdsl.assessment.security.services;

import com.mdsl.assessment.user.UserEntity;
import com.mdsl.assessment.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        try
        {
            UserEntity user = userRepository.findByUserName(userName).orElseThrow(()
                    -> new UsernameNotFoundException("User Not Found with username: " + userName));
            return UserDetailsImpl.build(user);
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("User Not Found with username: " + userName);
        }
    }

}