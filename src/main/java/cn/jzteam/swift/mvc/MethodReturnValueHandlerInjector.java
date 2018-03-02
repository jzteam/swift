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
 * 该bean必须实例化之后才能生效
 * 要配置到springmvc容器中，因为自动注入了RequestMappingHandlerAdapter
 * 虽然执行事件对容器做了判断，但是spring root容器似乎没有感知到这个listener的存在，没有执行
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
        if(event.getApplicationContext().getParent() == null){
            //root application context 没有parent，把事件处理放在springmvc容器中，因为requestMappingHandlerAdapter在mvc容器中才有
            return;
        }

        List<HandlerMethodReturnValueHandler> handlers = new ArrayList();
        // 将自定义的放在第一位，否则Map会被MapMethodProcessor处理...
        handlers.add(new ResponseJsonMethodReturnValueHandler());
        handlers.addAll(requestMappingHandlerAdapter.getReturnValueHandlers());
        requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
    }
}
