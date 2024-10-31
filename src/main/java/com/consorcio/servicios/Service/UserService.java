package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Enums.UserStatus;
import java.util.List;

public interface UserService {

    public void updateUser(Long idUser, UserDto userDto);

    void changeUserStatus(Long idUser, UserStatus status);

    public List<UserDto> getAllUsers();

}