package com.springexercise.mybudgetapp.bootstrap;

//import org.springframework.security.crypto.password.PasswordEncoder;

//@Slf4j
//@RequiredArgsConstructor
//@Component
public class UserDataLoader /*implements CommandLineRunner*/ {

    /*private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {

        Authority createCategory = authorityRepository.save(Authority.builder()
                .permission("category.create").build());
        Authority updateCategory = authorityRepository.save(Authority.builder()
                .permission("category.update").build());
        Authority getCategory = authorityRepository.save(Authority.builder()
                .permission("category.get").build());
        Authority deleteCategory = authorityRepository.save(Authority.builder()
                .permission("category.delete").build());

        Role adminRole = roleRepository.save(Role.builder().roleName("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().roleName("USER").build());

        Set<Authority> adminAuthorities = new HashSet<>();
        adminAuthorities.add(createCategory);
        adminAuthorities.add(updateCategory);
        adminAuthorities.add(getCategory);
        adminAuthorities.add(deleteCategory);
        adminRole.setAuthorities(adminAuthorities);
        Set<Authority> userAuthorities = new HashSet<>();
        userAuthorities.add(createCategory);
        userAuthorities.add(updateCategory);
        userAuthorities.add(getCategory);
        userAuthorities.add(deleteCategory);
        userRole.setAuthorities(userAuthorities);

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        userRepository.save(User.builder()
                .userName("admin")
                .password(passwordEncoder.encode("admin"))
                .role(adminRole)
                .build());

        userRepository.save(User.builder()
                .userName("user")
                .password(passwordEncoder.encode("user"))
                .role(userRole)
                .build());

        log.debug("Users loaded: " + userRepository.count());
    }


    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }*/
}
