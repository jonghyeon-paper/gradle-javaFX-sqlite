package mvvm.sample;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mvvm.sample.jpa.SampleService;
import mvvm.sample.jpa.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JUnitTest {
	
	@Autowired
	private SampleService sampleService;
	
	@Test
	public void show() throws IOException {
		for (Users item : sampleService.getUsersList()) {
			System.out.println(item.toString());
		}
	}
}
