package com.disscheng.weblog.mapper;


import com.disscheng.weblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM t_user WHERE username = #{username}")
    public User selectByUsername(String username);

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    public User selectById(long id);

    /**
     * 根据用户id更新用户信息
     * @param user 用户信息
     * @return 影响行数
     */
    @Update("UPDATE t_user SET username = #{userName}, password = #{passWord} WHERE id = #{id}")
    public boolean updateById(User user);
}
