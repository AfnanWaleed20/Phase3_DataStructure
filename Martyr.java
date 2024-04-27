package application;

import java.util.Date;

public class Martyr {
	// this class to information about martyr 
		private String name;
		private int age;
		private Date dateOfDeath=new Date();
	    private char gender;
		public static int sum;
Location location2=new Location();
		public Martyr() {
			super();
			
		}

		public Martyr(String name) {
			super();
			
		}

		public Martyr(String name, int age,Date dateOfDeath, char gender) {
			super();
			this.name = name;
			this.age = age;
			this.dateOfDeath = dateOfDeath;
			this.gender = gender;
			
		}



		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public char getGender() {
			return gender;
		}
		public void setGender(char gender) {
			this.gender = gender;
		}
		
		public Date getDateOfDeath() {
			return dateOfDeath;
		}

		public void setDateOfDeath(Date dateOfDeath) {
			this.dateOfDeath = dateOfDeath;
		}

		public Location getLocation2() {
			return location2;
		}

		public void setLocation2(Location location) {
			this.location2= location;
		}

		@Override
		public String toString() {
			return (name +","+ age +","+ (dateOfDeath.getMonth()+1)+"/"+(dateOfDeath.getDay())+"/" +(dateOfDeath.getYear()+1900)+","+gender);
		}
		

		
		

	}

