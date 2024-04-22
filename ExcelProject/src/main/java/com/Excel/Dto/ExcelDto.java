package com.Excel.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "ExcelDto")
@NoArgsConstructor
public class ExcelDto {
	private Double id;
	private String name;
	private Double price;
	private String city;
}
