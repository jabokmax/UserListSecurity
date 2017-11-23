package com.phuwit.domain.service;

import com.phuwit.app.user.UserForm;
import com.phuwit.domain.entity.User;
import com.phuwit.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findOne(int id){
        return userRepository.findById(id);
    }

    public void removeUser(int id){
        userRepository.delete(id);
    }

    public Page<User> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public void update(UserForm userForm) {
        System.out.println("find id");
        User user = findOne(userForm.getId());

        System.out.println("set name");
        user.setName(userForm.getName());

        System.out.println("Save");
        userRepository.save(user);

        System.out.println("Done");
    }
}
