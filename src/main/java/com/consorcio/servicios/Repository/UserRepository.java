package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import java.util.List;
import java.util.Optional;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.consorcio.servicios.Dto.UserDto(u.idUser, u.username, u.lastName, u.firstName, u.dni, u.phone, u.status) "
            + "FROM User u")
    List<UserDto> findAllUsers();

    @Query("SELECT NEW com.consorcio.servicios.Dto.UserDto(u.idUser, u.username, u.lastName, u.firstName, u.dni, u.phone, u.status) "
            + "FROM User u WHERE u.role = :role AND u.status = :status")
    List<UserDto> findActiveUsersWithRole(
            @Param("role") Role role,
            @Param("status") UserStatus status
    );

    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    Optional<Role> findRoleForUser(String username);

}