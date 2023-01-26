package host.luke.musicweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import host.luke.musicweb.pojo.Music;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
public interface MusicMapper extends BaseMapper<Music> {
}
