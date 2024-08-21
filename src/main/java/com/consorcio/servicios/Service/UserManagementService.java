package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import java.util.List;

public interface UserManagementService {

    public User updateUser(int userId, UserDto userDto);

    void changeUserStatus(int userId, UserStatus status);

    public List<UserDto> getAllUsers();

}