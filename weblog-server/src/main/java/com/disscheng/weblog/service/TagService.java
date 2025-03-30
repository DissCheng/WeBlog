package com.disscheng.weblog.service;


import com.disscheng.weblog.dto.TagDTO;
import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.entity.Tag;
import com.disscheng.weblog.vo.TagPageQueryVO;

import java.util.List;

public interface TagService {
    /**
     * 分页查询标签列表
     * @param tagPageQueryDTO
     * @return TagPageQueryVO
     */
    TagPageQueryVO list(TagPageQueryDTO tagPageQueryDTO);

    /**
     * 新增标签
     * @param tagDTO
     * @return Tag
     */
    int add(TagDTO tagDTO);

    /**
     * 删除标签
     * @param id
     * @return boolean
     */
    int delete(long id);

}
