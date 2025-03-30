package com.disscheng.weblog.service.impl;

import com.disscheng.weblog.dto.TagDTO;
import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.entity.Tag;
import com.disscheng.weblog.mapper.TagMapper;
import com.disscheng.weblog.service.TagService;
import com.disscheng.weblog.vo.TagPageQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
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

        return tagMapper.delete(id);
    }
}
