package com.Excel.Services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Excel.Dto.ExcelDto;
import com.Excel.ExcelEntity.ExcelEntity;



@Service
public interface Services {

	public String saveData(MultipartFile file);
	
	public List<ExcelDto> getAllData(Integer pageno,Integer pageSize);
	public List<ExcelDto> getAll();

	public ExcelEntity downloadExcelSheet(HttpServletResponse response);
	public String Data();
	public org.springframework.core.io.Resource Download_ExcelFormat();
	
	
	public ExcelEntity getById(Double num);
	public Integer getByAddress(String num);
}
