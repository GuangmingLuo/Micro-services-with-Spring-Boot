package com.faros.service;

import com.faros.entity.Menu;
import com.faros.entity.MenuRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Override
    public ArrayList<Menu> findMenuByRestaurantId(int restaurantId) {
        return menuRepository.findMenuByRestaurantId(restaurantId);
    }

    @Override
    public Menu addMenu(Menu menu) {
        int id = (int)menuRepository.count()+1;
        menu.setId(id);
        return menuRepository.save(menu);
    }
}
