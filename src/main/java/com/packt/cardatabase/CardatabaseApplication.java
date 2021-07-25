package com.packt.cardatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;

@SpringBootApplication
public class CardatabaseApplication {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Owner owner1 = new Owner("John", "Johnson");
				Owner owner2 = new Owner("Jack", "Robert");
				
				ownerRepository.save(owner1);
				ownerRepository.save(owner2);
				
				Car car1 = new Car("Ford", "Mustang" , "Red", "ADF-0010", 2017, 70000, owner1);
				Car car2 = new Car("Toyota", "Prius" , "Silver", "KKO-1012", 2020, 100000, owner2);
				Car car3 = new Car("Nissan", "Leaf" , "Black", "BDF-9091", 2017, 120000, owner2);
				
				carRepository.save(car1);
				carRepository.save(car2);
				carRepository.save(car3);
				
				// username: user, password: user
				userRepository.save(new User("user", "$2a$10$EtG3ClH2TEIWc3lZhRWTFe3SzPlo/nomFIMy5P/U3qxkcrJA4BVJ.", "USER"));
				// username: admin, password: admin
				userRepository.save(new User("admin", "$2a$10$fAOk3xXWabA.sKXJt25akOh001hz9M10VfaNXSUKIb6AKCxuom9Ya", "ADMIN"));
			}
		};
	}
}
