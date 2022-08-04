package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.annotation.PostMapping;
import com.ll.exam.mymap.MyMap;
import com.ll.exam.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerManager {
    private static Map<String, RouteInfo> routeInfos;

    static {
        routeInfos = new HashMap<>();

        scanMappings();
    }

    private static void scanMappings() {
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        // Controller 클래스 네임 + GetMapping의 uri
        for (Class<?> controllerCls : ref.getTypesAnnotatedWith(Controller.class)) {
            Method[] methods = controllerCls.getDeclaredMethods();

            for (Method method : methods) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                PostMapping postMapping = method.getAnnotation(PostMapping.class);

                String httpMethod = null;
                String path = null;

                if (getMapping != null) {
                    path = getMapping.value();
                    httpMethod = "GET";
                }
                if (postMapping != null) {
                    path = postMapping.value();
                    httpMethod = "POST";
                }

                if (path != null && httpMethod != null) {
                    String actionPath = Util.str.beforeFrom(path, "/", 4);

                    String key = httpMethod + "___" + actionPath;

                    routeInfos.put(key, new RouteInfo(path, actionPath, controllerCls, method));
                }
            }
        }
        
    }

    public static void runAction(HttpServletRequest req, HttpServletResponse res){
        Rq rq = new Rq(req, res);

        String routeMethod = rq.getRouteMethod();
        String actionPath = rq.getActionPath();

        String mappingKey = routeMethod + "___" + actionPath;

        boolean contains = routeInfos.containsKey(mappingKey);

        if (contains == false) {
            rq.println("해당 요청은 존재하지 않습니다.");
            return;
        }

        runAction(rq, routeInfos.get(mappingKey));
    }

    private static void runAction(Rq rq, RouteInfo routeInfo) {
        Class controllerCls = routeInfo.getControllerCls();
        Method actionMethod = routeInfo.getMethod();

        Object controllerObj = Container.getObj(controllerCls);

        try {
            actionMethod.invoke(controllerObj, rq);
        } catch (IllegalAccessException e) {
            rq.println("액션시작에 실패하였습니다.");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
            //rq.println("액션시작에 실패하였습니다.");
        } finally {
            MyMap myMap = Container.getObj(MyMap.class);
            myMap.closeConnection(); // 현재 쓰레드에 할당된 커넥션을 닫는다.
            // 안하면 벌어지는 일
            // 매 요청마다, DB 요청이 쌓인다.
        }
    }

    public static void init() {
    }

    public static Map<String, RouteInfo> getRouteInfosForTest() {
        return routeInfos;
    }
}
