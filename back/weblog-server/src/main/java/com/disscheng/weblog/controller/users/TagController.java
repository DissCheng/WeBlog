package com.disscheng.weblog.controller.users;

import com.disscheng.weblog.dto.TagDTO;
import com.disscheng.weblog.dto.TagPageQueryDTO;
import com.disscheng.weblog.dto.TagSearchDTO;
import com.disscheng.weblog.entity.Tag;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.TagService;
import com.disscheng.weblog.vo.TagPageQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/tag")
@Slf4j
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 分页查询标签列表
     * @param tagPageQueryDTO 标签分页查询条件
     * @return 标签列表
     */
    @PostMapping("/list")
    public Result<TagPageQueryVO> list(@RequestBody TagPageQueryDTO tagPageQueryDTO) {
       return Result.success(tagService.list(tagPageQueryDTO));
    }

    /**
     * 新增标签
     * @param tagDTO 标签信息
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody TagDTO tagDTO) {
        tagService.add(tagDTO);
        return Result.success();
    }

    /**
     * 删除标签
     * @param id 标签ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable("id") String id) {
        tagService.delete(Long.parseLong(id));
        return Result.success();
    }

    /**
     * 搜索标签
     * @param tagSearchDTO 标签名称
     * @return 标签列表
     */
    @PostMapping("/search")
    public Result<List<Tag>> search(@RequestBody TagSearchDTO tagSearchDTO) {
        log.info("search tag name: " + tagSearchDTO.getQuery());
        return Result.success(tagService.search(tagSearchDTO.getQuery()));
    }
    /**
     * 获取标签
     *
     *
     *
     */

    @GetMapping("/select/list")
    public Result<List<Tag>> get() {
        return Result.success(tagService.getAll());
    }
}
