package com.bluesky.automationjiahua.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:
 * TODO:注意：当在具有 SELECT语句的 Query方法上使用时，此查询生成的代码将在事务中运行。
 * 有两种主要的情况，你可能想要这样做:
 * 如果查询的结果相当大，那么最好在事务中运行它，来接收一致的结果。否则，如果查询结果不适合单个数据操作，由于游标窗口交换之间数据库中的更改，查询结果可能被损坏。
 * 如果查询的结果是一个带有relationship字段的POJO，则分别查询这些字段。要在这些查询之间接收一致的结果，还需要在单个事务中运行它们。
 * 如果查询是异步的，例如，返回lifecycle。或者RxJava Flowable，在运行查询时正确处理事务，而不是在调用方法时。
 * 将此注释放在 Insert、Update或Delete方法上没有影响，因为这些方法总是在事务中运行。类似地，如果一个方法使用Query注释，但是运行INSERT、UPDATE或DELETE语句，那么它将自动包装在一个事务中，而这个注释没有效果。
 * TODO:Room一次最多只执行一个事务，其他事务按先到先得顺序排队执行。
 */
@Dao
public interface DeviceDao {


    @Insert
    void insertAll(Device... devices);

    @Query("DELETE FROM DEVICE")
    void deleteAll();

    @Delete
    void deleteDevices(Device... devices);

    @Query("SELECT * FROM device")
    LiveData<List<Device>> getAllDevices();

    @Query("SELECT * FROM device")
    List<Device> getAllDevicesNoLivedata();


    @Query("SELECT * FROM DEVICE WHERE TAG LIKE :pattern")
    LiveData<List<Device>> queryDevicesByTag(String pattern);

    @Query("SELECT * FROM DEVICE WHERE AFFECT LIKE :pattern")
    LiveData<List<Device>> queryDevicesByAffect(String pattern);

    @Query("SELECT * FROM DEVICE WHERE NAME LIKE :pattern")
    LiveData<List<Device>> queryDevicesByName(String pattern);

    @Update
    void updateDevices(Device... devices);

    @Query("SELECT * FROM DEVICE WHERE STANDARD LIKE :pattern")
    LiveData<List<Device>> queryDevicesByStandard(String pattern);

    @Query("SELECT * FROM DEVICE WHERE TYPE LIKE :pattern")
    LiveData<List<Device>> queryDevicesByType(String pattern);

    //todo 这里的query写法不能直接是字符串拼接
    //改为采用下面的RawQuery
    @Query("SELECT * FROM DEVICE where domain=:pattern")
    LiveData<List<Device>> queryDevicesByPattern(String pattern);

    /**
     * bservable query return type (LiveData, Flowable, DataSource, DataSourceFactory etc)
     * can only be used with SELECT queries that directly or indirectly (via @Relation, for example) access at least one table.
     * For @RawQuery, you should specify the list of tables to be observed via the observedEntities field.
     * RawQuery 可以返回observable类型， 但是你需要指定在查询中访问的表所对应的实体类
     *
     * @param query
     * @return
     */
    @RawQuery(observedEntities = Device.class)
    LiveData<List<Device>> rawQueryDevicesByPattern(SupportSQLiteQuery query);


/*    @Query("pragma table_info(device)")
    List<TableInfo> queryDeviceTableColumn();*/
}
