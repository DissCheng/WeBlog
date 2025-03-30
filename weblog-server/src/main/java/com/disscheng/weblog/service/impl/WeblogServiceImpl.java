package com.disscheng.weblog.service.impl;


import com.disscheng.weblog.context.BaseContext;
import com.disscheng.weblog.dto.SettingDTO;
import com.disscheng.weblog.entity.Setting;
import com.disscheng.weblog.mapper.WeblogMapper;
import com.disscheng.weblog.service.WeblogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeblogServiceImpl implements WeblogService {
    @Autowired
    private WeblogMapper weblogMapper;
    /**
     * 获取网站设置详情
     * @return
     */
    public Setting getSettingDetail() {
        return weblogMapper.getSettingDetail(BaseContext.getUserId());
    }

    /**
     * 更新网站设置
     * @param settingDTO
     * @return
     */
    public int updateSetting(SettingDTO settingDTO) {
        Setting setting = new Setting();
        BeanUtils.copyProperties(settingDTO, setting);
        setting.setId(BaseContext.getUserId());
        return weblogMapper.updateSetting(setting);
    }
}
