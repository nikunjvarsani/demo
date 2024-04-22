package com.Excel.ExcelRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.Excel.ExcelEntity.ExcelEntity;

public interface ExcelRepo extends JpaRepository<ExcelEntity, Double> {

   @Procedure(value = "getById")
   ExcelEntity getById(Double id);
   
   @Procedure(value = "getByCityCount")
   Integer getByAddress(String address);

}
