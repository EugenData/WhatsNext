package com.company.project.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.company.project.entity.User;
import com.company.project.repository.UserRepository;
import com.github.javafaker.Faker;

import lombok.NoArgsConstructor;

@Service
public class PopulateDatabase {

    private final UserRepository userRepository;

    public PopulateDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void populateAll() {
        addUsers();
    }

    private void addUsers() {
        var domains = new String[] {
                "gmail.com",
                "protonmail.com",
                "icloud.com"
        };

        var users = new User[15];

        for (int i = 0; i < 15; i++) {
            var n = Faker.instance().name();
            var userName = n.firstName();
            var password = Faker.instance().random().toString();
            var email = userName + "@" + domains[i % domains.length];
            var id = Faker.instance().random().nextLong();

            users[i] = new User(id, userName, password, email, null, null);
        }
        userRepository.saveAll(List.of(users));
    }
}
