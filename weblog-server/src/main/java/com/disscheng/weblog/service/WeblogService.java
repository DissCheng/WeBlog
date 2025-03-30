package com.disscheng.weblog.service;

import com.disscheng.weblog.dto.SettingDTO;
import com.disscheng.weblog.entity.Setting;

public interface WeblogService {

    /**
     * 获取设置信息
     * @return 设置信息
     */
    public Setting getSettingDetail();

    /**
     * 更新设置信息
     * @param settingDTO 新的设置信息
     */
    public int updateSetting(SettingDTO settingDTO);
}
