package com.jonfriend.springsecuritypoc_v03.services;

import com.jonfriend.springsecuritypoc_v03.models.UserMdl;
import com.jonfriend.springsecuritypoc_v03.repositories.RoleRpo;
import com.jonfriend.springsecuritypoc_v03.repositories.UserRpo;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;

//import com.jonfriend.springsecuritypoc_v03.models.LoginUserMdl;

@Service
public class UserSrv{
    
	// get these things autowired, this is hurting my eyes
    private UserRpo userRpo;
    private RoleRpo roleRpo; 
    private BCryptPasswordEncoder bCryptPasswordEncoder;  

    public UserSrv (
    		UserRpo userRpo
    		, RoleRpo roleRpo
    		, BCryptPasswordEncoder bCryptPasswordEncoder
    		)
    {
        this.userRpo = userRpo;
        this.roleRpo = roleRpo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void newUser(
    		UserMdl usermdl
    		, String role  // note: this is just a string variable called 'role'... not the roleMdl
    		) {
    	usermdl.setPassword(bCryptPasswordEncoder.encode(usermdl.getPassword()));
    	usermdl.setRoleMdl(roleRpo.findByName(role));
    	userRpo.save(usermdl);
	}
    
    public void updateUser(
    		UserMdl usermdl
    		) {
    	userRpo.save(usermdl);
	}
    
    public UserMdl findByEmail(
    		String email
    		) {
		return userRpo.findByEmail(email);
	}
	
	public List<UserMdl> allUsers(){
		return userRpo.findAll();
	}
	
	public UserMdl upgradeUser(
			UserMdl usermdl
			) {
		usermdl.setRoleMdl(roleRpo.findByName("ROLE_ADMIN"));
		return userRpo.save(usermdl);
	}
	
	public void deleteUser(
			UserMdl usermdl
			) {
		userRpo.delete(usermdl);
	}
    
    public UserMdl findById(
    		Long id
    		) {
    	Optional<UserMdl> potentialUser = userRpo.findById(id);
    	if(potentialUser.isPresent()) {
    		return potentialUser.get();
    	}
    	return null;
    }
    
    // below is legacy code, not sure how will use in future
    
    
    // 1
    public void saveWithUserRole(UserMdl userMdl) {
    	userMdl.setPassword(bCryptPasswordEncoder.encode(userMdl.getPassword()));
    	userMdl.setRoleMdl(roleRpo.findByName("ROLE_USER"));
        userRpo.save(userMdl);
    }
     
     // 2 
    public void saveUserWithAdminRole(UserMdl userMdl) {
    	userMdl.setPassword(bCryptPasswordEncoder.encode(userMdl.getPassword()));
    	userMdl.setRoleMdl(roleRpo.findByName("ROLE_ADMIN"));
        userRpo.save(userMdl);
    }    
    
    // below is out for now, b/c in this new project there is no username field
//    // 3
//    public UserMdl findByUsername(String username) {
//        return userRpo.findByUsername(username);
//    }

// end of service
}
    
    	
