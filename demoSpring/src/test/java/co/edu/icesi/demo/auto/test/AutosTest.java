package co.edu.icesi.demo.auto.test;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edu.icesi.demo.auto.Motor;
import co.edu.icesi.demo.auto.Vehiculo;


public class AutosTest {

	private final static Logger log = LoggerFactory.getLogger(AutosTest.class);
	
	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContextID.xml"});
	Motor motor = (Motor) context.getBean("motor");
	
	log.info(motor.getMarca());
	log.info("" + motor.getCilindraje());
	log.info(motor.getSerial());
	log.info("" + motor.isTurbo());
	
	Vehiculo vehiculo =  (Vehiculo) context.getBean("vehiculo");
	
	log.info(vehiculo.getMotor().getSerial());
	log.info(vehiculo.getMotor().getMarca());
	
	context.close();
			
	
	}

}
