package com.faros.service;

import com.faros.model.UserRole;

import java.util.List;

/**
 * Created by guang on 2017/5/16.
 */
public interface UserRoleService {
    List<UserRole> findAll();
    UserRole save(UserRole userRole);
    void delete(long UserId);
}
