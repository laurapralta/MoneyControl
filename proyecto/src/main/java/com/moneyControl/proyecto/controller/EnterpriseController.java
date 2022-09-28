package com.moneyControl.proyecto.controller;

import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;


    @GetMapping ({"/","/VerEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Enterprise> empList=enterpriseService.getALLEnterprise();
        model.addAttribute("empList",empList);
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas"; //Llamamos al HTML
    }

    /*@GetMapping("/enterprises") //Ver json de todas las empresas
    public List<Enterprise> verEmpresas(){
        return enterpriseService.getALLEnterprise();
    }*/

    @GetMapping("/AddEnterprise")
    public String addEnterprise(Model model) {
        Enterprise emp = new Enterprise();
        model.addAttribute("emp", emp);
        return "addEnterprise";
    }

    /*@PostMapping("/Enterprises")
   public Enterprise saveEnterprise(@RequestBody Enterprise emp)
    {
        return this.enterpriseService.updateEnterprise(emp);
    }*/

    @PostMapping("/SaveEnterprise")
    public String saveEnterprise(Enterprise emp, RedirectAttributes redirectAttributes) {
        if (enterpriseService.updateEnterprise(emp) == true) {
            return "redirect:/VerEmpresas";
        }

        return "redirect:/AddEnterprise";
    }


    @GetMapping("/EditarEmpresa/{id}")
    public String editEnterprise(Model model, @PathVariable Integer id)
    {
        Enterprise ent= enterpriseService.getEnterpriseById(id);
        model.addAttribute("emp",ent);
        return "editarEmpresa";

    }
    /*@GetMapping(path = "/Enterprises/{id}")
    public Enterprise enterpriseID(@PathVariable("id")Integer id){
      return this.enterpriseService.getEnterpriseById(id);
    }*/

    @PostMapping("/ActualizarEmpresa")
    public String updateEnterprise(Enterprise ent){
        if(enterpriseService.updateEnterprise(ent)==true){
            return "redirect:/VerEmpresas";

        }
        return "redirect:/EditarEmpresa";
    }

    /*
    @PatchMapping("/Enterprises/{id}")
    public Enterprise UpdateEnterprises(@PathVariable("id") Integer id, @RequestBody Enterprise enterprise){
       Enterprise ent=enterpriseService.getEnterpriseById(id);
       ent.setName(enterprise.getName());
       ent.setAddress(enterprise.getAddress());
       ent.setNit(enterprise.getNit());
       ent.setPhone(enterprise.getPhone());
       ent.setCreateAt(enterprise.getCreateAt());
       ent.setUpdateAt(enterprise.getCreateAt());

       return enterpriseService.updateEnterprise(ent);
    }*/
    /*@DeleteMapping(path="Enterprises/{id}")
    public String DeleteEnterprises(@PathVariable("id") Integer id) {
        boolean response = this.enterpriseService.deleteEnterprise(id);
        if (response == true) {
            return "se elimino la empresa" + id;
        }
        else {
           return "No elimino nada";
        }
    }*/
    @GetMapping("/EliminarEmpresa/{id}")
    public String deleteEnterprise(@PathVariable Integer id){
        if(enterpriseService.deleteEnterprise(id)==null){
            return "redirect:/VerEmpresas";
        }
        return "redirect:/VerEmpresas";
    }

}
