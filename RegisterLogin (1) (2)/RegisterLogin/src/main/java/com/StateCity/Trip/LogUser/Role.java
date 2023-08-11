package com.StateCity.Trip.LogUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   public int getId() {
	return id;
   }
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Role(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public Role() {
	super();

}
@Override
public String toString() {
	return "Role [id=" + id + ", name=" + name + "]";
}

   
}
