package com.Excel.ExcelEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQuery(name = "ExcelEntity",procedureName = "getByCityCount",parameters = {
		@StoredProcedureParameter (mode = ParameterMode.IN,name = "city",type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT,name = "city",type = String.class)
})
public class ExcelEntity {
	@Id
	private Double id;
	private String name;
	private Double price;
	private String city;

}
