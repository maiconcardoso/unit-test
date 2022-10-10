package br.com.maiconcardoso.unittest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maiconcardoso.unittest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
}
