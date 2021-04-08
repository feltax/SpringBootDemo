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

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ArticleRepository articleRepository;

    @Autowired
    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.articleRepository = articleRepository;
    }

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
        createArticle1IfNotFound();
        createArticle2IfNotFound();
        createArticle3IfNotFound();
        createArticle4IfNotFound();
        createArticle5IfNotFound();

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
    public Article createArticle1IfNotFound() {
        Article article = articleRepository.findByArticleTitle("Article 1");
        if (article == null) {
            LocalDate today = LocalDate.now();
            article = new Article("Article 1", "Oliver Butler", "Article one text words", today);
            article = articleRepository.save(article);
        }
        return article;
    }

    @Transactional
    public Article createArticle2IfNotFound() {
        Article article = articleRepository.findByArticleTitle("Mask importance");
        if (article == null) {
            LocalDate today = LocalDate.now();
            article = new Article("Mask importance", "Michael Myrs", "Article one text words", today.plusDays(1));
            article = articleRepository.save(article);
        }
        return article;
    }

    @Transactional
    public Article createArticle3IfNotFound() {
        Article article = articleRepository.findByArticleTitle("Cats and how to cook them");
        if (article == null) {
            LocalDate today = LocalDate.now();
            article = new Article("Cats and how to cook them", "Kitty Perry", "Article one text words", today);
            article = articleRepository.save(article);
        }
        return article;
    }

    @Transactional
    public Article createArticle4IfNotFound() {
        Article article = articleRepository.findByArticleTitle("Demolition and deconstruction");
        if (article == null) {
            LocalDate today = LocalDate.now();
            article = new Article("Demolition and deconstruction", "Miley Cyrus", "Article one text words", today.plusDays(2));
            article = articleRepository.save(article);
        }
        return article;
    }

    @Transactional
    public Article createArticle5IfNotFound() {
        Article article = articleRepository.findByArticleTitle("Mixed nuts - a psychological profiling study");
        if (article == null) {
            LocalDate today = LocalDate.now();
            article = new Article("Mixed nuts - a psychological profiling study", "Dr Snacks", "Article one text words", today.plusDays(5));
            article = articleRepository.save(article);
        }
        return article;
    }

}
