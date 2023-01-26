package host.luke.musicweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import host.luke.musicweb.pojo.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
}
