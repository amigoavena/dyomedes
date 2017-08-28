package org.eightengine.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.eightengine.domain.Greeting;

@Mapper
public interface DBMapper {

	@Select("SELECT id, content FROM greetings WHERE id = #{id}")
    Greeting getGreeting(Long id);
	
}
