package com.moneyControl.proyecto.services;

import com.moneyControl.proyecto.models.Employee;
import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return employeeList;
    }


    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    /*public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }*/

    // metodo para guardar o actualizar objetos tipo empresa
    /*public Employee updateEnterprise(Employee empl) {
        Employee employee = employeeRepository.save(empl);
        return employee;
    }*/
    // metodo para guardar o actualizar objetos tipo empresa
    public boolean updateEmployee(Employee emple) {
        Employee employee = employeeRepository.save(emple);
        if(employeeRepository.findById(employee.getId())!=null){
            return true;
        }
        return false;

    }

    //Metodo para buscar empleados por empresa
    public ArrayList<Employee> getEmployeeByEnterprises(Integer id){
        return employeeRepository.findByEmpresa(id);
    }

    /*
    public void deleteEmployee(Integer id){
       employeeRepository.deleteById(id);
    }
*/
//Metodo para eliminar un registro de Empleado por Id
public boolean deleteEmployee(Integer id){
    employeeRepository.deleteById(id);
    if(this.employeeRepository.findById(id).isPresent()){
        return false;
    }
    return true;
}

}