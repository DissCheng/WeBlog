package com.disscheng.weblog.mapper;


import com.disscheng.weblog.entity.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WeblogMapper {

    /**
     * 查询设置信息
     * @return
     */
    @Select("SELECT * FROM t_blog_settings WHERE id = #{id}")
    Setting getSettingDetail(Long id);
    /**
     * 更新设置信息
     * @param setting
     * @return
     */
    @Update("UPDATE t_blog_settings SET logo = #{logo}, name = #{name}, author = #{author}, " +
            "introduction = #{introduction}, avatar = #{avatar}, github_homepage = #{githubHomepage}," +
            " csdn_homepage = #{csdnHomepage}, gitee_homepage = #{giteeHomepage}, zhihu_homepage = #{zhihuHomepage} " +
            "WHERE id = #{id}")
    int updateSetting(Setting setting);
}
