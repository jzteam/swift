package cn.jzteam.swift.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 调整MethodReturnValueHandler的位置
 */
public class MethodReturnValueHandlerInjector implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * 初始化application容器，refresh完成后调用
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<HandlerMethodReturnValueHandler> handlers = new ArrayList();
        // 将自定义的放在第一位，否则Map会被MapMethodProcessor处理...
        handlers.add(new ResponseJsonMethodReturnValueHandler());
        handlers.addAll(requestMappingHandlerAdapter.getReturnValueHandlers());
        requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
    }
}
