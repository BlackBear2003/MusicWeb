package host.luke.musicweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Data
@TableName("t_music")
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    @TableField("music_id")
    private Integer musicId;
    private String musicArtist;
    private String musicAlbum;
    private String musicDuration;
    private String musicName;
    @JsonIgnore
    private String playUrl;
}
