package com.example.nagoyameshi.form;

import java.sql.Date;
import java.sql.Time;

import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyameshi.entity.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShopRegisterForm {
	@NotBlank(message = "民宿名を入力してください。")
    private String name;
        
    private MultipartFile imageFile;
    
    @NotBlank(message = "説明を入力してください。")
    private String description;  
    
    @NotNull(message = "カテゴリを選択してください。")
    private Category categoryId;
    
    @NotNull(message = "開店時間を入力してください")
    private Time openingTime;
    
    @NotNull(message = "閉店時間を入力してください")
    private Time closingTime;
    
    @NotNull(message = "休業日を入力してください")
    private Date holiday;
    
    @NotNull(message = "料金を入力してください。")
    @Min(value = 1, message = "料金は1円以上に設定してください。")
    private Integer price;      
    
    @NotBlank(message = "郵便番号を入力してください。")
    private String postalCode;
    
    @NotBlank(message = "住所を入力してください。")
    private String address;
    
    @NotBlank(message = "電話番号を入力してください。")
    private String phoneNumber;
}
