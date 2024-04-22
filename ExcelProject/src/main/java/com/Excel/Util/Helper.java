package com.Excel.Util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.Excel.Dto.ExcelDto;
import com.Excel.ExcelEntity.ExcelEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

	public static boolean checkExcel(MultipartFile file) {
		if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		{
			return true;
		}
		return false;
	}
	
	public static List<ExcelEntity> convertExcelToList(InputStream is)
	{
		List<ExcelEntity> excellist=new ArrayList<>();
		
		try{
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			
			XSSFSheet dataSheet=xssfWorkbook.getSheet("data");
			
			int rowNumber=0;
			
			Iterator<Row>iterator=dataSheet.iterator();
			
			while (iterator.hasNext()) {
				
				Row row=iterator.next();
				
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
				}
				
				Iterator<Cell>cellsIterator=row.iterator();
				 int cid=0;
				 ExcelEntity entity=new ExcelEntity();
				while (cellsIterator.hasNext()) {
					Cell cell =  cellsIterator.next();
					
					switch (cid) {
					case 0:
						entity.setId(cell.getNumericCellValue());
						break;
						
					case 1:
						entity.setName(cell.getStringCellValue());
						break;
						
						
					case 2:
						entity.setPrice(cell.getNumericCellValue());
						break;
						
						
					case 3:
						entity.setCity(cell.getStringCellValue());
						break;

					default:
						break;
					}
					cid++;
					
				}
				
				excellist.add(entity);
				
			}
			
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return excellist;
	}
	
	
	public static List<ExcelDto> convertEntityToDto(List<ExcelEntity> data)
	{
		List<ExcelDto>list=new ArrayList<>();
		ObjectMapper mapeMapper =new ObjectMapper();
		
		
		  for (ExcelEntity entity : data) {
		  
				/*
				 * ExcelDto dto=ExcelDto.ExcelDto(entity.getId(), entity.getName(),
				 * entity.getPrice(), entity.getCity());
				 */
		  ExcelDto convertValue = mapeMapper.convertValue(entity, ExcelDto.class);
			list.add(convertValue);
		  
		 // list.add(dto);
		  }
		 
		return list;
	}


}
