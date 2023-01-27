package host.luke.musicweb.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_history")
public class History {
    @TableId
    private Integer historyId;
    private Integer userId;
    private Integer musicId;
    private Integer fav;
}
