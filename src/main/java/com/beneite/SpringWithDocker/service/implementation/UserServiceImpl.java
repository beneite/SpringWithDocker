package com.beneite.SpringWithDocker.service.implementation;


import com.beneite.SpringWithDocker.dto.UserDto;
import com.beneite.SpringWithDocker.entity.UserEntity;
import com.beneite.SpringWithDocker.exception.DuplicateEmailException;
import com.beneite.SpringWithDocker.exception.ResourceNotFoundException;
import com.beneite.SpringWithDocker.mapper.AutoUserMapper;
import com.beneite.SpringWithDocker.repository.UserRepository;
import com.beneite.SpringWithDocker.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<UserEntity> ifEmailExist = userRepository.findByEmail(userDto.getEmail());     // check if email exist in DB
        if(ifEmailExist.isPresent()){
            throw new DuplicateEmailException(String.format("Email: %s, already available.", userDto.getEmail()));
        }

        UserEntity userEntity = AutoUserMapper.MAPPER.mapToJpa(userDto);     // userEntity is of type JPA
        UserEntity savedEntity = userRepository.save(userEntity);     // savedEntity is of type JPA
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToDto(savedEntity);   // savedUserDto os of type DTO
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        UserDto userGetDto = AutoUserMapper.MAPPER.mapToDto(userEntity);
        return userGetDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> allUsers = userRepository.findAll();
        return allUsers.stream().map(UserEntity-> AutoUserMapper.MAPPER.mapToDto(UserEntity)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity existingData = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        // setting the new data to the existing one
        existingData.setFirstName(userDto.getFirstName());
        existingData.setLastName(userDto.getLastName());
        existingData.setEmail(userDto.getEmail());

        Optional<UserEntity> ifEmailExist = userRepository.findByEmail(userDto.getEmail());     // check if email exist in DB
        if(ifEmailExist.isPresent()){
            throw new DuplicateEmailException(String.format("Email: %s, already available.", userDto.getEmail()));
        }

        // saving the data
        UserEntity savedUser = userRepository.save(existingData);
        return AutoUserMapper.MAPPER.mapToDto(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity existingData = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }


}
