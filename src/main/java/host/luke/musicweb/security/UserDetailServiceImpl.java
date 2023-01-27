package host.luke.musicweb.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import host.luke.musicweb.dao.UserMapper;
import host.luke.musicweb.pojo.User;
import host.luke.musicweb.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserServiceImpl userService;
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userService.getOne(queryWrapper);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(Objects.isNull(user)){
            throw new RuntimeException("查无此用户名");
        }

        List<String> list = userMapper.getAuthByUid(user.getUserId());

        return new LoginUser(user,list);
    }
}
