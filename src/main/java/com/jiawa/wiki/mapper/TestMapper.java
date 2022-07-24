package com.jiawa.wiki.mapper;

import com.jiawa.wiki.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    public List<Test> list();
}
