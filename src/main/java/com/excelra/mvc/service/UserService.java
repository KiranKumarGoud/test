package com.excelra.mvc.service;

import com.excelra.mvc.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
