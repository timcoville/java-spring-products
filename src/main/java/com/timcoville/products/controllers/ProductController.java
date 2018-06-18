package com.timcoville.products.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.timcoville.products.models.Category;
import com.timcoville.products.models.Product;
import com.timcoville.products.service.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "/newProduct.jsp";
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product")Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "/newProduct.jsp";
		} else {
			productService.createProduct(product);
			Long id = product.getId();
			return "redirect:/products/" + id;
		}
	}
	
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "/newCategory.jsp";
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "/newCategory.jsp";
		} else {
			productService.createCategory(category);
			Long id = category.getId();
			return "redirect:/categories/" + id;
		}
	}
	
	@RequestMapping("/products/{id}")
	public String viewProduct(@PathVariable("id")Long id, Model model) {
		Product product = productService.findProduct(id);
		List<Category> categories = productService.allCategories();
		for (Category cat : product.getCategories()) {
			if (categories.contains(cat)) {
				categories.remove(cat);
			}
		}
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
	
		return "/viewProduct.jsp";
	}
	
	@RequestMapping(value="/addCategory", method=RequestMethod.POST)
	public String addCategory(@RequestParam("productId") String id, @RequestParam("newCategory") String category) {
		productService.NewCategory(id, category);
		return "redirect:/products/" + id;
	}
	
	@RequestMapping("/categories/{id}")
	public String viewCategory(@PathVariable("id")Long id, Model model) {
		Category category = productService.findCategory(id);
		List<Product> products = productService.allProducts();
		for (Product prod : category.getProducts()) {
			if (products.contains(prod)) {
				products.remove(prod);
			}
		}
		model.addAttribute("category", category);
		model.addAttribute("products", products);
		return "/viewCategory.jsp";
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String addProduct(@RequestParam("categoryId") String id, @RequestParam("newProduct") String product) {
		productService.NewProduct(id, product);
		return "redirect:/categories/" + id;
	}
	
}
