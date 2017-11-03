package cn.jzteam.swift.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 数据库基本操作。
 */
public interface BaseDao<E, Q, PK> {

    /**
     * 插入一条记录。
     *
     * @param entity
     * @return
     */
    long insert(@Param("entity") E entity);
    
    /**
     * 批量插入记录。
     * @param entityList
     * @return
     */
    long insertBatch(@Param("list") List<E> entityList);

    /**
     * 根据ID删除记录。
     *
     * @param id
     * @return
     */
    long delete(@Param("id") PK id);
    
    /**
     * 根据ids批量删除
     * @param idList
     * @return
     */
    long deleteBatch(@Param("list") List<PK> idList);

    /**
     * 根据ID更新所有不为空的字段。
     *
     * @param entity
     * @return
     */
    long update(@Param("entity") E entity);
    
    /**
     * 批量更新，根据ID
     * @param entityList
     * @return
     */
    long updateBatch(@Param("list") E entityList);
    
    /**
     * 根据条件更新entity
     * @param entity
     * @param query
     * @return
     */
    long updateSpecial(@Param("entity") E entity, @Param("query") Q query);
    
    /**
     * 根据id把field字段增加delta，限于数字类型
     * @param id
     * @param delta
     * @return
     */
    long incr(@Param("id") PK id, @Param("field") String field, @Param("delta") Object delta);
    
    /**
     * 根据id把field字段减少delta，限于数字类型
     * @param id
     * @param delta
     * @return
     */
    long decr(@Param("id") PK id, @Param("field") String field, @Param("delta") Object delta);

    /**
     * 根据ID获取指定记录。
     *
     * @param id
     * @return
     */
    E selectById(@Param("id") PK id);
    
    /**
     * 根据ids获取记录列表
     * @param idList
     * @return
     */
    List<E> selectByIds(@Param("list") List<PK> idList);

    /**
     * 根据id锁定记录，用于兼容以前处理。
     *
     * @param id
     * @return
     */
    E selectById_notry(@Param("id") PK id);

    /**
     * 根据条件获取一条记录。
     *
     * @param query
     * @return
     */
    E selectFirstOne(@Param("query") Q query);

    /**
     * 根据条件获取列表。
     *
     * @param query
     * @return
     */
    List<E> selectList(@Param("query") Q query);

    /**
     * 获取满足条件的记录数。
     *
     * @param query
     * @return
     */
    long queryPageCount(@Param("query") Q query);

    /**
     * 获取指定页码的所有记录。
     *
     * @param query
     * @return
     */
    List<E> queryPageList(@Param("query") Q query);

}
