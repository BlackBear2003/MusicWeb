package host.luke.musicweb.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import host.luke.musicweb.pojo.Music;
import host.luke.musicweb.utils.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Crawler {
    public Music doCraw(int mid) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String infoUrl = "http://www.kuwo.cn/api/www/music/musicInfo?mid="+mid+"&httpsStatus=1&reqId=1fb65040-9c72-11ed-bd80-99d3aad8d40d";
        String playUrl = "http://www.kuwo.cn/api/v1/www/music/playUrl?mid="+mid+"&type=music&httpsStatus=1&reqId=1fb65041-9c72-11ed-bd80-99d3aad8d40d";
        HttpGet infoGet = new HttpGet(infoUrl);
        infoGet.addHeader("Accept","application/json, text/plain, */*");
        infoGet.addHeader("Accept-Encoding","gzip, deflate");
        infoGet.addHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        infoGet.addHeader("Connection","keep-alive");
        infoGet.addHeader("Cookie","_ga=GA1.2.1170549960.1674543133; _gid=GA1.2.1260823527.1674543133; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1674543129,1674614942; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1674617657; _gat=1; kw_token=AYDVK7O34F");
        infoGet.addHeader("csrf","AYDVK7O34F");
        infoGet.addHeader("Host","www.kuwo.cn");
        infoGet.addHeader("Referer","http://www.kuwo.cn/search/list?key=%E5%B2%B8%E9%83%A8%E7%9C%9F%E6%98%8E");
        infoGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.61");
        HttpGet playGet = new HttpGet(playUrl);
        playGet.addHeader("Accept","application/json, text/plain, */*");
        playGet.addHeader("Accept-Encoding","gzip, deflate");
        playGet.addHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        playGet.addHeader("Connection","keep-alive");
        playGet.addHeader("Cookie","_ga=GA1.2.1170549960.1674543133; _gid=GA1.2.1260823527.1674543133; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1674543129,1674614942; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1674617657; _gat=1; kw_token=AYDVK7O34F");
        playGet.addHeader("csrf","AYDVK7O34F");
        playGet.addHeader("Host","www.kuwo.cn");
        playGet.addHeader("Referer","http://www.kuwo.cn/search/list?key=%E5%B2%B8%E9%83%A8%E7%9C%9F%E6%98%8E");
        playGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.61");

        HttpResponse infoRes = httpclient.execute(infoGet);
        HttpEntity infoEntity = infoRes.getEntity();
        HttpResponse playRes = httpclient.execute(playGet);
        HttpEntity playEntity = playRes.getEntity();
        String infoStr = EntityUtils.toString(infoEntity);
        String playStr = EntityUtils.toString(playEntity);
        System.out.println(infoStr);
        System.out.println(playStr);

        Music music = new Music();
        int infoCode,playCode;
        if(infoStr==null) return null;
        if(playStr==null) return null;
        try {
            JSONObject infoJson = JSONObject.parseObject(infoStr);
            JSONObject playJson = JSONObject.parseObject(playStr);
            infoCode = infoJson.getInteger("code");
            if(infoCode!=200){
                httpclient.close();
                return null;
            }
            else{
                JSONObject data = infoJson.getJSONObject("data");
                music.setMusicArtist(data.getString("artist"));
                music.setMusicId(data.getInteger("rid"));
                music.setMusicDuration(data.getString("duration"));
                music.setMusicAlbum(data.getString("album"));
                music.setMusicName(data.getString("name"));
                System.out.println(music);


                playCode = playJson.getInteger("code");
                if(playCode!=200){
                    httpclient.close();
                    return music;
                }
                else{
                    music.setPlayUrl(playJson.getJSONObject("data").getString("url"));
                    System.out.println(music);
                    httpclient.close();
                    return music;
                }
            }

        }catch (Exception e){

            e.printStackTrace();
            return null;
        }



    }

}
