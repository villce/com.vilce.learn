package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.mapper.AdminRoleMenuMapper;
import com.vilce.springboot_vue.model.po.AdminRoleMenu;
import com.vilce.springboot_vue.model.po.AdminUserRole;
import com.vilce.springboot_vue.service.AdminRoleMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 角色菜单相关服务实现
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.AdminRoleMenuServiceImpl
 * @Author: 雷才哲
 * @Date: 2020/8/25 17:02
 * @Version: 1.0
 */
@Service
public class AdminRoleMenuServiceImpl implements AdminRoleMenuService {
    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;

    /**
     * 根据用户角色获取角色菜单
     *
     * @param userRoleList
     * @return
     */
    @Override
    public List<AdminRoleMenu> getRoleMenuByUserRole(List<AdminUserRole> userRoleList) {
        List<AdminRoleMenu> adminRoleMenuList = new LinkedList<>();
        userRoleList.forEach(userRole -> {
            List<AdminRoleMenu> adminRoleMenus = adminRoleMenuMapper.getRoleMenuAByRid(userRole.getRid());
            adminRoleMenuList.addAll(adminRoleMenus);
        });
        return adminRoleMenuList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 根据角色id获取角色菜单
     *
     * @param rid
     * @return
     */
    @Override
    public List<AdminRoleMenu> getRoleMenuByRoleId(int rid) {
        return adminRoleMenuMapper.getRoleMenuAByRid(rid);
    }

    /**
     * 更新角色菜单信息
     *
     * @param rid
     * @param menusIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        // 判断当前是否存在角色菜单信息
        List<AdminRoleMenu> roleMenuList = adminRoleMenuMapper.getRoleMenuAByRid(rid);
        if (ObjectUtils.isNotEmpty(roleMenuList)) {
            // 删除之前角色菜单信息
            if (!adminRoleMenuMapper.deleteRoleMenuByRid(rid)) {
                throw new BasicException(ResultStatus.FAIL.getStatus(), "删除角色菜单信息失败!");
            }
        }
        for (Integer mid : menusIds.get("menusIds")) {
            if (!adminRoleMenuMapper.addRoleMenu(rid, mid)) {
                throw new BasicException(ResultStatus.FAIL.getStatus(), "保存角色菜单信息失败!");
            }
        }
        return BaseResponse.buildResponse(0, "更新角色菜单信息成功！");
    }
}
