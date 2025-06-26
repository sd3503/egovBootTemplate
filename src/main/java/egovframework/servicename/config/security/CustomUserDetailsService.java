package egovframework.servicename.config.security;

import egovframework.servicename.web.user.domain.Users;
import egovframework.servicename.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsernameWithRoles(username).orElseThrow(() ->
                new UsernameNotFoundException("User details not found for the user" + username));
        List<GrantedAuthority> authorities =users.getRoles().stream().map(authority ->
                new SimpleGrantedAuthority(authority.getRole().getCode())).collect(Collectors.toList());
        return new User(users.getEmail(), users.getPassword(),authorities);
    }
}
