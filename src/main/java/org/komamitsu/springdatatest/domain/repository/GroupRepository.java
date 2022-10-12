package org.komamitsu.springdatatest.domain.repository;

import org.komamitsu.springdatatest.domain.model.Group;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
    List<Group> findByName(String name);
}
