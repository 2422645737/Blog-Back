package com.wanghui.music.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Music {
    @TableId
    private int mid;

    private String music_name;
    private String singer;
    private float duration;
    private String surface;
    private String path;
    private String lrc;
    private boolean has_translate;       //歌词是否有翻译，默认为false，单行歌词不带翻译
}
