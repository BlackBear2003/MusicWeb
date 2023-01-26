package host.luke.musicweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import host.luke.musicweb.pojo.Music;
import host.luke.musicweb.service.impl.HistoryServiceImpl;
import host.luke.musicweb.service.impl.MusicServiceImpl;
import host.luke.musicweb.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    MusicServiceImpl musicService;
    @Autowired
    HistoryServiceImpl historyService;

    @GetMapping("/download/{rid}")
    public ResponseResult downloadMusic(@PathVariable("rid")int rid, HttpServletRequest request, HttpServletResponse response){
        String playUrl = musicService.getById(rid).getPlayUrl();
        return new ResponseResult(200,"资源链接：",playUrl);
    }

    @GetMapping("")
    public ResponseResult search(String text){
        IPage<Music> musicIPage = new Page<>(1,10);
        QueryWrapper<Music> musicQueryWrapper = new QueryWrapper<>();
        musicQueryWrapper.like("music_name",text).or().like("music_artist",text).or().like("music_album",text);
        List<Music> list = musicService.list(musicQueryWrapper);
        return new ResponseResult(200,"success",list);
    }

}
