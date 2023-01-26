package host.luke.musicweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import host.luke.musicweb.pojo.History;
import host.luke.musicweb.pojo.Music;
import host.luke.musicweb.service.impl.HistoryServiceImpl;
import host.luke.musicweb.service.impl.MusicServiceImpl;
import host.luke.musicweb.utils.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    HistoryServiceImpl historyService;
    @Resource
    MusicServiceImpl musicService;

    @GetMapping("/history")
    public ResponseResult getUserHistoryRecords(int page){

        //TODO: 完善安全框架部分，这边先用硬编码测试
        int uid = 1;

        /**
         *  response data structure
         *  code
         *  msg
         *  data
         *      list
         *          name
         *          artist
         *          album
         *          duration
         *          fav
         *          rid(music_id)
         *          id(history_id)
         *      count
         */
        List<Map<String, Object>> mapList = new ArrayList<>();

        Page<History> hisPage = new Page<>(page,10,true);
        QueryWrapper<History> midQW = new QueryWrapper<>();
        midQW.eq("user_id",uid);
        IPage<History> historyIPage = historyService.page(hisPage,midQW);
        int count = (int) historyIPage.getPages();
        List<History> hisList = historyIPage.getRecords();
        for (History history:
             hisList) {
            Music music = musicService.getById(history.getMusicId());
            Map<String, Object> map = new HashMap<>();
            map.put("name",music.getMusicName());
            map.put("artist",music.getMusicArtist());
            map.put("album",music.getMusicAlbum());
            //TODO: 把秒数表示为 |分：秒| 形式
            map.put("duration",music.getMusicDuration());
            map.put("fav",history.getFav());
            map.put("rid",music.getMusicId());
            map.put("id",history.getHistoryId());
            mapList.add(map);
        }
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("list",mapList);
        dataMap.put("count",count);
        return new ResponseResult(200,"success",dataMap);
    }

    @PutMapping("/history/lc")
    public ResponseResult updateFav(Integer id,Integer fav){
        History history = historyService.getById(id);
        history.setFav(fav);
        historyService.updateById(history);
        Music music = musicService.getById(history.getMusicId());
        return new ResponseResult(200,"message",music);
    }

    @DeleteMapping("/history")
    public ResponseResult dropHistoryRecords(@RequestParam("type")Integer type,@RequestParam("id")Integer id,@RequestParam("list")List<Integer> list){
        if(type == 1){
            if(historyService.removeByIds(list)){
                return new ResponseResult(200,"success");
            }
            else{
                return new ResponseResult(-1,"failed");
            }
        }
        else if(type == 2){
            if(historyService.removeById(id)){
                return new ResponseResult(200,"success");
            }
            else{
                return new ResponseResult(-1,"failed");
            }
        }
        else{
            return new ResponseResult(403,"bad request");
        }
    }

}
