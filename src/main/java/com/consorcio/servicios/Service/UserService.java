package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Enums.Role;

import java.util.List;

public interface UserService {

    public void updateUser(Long idUser, UserDto userDto);

    void changeUserStatus(Long idUser, UserStatus status);

    public List<UserDto> getAllUsers();

    public List<UserDto> getUsersActives(Role role, UserStatus status);

}