package com.wanghui.music.controller;

import com.wanghui.common.utils.R;
import com.wanghui.music.pojo.Music;
import com.wanghui.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping("list")           //获取音乐列表
    public R getList(){
        List<Music> list = musicService.getList();
        return R.ok().put("list",list);
    }


    @GetMapping("/{mid}")   //获取音乐
    public R getMusic(@PathVariable String mid){
        Music music = musicService.getMusic(mid);
        return R.ok().put("music",music);
    }
}
