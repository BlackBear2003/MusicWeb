package host.luke.musicweb.controller;

import host.luke.musicweb.crawler.Crawler;
import host.luke.musicweb.dao.MusicMapper;
import host.luke.musicweb.pojo.Music;
import host.luke.musicweb.service.impl.MusicServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {
    @Resource
    Crawler crawler;
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    MusicServiceImpl musicService;
    @GetMapping("/testget")
    public void doGet() throws IOException, InterruptedException {
        for (int i = 1; i < 100000000; i++) {
            int mid = (int) (Math.random()*10000000);
            if(mid%10==0){
                Thread.sleep(1500);
            }
            Music music = crawler.doCraw(mid);
            System.out.println(music);
            if(music==null){
                continue;
            }
            try {
                musicService.saveOrUpdate(music);
            }
            catch (Exception e){
                e.printStackTrace();
                //wusuowei~~~~~~~
            }
            System.out.println(mid);
        }
    }
}
