package edu.poly.SpringShop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.SpringShop.domain.Category;
import edu.poly.SpringShop.dto.CategoryDto;
import edu.poly.SpringShop.service.CategoryService;
import edu.poly.SpringShop.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	
	@Autowired
	CategoryService categoryService;
	
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	
	@GetMapping
	public ResponseEntity<?> getCategories(){		
		List<Category> categories = categoryService.findAll();		
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
		
	@PostMapping
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto dto,
											BindingResult result){
		
		/*
		 * @Valid: kiểm tra tính hợp lệ của dữ liệu
		 * BindingResult: ghi nhận thông tin trong quá trình kiểm tra
		 * 
		 * */
//		c1:
//		Dùng cách này để xuất toàn bộ lỗi -> nó sẽ dư thừa thông tin làm mình bị rối		
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
//		}	
		
		
//		c2:		
		//Tạo 1 service để bắt lỗi -> cách này sẽ log ra những lỗi cần thiết chứ không phải toàn bộ thông tin như cách trên
		ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);
		
		if(responseEntity != null) {
			return responseEntity;
		}
							
		Category category = new Category();
		
		//copy data từ CategoryDto sang CategoryEntity
		BeanUtils.copyProperties(dto, category);
		
		category = categoryService.save(category);
				
		//cập nhật lại id của CategoryDto và trả lại CategoryDto cho client
		dto.setId(category.getId());
		return new ResponseEntity<>(dto, HttpStatus.CREATED);	
	}
	
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateCategory(
			@PathVariable("id") Long id,
			@RequestBody CategoryDto dto){
		
		Category category = new Category();
		
		//copy data từ CategoryDto sang CategoryEntity
		BeanUtils.copyProperties(dto, category);
		
		category = categoryService.update(id, category);
				
		//cập nhật lại id của CategoryDto và trả lại CategoryDto cho client
		dto.setId(category.getId());
		return new ResponseEntity<>(dto, HttpStatus.CREATED);	
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> getCategories(
			@PageableDefault(size = 5, sort ="name", direction = Sort.Direction.ASC)
			Pageable pageable){	
		
		/*
		 * @PageableDefault : Khởi tạo các tham số để phân trang ban đầu nếu user không nhập giá trị vào
		 * size: tổng số category trên mỗi trang
		 * sort: tên của trường được sắp xếp
		 * page: xác định trang hiện tại
		 * 
		 * */
		
		Page<Category> categoryPage = categoryService.findAll(pageable);		
		return new ResponseEntity<>(categoryPage, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/get")
	public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id){	
		
		Category category = categoryService.findById(id);		
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
		
		categoryService.deleteById(id);
		
		return new ResponseEntity<>("Category with Id " +id+" was deleted", HttpStatus.OK);
	}
	
	
	
	
	
}
