package com.moneyControl.proyecto;

import com.moneyControl.proyecto.models.Employee;
import com.moneyControl.proyecto.models.Enterprise;
import com.moneyControl.proyecto.models.Money_movement;
import com.moneyControl.proyecto.models.Profile;
import org.attoparser.trace.MarkupTraceEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}
/*
@GetMapping("/test")
	public String test() {

		Enterprise ent = new Enterprise("VICTOR", 101010, 30000, "CALLE 32", LocalDate.now(), LocalDate.now());
		return ent.getName();

	}
*/
	/*public String testEmployee() {
		Enterprise ent = new Enterprise("VICTOR", 101010, 30000, "CALLE 32", LocalDate.now(), LocalDate.now());
		Employee empl = new Employee("jose ramirez", "josramirez@gmail.com", ent, "operario", null, LocalDate.now(), LocalDate.now());
		return empl.getRol();
	}*/
/*
	public void Movement(Employee employee, Enterprise emp) {

		Money_movement movement = new Money_movement(50000, "Servicios publicos", employee, LocalDate.now(), LocalDate.now());
	  employee.setNombre("victorperez");
	}
*/



}
