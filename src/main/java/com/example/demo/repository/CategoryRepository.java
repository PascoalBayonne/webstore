package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin1 on 9/1/2017.
 */
@Repository //a mechanism for encapsulating storage,retrieval, and search behavior which emulates a collection of objects
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
