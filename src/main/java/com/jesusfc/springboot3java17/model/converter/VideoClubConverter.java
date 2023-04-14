package com.jesusfc.springboot3java17.model.converter;

import com.jesusfc.springboot3java17.database.entity.VideoClubEntity;
import com.jesusfc.springboot3java17.openApi.v1.model.VideoClub;

import java.util.List;

public class VideoClubConverter implements ConverterAll<VideoClubEntity, VideoClub> {

    @Override
    public VideoClub convert(VideoClubEntity source) {
        VideoClub videoClub = new VideoClub();
        videoClub.setId(source.getId());
        videoClub.setCode(source.getCode());
        videoClub.setName(source.getName());
        videoClub.setDescription(source.getDescription());
        videoClub.setUserEntities(videoClub.getUserEntities());
        return videoClub;
    }

    @Override
    public List<VideoClub> convertList(List<VideoClubEntity> sourceList) {
        return sourceList.stream().map(this::convert).toList();
    }

    @Override
    public VideoClubEntity convertSource(VideoClub target) {
        return null;
    }

    @Override
    public List<VideoClubEntity> convertSourceList(List<VideoClub> targetList) {
        return null;
    }
}
