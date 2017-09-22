package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Controller is used to mark classes as Spring MVC Controller.
 @RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations.
 @ResponseBody annotation can be put on a method and indicates that the return type should be written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name
 * Created by Pascoal on 9/1/2017.
 */
@RestController //It's a mix of @Controller + @ResponseBody
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    //create
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public ResponseEntity<Category> create(@RequestBody Category category){
      Category saveCategories =  categoryRepository.save(category);
        return new ResponseEntity<Category>(saveCategories,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find/all", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAll(){
       List<Category> allCategories = categoryRepository.findAll();
       if (allCategories ==null){
           return new ResponseEntity<List<Category>>(allCategories,HttpStatus.NO_CONTENT);
       }
        return new ResponseEntity< List<Category>>(allCategories,HttpStatus.OK);
    }

    @RequestMapping(value = "/find/one/{id}")
    public ResponseEntity<Category> findOne(@PathVariable Long id){
        Category oneCategory = categoryRepository.findOne(id);
        if (oneCategory == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(oneCategory,HttpStatus.OK);
    }

    //update an existing object through PUT method.
    /*DispatcherServlet will process TRACE and OPTIONS with the default HttpServlet behavior unless explicitly
 told to dispatch those request types as well:*/
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Category> update(@PathVariable Long id,@RequestBody Category category) throws Exception {
        Category categoryOne = categoryRepository.findOne(id);
        if (categoryOne == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BeanUtils.copyProperties(category,categoryOne,"id");
        categoryOne = categoryRepository.save(categoryOne);
        return new ResponseEntity<>(categoryOne,HttpStatus.OK);
    }

    //delete existing resource (object)
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Category> delete(@PathVariable Long id){
        Category categoryToDelete = categoryRepository.findOne(id);
        if (categoryToDelete == null){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        try {
            categoryRepository.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Category>(HttpStatus.OK);
    }
}
