package cn.com.yusys.yusys.utils.idempotent;

import java.io.IOException;

public interface YusysIdempotentStore {
    /**
     * 首次写入幂等的请求信息，需要保证分布式锁，实现唯一一个线程的写入，建议使用redis
     *
     * @param key  请求信息指定属性或者请求信息的md5
     * @param value  首次写入是idempotent-biz-process
     * @param ttl    存储时长，通过redis实现，此值暂时无用；通过数据库实现，为了过滤查询结果，需要记录数据创建时间
     */
    public boolean write(String key,String value,Long ttl);

    /**
     * 更新写入的幂等信息，如果是redis实现，需要在成功写入后设置有效时间
     *
     * @param key  请求信息指定属性或者请求信息的md5
     * @param value  业务处理返回的结果
     * @param ttl    存储时长，通过redis实现，需要设置该值；通过数据库实现，此值在writ方法中已经设置过了，此方法无须更新
     */
    public boolean update(String key,String value,Long ttl);

    /**
     * 从数据源中读取信息，需要用（当前时间-数据写入的时间）和ttl时间进行结果的过滤
     *
     * @param key  请求信息指定属性或者请求信息的md5
     */
    public String read(String key);

    /**
     * 从数据源中删除信息
     *
     * @param key  请求信息指定属性或者请求信息的md5
     */
    public boolean delete(String key) throws IOException;
}
