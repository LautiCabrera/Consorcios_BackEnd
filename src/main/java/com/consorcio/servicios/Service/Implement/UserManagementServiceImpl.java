package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import com.consorcio.servicios.Repository.UserRepository;
import org.springframework.stereotype.Service;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import jakarta.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.TransactionSystemException;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    
    @Autowired
    private UserRepository userRepository;
    
    // El registro del usuario se encuentra en AuthService

    @Override
    public User updateUser(int userId, UserDto userDto) {
        
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado");
        }

        User user = userOptional.get();

        if (userDto.getUsername() == null || userDto.getFirstname() == null || userDto.getLastname() == null ||
            userDto.getDni() <= 0 || userDto.getPhone() <= 0) {
            throw new IllegalArgumentException("Datos inválidos para actualizar usuario");
        }

        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setDni(userDto.getDni());
        user.setPhone(userDto.getPhone());

        try {
            return userRepository.save(user);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Violación de restricciones: " + e.getMessage(), e);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Violación de integridad de datos: " + e.getMessage(), e);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new IllegalStateException("Error de concurrencia optimista: " + e.getMessage(), e);
        } catch (TransactionSystemException e) {
            throw new IllegalStateException("Error de sistema de transacciones: " + e.getMessage(), e);
        }
        
    }
    
    @Override
    public void changeUserStatus(int userId, UserStatus status) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (!userOptional.isPresent()) {
            throw new NoSuchElementException("Usuario no encontrado");
        } 
        
        User user = userOptional.get();
        user.setStatus(status);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        
        List<UserDto> users = userRepository.findAllUsers();

         if (users.isEmpty()) {
            throw new NoSuchElementException("Usuarios activos no encontrados");
        }
         
        return users;
    }

    @Override
    public List<UserDto> searchActiveUserByName(String name) {
        
        List<UserDto> users = userRepository.findActiveUserByName(name);
        
         if (users.isEmpty()) {
            throw new NoSuchElementException("Usuario/s no encontrados");
        }
        
        return users ;
    }

    @Override
    public List<UserDto> searchActiveUserByDni(int dni) {

        List<UserDto> users = userRepository.findActiveUserByDni(dni);
        
        if (users.isEmpty()) {
            throw new NoSuchElementException("Usuario no encontrado");
        }
        
        return users;
    }
    
}