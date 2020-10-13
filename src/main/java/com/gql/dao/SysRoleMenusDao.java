package com.gql.dao;

import com.gql.entity.SysMenusEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gql
 * @date 2020/8/13
 **/
@Repository
public interface SysRoleMenusDao {


     public List<SysMenusEntity> getRoleMenus(String username);

}
