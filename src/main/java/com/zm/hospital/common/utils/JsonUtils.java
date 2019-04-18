package com.zm.hospital.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.hospital.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * 返回json
 * Created by ange on 2016/11/13.
 */
public class JsonUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);


    /**
     * response 输出JSON
     */
    public static void out(ServletResponse response, Result result) {

        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();

            ObjectMapper mapper = new ObjectMapper();
            // Convert object to JSON string
            String Json = mapper.writeValueAsString(result);

            out.println(Json);
        } catch (Exception e) {
            LOGGER.debug("输出JSON报错。");
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
