package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.core.MenuTree;
import com.anthonyzero.scaffold.common.utils.TreeUtil;
import com.anthonyzero.scaffold.system.entity.Permission;
import com.anthonyzero.scaffold.system.mapper.PermissionMapper;
import com.anthonyzero.scaffold.system.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findUserPermissions(String username) {
        return this.baseMapper.findUserPermissions(username);
    }

    @Override
    public MenuTree<Permission> findUserMenus(String username) {
        List<Permission> menus = this.baseMapper.findUserMenus(username);
        List<MenuTree<Permission>> trees = this.convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public MenuTree<Permission> findMenus(String menuName) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(menuName)) {
            queryWrapper.lambda().like(Permission::getName, menuName);
        }
        queryWrapper.lambda().orderByAsc(Permission::getOrderNum);
        List<Permission> menus = this.baseMapper.selectList(queryWrapper);
        List<MenuTree<Permission>> trees = this.convertMenus(menus);

        return TreeUtil.buildMenuTree(trees);
    }

    private List<MenuTree<Permission>> convertMenus(List<Permission> menus) {
        List<MenuTree<Permission>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<Permission> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getPermissionId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getName());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getUrl());
            tree.setData(menu);
            trees.add(tree);
        });
        return trees;
    }
}
