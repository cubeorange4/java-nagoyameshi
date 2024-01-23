package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.ShopRepository;

@Controller
@RequestMapping("/shops")
public class ShopController {
	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;
	
	public ShopController(ShopRepository shopRepository, CategoryRepository categoryRepository) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "category", required = false) Integer categoryId,
                        @RequestParam(name = "price", required = false) Integer price, 
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model)
	{
		Page<Shop> shopPage;
		List<Category> category = categoryRepository.findAll();
		
		if(categoryId != null) {
			shopPage = shopRepository.findByCategoryLike(categoryId, pageable);
		}else if (price != null) {
			shopPage = shopRepository.findByPriceLessThanEqual(price, pageable);
		}else {
			shopPage = shopRepository.findAll(pageable);
		}
		
		model.addAttribute("shopPage", shopPage);
		model.addAttribute("categoryId", categoryId);
        model.addAttribute("price", price);
        model.addAttribute("category", category);
		
		return "shops/index";
	}
}