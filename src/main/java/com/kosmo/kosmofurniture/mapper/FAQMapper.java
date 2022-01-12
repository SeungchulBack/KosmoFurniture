package com.kosmo.kosmofurniture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosmo.kosmofurniture.domain.FAQ;

@Mapper
public interface FAQMapper {
    
	Long save(FAQ faq);
    FAQ findById(Long faqId);
    List<FAQ> findAll();
    void deleteAll();
    void setAutoIncToZero();
    void update(FAQ faq);
    void deleteById(Long faqId);
}
