package com.zyr.common.util.img;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.NumberUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
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
        int srcHeight = bufferedImage.getWidth();
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

}
