package com.ecommerce.sm_ecommerce.controller;

import com.ecommerce.sm_ecommerce.model.Category;
import com.ecommerce.sm_ecommerce.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * REST Controller for handling category-related HTTP requests.
 * 
 * <h2>About ResponseEntity</h2>
 * ResponseEntity is a generic class that represents the entire HTTP response including:
 * 1. Status Code: HTTP status code (200 OK, 201 CREATED, 404 NOT FOUND, etc.)
 * 2. Headers: Response headers (Content-Type, Location, etc.)
 * 3. Body: The actual response payload (JSON, XML, String, etc.)
 * 
 * <h3>Why use ResponseEntity?</h3>
 * - Full control over the HTTP response
 * - Can set custom status codes
 * - Can add custom headers
 * - Can include different types of response bodies
 * - Makes API behavior more explicit and self-documenting
 * 
 * <h3>Common Usage Patterns:</h3>
 * 1. Return just the status: ResponseEntity<Void>
 * 2. Return data with status: ResponseEntity<Category>
 * 3. Return data with custom headers and status: ResponseEntity.created(uri).body(entity)
 */
@RestController
@RequestMapping("/api")
public class CategoryController {

    // The @RestController annotation combines @Controller and @ResponseBody, indicating
    // that the return value of methods will be written directly to the HTTP response body.


    @Autowired
    private CategoryService categoryService;  // Injects the CategoryService to handle business logic

    /**
     * Retrieves all categories
     * @return ResponseEntity containing a list of all categories and HTTP 200 OK status
     * 
     * ResponseEntity allows us to:
     * 1. Set the HTTP status code (200 OK in this case)
     * 2. Include response headers if needed
     * 3. Attach a response body (the categories list)
     */
    //@GetMapping("/api/public/categories")
    @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Creates a new category
     * @param category The category object to be created (deserialized from request body)
     * @return ResponseEntity with success message and HTTP 201 CREATED status
     * 
     * @RequestBody annotation automatically deserializes the request body to a Category object.
     * HttpStatus.CREATED (201) is the appropriate status for successful resource creation.
     */
    
    //@PostMapping("/api/public/categories")
    @RequestMapping(value = "/public/categories",method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    /**
     * Deletes a category by its ID
     * @param categoryId The ID of the category to delete
     * @return ResponseEntity with status message and appropriate HTTP status
     * 
     * Demonstrates two ways to use ResponseEntity:
     * 1. Using constructor: new ResponseEntity<>(body, status)
     * 2. Using builder pattern: ResponseEntity.status(status).body(body)
     * 
     * @throws ResponseStatusException if category is not found or deletion fails
     */
    //@DeleteMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value = "/admin/categories/{categoryId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (ResponseStatusException e){
            // If an exception occurs, return the error message with the appropriate status code
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    //@PutMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value = "/admin/categories/{categoryId}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
        try {
            String status = categoryService.updateCategory(categoryId, category);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (ResponseStatusException e){
            // If an exception occurs, return the error message with the appropriate status code
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}