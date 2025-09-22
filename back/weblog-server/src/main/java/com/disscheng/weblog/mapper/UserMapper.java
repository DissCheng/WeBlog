package com.disscheng.weblog.mapper;


import com.disscheng.weblog.entity.User;
import org.apache.ibatis.annotations.Insert;
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
     * 添加用户
     * @param user
     * @return 影响行数
     */
    @Insert("INSERT INTO t_user (id, username, password, create_time, update_time, is_deleted, permission) " +
            "VALUES (0, #{userName}, #{passWord}, NOW(), NOW(), false, false)")
    public int insert(User user);
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

    /**
     * 查询权限
     *
     */
    @Select("SELECT permission FROM t_user WHERE id=#{id}")
    public boolean getPermission(long id);
}
