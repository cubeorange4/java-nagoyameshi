package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.repository.ShopRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.FavoriteService;
import com.example.nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/shops")
public class ShopController {
	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;
	private final FavoriteRepository favoriteRepository;
	private final FavoriteService favoriteService;
	private final ReviewRepository reviewRepository;   
    private final ReviewService reviewService;
	
	public ShopController(ShopRepository shopRepository, CategoryRepository categoryRepository, FavoriteRepository favoriteRepository, FavoriteService favoriteService, ReviewRepository reviewRepository, ReviewService reviewService) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
		this.favoriteRepository = favoriteRepository;
		this.favoriteService = favoriteService;
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
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
			shopPage = shopRepository.findByCategoryId(categoryId, pageable);
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
	
	@GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Shop shop = shopRepository.getReferenceById(id);
        Favorite favorite = null;
        boolean isFavorite = false;
        boolean hasUserAlreadyReviewed = false;
        
        if(userDetailsImpl != null) {
        	User user = userDetailsImpl.getUser();
        	isFavorite = favoriteService.isFavorite(shop, user);
        	hasUserAlreadyReviewed = reviewService.hasUserAlreadyReviewed(shop, user);
        	if(isFavorite) {
        		favorite = favoriteRepository.findByShopAndUser(shop, user);
        	}
        }
        
        List<Review> newReviews = reviewRepository.findTop6ByShopOrderByCreatedAtDesc(shop);        
        long totalReviewCount = reviewRepository.countByShop(shop);
        
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        model.addAttribute("favorite", favorite);
        model.addAttribute("shop", shop);        
        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("hasUserAlreadyReviewed", hasUserAlreadyReviewed);
        model.addAttribute("newReviews", newReviews);        
        model.addAttribute("totalReviewCount", totalReviewCount); 
        
        return "shops/show";
    }
}
