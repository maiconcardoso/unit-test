package br.com.maiconcardoso.unittest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maiconcardoso.unittest.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
    
}
