package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>{
	public Shop findByCategory(Category category);
	
	public Page<Shop> findByCategoryId(Integer categoryId, Pageable pageable);
	public Page<Shop> findByPriceLessThanEqual(Integer price, Pageable pageable);
	public List<Shop> findTop10ByOrderByCreatedAtDesc();
}
