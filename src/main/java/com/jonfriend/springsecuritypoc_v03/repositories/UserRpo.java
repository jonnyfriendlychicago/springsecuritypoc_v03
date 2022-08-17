package com.jonfriend.springsecuritypoc_v03.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.springsecuritypoc_v03.models.UserMdl;

@Repository
public interface UserRpo extends CrudRepository<UserMdl, Long> {
    
//    UserMdl findByUsername(String username); 
	UserMdl findByEmail(String email);

	List<UserMdl> findAll();
	
//	Optional<UserMdl> findByEmail(String email);
//    
//    // JRF 724
//    List<UserMdl> findAll();
//    
//    UserMdl findByIdIs(Long id);
    
    
    // end of rpo   
}