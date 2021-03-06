package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.MapMarker;
import com.kosmo.kosmofurniture.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMarkerMapper {

    void save(MapMarker mapMarker);
    MapMarker findByCity(String city);
    MapMarker findById(Long id);
    List<MapMarker> findAll();
    void deleteAll();
    void setAutoIncToZero();
}
