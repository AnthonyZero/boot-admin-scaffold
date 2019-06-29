package com.anthonyzero.scaffold.common.utils;

import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

/**
 * 验证码工具类
 */
@Slf4j
public class CaptchaUtil {

    // gif 类型验证码
    private static final int GIF_TYPE = 1;
    // png 类型验证码
    private static final int PNG_TYPE = 0;

    // 验证码图片默认高度
    private static final int DEFAULT_HEIGHT = 48;
    // 验证码图片默认宽度
    private static final int DEFAULT_WIDTH = 130;
    // 验证码默认位数
    private static final int DEFAULT_LEN = 5;

    public static String outGif(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outGif(DEFAULT_LEN, request, response);
    }

    public static String outGif(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outGif(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, request, response);
    }

    public static String outGif(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outGif(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, font, request, response);
    }

    public static String outGif(int width, int height, int len, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outGif(width, height, len, vType, null, request, response);
    }

    public static String outGif(int width, int height, int len, Integer vType, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return writeCaptcha(width, height, len, font, GIF_TYPE, vType, request, response);
    }

    public static String outPng(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outPng(DEFAULT_LEN, request, response);
    }

    public static String outPng(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outPng(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, request, response);
    }

    public static String outPng(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outPng(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, font, request, response);
    }

    public static String outPng(int width, int height, int len, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return outPng(width, height, len, vType, null, request, response);
    }

    public static String outPng(int width, int height, int len, Integer vType, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return writeCaptcha(width, height, len, font, PNG_TYPE, vType, request, response);
    }

    /**
     * 输出验证码图片 同时返回验证码
     * @param width
     * @param height
     * @param len
     * @param font
     * @param cType
     * @param vType
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private static String writeCaptcha(int width, int height, int len, Font font, int cType, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        setHeader(response, cType);
        Captcha captcha = null;
        if (cType == GIF_TYPE) {
            captcha = new GifCaptcha(width, height, len);
        } else {
            captcha = new SpecCaptcha(width, height, len);
        }
        if (font != null) {
            captcha.setFont(font);
        }
        if (vType != null) {
            captcha.setCharType(vType);
        }
        String code = captcha.text().toLowerCase();

        captcha.out(response.getOutputStream());
        return code;
    }

    public static void setHeader(HttpServletResponse response, int cType) {
        if (cType == GIF_TYPE) {
            response.setContentType("image/gif");
        } else {
            response.setContentType("image/png");
        }
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
    }
}
