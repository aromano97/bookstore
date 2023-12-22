package it.angeloromano.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.angeloromano.db.*;
import it.angeloromano.model.*;
import it.angeloromano.service.UserService;
import it.angeloromanoexception.UserNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "users")
public class UserController {
	
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
	
	/*@GetMapping("/get")
	public List<User> getUsers() {
		return userRepository.findAll();
	}*/
	
	@GetMapping("/get") //serve per mappare il seguente metodo come una richiesta GET, che accederà al backend per prendere dei dati
    public ResponseEntity<List<User>> getUsers (){//risposta http nel cui corpo ci sarà una lista di utenti
        List<User> listUser = userService.getUsers();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }
	
	/*@PostMapping("/add")
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}*/
	
	@PostMapping("/add")
    public ResponseEntity<User> createUser (@RequestBody User user){//uso come utente quello che c'è nel corpo della richiesta
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);//abbiamo creato qualcosa sul server quindi uso CREATED
    }
	
	/*@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		User user = userRepository.getReferenceById(id);
		userRepository.deleteById(id);
	}*/
	
	@DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	/*@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		if(userRepository.existsById(user.getId())) {
			userRepository.save(user);
			return user;
		}
		throw new UtenteNonTrovatoException("Utente con id: "+user.getId()+" non è stato trovato");
	}*/
	
	@PutMapping("/update")
    public ResponseEntity<User> updateUser (@RequestBody User user){
        User uppUser = userService.updateUser(user);
        return new ResponseEntity<>(uppUser, HttpStatus.OK);
    }
	
	/*@PostMapping("/login")
	public UserLogged loginUser(@RequestBody BodyParams body) {
		List<User> l = userRepository.findAll();
		UserLogged userLogged = null;
		
		for (User user : l) {
			if(user.getEmail().equals(body.getEmail()) && user.getPassword().equals(body.getPassword())) {
				userLogged = new UserLogged(user);
				break;
			}
		}
		return userLogged;
	}*/
	
	@PostMapping("/login")
	public UserLogged loginUser(@RequestBody BodyParams body) {
		UserLogged userLogged = userService.loginUser(body);
		return userLogged;
	}

}
