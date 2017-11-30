package com.bt.shopguide.dao.mapper;

import com.bt.shopguide.dao.entity.Category;

import java.util.List;

public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORYS
     *
     * @mbggenerated Tue Oct 10 10:40:57 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORYS
     *
     * @mbggenerated Tue Oct 10 10:40:57 CST 2017
     */
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORYS
     *
     * @mbggenerated Tue Oct 10 10:40:57 CST 2017
     */
    int insertSelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORYS
     *
     * @mbggenerated Tue Oct 10 10:40:57 CST 2017
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORYS
     *
     * @mbggenerated Tue Oct 10 10:40:57 CST 2017
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CATEGORYS
     *
     * @mbggenerated Tue Oct 10 10:40:57 CST 2017
     */
    int updateByPrimaryKey(Category record);

    List<Category> getTopN(Integer n);
}