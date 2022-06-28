package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.generator.entity.GenTemplateCollection;
import com.moonlit.generator.generator.entity.vo.CollectionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * 模板组Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 28/6/2022 21:17
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenTemplateCollectionMapper extends BaseMapper<GenTemplateCollection> {

    /**
     * 获取所有的模板组名称
     *
     * @return 结果集合
     */
    ArrayList<CollectionVO> getCollectionName();

}
