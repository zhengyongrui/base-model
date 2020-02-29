package com.zyr.common.util.img;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.NumberUtil;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zhengyongrui
 * @Date: 2020-02-28 11:27
 */
public class CommonImgUtil extends ImgUtil {

    /**
     * 压缩图像（按宽度缩放，高度自动计算）<br>
     * 缩放后默认为jpeg格式
     *
     * @param srcStream  源图像流
     * @param width      缩放后的宽度
     * @param fixedColor 比例不对时补充的颜色，不补充为<code>null</code>
     * @return {@link Image}
     */
    public static Image compress(InputStream srcStream, int width, Color fixedColor) {
        BufferedImage bufferedImage = read(srcStream);
        int srcWidth = bufferedImage.getWidth();
        int srcHeight = bufferedImage.getHeight();
        double widthRatio = NumberUtil.div(width, srcWidth);
        Img srcImg = Img.from(bufferedImage);
        Img resultImg;
        if (widthRatio < 1) {
            int height = (int) (srcHeight * widthRatio);
            resultImg = srcImg.scale(width, height, fixedColor);
        } else {
            return bufferedImage;
        }
        return resultImg.getImg();
    }

    /**
     * 从流中读取图片(兼容gif)
     *
     * @param multipartFile 图片文件
     * @return 图片
     * @since 3.2.2
     */
    public static BufferedImage read(MultipartFile multipartFile) {
        if (!MediaType.IMAGE_GIF_VALUE.equals(multipartFile.getContentType())) {
            try {
                return ImageIO.read(multipartFile.getInputStream());
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        } else {
            try {
                final GifDecoder.GifImage gifImage = GifDecoder.read(multipartFile.getInputStream());
                return new BufferedImage(gifImage.getWidth(), gifImage.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }
    }

}
