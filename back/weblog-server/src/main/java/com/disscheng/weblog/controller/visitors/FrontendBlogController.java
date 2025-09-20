package com.disscheng.weblog.controller.visitors;


import com.disscheng.weblog.entity.Setting;
import com.disscheng.weblog.result.Result;
import com.disscheng.weblog.service.WeblogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Slf4j
public class FrontendBlogController {


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

}

