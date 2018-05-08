package com.mapper;

import org.apache.ibatis.annotations.CacheNamespace;

import com.entity.Dict;

import tk.mybatis.mapper.common.Mapper;

@CacheNamespace(size = 512)
public interface DictMapper extends Mapper<Dict>{

	/*int deleteByPrimaryKey(Integer nId);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer nId);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);*/
	
}