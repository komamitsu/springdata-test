package org.komamitsu.springdatatest;

import org.komamitsu.springdatatest.domain.repository.GroupRepository;
import org.komamitsu.springdatatest.domain.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootApplication
public class Main {
    @Autowired
    public JdbcTemplate template;

    @Autowired
    public GroupRepository groupRepo;

    enum DbType {
        SCALARDB("scalardb"), PG("pg");

        private String label;

        DbType(String label) {
            this.label = label;
        }
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        // DbType dbType = DbType.SCALARDB;
        DbType dbType = DbType.PG;
        ClassPathResource resource = new ClassPathResource(String.format("schema-%s.sql", dbType.label));
        template.execute(Files.readString(Paths.get(resource.getURI())));

        return (String[] args) -> {
            groupRepo.deleteAll();

            Iterable<Group> savedGroups = groupRepo.insertAll();

            System.out.println("count(): " + groupRepo.count());

            System.out.println("findByName(group-2):");
            Group secondGroup = groupRepo.findByName("group-2").get(0);
            System.out.println(secondGroup);

            System.out.println("findById(first id): " + groupRepo.findById(savedGroups.iterator().next().id));

            groupRepo.save(secondGroup.withName("updated-" + secondGroup.name));

            System.out.println("findAll(Sort.by(Sort.Order.desc(name))):");
            groupRepo.findAll(Sort.by(Sort.Order.desc("name"))).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}