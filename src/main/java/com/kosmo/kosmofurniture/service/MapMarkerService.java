package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.MapMarker;
import com.kosmo.kosmofurniture.mapper.MapMarkerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapMarkerService {

    private final MapMarkerMapper mapMarkerMapper;

    public MapMarker createMapMarker(MapMarker mapMarker) {
        mapMarkerMapper.save(mapMarker);
        return mapMarker;
    }

    public List<MapMarker> getAllMapMarkers() {
        return mapMarkerMapper.findAll();
    }
}
