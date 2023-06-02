package edu.poly.SpringShop.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.SpringShop.domain.Category;
import edu.poly.SpringShop.exception.CategoryException;
import edu.poly.SpringShop.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {		
		return categoryRepository.save(category);
	}
	
	public Category update(Long id, Category category) {
		
		Optional<Category> existed = categoryRepository.findById(id);
		if(existed.isEmpty()) {
			throw new CategoryException("Category id " + id + " does't exist");
		}
		
		try {
			Category existedCategory = existed.get();
			existedCategory.setName(category.getName());
			existedCategory.setStatus(category.getStatus());
			
			return categoryRepository.save(existedCategory);
		} catch (Exception e) {
			throw new CategoryException("Category is updated failed ");
		}

	}

	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

		
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	
	public Category findById(Long id) {
		
		Optional<Category> found = categoryRepository.findById(id);
		if(found.isEmpty()) {
			throw new CategoryException("Category with id " + id + " doesn't exist");
		}
		
		return found.get();
	}

		
	public void deleteById(Long id) {		
		Category existed = findById(id);				
		categoryRepository.delete(existed);
		
	}

	
	
	
}
