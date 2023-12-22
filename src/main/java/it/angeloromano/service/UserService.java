package it.angeloromano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.angeloromano.db.UserRepository;
import it.angeloromano.model.BodyParams;
import it.angeloromano.model.User;
import it.angeloromano.model.UserLogged;
import it.angeloromanoexception.UserNotFoundException;

import java.util.*;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public List<User> getUsers (){
        return userRepository.findAll();
    }
    
    public User createUser (User user){
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id){
    	if(userRepository.existsById(id)) {
    		userRepository.deleteById(id);
    	}
    	else throw new UserNotFoundException("Utente con Id "+id+" non trovato");
    }
    
    public User updateUser (User user){
    	if(userRepository.existsById(user.getId())) {
    		return userRepository.save(user);
    	}
    	else throw new UserNotFoundException("Utente con Id "+user.getId()+" non trovato");
    }
    
    public Optional<User> getUserById(Long id) {
    	if(userRepository.existsById(id)) {
    		return userRepository.findById(id);
    	}
    	else throw new UserNotFoundException("Utente con Id "+id+" non trovato");
    }
    
	public UserLogged loginUser(BodyParams body) {
		List<User> l = userRepository.findAll();
		UserLogged userLogged = null;
		for (User user : l) {
			if(user.getEmail().equals(body.getEmail()) && user.getPassword().equals(body.getPassword())) {
				userLogged = new UserLogged(user);
				break;
			}
		}
		return userLogged;
	}

}
