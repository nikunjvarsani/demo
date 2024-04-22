package com.Excel.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.Excel.Dto.ExcelDto;
import com.Excel.ExcelEntity.ExcelEntity;
import com.Excel.ExcelRepository.ExcelRepo;
import com.Excel.Services.Services;
import com.Excel.Util.Helper;
import com.github.javafaker.Faker;

@Component
public class ServiceImpl implements Services {

	@Autowired
	private ExcelRepo repo;
	
	@Autowired
	private Faker faker;

	@Override
	public String saveData(MultipartFile file) {
		try {
			if (Helper.checkExcel(file)) {
				List<ExcelEntity> convertExcelToList = Helper.convertExcelToList(file.getInputStream());

				String absolutePath = new ClassPathResource("").getFile().getAbsolutePath();
				
				Files.copy(file.getInputStream(), Path.of(absolutePath+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				repo.saveAll(convertExcelToList);
				return "Save";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Enter Proper Excel File";
	}

	@Override
	public List<ExcelDto> getAllData(Integer pageNO ,Integer pageSize) {
		
		 PageRequest of = PageRequest.of(pageNO, pageSize);
		
		 Page<ExcelEntity> all = repo.findAll(of);
		 List<ExcelEntity> content = all.getContent();
		 System.out.println("page ===="+of);
		  
		 List<ExcelDto> entityList = Helper.convertEntityToDto(content);
			
			return  entityList;
		
		
		/*
		 * List<ExcelEntity> all = (List<ExcelEntity>) repo.findAll(); List<ExcelDto>
		 * convertEntityToDto = Helper.convertEntityToDto(all); return
		 * convertEntityToDto;
		 */
	}

	@Override
	public ExcelEntity downloadExcelSheet(HttpServletResponse response) {

		// final String
		// pathString="C:\\Users\\NikunjVarsani\\Documents\\workspace-spring-tool-suite-4-4.21.0.RELEASE\\ExcelProject\\src\\main\\resources\\static\\Excel";
		List<ExcelEntity> all = (List<ExcelEntity>) repo.findAll();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("Book");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("City");
		row.createCell(3).setCellValue("Price");

		int RowNumber = 1;
		for (ExcelEntity cell : all) {
			HSSFRow DataRow = sheet.createRow(RowNumber);
			DataRow.createCell(0).setCellValue(cell.getId());
			DataRow.createCell(1).setCellValue(cell.getName());
			DataRow.createCell(2).setCellValue(cell.getCity());
			DataRow.createCell(3).setCellValue(cell.getPrice());
			RowNumber++;
		}
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			hssfWorkbook.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}

	@Override
	public Resource Download_ExcelFormat() {
		Resource resource = new ClassPathResource("Excel/Book.xlsx");
		return resource;

	}

	@Override
	public String Data() {
		
		for (int i = 0; i <1000; i++) {
			
			ExcelEntity dataEntity=new ExcelEntity(faker.number().randomDouble(0, 1, 1010),faker.name().firstName(),faker.number().randomDouble(1, 1, 1010),faker.address().cityName());
            repo.save(dataEntity);
		}
		return null;
	}

	@Override
	public List<ExcelDto> getAll() {
		List<ExcelEntity> all = repo.findAll();
		List<ExcelDto> convertEntityToDto = Helper.convertEntityToDto(all);
		return convertEntityToDto;
	}

	@Override
	public ExcelEntity getById(Double num) {
		ExcelEntity data=repo.findById(num).get();
	
		return data;
	}

	@Override
	public Integer getByAddress(String num) {
		
		return repo.getByAddress(num);
	}

}
