package com.moneyControl.proyecto.repositories;

import com.moneyControl.proyecto.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise,Integer> {

}
