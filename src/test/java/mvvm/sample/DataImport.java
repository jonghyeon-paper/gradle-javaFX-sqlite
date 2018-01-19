package mvvm.sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mvvm.sample.jpa.SampleService;
import mvvm.sample.jpa.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataImport {

	@Autowired
	private SampleService sampleService;
	
	@Test
	public void dataImport() throws IOException {
		String filePath = "src/main/resources/data/basic";
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String s;
		
		String version = null;
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while ((s = in.readLine()) != null) {
			if (first) {
				version = s;
				first = false;
				continue;
			}
			
			sb.append(s);
		}
		in.close();
		
		System.out.println("version >> " + version);
		System.out.println("data >> " + sb.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		List<Users> usersList = mapper.readValue(sb.toString(), new TypeReference<List<Users>>(){});
		sampleService.add(usersList);
		
		for (Users item : sampleService.retrieveAll()) {
			System.out.println(item.toString());
		}
	}
}
