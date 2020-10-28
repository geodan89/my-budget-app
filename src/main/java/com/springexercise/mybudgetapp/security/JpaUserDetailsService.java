package com.springexercise.mybudgetapp.security;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Slf4j
//@RequiredArgsConstructor
//@Service
public class JpaUserDetailsService /*implements UserDetailsService*/ {

    //private final UserRepository userRepository;

//    @Transactional
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        log.debug("Getting user information via Jpa");
//
//        User user = userRepository.findByUserName(username).orElseThrow(() -> {
//            return new UsernameNotFoundException("User name: " + username + " not found.");
//        });
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
//                user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(),
//                user.getAccountNonLocked(), convertToSpringSimpleGrantedAuthorities(user.getAuthorities()));
//    }
//
//    private Collection<? extends GrantedAuthority> convertToSpringSimpleGrantedAuthorities(Set<Authority> authorities) {
//        if (authorities != null && authorities.size() > 0) {
//            return authorities.stream()
//                    .map(Authority::getPermission)
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toSet());
//        } else {
//            return new HashSet<>();
//        }
//    }
}
