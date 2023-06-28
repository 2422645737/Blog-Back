package com.wanghui.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanghui.music.Constance;
import com.wanghui.music.dao.MusicMapper;
import com.wanghui.music.pojo.Music;
import com.wanghui.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;
    private final String basePath = "http://localhost:8088/";

    public List<Music> getList() {
        List<Music> music = musicMapper.selectList(new QueryWrapper<Music>());
        for (Music music1 : music) {
            packageMusic(music1);
        }
        return music;
    }

    /**
     * 获取音乐
     *
     * @param mid 音乐id
     * @return {@link Music}
     */
    @Override
    public Music getMusic(String mid) {
        Music music = musicMapper.selectById(mid);
        //需要对获取到的音乐路径进行修改
        if(music != null){
            String path = music.getPath();
            music.setPath(this.basePath + Constance.prefix + "/" + path);
        }
        return music;
    }

    @Override    //封装音乐
    public void packageMusic(Music music) {
        if(music != null){
            String path = music.getPath();
            music.setPath(this.basePath + Constance.prefix + "/" + path);
        }
    }
}
