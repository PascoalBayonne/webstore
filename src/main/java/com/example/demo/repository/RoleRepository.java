package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin1 on 8/8/2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

   // @Query("select u.email,r.role from user u inner join user_role ur on(u.user_id=ur.user_id)inner  join r on (ur.role=r.role_id) where u.email=?")
    Role findByRole(String role);
}

