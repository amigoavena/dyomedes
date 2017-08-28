package org.eightengine.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.eightengine.domain.Greeting;

@Mapper
public interface DBMapper {

	@Select("SELECT id, name, state, country FROM city WHERE state = #{state}")
    Greeting getGreeting(String id);
	
}
