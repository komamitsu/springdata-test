package org.komamitsu.springdatatest;

import org.komamitsu.springdatatest.domain.model.User;
import org.komamitsu.springdatatest.domain.repository.GroupRepository;
import org.komamitsu.springdatatest.domain.model.Group;
import org.komamitsu.springdatatest.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
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
    @Autowired
    public UserRepository userRepo;


    @Bean
    public CommandLineRunner run() throws Exception {
        ClassPathResource resource = new ClassPathResource("schema.sql");
        template.execute(Files.readString(Paths.get(resource.getURI())));

        return (String[] args) -> {
            // Initialization
            userRepo.deleteAll();
            groupRepo.deleteAll();

            List<Group> groups = Arrays.asList(
                    Group.create("group-1"),
                    Group.create("group-2"),
                    Group.create("group-3")
            );
            for (Group group : groups) {
                Group savedGroup = groupRepo.save(group);
                for (int i = 0; i < 5; i++) {
                    String userName = String.format("user-%s-%d", group.getName(), i);
                    User savedUser = User.create(userName, 100 * i);
                    group.addUser(savedUser);
                }
                groupRepo.save(savedGroup);
            }
            groupRepo.findAll().forEach(g -> System.out.println(g));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}