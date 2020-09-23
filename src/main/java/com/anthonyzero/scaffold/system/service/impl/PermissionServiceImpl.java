package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.authentication.ShiroRealm;
import com.anthonyzero.scaffold.common.core.MenuTree;
import com.anthonyzero.scaffold.common.utils.TreeUtil;
import com.anthonyzero.scaffold.system.entity.Permission;
import com.anthonyzero.scaffold.system.mapper.PermissionMapper;
import com.anthonyzero.scaffold.system.service.PermissionService;
import com.anthonyzero.scaffold.system.service.RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private ShiroRealm shiroRealm;

    @Override
    public List<Permission> findUserPermissions(String username) {
        return this.baseMapper.findUserPermissions(username);
    }

    @Override
    public MenuTree<Permission> findUserMenus(String username) {
        List<Permission> menus = this.baseMapper.findUserMenus(username);
        //去重 并排序
        List<Permission> list = menus.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o-> o.getPermissionId()))), ArrayList::new))
                .stream().sorted(Comparator.comparing(Permission::getOrderNum)).collect(Collectors.toList());
        List<MenuTree<Permission>> trees = this.convertMenus(list);
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

    @Override
    @Transactional
    public void deletePermissions(String permissionIds) {
        String[] permsIds = permissionIds.split(StringPool.COMMA);
        this.delete(Arrays.asList(permsIds));

        shiroRealm.clearCache();
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

    //递归删除 菜单 以及角色菜单 信息
    private void delete(List<String> permsIds) {
        List<String> list = new ArrayList<>(permsIds);
        removeByIds(permsIds);

        //查询删除的菜单中 是否存在子孩子
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Permission::getParentId, permsIds);
        List<Permission> perm = baseMapper.selectList(queryWrapper);

        if (CollectionUtils.isNotEmpty(perm)) {
            List<String> permIdList = new ArrayList<>();
            perm.forEach(m -> permIdList.add(String.valueOf(m.getPermissionId())));
            list.addAll(permIdList);
            this.rolePermissionService.deleteRolePermsByPermId(list);
            //递归删除 子孩子
            this.delete(permIdList);
        } else {
            this.rolePermissionService.deleteRolePermsByPermId(list);
        }
    }
}
