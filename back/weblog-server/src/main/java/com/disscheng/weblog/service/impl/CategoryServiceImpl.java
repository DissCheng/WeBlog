package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.constant.MessageConstant;
import com.disscheng.weblog.dto.ArticlePageQueryByCategoryDTO;
import com.disscheng.weblog.dto.ArticlePageQueryDTO;
import com.disscheng.weblog.dto.CategoryDTO;
import com.disscheng.weblog.dto.CategoryPageQueryDTO;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.entity.Category;
import com.disscheng.weblog.exception.ArticleDeleteException;
import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.mapper.CategoryMapper;
import com.disscheng.weblog.service.CategoryService;
import com.disscheng.weblog.vo.ArticlePageQueryVO;
import com.disscheng.weblog.vo.CategoryPageQueryVO;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 分页查询分类列表
     * @param categoryPageQueryDTO
     * @return
     */
    public CategoryPageQueryVO list(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getCurrent(), categoryPageQueryDTO.getSize());
        Page<Category> categoryPage = categoryMapper.pageQuery(categoryPageQueryDTO);
        CategoryPageQueryVO categoryPageQueryVO = new CategoryPageQueryVO();
        categoryPageQueryVO.setTotal(categoryPage.getTotal());
        categoryPageQueryVO.setCurrent(categoryPageQueryDTO.getCurrent());
        categoryPageQueryVO.setSize(categoryPage.getPageSize());
        categoryPageQueryVO.setPages(categoryPage.getPages());
        categoryPageQueryVO.setData(categoryPage.getResult());
        return categoryPageQueryVO;
    }
    /**
     * 查询所有分类
     * @return
     */
    public List<Category> listAll() {
        return categoryMapper.getAll();
    }
    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    public Category add(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setDeleted(false);
        categoryMapper.add(category);
        return category;
    }
    /**
     * 删除分类
     * @param id
     */
    public void delete(Long id) {
        if(categoryMapper.getArticles(id) > 0){
            throw new ArticleDeleteException(MessageConstant.ARTICLE_DELETE_CATEGORY_EXIST);
        }
        categoryMapper.delete(id);
    }

    /**
     * 根据分类查询文章列表
     * @param articlePageQueryByCategoryDTO
     * @return
     */
    public ArticlePageQueryVO getArticlesByCategory(ArticlePageQueryByCategoryDTO articlePageQueryByCategoryDTO) {
        PageHelper.startPage(articlePageQueryByCategoryDTO.getCurrent(), articlePageQueryByCategoryDTO.getSize());
        Page<Long> articleIdList = categoryMapper.getArticleIdListByCategoryId(articlePageQueryByCategoryDTO.getId());
        List<Article> articleList = new ArrayList<>();
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
