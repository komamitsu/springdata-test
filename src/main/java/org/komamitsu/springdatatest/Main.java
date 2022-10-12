package org.komamitsu.springdatatest;

import org.komamitsu.springdatatest.domain.model.User;
import org.komamitsu.springdatatest.domain.repository.GroupRepository;
import org.komamitsu.springdatatest.domain.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class Main {
    @Autowired
    public JdbcTemplate template;

    @Autowired
    public GroupRepository groupRepo;

    @Bean
    public CommandLineRunner run() throws Exception {
        ClassPathResource resource = new ClassPathResource("schema.sql");
        template.execute(Files.readString(Paths.get(resource.getURI())));

        return (String[] args) -> {
            // Initialization
            groupRepo.deleteAll();

            List<Group> groups = Arrays.asList(
                    Group.create("group-1"),
                    Group.create("group-2"),
                    Group.create("group-3")
            );
            for (Group group : groups) {
                for (int i = 0; i < 5; i++) {
                    String userName = String.format("user-%s-%d", group.name, i);
                    User savedUser = User.create(userName, 100 * i);
                    group.addUser(savedUser);
                }
            }
            groupRepo.saveAll(groups);
            System.out.println("Calling findByName(group-2)");
            groupRepo.findByName("group-2", Pageable.ofSize(2)).forEach(g -> System.out.println(g));
            System.out.println("Calling findAll(Pageable.ofSize(2))");
            groupRepo.findAll(Pageable.ofSize(2)).forEach(g -> System.out.println(g));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}