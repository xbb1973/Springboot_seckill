package slf.xbb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import slf.xbb.domain.UserDo;
import slf.xbb.domain.UserDoExample;

public interface UserDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    long countByExample(UserDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int deleteByExample(UserDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int insert(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int insertSelective(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    List<UserDo> selectByExample(UserDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    UserDo selectByPrimaryKey(Integer id);

    /**
     * 通过telphone获取user_info
     * @param telphone
     * @return
     */
    UserDo selectByTelphone(String telphone);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserDo record, @Param("example") UserDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByExample(@Param("record") UserDo record, @Param("example") UserDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByPrimaryKeySelective(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByPrimaryKey(UserDo record);
}