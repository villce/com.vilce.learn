//package com.vilce.security.service.Impl;
//
//import com.vilce.security.model.UserDO;
//import com.vilce.security.repository.UserRepository;
//import com.vilce.security.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @Description: Description
// * @ProjectName: com.vilce.learn
// * @Package: com.vilce.security.service.Impl.UserServiceImpl
// * @Author: 雷才哲
// * @Date: 2019/12/11 11:27
// * @Version: 1.0
// */
//@Service
//public class UserServiceImpl2 implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void insert(UserDO userDO) {
//        String username = userDO.getUsername();
//        if (exist(username)){
//            throw new RuntimeException("用户名已存在！");
//        }
//        userRepository.insert(userDO);
//    }
//
//    @Override
//    public UserDO getByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    /**
//     * 判断用户是否存在
//     */
//    private boolean exist(String username){
//        UserDO userDO = userRepository.findByUsername(username);
//        return (userDO != null);
//    }
//}
