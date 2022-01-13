package com.kosmo.kosmofurniture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosmo.kosmofurniture.domain.Notice;

@Mapper
public interface NoticeMapper {
    
	Long save(Notice notice);
    Notice findById(Long noticeId);
    List<Notice> findAll();
    void deleteAll();
    void setAutoIncToZero();
    void update(Notice notice);
    void deleteById(Long noticeId);
}
