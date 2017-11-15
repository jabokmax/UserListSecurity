package com.phuwit.domain.service;

import com.phuwit.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService {
    User findOneByEmail(String username);
}
