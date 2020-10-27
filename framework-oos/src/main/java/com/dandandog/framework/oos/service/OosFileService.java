package com.dandandog.framework.oos.service;

import com.dandandog.framework.oos.model.OosBucket;
import com.dandandog.framework.oos.model.OosItem;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author JohnnyLiu
 */
public interface OosFileService {

    /**
     * 查询所有桶
     *
     * @return 桶名
     */
    List<String> getAllBuckets();

    OosBucket getBucketByName(String bucketName);

    void createBucket(String bucketName);

    void removeBucket(String bucketName);

    Collection<OosItem> getAllItems(String bucketName, String prefix);

    OosItem getItem(String bucketName, String itemName);

    String putItem(String bucketName, String itemName, InputStream stream);

    String putItem(String itemName, InputStream stream);

    void removeItem(String bucketName, String itemName);


}
