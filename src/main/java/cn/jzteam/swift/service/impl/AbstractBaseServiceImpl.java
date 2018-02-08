package cn.jzteam.swift.service.impl;


import cn.jzteam.swift.dao.BaseDao;
import cn.jzteam.swift.query.PageResult;
import cn.jzteam.swift.query.QueryCondition;
import cn.jzteam.swift.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractBaseServiceImpl<E, PK> implements BaseService<E, PK> {

    @Autowired
    protected BaseDao<E, PK> dao;

    /**
     * 插入一条记录。
     *
     * @param entity
     * @return id
     */
    @Override
    public PK insert(E entity){
        final long insert = dao.insert(entity);
        if(insert > 0){
            try {
                final Field idField = entity.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                return (PK) idField.get(entity);
            }catch (Exception e){
                throw new RuntimeException("insert成功，获取id失败");
            }
        }else{
            throw new RuntimeException("insert 失败");
        }
    }

    /**
     * 批量插入记录。
     * @param entityList
     * @return 数量
     */
    public long insertBatch(List<E> entityList){
        if(CollectionUtils.isEmpty(entityList)){
            return 0;
        }

        if(entityList.size() <= 50){
            return dao.insertBatch(entityList);
        }

        // 如果数量过多，每50条一次插入
        int total = entityList.size();
        int count = 0;
        int num = 0;
        while(count < total) {
            if((count = count + 50) > total){
                count = total;
            }
            List<E> temp = entityList.subList(count, count);
            final long l = dao.insertBatch(temp);
            num += l;
        }
        return num;
    }

    /**
     * 根据ID删除记录。
     *
     * @param id
     * @return 数量
     */
    public long delete(PK id){
        return dao.delete(id);
    }

    /**
     * 根据ids批量删除
     * @param idList
     * @return 数量
     */
    public long deleteBatch(List<PK> idList){
        if(CollectionUtils.isEmpty(idList)){
            return 0;
        }

        if(idList.size() < 200){
            return dao.deleteBatch(idList);
        }

        // 如果数量过多，每50条一次插入
        int total = idList.size();
        int count = 0;
        int num = 0;
        while(count < total) {
            if((count = count + 200) > total){
                count = total;
            }
            List<PK> temp = idList.subList(count, count);
            final long l = dao.deleteBatch(temp);
            num += l;
        }
        return num;
    }

    /**
     * 根据ID更新所有不为空的字段。
     *
     * @param entity
     * @return 数量
     */
    public long update(E entity){
        return dao.update(entity);
    }

    /**
     * 批量更新，根据ID
     * @param entityList
     * @return 数量
     */
    public long updateBatch(List<E> entityList){
        if(CollectionUtils.isEmpty(entityList)){
            return 0;
        }

        if(entityList.size() <= 50){
            return dao.updateBatch(entityList);
        }

        // 如果数量过多，每50条一次插入
        int total = entityList.size();
        int count = 0;
        int num = 0;
        while(count < total) {
            if((count = count + 50) > total){
                count = total;
            }
            List<E> temp = entityList.subList(count, count);
            final long l = dao.updateBatch(temp);
            num += l;
        }
        return num;
    }

    /**
     * 根据条件更新entity
     * @param entity
     * @param query
     * @return 数量
     */
    public long updateSpecial(E entity, QueryCondition query){
        return dao.updateSpecial(entity, query);
    }

    /**
     * 根据id把field字段增加delta，限于数字类型
     * @param id
     * @param delta
     * @return
     */
    public long incr(PK id, String field, Object delta){
        return dao.incr(id, field, delta);
    }

    /**
     * 根据id把field字段减少delta，限于数字类型
     * @param id
     * @param delta
     * @return
     */
    public long decr(PK id, String field, Object delta){
        return dao.decr(id, field, delta);
    }

    /**
     * 根据ID获取指定记录。
     *
     * @param id
     * @return
     */
    public E selectById(PK id){
        return dao.selectById(id);
    }

    /**
     * 根据ids获取记录列表
     * @param idList
     * @return
     */
    public List<E> selectByIds(List<PK> idList){
        if(CollectionUtils.isEmpty(idList)){
            return null;
        }
        if(idList.size() < 200){
            return dao.selectByIds(idList);
        }

        if(idList.size() > 2000){
            throw new RuntimeException("单次查询不能超过2000条");
        }

        // 如果数量过多，每50条一次插入
        int total = idList.size();
        int count = 0;
        List<E> result = new ArrayList<>();
        while(count < total) {
            int end = count + 200;
            if(end > total){
                end = total;
            }
            List<PK> temp = idList.subList(count, count = end);
            final List<E> list = dao.selectByIds(temp);
            result.addAll(list);
        }
        return result;
    }

    /**
     * 根据id锁定记录，用于兼容以前处理。
     *
     * @param id
     * @return
     */
    public E selectById_readLock(PK id){
        return dao.selectById_readLock(id);
    }

    /**
     * 根据条件获取一条记录。
     *
     * @param query
     * @return
     */
    public E selectFirstOne(QueryCondition query){
        return dao.selectFirstOne(query);
    }

    /**
     * 根据条件获取列表。
     *
     * @param query
     * @return
     */
    public List<E> selectList(QueryCondition query){
        return dao.selectList(query);
    }

    /**
     * 获取满足条件的记录数。
     *
     * @param query
     * @return
     */
    public long queryCount(QueryCondition query){
        return dao.queryCount(query);
    }

    /**
     * 获取指定页码的所有记录。
     *
     * @param query
     * @return
     */
    public List<E> queryPageList(QueryCondition query){
        return dao.queryPageList(query);
    }

    @Override
    public PageResult<E> queryPage(QueryCondition query) {

        long count = 0;
        if(query.getHasCount()){
            count = this.queryCount(query);
            if(count <= 0){
                return new PageResult<>();
            }
        }

        final List<E> list = this.queryPageList(query);

        PageResult<E> result = new PageResult<>();
        result.setDataList(list);
        result.setTotalCount(count);

        return result;
    }
}
