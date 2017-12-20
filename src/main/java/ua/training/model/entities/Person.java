package ua.training.model.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Денис on 14.12.2016.
 */
public class Person {
	private int id;
	private String name;
	private LocalDate birthdate;
	private String password;
	private String email;
	private Role role;
	
	public enum Role{
		USER, ADMIN
	}
	
	public static class Builder{
		//private Person instance = new Person();
		private int id;
		private String name;
		private LocalDate birthdate;
		private String password;
		private String email;
		private Role role;
		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setBirthdate(Timestamp birthdate , boolean isNull) {
			if(!isNull){
				this.birthdate = birthdate.toLocalDateTime().toLocalDate();
			}
			return this;
		}
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setRole(Role role) {
			this.role = role;
			return this;
		}
		
		public Person build(){
			Person person = new Person();
			person.setId(id);
			person.setName(name);
			person.setBirthdate(birthdate);
			person.setPassword(password);
			person.setRole(role);
			person.setEmail(email);
			return person;
		}
	}

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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
