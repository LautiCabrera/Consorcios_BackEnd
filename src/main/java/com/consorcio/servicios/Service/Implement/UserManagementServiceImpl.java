package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import com.consorcio.servicios.Repository.UserRepository;
import org.springframework.stereotype.Service;
import com.consorcio.servicios.Entity.User;
import java.util.Optional;
import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateUser(int userId, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDto.getUsername());
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setDni(userDto.getDni());
            user.setPhone(userDto.getPhone());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deactivateUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public List<UserDto> getAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }
    
}