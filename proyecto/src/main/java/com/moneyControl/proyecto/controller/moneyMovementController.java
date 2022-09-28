package com.moneyControl.proyecto.controller;

import com.moneyControl.proyecto.models.Employee;
import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.models.Money_movement;
import com.moneyControl.proyecto.services.EmployeeService;
import com.moneyControl.proyecto.services.EnterpriseService;
import com.moneyControl.proyecto.services.MoneyMovementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class moneyMovementController {
    @Autowired
    MoneyMovementServices moneymovementservices;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EnterpriseService enterpriseService;


    /*@GetMapping("/movements")
    public List<Money_movement> viewMovement()
    {
        return moneymovementservices.getAllMovement();
    }*/

    @GetMapping({"/ViewMovements"})
    public String viewMovements(Model model, @ModelAttribute("mensaje") String movements) {
        List<Money_movement> movementsList = moneymovementservices.getAllMovement();
        model.addAttribute("movementslist", movementsList);
        model.addAttribute("mensaje", movements);
        Long sumAmount= moneymovementservices.getSumAmount();
        model.addAttribute("sumAmount",sumAmount);
        return "viewMovements"; //Llamamos al HTML

    }

    /*@PostMapping("/movements")
    public Money_movement saveMoneyMovement(@RequestBody Money_movement moneymovement)
    {
        return this.moneymovementservices.updateMoneyMovement(moneymovement);
    }*/

    @GetMapping("/movements/{id}")
    public Money_movement movementById(@PathVariable("id") Integer id) {
        return moneymovementservices.getMovementId(id);
    }

    /*@PatchMapping("/movements/{id}")
    public Money_movement updateEmployee(@PathVariable("id") Integer id, @RequestBody Money_movement movement){
        Money_movement mov= moneymovementservices.getMovementId(id);
        mov.setAmount(movement.getAmount());
        mov.setConcept(movement.getConcept());
        mov.setUser(movement.getUser());
        mov.setCreateAt(movement.getCreateAt());
        mov.setUpdateAt(movement.getUpdateAt());

        return moneymovementservices.updateMoneyMovement(mov);
    }*/
    /*@DeleteMapping("/movements/{id}")
    public void deleteMovement(@PathVariable("id") Integer id){
        moneymovementservices.deleteMoneyMovement(id);
    }
    @GetMapping("enterprises/{id}/movements")
    public ArrayList<Money_movement> movementByEnterprise(@PathVariable("id")Integer id)
    {
       return moneymovementservices.getMovementEnterprise(id);
    }*/

    @GetMapping("/AddMovements")
    public String addEmployee(Model model, @ModelAttribute("mensaje") String mensaje ) {
        Money_movement movement = new Money_movement();
        model.addAttribute("mov", movement);
        model.addAttribute("mensaje", mensaje);
        List<Employee> listaUsuarios = employeeService.getAllEmployee();
        model.addAttribute("userlist", listaUsuarios);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo=auth.getName();
        Integer idEmpleado=moneymovementservices.IdBYEmail(correo);
        model.addAttribute("idEmpleado",idEmpleado);

        return "addMovement";
    }

    @PostMapping("/SaveMovement")
    public String saveMovement(Money_movement mov, RedirectAttributes redirectAttributes) {
        if (moneymovementservices.updateMovement(mov) == true) {
            return "redirect:/ViewMovements";
        }

        return "redirect:/AddMovement";
    }
    @GetMapping("/EditMovements/{id}")
    public String editMovement(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Money_movement movement = moneymovementservices.getMovementId(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov", movement);
        model.addAttribute("mensaje", mensaje);
        List<Employee> listemployee = employeeService.getAllEmployee();
        model.addAttribute("userlist", listemployee);
        return "editMovement";
    }
    @PostMapping("/UpdateMovement")
    public String updateMovement(Money_movement mov, RedirectAttributes redirectAttributes) {
        if (moneymovementservices.updateMovement(mov) == true) {
            return "redirect:/ViewMovements";
        }

        return "redirect:/EditMovements/"+mov.getId_movement();
    }

    @GetMapping("/DeleteMovement/{id}")
    public String deleteMovement(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (moneymovementservices.deleteMovement(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/ViewMovements";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/ViewMovements";
    }
   //movimientos por empleados
    @GetMapping("/Employee/{id}/Movements")
    public String movementsByEmployee(@PathVariable("id")Integer id, Model model )
    {
        List<Money_movement> movlist = moneymovementservices.getMovementEmployee(id);
        model.addAttribute("movementslist",movlist);
        Long sumAmount= moneymovementservices.getSumAmountByEmployee(id);
        model.addAttribute("sumAmount",sumAmount);
        return "viewMovements";
    }

    //movimientos por empresas
    @GetMapping("/Enterprise/{id}/Movements")
    public String movementsByEnterprise(@PathVariable("id")Integer id, Model model )
    {
        List<Money_movement> movlist = moneymovementservices.getMovementEnterprise(id);
        model.addAttribute("movementslist",movlist);
        Long sumAmount= moneymovementservices.getSumAmountByEnterprise(id);
        model.addAttribute("sumAmount",sumAmount);
        return "viewMovements";
    }
}