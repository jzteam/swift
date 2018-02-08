package cn.jzteam.swift.service;

import cn.jzteam.swift.query.PageResult;
import cn.jzteam.swift.query.QueryCondition;

import java.util.List;

public interface BaseService<E, PK> {

    /**
     * 插入一条记录。
     *
     * @param entity
     * @return id
     */
    PK insert(E entity);

    /**
     * 批量插入记录。
     * @param entityList
     * @return 数量
     */
    long insertBatch(List<E> entityList);

    /**
     * 根据ID删除记录。
     *
     * @param id
     * @return 数量
     */
    long delete(PK id);

    /**
     * 根据ids批量删除
     * @param idList
     * @return 数量
     */
    long deleteBatch(List<PK> idList);

    /**
     * 根据ID更新所有不为空的字段。
     *
     * @param entity
     * @return 数量
     */
    long update(E entity);

    /**
     * 批量更新，根据ID
     * @param entityList
     * @return 数量
     */
    long updateBatch(List<E> entityList);

    /**
     * 根据条件更新entity
     * @param entity
     * @param query
     * @return 数量
     */
    long updateSpecial(E entity, QueryCondition query);

    /**
     * 根据id把field字段增加delta，限于数字类型
     * @param id
     * @param delta
     * @return
     */
    long incr(PK id, String field, Object delta);

    /**
     * 根据id把field字段减少delta，限于数字类型
     * @param id
     * @param delta
     * @return
     */
    long decr(PK id, String field, Object delta);

    /**
     * 根据ID获取指定记录。
     *
     * @param id
     * @return
     */
    E selectById(PK id);

    /**
     * 根据ids获取记录列表
     * @param idList
     * @return
     */
    List<E> selectByIds(List<PK> idList);

    /**
     * 根据id锁定记录，用于兼容以前处理。
     *
     * @param id
     * @return
     */
    E selectById_readLock(PK id);

    /**
     * 根据条件获取一条记录。
     *
     * @param query
     * @return
     */
    E selectFirstOne(QueryCondition query);

    /**
     * 根据条件获取列表。
     *
     * @param query
     * @return
     */
    List<E> selectList(QueryCondition query);

    /**
     * 获取满足条件的记录数。
     *
     * @param query
     * @return
     */
    long queryCount(QueryCondition query);

    /**
     * 获取指定页码的所有记录。
     *
     * @param query
     * @return
     */
    List<E> queryPageList(QueryCondition query);

    /**
     * 获取指定页码返回分页对象
     * @param query
     * @return
     */
    PageResult<E> queryPage(QueryCondition query);

}
