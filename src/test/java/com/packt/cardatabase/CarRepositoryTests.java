package com.packt.cardatabase;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CarRepository repository;
	
	@Test
	public void saveCar() {
		Car car = new Car("Testla", "Model X", "White", "ABC-1234", 2017, 86000, null);
		entityManager.persistAndFlush(car);
		Assertions.assertThat(car.getId()).isNotNull();
	}
	
	@Test
	public void deleteCar() {
		Car car1 = new Car("Testla", "Model X", "White", "ABC-1234", 2017, 86000, null);
		Car car2 = new Car("Testla", "Model Y", "Silver", "XYZ-1234", 2017, 86000, null);
		
		entityManager.persistAndFlush(car1);
		entityManager.persistAndFlush(car2);
		
		repository.deleteAll();
		Assertions.assertThat(repository.findAll()).isEmpty();
	}
}
