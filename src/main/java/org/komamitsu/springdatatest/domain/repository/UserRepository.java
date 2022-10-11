package org.komamitsu.springdatatest.domain.repository;

import org.komamitsu.springdatatest.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
