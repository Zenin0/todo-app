package com.zenin.todoapp.service.user;

import com.zenin.todoapp.exception.BaseApplicationException;
import com.zenin.todoapp.exception.user.UserException;
import com.zenin.todoapp.model.User;
import com.zenin.todoapp.repository.UserRepository;
import com.zenin.todoapp.utils.password.PasswordUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) throws BaseApplicationException {
        try {
            Optional<User> user = userRepository.getUserById(id);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new BaseApplicationException("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new UserException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public User createUser(User user) throws BaseApplicationException {
        try {
            user.setPassword(PasswordUtils.encodePassword(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserException("User already exists", e, HttpStatus.CONFLICT);
        } catch (Exception e) {
            throw new UserException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
