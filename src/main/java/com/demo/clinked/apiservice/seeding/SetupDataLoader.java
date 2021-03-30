package com.demo.clinked.apiservice.seeding;

import java.time.LocalDate;
import java.util.Set;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.data.Role;
import com.demo.clinked.apiservice.data.UserEntity;
import com.demo.clinked.apiservice.repository.ArticleRepository;
import com.demo.clinked.apiservice.repository.RoleRepository;
import com.demo.clinked.apiservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // Create user roles
        var userRole = createRoleIfNotFound(Role.ROLE_USER);
        var adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);

        // Create users
        createUserIfNotFound("user", userRole);
        createUserIfNotFound("admin", adminRole);
        createArticle1IfNotFound("Article 1");
        alreadySetup = true;
    }

    @Transactional
    public Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    public UserEntity createUserIfNotFound(final String name, final Role role) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity user = userRepository.findByUsername(name);
        if (user == null) {
            user = new UserEntity(name, passwordEncoder.encode("password"));
            user.setRoles(Set.of(role));
            user = userRepository.save(user);
        }
        return user;
    }

    @Transactional
    public Article createArticle1IfNotFound(String title) {
        Article article = articleRepository.findByTitle(title);
        if (article == null) {
            LocalDate today = LocalDate.now();
            article = new Article("Article 1", "Oliver Butler", "Article one text words", today);
            article = articleRepository.save(article);
        }
        return article;
    }

}
