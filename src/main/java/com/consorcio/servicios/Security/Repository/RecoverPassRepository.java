package com.consorcio.servicios.Security.Repository;

import com.consorcio.servicios.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoverPassRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);
    Optional<User> findByResetToken(String resetToken);
    
}