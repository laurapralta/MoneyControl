package com.moneyControl.proyecto.services;

import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.repositories.EnterpriseRepository;
import com.moneyControl.proyecto.repositories.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseService {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    //metodo de retorno de empresa
    public List<Enterprise> getALLEnterprise()  {
        List<Enterprise> enterprisesList = new ArrayList<>();
        enterpriseRepository.findAll().forEach(enterprise -> enterprisesList.add(enterprise));
        return enterprisesList;
    }

    public Enterprise getEnterpriseById(Integer id) {
        return enterpriseRepository.findById(id).get();
    }

    /*// metodo para guardar o actualizar objetos tipo empresa
    public Enterprise updateEnterprise(Enterprise emp) {
        Enterprise ent = enterpriseRepository.save(emp);
            return ent;
}*/

    // metodo para guardar o actualizar objetos tipo empresa
    public boolean updateEnterprise(Enterprise emp) {
        Enterprise ent = enterpriseRepository.save(emp);
        if(enterpriseRepository.findById(ent.getId())!=null){
            return true;
        }
        return false;

    }


   /* //delete
    public boolean deleteEnterprise(Integer id) {

            enterpriseRepository.deleteById(id);
         if(enterpriseRepository.findById(id)!=null) {
             return true;
         }
        return false;
    }*/
   //delete
   public String deleteEnterprise(Integer id) {
       try {
           enterpriseRepository.deleteById(id);
       } catch (Exception e) {
           return "redirect:/VerEmpresas";
       }
       return "redirect:/VerEmpresas";
   }
}