package mvvm.sample.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, String> {

}
