package org.komamitsu.springdatatest.domain.repository;

import org.komamitsu.springdatatest.domain.model.Group;
import org.komamitsu.springdatatest.domain.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
    @Transactional(readOnly = true)
    List<Group> findByName(String name);

    @Transactional
    default Iterable<Group> insertAllToPg() {
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
        return saveAll(groups);
    }
    @Transactional
    default Iterable<Group> insertAllToScalarDb() {
        List<Group> groups = Arrays.asList(
                new Group(0, "group-1"),
                new Group(1, "group-2"),
                new Group(2, "group-3")
        );
        for (Group group : groups) {
            for (int i = 0; i < 5; i++) {
                String userName = String.format("user-%s-%d", group.name, i);
                User savedUser = new User((long) i, userName, 100 * i);
                group.addUser(savedUser);
            }
        }
        return saveAll(groups);
    }
}
