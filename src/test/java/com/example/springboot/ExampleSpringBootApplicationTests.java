package com.example.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.example.springboot")
public class ExampleSpringBootApplicationTests {

	@Test
	public void whenGetBeans_returnsBean() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("indexApp-config.xml");
		IndexApp indexApp = applicationContext.getBean("indexApp", IndexApp.class);
		Assert.assertNotNull(indexApp);
	}

}
