package com.cinemas.service.impl;

import com.cinemas.Utils.ObjectUtils;
import com.cinemas.dto.request.UserRequest;
import com.cinemas.dto.response.UserResponse;
import com.cinemas.entities.MovieGenre;
import com.cinemas.entities.User;
import com.cinemas.exception.AppException;
import com.cinemas.repositories.UserRepository;
import com.cinemas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.cinemas.exception.ErrorCode.NAME_EXISTED;
import static com.cinemas.exception.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            }
        };
    }

    @Override
    public boolean addUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new AppException(NAME_EXISTED);
        }

        User user = new User();

        ObjectUtils.copyFields(userRequest, user);
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public UserResponse getEditUserById(int id) {
        User user = userRepository.findById(id).get();

        if (user == null) throw new AppException(NOT_FOUND);

        UserResponse userResponse = new UserResponse();
        ObjectUtils.copyFields(user, userResponse);

        return userResponse;
    }

    @Override
    public boolean updateUser(UserRequest userRequest) {
        User user = userRepository
                .findById(userRequest.getId())
                .orElseThrow(() -> new AppException(NOT_FOUND));

        if (userRepository.findByEmailWithId(userRequest.getEmail(), userRequest.getId()) != null) {
            throw new AppException(NAME_EXISTED);
        }

//        ObjectUtils.copyFields(userRequest, user);
        if(userRequest.getName() != null){
            user.setName(userRequest.getName());
        }

        if(userRequest.getEmail() != null){
            user.setEmail(userRequest.getEmail());
        }

        if(userRequest.getPassword() != null){
            user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        }

        if(userRequest.getPhone() != null){
            user.setPhone(userRequest.getPhone());
        }

        if(userRequest.getDOB() != null){
            user.setDOB(userRequest.getDOB());
        }

        if(userRequest.getGender() != null){
            user.setGender(userRequest.getGender());
        }

        if(userRequest.getRole() != null){
            user.setRole(userRequest.getRole());
        }
        userRepository.save(user);

        return true;
    }


}
