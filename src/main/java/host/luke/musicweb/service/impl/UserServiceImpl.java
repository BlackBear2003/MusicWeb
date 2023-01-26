package host.luke.musicweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import host.luke.musicweb.dao.UserMapper;
import host.luke.musicweb.pojo.User;
import host.luke.musicweb.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
