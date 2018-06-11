package repositories;

import org.springframework.data.repository.CrudRepository;

import model.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByIdentificador(String dni);

}
