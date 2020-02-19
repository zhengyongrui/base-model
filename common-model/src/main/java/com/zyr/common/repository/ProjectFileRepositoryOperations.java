package com.zyr.common.repository;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

/**
 * 项目文件仓库类操作类
 * 方便更换数据库
 * @Author: zhengyongrui
 * @Date: 2020-02-17 19:26
 */
public interface ProjectFileRepositoryOperations extends GridFsOperations {

    GridFSFile findFile(final String fileId);

    InputStream findInputStream(final String fileId) throws IOException;

    ResponseEntity<InputStreamResource> findResponseEntity(final String fileId) throws IOException;

    String saveFile(InputStream content, @Nullable String filename, @Nullable String contentType);


}
