package host.luke.musicweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Data
@TableName("t_music")
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    @JsonProperty("rid")
    private Integer musicId;
    @JsonProperty("artist")
    private String musicArtist;
    @JsonProperty("album")
    private String musicAlbum;
    @JsonProperty("duration")
    private String musicDuration;
    @JsonProperty("name")
    private String musicName;
    @JsonIgnore
    private String playUrl;
}
