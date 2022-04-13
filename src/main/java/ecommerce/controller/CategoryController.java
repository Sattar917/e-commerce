package ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import ecommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecommerce.common.ApiResponse;
import ecommerce.model.Category;
import ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	

	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @Valid @RequestBody Category category) {
		// Check to see if the category exists.
		if (Helper.notNull(categoryService.readCategory(categoryID))) {
			// If the category exists then update it.
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		// If the category doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{categoryID}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryID") Integer categoryId){
		Optional<Category> optionalCategory = categoryService.readCategory(categoryId);
		if (!optionalCategory.isPresent()) {
    		return new ResponseEntity<>(new ApiResponse(false, "Category do not exist"), HttpStatus.CONFLICT);

		}
		categoryService.deleteCagegoryAndAllProducts(categoryId);
		return new ResponseEntity<>(new ApiResponse(true, "Category has been deleted"), HttpStatus.OK);
		
	}
}




