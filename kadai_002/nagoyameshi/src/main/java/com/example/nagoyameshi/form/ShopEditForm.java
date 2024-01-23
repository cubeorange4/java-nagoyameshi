package com.example.nagoyameshi.form;

import java.sql.Time;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShopEditForm {

    @NotNull
    private Integer id;

    @NotBlank(message = "民宿名を入力してください。")
    private String name;

    private MultipartFile imageName;

    @NotBlank(message = "説明を入力してください。")
    private String description;

    @NotNull(message = "カテゴリを選択してください。")
    private Integer categoryId;

    @NotNull(message = "開店時間を入力してください")
    private Time openingTime;

    @NotNull(message = "閉店時間を入力してください")
    private Time closingTime;

    @NotBlank(message = "曜日を選択してください")
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
    
    public ShopEditForm(Integer id, String name, MultipartFile imageName, String description, Integer categoryId,
            Time openingTime, Time closingTime, String holiday, Integer price, String postalCode, String address,
            String phoneNumber) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.description = description;
        this.categoryId = categoryId;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.holiday = holiday;
        this.price = price;
        this.postalCode = postalCode;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
