package com.example.nagoyameshi.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "日付を選択してください。")
    private String checkinDate;  
	
	@NotBlank(message = "時間を選択してください")
	private String checkinTime;
    
    @NotNull(message = "人数を入力してください。")
    @Min(value = 1, message = "宿泊人数は1人以上に設定してください。")
    private Integer numberOfPeople; 
}
