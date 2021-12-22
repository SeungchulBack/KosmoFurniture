package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MapMarker {

    private Long mapMarkerId;
    private String branchName;
    private Float latitude;
    private Float longitude;
    private String city;
}
