package com.moneyControl.proyecto.services;

import com.moneyControl.proyecto.models.Employee;
import com.moneyControl.proyecto.models.Money_movement;
import com.moneyControl.proyecto.repositories.MoneyMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoneyMovementServices {
 @Autowired
    MoneyMovementRepository movementRepository;


 public List<Money_movement> getAllMovement()
 {
     List<Money_movement> movementList = new ArrayList<>();
     movementRepository.findAll().forEach(movement -> movementList.add(movement));
     return movementList;
 }

 public Money_movement getMovementId(Integer id)
 {
     return movementRepository.findById(id).get();
 }

   /* public Money_movement updateMoneyMovement(Money_movement movement) {
        Money_movement mov = movementRepository.save(movement);
        return mov;
    }*/
   // metodo para guardar o actualizar objetos tipo empresa
   public boolean updateMovement(Money_movement mov) {
       Money_movement movement = movementRepository.save(mov);
       if(movementRepository.findById(mov.getId_movement())!=null){
           return true;
       }
       return false;

   }


   /* public void deleteMoneyMovement(Integer id){
        movementRepository.deleteById(id);
    }*/

    public ArrayList<Money_movement> getMovementEmployee(Integer id)
    {
        return movementRepository.findByEmployeee(id);
    }

    public ArrayList<Money_movement> getMovementEnterprise(Integer id)
    {
        return movementRepository.findByEnterprise(id);
    }
    public boolean deleteMovement (Integer id){
        movementRepository.deleteById(id);
        if(this.movementRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

   public Long getSumAmount(){
        return movementRepository.sumAmount();
   }
    public Long getSumAmountByEnterprise(Integer id){
        return movementRepository.sumAmountByEnterprise(id);
    }

    public Long getSumAmountByEmployee(Integer id){
        return movementRepository.sumAmountByEmployee(id);
    }

    //servicio que nos deja conseguir el id de un empleado si tenemos su correo
    public Integer IdBYEmail(String Correo){
        return movementRepository.IdBYEmail(Correo);
    }
}
