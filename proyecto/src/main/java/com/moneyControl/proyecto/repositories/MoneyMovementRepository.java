package com.moneyControl.proyecto.repositories;

import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.models.Money_movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MoneyMovementRepository extends JpaRepository<Money_movement,Integer> {
 @Query(value= "Select * from moneymovement where id=?1",nativeQuery = true)
   public abstract ArrayList<Money_movement> findByEmployeee (Integer id);
//filtrar movimientos por empresas
 @Query(value= "Select * from moneymovement where id in (select id from employee where id_empresa =?1)",nativeQuery = true)
   public abstract ArrayList<Money_movement> findByEnterprise (Integer id);
 //suma total de movimientos
 @Query(value="SELECT SUM(amount) from moneymovement", nativeQuery = true)
    public abstract Long sumAmount ();
    //suma movimientos por empresa
    @Query(value="Select sum(amount) from moneymovement where id in (select id from employee where id_empresa =?1)", nativeQuery = true)
    public abstract Long sumAmountByEnterprise (Integer id);
    //suma movimientos por empleado
    @Query(value="SELECT sum(amount) from moneymovement where id=?1", nativeQuery = true)
    public abstract Long sumAmountByEmployee (Integer id);
    @Query(value="select id from employee where correo=?1", nativeQuery = true)
    public abstract Integer IdBYEmail(String correo);
}

