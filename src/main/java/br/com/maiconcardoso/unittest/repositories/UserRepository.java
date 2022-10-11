package br.com.maiconcardoso.unittest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maiconcardoso.unittest.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

    Optional<UserModel> findByUserName(String name);
    
}
