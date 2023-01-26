package host.luke.musicweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import host.luke.musicweb.dao.MusicMapper;
import host.luke.musicweb.pojo.Music;
import host.luke.musicweb.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
}
