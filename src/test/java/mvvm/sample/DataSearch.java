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
public class DataSearch {

	@Autowired
	private SampleService sampleService;
	
	@Test
	public void searchId() throws IOException {
		Users users = new Users();
		users.setId(2);
		for (Users item : sampleService.retrieveList(users)) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	public void searchName() throws IOException {
		Users users = new Users();
		users.setName("aaa");
		for (Users item : sampleService.retrieveList(users)) {
			System.out.println(item.toString());
		}
	}
}
