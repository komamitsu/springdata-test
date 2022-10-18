package org.komamitsu.springdatatest.domain.repository;

import org.komamitsu.springdatatest.domain.model.Group;
import org.komamitsu.springdatatest.domain.model.User;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
    @Transactional(readOnly = true)
    List<Group> findByName(String name);

    @Transactional
    default Iterable<Group> insertAll(JdbcAggregateTemplate jdbcAggregateTemplate) {
        List<Group> groups = Arrays.asList(
                new Group(1L, "group-1", null),
                new Group(2L, "group-2", null),
                new Group(3L, "group-3", null)
        );
        AtomicLong userId = new AtomicLong(1);
        return groups.stream().map(group -> {
            for (int i = 0; i < 5; i++) {
                String userName = String.format("user-%s-%d", group.name, i);
                User savedUser = new User(userId.getAndIncrement(), userName, 100 * i);
                group.addUser(savedUser);
            }
            return jdbcAggregateTemplate.insert(group);
        }).toList();
    }
}
