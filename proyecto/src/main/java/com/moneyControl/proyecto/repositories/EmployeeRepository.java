package com.moneyControl.proyecto.repositories;

import com.moneyControl.proyecto.models.Employee;
import com.moneyControl.proyecto.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    @Query(value="SELECT * FROM employee where id_empresa= ?1", nativeQuery=true)
    public abstract ArrayList<Employee> findByEmpresa(Integer id);
}
