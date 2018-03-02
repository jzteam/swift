package cn.jzteam.swift.mvc;

import cn.jzteam.swift.bean.ResponseResult;
import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseJsonMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        boolean hasResponseBody = AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class)
                || returnType.hasMethodAnnotation(ResponseBody.class);
        return hasResponseBody;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        // ture：不再跳转到下一个方法返回值处理器
        modelAndViewContainer.setRequestHandled(true);

        final HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("application/json;charset=UTF-8");

        Object result = o;
        // 如果返回的不是指定结构，包装一下再处理返回。null也会被包装
        if(!(o instanceof ResponseResult)){
            result = ResponseResult.build(0,"", o);
        }

        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(result));
        pw.flush();
    }

}
