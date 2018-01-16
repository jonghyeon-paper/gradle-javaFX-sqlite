package mvvm.sample.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> getUsersList() {
		return (List<Users>) usersRepository.findAll();
	}

}
