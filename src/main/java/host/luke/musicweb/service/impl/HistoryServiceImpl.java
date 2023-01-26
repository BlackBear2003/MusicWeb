package host.luke.musicweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import host.luke.musicweb.dao.HistoryMapper;
import host.luke.musicweb.pojo.History;
import host.luke.musicweb.service.HistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
}
