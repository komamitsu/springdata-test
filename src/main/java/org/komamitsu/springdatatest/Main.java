package org.komamitsu.springdatatest;

import org.komamitsu.springdatatest.domain.model.Group;
import org.komamitsu.springdatatest.domain.model.User;
import org.komamitsu.springdatatest.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;


@SpringBootApplication
public class Main {
    @Autowired
    public JdbcTemplate template;
    @Autowired
    public JdbcAggregateTemplate aggregateTemplate;

    @Autowired
    public GroupRepository groupRepo;

    enum DbType {
        SCALARDB("scalardb", true),
        PG("pg", false);

        private final String label;
        private final boolean shouldSplitDDLs;

        DbType(String label, boolean shouldSplitDDLs) {
            this.label = label;
            this.shouldSplitDDLs = shouldSplitDDLs;
        }
    }

    @Bean
    public CommandLineRunner run() throws Exception {
//        DbType dbType = DbType.SCALARDB;
         DbType dbType = DbType.PG;
        ClassPathResource resource = new ClassPathResource(String.format("schema-%s.sql", dbType.label));
        String ddls = Files.readString(Paths.get(resource.getURI()));
        if (dbType.shouldSplitDDLs) {
            Arrays.stream(ddls.split("\n")).forEach(ddl -> template.execute(ddl));
        }
        else {
            template.execute(ddls);
        }

        return (String[] args) -> {
//            groupRepo.deleteAll();
            aggregateTemplate.deleteById(1, Group.class);
            aggregateTemplate.deleteById(2, Group.class);
            aggregateTemplate.deleteById(3, Group.class);

//            Iterable<Group> savedGroups = groupRepo.insertAll(aggregateTemplate);
            List<Group> savedGroups = new ArrayList<>();
            {
                List<Group> groups = Arrays.asList(
                        new Group(1L, "group-1", null),
                        new Group(2L, "group-2", null),
                        new Group(3L, "group-3", null)
                );
                AtomicLong userId = new AtomicLong(1);
                savedGroups = groups.stream().map(group -> {
                    for (int i = 0; i < 5; i++) {
                        String userName = String.format("user-%s-%d", group.name, i);
                        User savedUser = new User(userId.getAndIncrement(), userName, 100 * i);
                        group.addUser(savedUser);
                    }
                    return aggregateTemplate.insert(group);
                }).toList();
            }

//            System.out.println("count(): " + groupRepo.count());
            System.out.println("count(): " + aggregateTemplate.count(Group.class));

            System.out.println("findByName(group-2):");
//            Group secondGroup = groupRepo.findByName("group-2").get(0);
//            Group secondGroup = aggregateTemplate.findByName("group-2").get(0);
            // FIXME
            Group secondGroup = aggregateTemplate.findById(2L, Group.class);
            System.out.println(secondGroup);

//            System.out.println("findById(first id): " + groupRepo.findById(savedGroups.iterator().next().id));
            System.out.println("findById(first id): " + aggregateTemplate.findById(savedGroups.iterator().next().id, Group.class));

//            groupRepo.save(secondGroup.withName("updated-" + secondGroup.name));
            aggregateTemplate.save(secondGroup.withName("updated-" + secondGroup.name));

            System.out.println("findAll(Sort.by(Sort.Order.desc(name))):");
//            groupRepo.findAll(Sort.by(Sort.Order.desc("name"))).forEach(System.out::println);
            aggregateTemplate.findAll(Group.class, Sort.by(Sort.Order.desc("name"))).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}