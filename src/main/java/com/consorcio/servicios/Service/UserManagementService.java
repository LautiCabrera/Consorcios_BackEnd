package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import java.util.List;

public interface UserManagementService {
    
    public User updateUser(int userId, UserDto userDto);
    public void deactivateUser(int userId);
    public List<UserDto> getAllActiveUsers();
    
}