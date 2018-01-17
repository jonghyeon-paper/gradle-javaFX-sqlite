package mvvm.sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import mvvm.sample.jpa.SampleService;
import mvvm.sample.jpa.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataExport {
	
	private static final String VERSION = "v0.0.0.20180117";
	private static final String NEWLINE = System.getProperty("line.separator");
	
	@Autowired
	private SampleService sampleService;
	
	@Test
	public void dataExport() throws IOException {
		for (Users item : sampleService.getUsersList()) {
			System.out.println(item.toString());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String result = VERSION + NEWLINE + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sampleService.getUsersList());
		System.out.println(result);
		
		String filePath = "src/main/resources/data/basic";
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(filePath, false);
		fw.write(result);
		fw.flush();
		fw.close();
	}
}
