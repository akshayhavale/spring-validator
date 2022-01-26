package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
import com.example.model.User;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/save")
	public @ResponseBody ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) throws MethodArgumentNotValidException{
		log.debug("ENTERED CREATE METHOD");
		User user = new User();
		user.setUsername(userRequestDto.getUsername());
		user.setEmail(userRequestDto.getEmail());
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setPassword(userRequestDto.getPassword());
		user.setUdpatedDate(new Date());
		user.setCreatedDAte(new Date());

		User savedUser = userRepository.save(user);

		UserResponseDto responseDto = new UserResponseDto();
		responseDto.setId(savedUser.getId());
		log.debug("EXITING CREATE METHOD");
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping(value = "/")
	public @ResponseBody ResponseEntity<List<UserResponseDto>> getAll() {
		
		log.debug("ENTERED GETALL METHOD");
		List<User> users = userRepository.findAll();

		log.debug("EXITING GETALL METHOD");
		return new ResponseEntity<>(users.stream()
				.map(user -> new UserResponseDto(user.getId(), user.getUsername())).collect(Collectors.toList()),
				HttpStatus.OK);

	}
	


}
