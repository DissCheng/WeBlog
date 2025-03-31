package com.disscheng.weblog.service;


import com.disscheng.weblog.dto.ArticlePageQueryByCategoryDTO;
import com.disscheng.weblog.dto.TagDTO;
import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.entity.Tag;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
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

    /**
     * 模糊查询标签
     * @param name
     * @return List<Tag>
     */
    List<Tag> search(String name);

    /**
     * 返回所有标签
     *
     * @return Tag
     */
    List<Tag> getAll();


    /**
     * 根据标签获取文章
     * @param articlePageQueryByCategoryDTO
     * @return
     */
    ArticlePageQueryVO getArticlesByTag(ArticlePageQueryByCategoryDTO articlePageQueryByCategoryDTO);
}
