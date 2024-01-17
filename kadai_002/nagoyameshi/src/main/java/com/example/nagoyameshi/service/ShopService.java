package com.example.nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.form.ShopRegisterForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.ShopRepository;

@Service
public class ShopService {
	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;
	
	public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional
	public void create(ShopRegisterForm shopRegisterForm) {
		Shop shop = new Shop();
		MultipartFile imageFile = shopRegisterForm.getImageFile();
		
        Integer categoryId = Integer.valueOf(shopRegisterForm.getCategoryId());
        Category category = categoryRepository.getReferenceById(categoryId);
		Time openingTime = Time.valueOf(shopRegisterForm.getOpeningTime());
		Time closingTime = Time.valueOf(shopRegisterForm.getClosingTime());
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            shop.setImageName(hashedImageName);
       }
        
        shop.setName(shopRegisterForm.getName());
        shop.setDescription(shopRegisterForm.getDescription());
        shop.setCategory(category);
        shop.setOpeningTime(openingTime);
        shop.setClosingTime(closingTime);
        shop.setHoliday(shopRegisterForm.getHoliday());
        shop.setPrice(shopRegisterForm.getPrice());
        shop.setPostalCode(shopRegisterForm.getPostalCode());
        shop.setAddress(shopRegisterForm.getAddress());
        shop.setPhoneNumber(shopRegisterForm.getPhoneNumber());
        
        shopRepository.save(shop);
        
        
	}
        public String generateNewFileName(String fileName) {
            String[] fileNames = fileName.split("\\.");                
            for (int i = 0; i < fileNames.length - 1; i++) {
                fileNames[i] = UUID.randomUUID().toString();            
            }
            String hashedFileName = String.join(".", fileNames);
            return hashedFileName;
        }
        
        public void copyImageFile(MultipartFile imageFile, Path filePath) {           
            try {
                Files.copy(imageFile.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
	}
}