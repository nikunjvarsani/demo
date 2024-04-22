package com.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.Administartor;

@Repository
public interface AdministartoRepo extends CrudRepository<Administartor, Long> {

}
