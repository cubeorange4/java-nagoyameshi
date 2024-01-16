package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.form.ShopRegisterForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.ShopRepository;

@Controller
@RequestMapping("/admin/shops")
public class AdminShopController {
	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;
	
	public AdminShopController(ShopRepository shopRepository, CategoryRepository categoryRepository) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		Page<Shop> shop = shopRepository.findAll(pageable);
		
		model.addAttribute("shop", shop);
		
		return "admin/shops/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Shop shop = shopRepository.getReferenceById(id);
		
		model.addAttribute("shop", shop);
		
		return "admin/shops/show";
	}
	
	@GetMapping("/register")
    public String register(Model model) {
		List<Category> category = categoryRepository.findAll();
		
        model.addAttribute("shopRegisterForm", new ShopRegisterForm());
        model.addAttribute("category", category);
        return "admin/shops/register";
    }
}
