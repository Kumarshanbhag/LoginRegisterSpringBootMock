package com.loginregister.repository;

import com.loginregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query ("select u from User u where u.emailId=?1 and u.password=?2")
    User findUserByEmailIdAndPassword(String userName, String password);
}
