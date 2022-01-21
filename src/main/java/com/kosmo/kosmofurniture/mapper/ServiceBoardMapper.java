package com.kosmo.kosmofurniture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosmo.kosmofurniture.domain.ServiceBoard;

@Mapper
public interface ServiceBoardMapper {
    
	Long save(ServiceBoard serviceboard);
	ServiceBoard findById(Long serviceboardId);
    List<ServiceBoard> findAll();
    void deleteAll();
    void setAutoIncToZero();
    void update(ServiceBoard serviceboard);
    void deleteById(Long serviceboardId);
}
