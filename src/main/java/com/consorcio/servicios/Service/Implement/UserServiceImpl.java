package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Security.Config.Authenticated;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.consorcio.servicios.Repository.UserRepository;
import org.springframework.stereotype.Service;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // El registro del usuario se encuentra en AuthService

    @Override
    public void updateUser(Long userId, UserDto userDto) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado");
        }

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        User user = userOptional.get();

        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDni(userDto.getDni());
        user.setPhone(userDto.getPhone());
        user.setDateUpdate(LocalDateTime.now());

        user.setIdUserRegister(currentUser.getUser().getIdUser());
        user.setIdUserUpdate(currentUser.getUser().getIdUser());

        userRepository.save(user);
    }

    @Override
    public void changeUserStatus(Long idUser, UserStatus status) {
        Optional<User> userOptional = userRepository.findById(idUser);

        if (!userOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado");
        }

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        User user = userOptional.get();

        user.setStatus(status);
        user.setIdUserUpdate(currentUser.getUser().getIdUser());

        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = userRepository.findAllUsers();
        return users;
    }

    @Override
    public List<UserDto> getUsersActives(Role role, UserStatus status) {
        List<UserDto> users = userRepository.findActiveUsersWithRole(role, status);
        return users;
    }

}