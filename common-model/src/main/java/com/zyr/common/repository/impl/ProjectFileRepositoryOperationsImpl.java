package com.zyr.common.repository.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.zyr.common.repository.ProjectFileRepositoryOperations;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zhengyongrui
 * @Date: 2020-02-17 19:36
 */
@Repository
public class ProjectFileRepositoryOperationsImpl extends GridFsTemplate implements ProjectFileRepositoryOperations {

    public ProjectFileRepositoryOperationsImpl(MongoDbFactory dbFactory, MongoConverter converter) {
        super(dbFactory, converter);
    }

    @Override
    public GridFSFile findFile(String fileId) {
        return super.findOne((buildFileIdQuery(fileId)));
    }

    @Override
    public InputStream findInputStream(String fileId) throws IOException {
        GridFsResource gridFsResource = findInputStreamResource(fileId);
        if (gridFsResource != null) {
            return gridFsResource.getInputStream();
        } else {
            throw new IOException("文件找不到");
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> findResponseEntity(String fileId) throws IOException {
        GridFsResource gridFsResource = findInputStreamResource(fileId);
        if (gridFsResource != null) {
            HttpHeaders headers = new HttpHeaders();
            setContentType(gridFsResource, headers);
            return new ResponseEntity<>(gridFsResource, headers, HttpStatus.OK);
        } else {
            throw new IOException("文件找不到");
        }
    }

    @Override
    public String saveFile(InputStream content, @Nullable String filename, @Nullable String contentType) {
        ObjectId objectId = super.store(content, filename, contentType);
        return String.valueOf(objectId);
    }

    /**
     * 获取文件
     * @param fileId 文件id
     * @return InputStreamResource
     */
    private GridFsResource findInputStreamResource(final String fileId) {
        GridFSFile file = findFile(fileId);
        if (file != null) {
            return super.getResource(file);
        } else {
            return null;
        }
    }

    /**
     * 设置文件类型
     *
     * @param gridFsResource 文件
     * @param headers        header
     */
    private void setContentType(GridFsResource gridFsResource, HttpHeaders headers) {
        MediaType mediaType = MediaType.valueOf(gridFsResource.getContentType());
        headers.setContentType(mediaType);
    }


    /**
     * 添加文件id查询条件
     *
     * @param fileId 文件id
     * @return 查询条件
     */
    private Query buildFileIdQuery(@NonNull final String fileId) {
        final Criteria criteria = Criteria.where("_id").is(new ObjectId(fileId));
        return new Query(criteria);
    }


}
