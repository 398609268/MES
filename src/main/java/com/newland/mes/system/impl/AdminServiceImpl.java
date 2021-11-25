package com.newland.mes.system.impl;

import com.newland.mes.system.entity.User;
import com.newland.mes.system.mapper.UserMapper;
import com.newland.mes.system.service.AdminService;
import com.newland.mes.system.util.JwtTokenUtil;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserMapper userMapper;
    @Value("jwt.tokenHead")
    private String tokenHead;
    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Object> login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        if(null==userDetails || passwordEncoder.matches(password,userDetails.getPassword())){
            return Result.failure("用户名或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return Result.failure("账号被禁用");
        }
        //更新sercuity登陆用户对象
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,
                null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token=jwtTokenUtil.generateToken(userDetails);

        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return ResponseEntity.ok(tokenMap);

    }

    @Override
    public User getUserByUserName(String username) {
        User user=userMapper.getUserByUsername(username);

        return user;
    }
}
