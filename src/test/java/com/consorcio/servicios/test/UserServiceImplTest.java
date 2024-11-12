package com.consorcio.servicios.test;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Service.Implement.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testUpdateUser_UserExists() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setUsername("newUsername");
        userDto.setFirstName("New");
        userDto.setLastName("Name");
        userDto.setDni(12345678);
        userDto.setPhone("555-1234");

        User user = new User();
        user.setIdUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.updateUser(userId, userDto);

        verify(userRepository, times(1)).save(user);
        assertEquals("newUsername", user.getUsername());
        assertEquals("New", user.getFirstName());
        assertEquals("Name", user.getLastName());
    }

    @Test
    void testUpdateUser_UserDoesNotExist() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userService.updateUser(userId, userDto));
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void testChangeUserStatus_UserExists() {
        Long userId = 1L;
        UserStatus status = UserStatus.ACTIVE;

        User user = new User();
        user.setIdUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.changeUserStatus(userId, status);

        verify(userRepository, times(1)).save(user);
        assertEquals(UserStatus.ACTIVE, user.getStatus());
    }

    @Test
    void testChangeUserStatus_UserDoesNotExist() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> userService.changeUserStatus(userId, UserStatus.ACTIVE));
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void testGetAllUsers_NoUsers() {
        when(userRepository.findAllUsers()).thenReturn(Arrays.asList());

        List<UserDto> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
    }

    @Test
    void testGetAllUsers_WithUsers() {
        UserDto user1 = new UserDto();
        UserDto user2 = new UserDto();
        when(userRepository.findAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertSame(user2, users.get(0));
    }

    @Test
    void testGetUsersActives() {
        Role role = Role.ROLE_USER;
        UserStatus status = UserStatus.ACTIVE;
        UserDto user1 = new UserDto();
        when(userRepository.findActiveUsersWithRole(role, status)).thenReturn(Arrays.asList(user1));

        List<UserDto> activeUsers = userService.getUsersActives(role, status);

        assertEquals(1, activeUsers.size());
        assertSame(user1, activeUsers.get(0));
    }
}