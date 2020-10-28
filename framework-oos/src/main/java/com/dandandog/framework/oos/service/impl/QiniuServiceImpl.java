package com.dandandog.framework.oos.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dandandog.framework.oos.config.properties.OosProperties;
import com.dandandog.framework.oos.model.OosBucket;
import com.dandandog.framework.oos.model.OosItem;
import com.dandandog.framework.oos.service.OosFileService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author JohnnyLiu
 */
@AllArgsConstructor
public class QiniuServiceImpl implements OosFileService {

    private final OosProperties properties;

    private final Auth auth;

    private final UploadManager uploadManager;

    private final BucketManager bucketManager;

    public QiniuServiceImpl(OosProperties properties) {
        this.properties = properties;
        auth = Auth.create(properties.getAccessKey(), properties.getSecretKey());
        Configuration cfg = new Configuration(Region.autoRegion());
        uploadManager = new UploadManager(cfg);
        bucketManager = new BucketManager(auth, cfg);
    }


    @Override
    public List<String> getAllBuckets() {
        try {
            return Arrays.asList(bucketManager.buckets());
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OosBucket getBucketByName(String bucketName) {
        try {
            bucketManager.getBucketInfo(bucketName);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createBucket(String bucketName) {
        try {
            bucketManager.createBucket(bucketName, null);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBucket(String bucketName) {
    }

    @Override
    public Collection<OosItem> getAllItems(String bucketName, String prefix) {
        try {
            bucketManager.listFiles(bucketName, prefix, null, 10, "");
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OosItem getItem(String bucketName, String itemName) {
        return null;
    }

    @Override
    public String putItem(String bucketName, String itemName, InputStream stream) {
        String token = auth.uploadToken(StrUtil.isNotBlank(bucketName) ? bucketName : properties.getDefBucket());
        try {
            Response response = uploadManager.put(stream, itemName, token, null, null);
            DefaultPutRet putRet = JSONUtil.toBean(response.bodyString(), DefaultPutRet.class);
            return StrUtil.addPrefixIfNot(putRet.key, "/");
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String putItem(String itemName, InputStream stream) {
        return putItem(properties.getDefBucket(), itemName, stream);
    }

    @Override
    public void removeItem(String bucketName, String itemName) {

    }
}
