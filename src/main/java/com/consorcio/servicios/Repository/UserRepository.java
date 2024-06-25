package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.consorcio.servicios.Dto.UserDto(u.username, u.lastname, u.firstname, u.dni, u.phone, u.status) " +
           "FROM User u")
    List<UserDto> findAllUsers();
    
    @Query("SELECT NEW com.consorcio.servicios.Dto.UserDto(u.username, u.lastname, u.firstname, u.dni, u.phone, u.status) " +
           "FROM User u WHERE u.firstname LIKE %:name%")
    List<UserDto> findActiveUserByName(@Param ("name") String name);
    
    @Query("SELECT NEW com.consorcio.servicios.Dto.UserDto(u.username, u.lastname, u.firstname, u.dni, u.phone, u.status) " +
           "FROM User u WHERE u.dni = :dni")
    List<UserDto> findActiveUserByDni(@Param ("dni") int dni);
    
}