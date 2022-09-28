package com.moneyControl.proyecto.controller;

import com.moneyControl.proyecto.models.Employee;
import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.services.EmployeeService;
import com.moneyControl.proyecto.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class employeeController {

    @Autowired
    EmployeeService employeeservice;
    @Autowired
    EnterpriseService enterpriseService;

    @GetMapping({"/ViewEmployee"})
    public String viewEmployee(Model model, @ModelAttribute("mensaje") String employee) {
        List<Employee> empleList = employeeservice.getAllEmployee();
        model.addAttribute("empleList", empleList);
        model.addAttribute("mensaje", employee);
        return "viewEmployees"; //Llamamos al HTML
    }

    /*
    @GetMapping("/Employees")
    public List<Employee> viewEmployee(){
     return employeeservice.getAllEmployee();
    } */
    /*@PostMapping("/Employees")
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return this.employeeservice.updateEnterprise(employee);
    }*/
    @GetMapping("/AddEmployee")
    public String addEmployee(Model model, @ModelAttribute("mensaje") String mensaje) {


        Employee emple = new Employee();
        model.addAttribute("emple", emple);
        model.addAttribute("mensaje", mensaje);

        try {
            List<Enterprise> listaEmpresas = enterpriseService.getALLEnterprise();
            model.addAttribute("emprelist", listaEmpresas);
            //return "addEmployee"; //Llamar HTMl
        } catch (Exception e) {
            System.out.println("error");
        }
        return "addEmployee"; //Llamar HTMl
    }

    @PostMapping("/SaveEmployee")
    public String saveEmployee(Employee emple, RedirectAttributes redirectAttributes) {
        String passEncriptada=passwordEncoder().encode(emple.getPassword());
        emple.setPassword(passEncriptada);
        if (employeeservice.updateEmployee(emple) == true) {
            return "redirect:/ViewEmployee";
        }

        return "redirect:/AddEmployee";
    }

    @GetMapping(path = "/Employees/{id}")
    public Employee employeeID(@PathVariable("id") Integer id) {
        return this.employeeservice.getEmployeeById(id);
    }

    @GetMapping("/EditEmployee/{id}")
    public String editEnterprise(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Employee emple = employeeservice.getEmployeeById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emple", emple);
        model.addAttribute("mensaje", mensaje);
        List<Enterprise> listaEmpresas = enterpriseService.getALLEnterprise();
        model.addAttribute("emprelist", listaEmpresas);
        return "editEmployee";
    }

    @PostMapping("/UpdateEmployee")
    public String updateEmpleado(@ModelAttribute("emple") Employee emple, RedirectAttributes redirectAttributes) {
        Integer id=emple.getId();
        String passOld= employeeservice.getEmployeeById(id).getPassword();
        if (!emple.getPassword().equals(passOld)){
            String passEncriptada= passwordEncoder().encode(emple.getPassword());
            emple.setPassword(passEncriptada);
        }
        if (employeeservice.updateEmployee(emple)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/ViewEmployee";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditEmployee/" + emple.getId();

    }
    /*@PatchMapping("/Employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee){
     Employee empl= employeeservice.getEmployeeById(id);
     empl.setNombre(employee.getNombre());
     empl.setCorreo(employee.getCorreo());
     empl.setEnterprise(employee.getEnterprise());
     empl.setRol(employee.getRol());
     empl.setProfile(employee.getProfile());
     empl.setCreateAt(employee.getCreateAt());
     empl.setUpdateAt(employee.getUpdateAt());
     return employeeservice.updateEnterprise(empl);

    }*/

    /*@DeleteMapping("/Employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id){
        employeeservice.deleteEmployee(id);
    }*/
    @GetMapping("/DeleteEmployee/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (employeeservice.deleteEmployee(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/ViewEmployee";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/ViewEmployee";
    }

    @GetMapping("/Enterprise/{id}/Employees")
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model) {
        List<Employee> listaEmpleados = employeeservice.getEmployeeByEnterprises(id);
        model.addAttribute("empleList", listaEmpleados);
        return "ViewEmployees"; //Llamamos al ht
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}