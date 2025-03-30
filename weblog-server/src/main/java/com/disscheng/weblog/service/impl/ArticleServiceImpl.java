package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.service.ArticleService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 文章分页查询
     * @param articlePageQueryDTO
     * @return
     */
    @Override
    public ArticlePageQueryVO list(ArticlePageQueryDTO articlePageQueryDTO) {
        ArticlePageQueryVO articlePageQueryVO = new ArticlePageQueryVO();

        PageHelper.startPage(articlePageQueryDTO.getCurrent(), articlePageQueryDTO.getSize());
        Page<Article> page = articleMapper.pageQuery(articlePageQueryDTO);
        articlePageQueryVO.setData(page.getResult());
        articlePageQueryVO.setCurrent(articlePageQueryDTO.getCurrent());
        articlePageQueryVO.setSize(page.getPageSize());
        articlePageQueryVO.setTotal(page.getTotal());
        articlePageQueryVO.setPages(page.getPages());
        return articlePageQueryVO;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @Override
    public void delete(long id) {
        articleMapper.delete(id);
    }
}
