package com.example.nagoyameshi.form;

import java.sql.Time;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopEditForm {
	public ShopEditForm(Integer id2, String name2, Object imageFile2, String description2, Integer id3,
			Time openingTime2, Time closingTime2, String holiday2, Integer price2, String postalCode2, String address2,
			String phoneNumber2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	@NotNull
	private Integer id;
	
	@NotBlank(message = "民宿名を入力してください。")
    private String name;
        
    private MultipartFile imageFile;
    
    @NotBlank(message = "説明を入力してください。")
    private String description;  
    
    @NotNull(message = "カテゴリを選択してください。")
    private Integer categoryId;
    
    @NotNull(message = "開店時間を入力してください")
    private String openingTime;
    
    @NotNull(message = "閉店時間を入力してください")
    private String closingTime;
    
    @NotNull(message = "曜日を選択してください")
    private String holiday;
    
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
