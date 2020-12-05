package com.example.user;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Ehsan Sh
 */


public interface UserRepository extends CrudRepository<User,Long> {
}
