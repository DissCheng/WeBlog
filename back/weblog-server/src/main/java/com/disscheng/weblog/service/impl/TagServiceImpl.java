package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.constant.MessageConstant;
import com.disscheng.weblog.dto.ArticlePageQueryByCategoryDTO;
import com.disscheng.weblog.dto.TagDTO;
import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.entity.Tag;
import com.disscheng.weblog.exception.ArticleDeleteException;
import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.mapper.TagMapper;
import com.disscheng.weblog.service.TagService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.disscheng.weblog.vo.TagPageQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 分页查询标签
     * @param tagPageQueryDTO
     * @return
     */

    public TagPageQueryVO list(TagPageQueryDTO tagPageQueryDTO) {
        PageHelper.startPage(tagPageQueryDTO.getCurrent(), tagPageQueryDTO.getSize());
        Page<Tag> page = tagMapper.pageQuery(tagPageQueryDTO);
        TagPageQueryVO tagPageQueryVO = new TagPageQueryVO();
        tagPageQueryVO.setTotal(page.getTotal());
        tagPageQueryVO.setCurrent(tagPageQueryDTO.getCurrent());
        tagPageQueryVO.setSize(page.getPageSize());
        tagPageQueryVO.setData(page.getResult());
        return tagPageQueryVO;
    }

    /**
     * 新增标签
     * @param tagDTO
     * @return
     */
    public int add(TagDTO tagDTO) {
        log.info("新增标签：{}", tagDTO.getTags());
        for(String name : tagDTO.getTags())
        {
            Tag tag = new Tag();
            tag.setName(String.valueOf(name));
            tag.setCreateTime(LocalDateTime.now());
            tag.setUpdateTime(LocalDateTime.now());
            tag.setDeleted(false);
            tagMapper.add(tag);
        }
        return 1;
    }


    /**
     * 删除标签
     * @param id
     * @return
     */
    public int delete(long id) {
        if(tagMapper.getArticles(id)>0)
        {
            throw new ArticleDeleteException(MessageConstant.ARTICLE_DELETE_TAG_EXIST);
        }
        return tagMapper.delete(id);
    }

    /**
     * 搜索标签
     * @param name
     * @return
     */
    public List<Tag> search(String name) {
        return tagMapper.selectByName(name);
    }

    /**
     * 获取所有标签
     * @return
     */
    public List<Tag> getAll() {
        return tagMapper.selectAll();
    }

    /**
     * 根据标签获取文章
     * @param articlePageQueryByCategoryDTO
     * @return
     */
    public ArticlePageQueryVO getArticlesByTag(ArticlePageQueryByCategoryDTO articlePageQueryByCategoryDTO) {
        PageHelper.startPage(articlePageQueryByCategoryDTO.getCurrent(), articlePageQueryByCategoryDTO.getSize());
        Page<Long> articleIdList = tagMapper.getArticleIdListByTagId(articlePageQueryByCategoryDTO.getId());
        List<Article> articleList = new ArrayList<>();
        log.info(articleIdList.toString());
        for (Long articleId : articleIdList) {
            Article article = articleMapper.getArticle(articleId);
            if (article != null) {
                articleList.add(article);
            }
        }
        log.info("articleList:{}", articleList);
        ArticlePageQueryVO articlePageQueryVO = ArticlePageQueryVO.builder()
                .current(articlePageQueryByCategoryDTO.getCurrent())
                .size(articleIdList.getPageSize())
                .total(articleIdList.getTotal())
                .pages(articleIdList.getPages())
                .data(articleList)
                .build();
        return articlePageQueryVO;
    }
}
