package cn.scorestatistics.demo.aop;

import cn.scorestatistics.demo.annotation.SystemControllerLog;
import cn.scorestatistics.demo.annotation.SystemServiceLog;
import cn.scorestatistics.demo.model.entity.TbLog;
import cn.scorestatistics.demo.service.intf.SystemService;
import cn.scorestatistics.demo.utils.IPInfoUtil;
import cn.scorestatistics.demo.utils.ObjectUtil;
import cn.scorestatistics.demo.utils.ThreadPoolUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class SystemLogAspect {

    private Logger log = LoggerFactory.getLogger(SystemLogAspect.class);

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    @Autowired
    private SystemService systemService;

    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * Controller层切点，注解方式
     */
    @Pointcut("@annotation(cn.scorestatistics.demo.annotation.SystemControllerLog)")
    public void controllerAspect() {
        log.info("=========controllerAspect=========");
    }

    /**
     * Service层切点，注解方式
     */
    @Pointcut("@annotation(cn.scorestatistics.demo.annotation.SystemServiceLog)")
    public void serviceAspect() {
        log.info("=========serviceAspect=========");
    }

    /**
     * 前置通知（在方法执行前返回）用于拦截Controller层记录用户的操作的开始时间
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        // 线程绑定变量（该数据只有当前请求的线程可见）
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
    }

    /**
     * 后置通知（在方法执行之后返回）用于拦截Controller层操作
     * @param joinPoint
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try{

            String username = SecurityUtils.getSubject().getPrincipal().toString();
            if(username != null) {

                TbLog tbLog = new TbLog();

                // 日志标题
                tbLog.setName(getControllerMethodDescription(joinPoint));
                // 日志类型
                tbLog.setType(1);
                // 日志请求url
                tbLog.setUrl(request.getRequestURI());
                // 请求方式
                tbLog.setRequestType(request.getMethod());
                // 请求参数
                Map<String, String[]> logParams = request.getParameterMap();
                tbLog.setMapToParams(logParams);
                IPInfoUtil.getInfo(request, ObjectUtil.mapToStringAll(logParams));
                // 请求用户
                tbLog.setUser(username);
                // 请求IP
                tbLog.setIp(IPInfoUtil.getIpAddr(request));
                // IP地址
                tbLog.setIpInfo(IPInfoUtil.getIpCity(IPInfoUtil.getIpAddr(request)));
                // 请求开始时间
                Date logStartTime = beginTimeThreadLocal.get();

                long beginTime = beginTimeThreadLocal.get().getTime();
                long endTime = System.currentTimeMillis();
                // 请求耗时
                Long logElapsedTime = endTime - beginTime;
                tbLog.setTime(Math.toIntExact(logElapsedTime));
                tbLog.setCreateDate(logStartTime);

                // 调用线程保存至数据库
                ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(tbLog, systemService));
            }
        }catch(Exception e) {
            log.error("AOP后置通知异常", e);
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try{

            String username = SecurityUtils.getSubject().getPrincipal().toString();

            if(username != null) {

                TbLog tbLog = new TbLog();

                // 日志标题
                tbLog.setName(getControllerMethodDescription(joinPoint));
                // 日志类型
                tbLog.setType(0);
                // 日志请求url
                tbLog.setUrl(request.getRequestURI());
                // 请求方式
                tbLog.setRequestType(request.getMethod());
                // 请求参数
                Map<String, String[]> logParams = request.getParameterMap();
                tbLog.setMapToParams(logParams);
                IPInfoUtil.getInfo(request, ObjectUtil.mapToStringAll(logParams));
                // 请求用户
                tbLog.setUser(username);
                // 请求IP
                tbLog.setIp(IPInfoUtil.getIpAddr(request));
                // IP地址
                tbLog.setIpInfo(IPInfoUtil.getIpCity(IPInfoUtil.getIpAddr(request)));
                // 请求开始时间
                Date logStartTime = beginTimeThreadLocal.get();

                long beginTime = beginTimeThreadLocal.get().getTime();
                long endTime = System.currentTimeMillis();
                // 请求耗时
                Long logElapsedTime = endTime - beginTime;
                tbLog.setTime(Math.toIntExact(logElapsedTime));
                tbLog.setCreateDate(logStartTime);

                // 调用线程保存至数据库
                ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(tbLog, systemService));
            }
        }catch(Exception el) {
            log.error("AOP后置通知异常", el);
        }
    }

    /**
     * 保存日志
     */
    private static class SaveSystemLogThread implements Runnable {

        private TbLog tbLog;
        private SystemService systemService;

        public SaveSystemLogThread(TbLog tbLog, SystemService systemService) {
            this.tbLog = tbLog;
            this.systemService = systemService;
        }

        @Override
        public void run() {
            systemService.addLog(tbLog);
        }
    }

    /**
     * 获取注解中对方的描述信息 用于Controller层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";

        for(Method method: methods) {
            if(!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if(clazzs.length != arguments.length) {
                // 比较方法中参数个数与从切点中获取的参数个数是否相同
                continue;
            }
            description = method.getAnnotation(SystemControllerLog.class).description();
        }
        return description;

    }

    /**
     * 获取注解中对方的描述信息 用于Service层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";

        for(Method method: methods) {
            if(!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if(clazzs.length != arguments.length) {
                // 比较方法中参数个数与从切点中获取的参数个数是否相同
                continue;
            }
            description = method.getAnnotation(SystemServiceLog.class).description();
        }
        return description;

    }
}
