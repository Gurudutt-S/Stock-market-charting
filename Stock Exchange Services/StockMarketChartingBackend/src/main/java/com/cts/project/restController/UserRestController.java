package com.cts.project.restController;

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
import org.springframework.web.bind.annotation.RestController;

import com.cts.project.dto.UserDTO;
import com.cts.project.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserRestController {

//	@Autowired
//	UserDAO userDao;
//
//	@Autowired
//	JavaMailSender jms;

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public ResponseEntity<?> getUsers() {
		List<UserDTO> list = userService.getAllUsers();
		if (list.size() > 0) {
			return new ResponseEntity<List<UserDTO>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("No Users Found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("user/{id}")
	public UserDTO getUserById(@PathVariable("id") int id) {
		return userService.getUserById(id);
	}

	@PostMapping("/user")
	public UserDTO saveUser(@RequestBody UserDTO userDTO) {
		return userService.saveUser(userDTO);
	}

	@PutMapping(value = "/activate")
	public boolean activateUser(@RequestBody String email) {
		return userService.activateUser(email);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	@PutMapping("/user")
	public UserDTO updateUser(@RequestBody UserDTO userDTO) {
		return userService.updateUser(userDTO);
	}

}
