 package com.Excel.Controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Excel.Dto.ExcelDto;
import com.Excel.ExcelEntity.ExcelEntity;
import com.Excel.Services.Services;

@RestController
@CrossOrigin("*")
public class Controller {

	@Autowired
	private Services services;

	@PostMapping("saveData")
	public ResponseEntity<String> saveData(@RequestParam("Excel") MultipartFile file) {

		return new ResponseEntity<>(services.saveData(file), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("saveDatainDB")
	public ResponseEntity<String> saveData() {

		return new ResponseEntity<>(services.Data(), HttpStatus.ACCEPTED);
	}

	@GetMapping("getAllData/{pageNo}/{pageSize}")
	public ResponseEntity<List<ExcelDto>> getAllData(@PathVariable Integer pageNo,@PathVariable(required = false) Integer pageSize ) {

		return new ResponseEntity<>(services.getAllData(pageNo,pageSize), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("getAll")
	public ResponseEntity<List<ExcelDto>> getAll( ) {

		return new ResponseEntity<>(services.getAll(), HttpStatus.ACCEPTED);
	}


	@GetMapping("getExcel")
	public ResponseEntity<ExcelEntity> importExcelSheet(HttpServletResponse response) {
		response.setContentType("application/octet-stream");
		String keyString = "Content-Disposition";
		String valueString = "attachment;filename=Book.xls";
		response.setHeader(keyString, valueString);
		

		return new ResponseEntity<>(services.downloadExcelSheet(response), HttpStatus.ACCEPTED);
	}



	@GetMapping("/GetExcel_WithFiled")
	public ResponseEntity<Resource> Download_ExcelFormat() {
		Resource resource = services.Download_ExcelFormat();
//
//		HttpHeaders header = new HttpHeaders();
//		header.add(HttpHeaders.CONTENT_DISPOSITION, "attchment;filename=Book.xlsx");

//		return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=Bookkk.xls").contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	
	@GetMapping("getById/{num}")
	public ResponseEntity<ExcelEntity> getById(@PathVariable Double num)
	{
		return new ResponseEntity<>(services.getById(num),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("getByCity/{num}")
	@Transactional
	public ResponseEntity<Integer> getByCity(@PathVariable String num)
	{
		return new ResponseEntity<>(services.getByAddress(num),HttpStatus.ACCEPTED);
	}
	
	}

