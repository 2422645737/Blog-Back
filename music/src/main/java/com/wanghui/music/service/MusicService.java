package com.wanghui.music.service;

import com.wanghui.music.pojo.Music;

import java.util.List;

public interface MusicService {
    List<Music> getList();

    Music getMusic(String mid);

    void packageMusic(Music music);
}
