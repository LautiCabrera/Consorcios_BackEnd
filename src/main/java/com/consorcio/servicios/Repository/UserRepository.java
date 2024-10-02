package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.consorcio.servicios.Dto.UserDto(u.idUser, u.username, u.lastName, u.firstName, u.dni, u.phone, u.status) "
            + "FROM User u")
    List<UserDto> findAllUsers();

}
