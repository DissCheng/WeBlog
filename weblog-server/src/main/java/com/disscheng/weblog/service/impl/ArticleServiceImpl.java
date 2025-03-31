package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.dto.*;
import com.disscheng.weblog.entity.Article;
import com.disscheng.weblog.entity.ArticleCategory;
import com.disscheng.weblog.entity.ArticleContent;
import com.disscheng.weblog.entity.ArticleTag;
import com.disscheng.weblog.mapper.ArticleMapper;
import com.disscheng.weblog.mapper.CategoryMapper;
import com.disscheng.weblog.mapper.TagMapper;
import com.disscheng.weblog.service.ArticleService;
import com.disscheng.weblog.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;

    /**
     * 文章分页查询
     *
     * @param articlePageQueryDTO
     * @return
     */
    @Override
    public ArticlePageQueryVO list(ArticlePageQueryDTO articlePageQueryDTO) {

        log.info("articlePageQueryDTO:{}", articlePageQueryDTO);
        PageHelper.startPage(articlePageQueryDTO.getCurrent(), articlePageQueryDTO.getSize());
        Page<Article> page = articleMapper.pageQuery(articlePageQueryDTO);
        ArticlePageQueryVO articlePageQueryVO = ArticlePageQueryVO.builder()
                .data(page.getResult())
                .current(articlePageQueryDTO.getCurrent())
                .size(page.getPageSize())
                .total(page.getTotal())
                .pages(page.getPages())
                .build();
        log.info("articlePageQueryVO:{}", articlePageQueryVO);
        return articlePageQueryVO;
    }


    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @Override
    public void delete(long id) {
        articleMapper.delete(id);
        articleMapper.deleteContent(id);
        articleMapper.deleteCategory(id);
        articleMapper.deleteTag(id);
    }

    /**
     * 发布文章
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int publish(ArticleDTO articleDTO) {
        log.info("publish article, articleDTO: {}", articleDTO);
        Article article = Article.builder()
                .id(0L)
                .title(articleDTO.getTitle())
                .cover(articleDTO.getCover())
                .summary(articleDTO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(0)
                .readNum(0)
                .build();
        articleMapper.insert(article);
        log.info("publish article success, id: {}", article.getId());
        ArticleContent articleContent = ArticleContent.builder()
                .id(0L)
                .articleId(article.getId())
                .content(articleDTO.getContent())
                .build();
        articleMapper.insertContent(articleContent);
        ArticleCategory articleCategory = ArticleCategory.builder()
                .id(0L)
                .articleId(article.getId())
                .categoryId(articleDTO.getCategoryId())
                .build();
        articleMapper.insertCategory(articleCategory);
        for (Long tagId : articleDTO.getTags()) {
            ArticleTag articleTag = ArticleTag.builder()
                    .id(0L)
                    .articleId(article.getId())
                    .tagId(tagId)
                    .build();
            articleMapper.insertTag(articleTag);
        }
        return article.getId().intValue();
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @Override

    public ArticleVO getArticleDetail(long id) {
        Article article = articleMapper.getArticle(id);
        String articleContent = articleMapper.getArticleContent(id);
        long articleCategoryId = articleMapper.getArticleCategory(id);
        List<Long> articleTag = articleMapper.getArticleTag(id);
        String articleCategoryName = categoryMapper.getName(articleCategoryId);
        ArticleVO articleVO = ArticleVO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .cover(article.getCover())
                .content(articleContent)
                .summary(article.getSummary())
                .categoryId(articleCategoryId)
                .categoryName(articleCategoryName)
                .tagIds(articleTag)
                .build();
        return articleVO;
    }

    /**
     * 更新文章
     *
     * @param articleUpdateDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(ArticleUpdateDTO articleUpdateDTO) {

        articleMapper.delete(articleUpdateDTO.getId());
        Article article = Article.builder()
                .id(0L)
                .title(articleUpdateDTO.getTitle())
                .cover(articleUpdateDTO.getCover())
                .summary(articleUpdateDTO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(0)
                .readNum(0)
                .build();
        articleMapper.insert(article);
        ArticleContent articleContent = ArticleContent.builder()
                .id(0L)
                .articleId(article.getId())
                .content(articleUpdateDTO.getContent())
                .build();
        articleMapper.insertContent(articleContent);
        ArticleCategory articleCategory = ArticleCategory.builder()
                .id(0L)
                .articleId(article.getId())
                .categoryId(articleUpdateDTO.getCategoryId())
                .build();
        articleMapper.insertCategory(articleCategory);
        for (Long tagId : articleUpdateDTO.getTags()) {
            ArticleTag articleTag = ArticleTag.builder()
                    .id(0L)
                    .articleId(article.getId())
                    .tagId(tagId)
                    .build();
            articleMapper.insertTag(articleTag);
        }
    }

    /**
     * 分页查询文章(前台展示)
     *
     * @param articlePageQueryDTO
     * @return
     */

    public TagDTO.ArticleFrontendPageQueryVO listFrontend(ArticlePageQueryDTO articlePageQueryDTO) {
        TagDTO.ArticleFrontendPageQueryVO articlePageQueryVO = new TagDTO.ArticleFrontendPageQueryVO();
        List<ArticleFrontendVO> articleFrontendVO = new ArrayList<ArticleFrontendVO>();
        log.info("articlePageQueryDTO:{}", articlePageQueryDTO);
        PageHelper.startPage(articlePageQueryDTO.getCurrent(), articlePageQueryDTO.getSize());
        Page<Article> page = articleMapper.pageQuery(articlePageQueryDTO);
        List<Article> articles = page.getResult();// 文章列表
        for (Article article : articles) {
            articleFrontendVO.add(ArticleFrontendVO.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .cover(article.getCover())
                    .summary(article.getSummary())
                    .createTime(article.getCreateTime())
                    .category(categoryMapper.getCategoryByArticleId(article.getId()))
                    .tags(tagMapper.getTagsByArticleId(article.getId())).build());
        }
        articlePageQueryVO.setData(articleFrontendVO);
        articlePageQueryVO.setCurrent(articlePageQueryDTO.getCurrent());
        articlePageQueryVO.setSize(page.getPageSize());
        articlePageQueryVO.setTotal(page.getTotal());
        articlePageQueryVO.setPages(page.getPages());
        log.info("articlePageQueryVO:{}", articlePageQueryVO);

        return articlePageQueryVO;
    }

    /**
     * 文章归档
     *
     * @return
     */
    public ArticleArchivePageQueryVO archiveList(ArticlePageQueryDTO articlePageQueryDTO){
        PageHelper.startPage(articlePageQueryDTO.getCurrent(), articlePageQueryDTO.getSize());
        Page<Article> list = articleMapper.pageQuery(articlePageQueryDTO);
        List<Article> articles = list.getResult();
        // 使用Stream API按月份分组
        Map<String, List<Article>> groupedArticles = articles.stream()
                .collect(Collectors.groupingBy(article -> {
                    // 将createTime转换为LocalDate
                    LocalDate date = article.getCreateTime().atZone(ZoneId.systemDefault()).toLocalDate();
                    // 按年月格式化
                    return date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
                }));

        // 将分组结果转换为List<ArticleArchivePageQueryDTO>
        List<ArticleArchivePageQueryDTO> result = new ArrayList<>();
        groupedArticles.forEach((monthStr, articleList) -> {
            // 直接使用monthStr作为month字段的值
            ArticleArchivePageQueryDTO dto = ArticleArchivePageQueryDTO.builder()
                    .month(monthStr) // 存储为"yyyy-MM"格式的字符串
                    .articles(articleList)
                    .build();
            // 添加到结果列表
            result.add(dto);
        });

        return ArticleArchivePageQueryVO.builder()
                .data(result)
                .current(articlePageQueryDTO.getCurrent())
                .size(list.getPageSize())
                .total(list.getTotal())
                .pages(list.getPages())
                .build();
    }

}
