package net.schoolvery.schoolveryserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import net.schoolvery.schoolveryserver.domain.user.repository.UserRepository;
import net.schoolvery.schoolveryserver.global.error.exception.User.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> createUser(email, user))
                .orElseThrow(() -> new UserNotFoundException(email + "-> 해당 유저를 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String email, User user) {
        if (!Objects.equals(email, user.getEmail())) {
            throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
