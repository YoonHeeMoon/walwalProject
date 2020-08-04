
package com.web.blog.dao.user;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
    User getUserByEmail(String email);
    User getUserByNickname(String nickname);
    User getNicknameByEmail(String email);
    User findUserByEmail(String email);
    Optional<User> findUserByEmailAndPassword(String email, String password);
    
    Optional<User> findUserByEmailAndNickname(String email, String nickname);
}
