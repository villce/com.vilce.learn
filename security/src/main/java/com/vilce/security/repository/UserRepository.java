package com.vilce.security.repository;

import com.vilce.security.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.repository.UserRepository
 * @Author: 雷才哲
 * @Date: 2019/12/11 11:28
 * @Version: 1.0
 */
@Repository
@Component
public interface UserRepository extends CrudRepository<UserDO, Long> {
    UserDO findByUsername(String username);
}
