package com.dandandog.framework.oos.service.impl;

import com.dandandog.framework.oos.model.OosBucket;
import com.dandandog.framework.oos.model.OosItem;
import com.dandandog.framework.oos.config.properties.OosProperties;
import com.dandandog.framework.oos.service.OosFileService;
import io.minio.MinioClient;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author JohnnyLiu
 */

public class MinioServiceImpl implements OosFileService {

    private final OosProperties properties;

    private final MinioClient client;

    public MinioServiceImpl(OosProperties properties) {
        this.properties = properties;
        client = MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }


    @Override
    public List<String> getAllBuckets() {
        return null;
    }

    @Override
    public OosBucket getBucketByName(String bucketName) {
        return null;
    }

    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void removeBucket(String bucketName) {

    }

    @Override
    public Collection<OosItem> getAllItems(String bucketName, String prefix) {
        return null;
    }

    @Override
    public OosItem getItem(String bucketName, String itemName) {
        return null;
    }

    @Override
    public String putItem(String bucketName, String itemName, InputStream stream) {
        return null;
    }

    @Override
    public String putItem(String itemName, InputStream stream) {
        return null;
    }

    @Override
    public void removeItem(String bucketName, String itemName) {

    }
}
