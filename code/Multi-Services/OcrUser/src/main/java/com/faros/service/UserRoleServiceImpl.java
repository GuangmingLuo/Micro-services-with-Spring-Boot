package com.faros.service;

import com.faros.model.UserRole;
import com.faros.model.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by guang on 2017/5/16.
 */
@Service("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void delete(long UserId) {
        Iterator<UserRole> iterator = findAll().iterator();
        while (iterator.hasNext()){
            UserRole it = iterator.next();
            if(it.getUserId()==UserId){
                userRoleRepository.delete(it);
            }
        }
    }
}
