package com.timcoville.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timcoville.products.models.Category;
import com.timcoville.products.models.Product;
import com.timcoville.products.repositories.CategoryRepo;
import com.timcoville.products.repositories.ProductRepo;

@Service
public class ProductService {
	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;
	public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
	}
	
	public Product createProduct(Product record) {
		return productRepo.save(record);
	}
	
	public List<Product> allProducts(){
		return productRepo.findAll();
	}
	
	public Product findProduct(Long id) {
		Optional<Product> record = productRepo.findById(id);
		if (record.isPresent()) {
			return record.get();
		} else {
			return null;
		}
		
	}
	
	public Category createCategory(Category record) {
		return categoryRepo.save(record);
	}
	
	public List<Category> allCategories(){
		return categoryRepo.findAll();
	}
	
	public Category findCategory(Long id) {
		Optional<Category> record = categoryRepo.findById(id);
		if (record.isPresent()) {
			return record.get();
		} else {
			return null;
		}
	}
	
	public void NewCategory(String id, String category) {
		Long prodId = Long.parseLong(id);
		Long catId = Long.parseLong(category);
		Category cat = findCategory(catId);
		Product product = findProduct(prodId);
		List<Category> categories = product.getCategories();
		categories.add(cat);
		product.setCategories(categories);
		productRepo.save(product);
	}
	
	public void NewProduct(String id, String product) {
		Long catId = Long.parseLong(id);
		Long prodId = Long.parseLong(product);
		Product prod = findProduct(prodId);
		Category category = findCategory(catId);
		List<Product> products = category.getProducts();
		products.add(prod);
		category.setProducts(products);
		categoryRepo.save(category);
	}
	
}
