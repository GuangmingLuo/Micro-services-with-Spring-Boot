package com.faros.service;

import com.faros.entity.Menu;
import com.faros.entity.MenuRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guang on 2017/4/25.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Override
    public List<Menu> findMenuByRestaurantId(int restaurantId) {
        return menuRepository.findMenuByRestaurantId(restaurantId);
    }

    @Override
    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }
}
