package edu.poly.SpringShop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.SpringShop.domain.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByNameStartsWith(String name, Pageable pageable);
}
