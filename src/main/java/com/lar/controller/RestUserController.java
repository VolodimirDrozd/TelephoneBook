package com.lar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lar.dto.UserDTO;
import com.lar.entity.User;
import com.lar.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class RestUserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/save")
	public User save(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO.build());
	}

	@GetMapping("/get")
	public User getUserBy(@RequestParam Long userId) {
		return userService.findUserBy(userId);
	}

}
