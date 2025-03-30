package com.disscheng.weblog.controller;


import com.disscheng.weblog.context.BaseContext;
import com.disscheng.weblog.dto.SettingDTO;
import com.disscheng.weblog.entity.Setting;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.WeblogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/blog")
@Slf4j
public class BlogController {


    @Autowired
    private WeblogService weblogService;

    /**
     * 获取设置信息
     * @return
     */
    @GetMapping("/settings/detail")
    public Result<Setting> detail() {
        log.info("获取设置信息");
        return Result.success(weblogService.getSettingDetail());
    }

    /**
     * 更新设置信息
     * @param settingDTO
     * @return
     */
    @PostMapping("/settings/update")
    public Result<String> update(@RequestBody SettingDTO settingDTO) {
        log.info("更新设置信息"+BaseContext.getUserId());
        log.info("更新设置信息"+settingDTO.toString());
        weblogService.updateSetting(settingDTO);
        log.info("更新设置信息成功");
        return Result.success("");
    }
}

