package com.vilce.security.service.Impl;

import com.vilce.security.model.UserDO;
import com.vilce.security.repository.UserRepository;
import com.vilce.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.security.service.Impl.UserServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/11 11:27
 * @Version: 1.0
 */
@Service
@Primary
@Slf4j
public class UserServiceImpl implements UserService {
    private final static Map<Integer, String> ENCODER_TYPE = new HashMap<>();
    private final static Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();
    private final static String PASSWORD_FORMAT = "{%s}%s";

    @Autowired
    private UserRepository userRepository;

    static {
        ENCODER_TYPE.put(0, "noop");
        ENCODER_TYPE.put(1, "bcrypt");
        ENCODER_TYPE.put(2, "pbkdf2");
        ENCODER_TYPE.put(3, "scrypt");
        ENCODER_TYPE.put(4, "sha256");
        ENCODER_MAP.put("noop", NoOpPasswordEncoder.getInstance());
        ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
        ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
        ENCODER_MAP.put("scrypt", new SCryptPasswordEncoder());
        ENCODER_MAP.put("sha256", new StandardPasswordEncoder());
    }

    @Override
    public void insert(UserDO userDO) {
        String username = userDO.getUsername();
        if (exist(username)) {
            throw new RuntimeException("用户名已存在！");
        }
        // 随机使用加密方式
        Random random = new Random();
        int x = random.nextInt(5);
        String encoderType = ENCODER_TYPE.get(x);
        PasswordEncoder passwordEncoder = ENCODER_MAP.get(encoderType);
        userDO.setPassword(String.format(PASSWORD_FORMAT, encoderType, passwordEncoder.encode(userDO.getPassword())));
        userRepository.save(userDO);
    }

    @Override
    public UserDO getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 判断用户是否存在
     */
    private boolean exist(String username) {
        UserDO userDO = userRepository.findByUsername(username);
        return (userDO != null);
    }
}
