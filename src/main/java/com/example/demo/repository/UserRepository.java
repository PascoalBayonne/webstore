package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin1 on 8/7/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
    User findByUserName(String userName);

   // List<User> findByuserNameNameContainingIgnoreCase(String userName);

//    User findByUserName(String userName);
//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName =:userName")
//    boolean userExistsByUserName(@Param("userName") String userName);
//
//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email =:email")
//    boolean userExistsByEmail(@Param("email") String email);

//    @Query("select u from User u where u.uuid =:uuid")
//    User findByUUID(@Param("uuid") String uuid);



}
