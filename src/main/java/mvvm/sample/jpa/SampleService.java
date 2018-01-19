package mvvm.sample.jpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> retrieveAll() {
		return (List<Users>) usersRepository.findAll();
	}
	
	public List<Users> retrieveList(Users users) {
//		ExampleMatcher matcher = ExampleMatcher.matching()
//				.withIncludeNullValues();
//		Example<Language> languageExample = Example.of(language, matcher);
//		languageRepository.findAll(languageExample);
		return (List<Users>) usersRepository.findAll(Example.of(users));
	}
	
	public Users retrieve(Users users) {
		return usersRepository.findOne(Example.of(users));
	}
	
	@Transactional
	public void add(Users users) {
		if (usersRepository.exists(Example.of(users))) {
			throw new RuntimeException("Already data exist!");
		} else {
			usersRepository.save(users);
		}
	}
	
	@Transactional
	public void add(List<Users> usersList) {
		for (Users item : usersList) {
			this.add(item);
		}
	}
	
	@Transactional
	public void edit(Users users) {
		if (usersRepository.exists(Example.of(users))) {
			usersRepository.save(users);
		} else {
			throw new RuntimeException("Data not found!");
		}
	}
	
	@Transactional
	public void remove(Users users) {
		if (usersRepository.exists(Example.of(users))) {
			usersRepository.delete(users);
		} else {
			throw new RuntimeException("Data not found!");
		}
	}

}
