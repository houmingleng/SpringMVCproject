package com.uplooking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uplooking.pojo.Dict;

public interface DictMapper {
	Dict findByKey(@Param("key")String key);
	List<Dict> findByType(@Param("type")String type);
}
