package com.saksoft.usermangementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saksoft.usermangementsystem.dto.UserDto;
import com.saksoft.usermangementsystem.exception.UserNotFoundException;
import com.saksoft.usermangementsystem.model.User;
import com.saksoft.usermangementsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private  UserRepository userRepository;
	
	private final ModelMapper modelMapper;

	
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user= modelMapper.map(userDto, User.class);
		User createdUser = userRepository.save(user);
		return modelMapper.map(createdUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id)
				         .orElseThrow(()->new UserNotFoundException("User not found with id:"+ id));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user->modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDto) {
		User existingUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id: "+id));
		modelMapper.map(userDto, existingUser);
		User updatedUser = userRepository.save(existingUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
	

}
