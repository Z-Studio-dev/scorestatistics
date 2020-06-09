package cn.scorestatistics.demo.aop;

import cn.scorestatistics.demo.annotation.FractionControllerLog;
import cn.scorestatistics.demo.annotation.SystemControllerLog;
import cn.scorestatistics.demo.model.entity.TbFractionLog;
import cn.scorestatistics.demo.model.entity.TbStudent;
import cn.scorestatistics.demo.service.intf.FractionService;
import cn.scorestatistics.demo.service.intf.LoginService;
import cn.scorestatistics.demo.service.intf.StudentService;
import cn.scorestatistics.demo.utils.IPInfoUtil;
import cn.scorestatistics.demo.utils.ThreadPoolUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
public class FractionLogAspect {

    private Logger log = LoggerFactory.getLogger(FractionLogAspect.class);

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    private int oldFraction = 0;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FractionService fractionService;

    @Autowired
    private LoginService loginService;

    /**
     * Controller层切点，注解方式
     */
    @Pointcut("@annotation(cn.scorestatistics.demo.annotation.FractionControllerLog)")
    public void controllerAspect() {
        log.info("=========controllerAspect=========");
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
        // 获取修改前的分数
        Map<String, String[]> logParams = request.getParameterMap();
        String id = logParams.get("id")[0];
        oldFraction = studentService.getStudentById(Long.valueOf(id)).getFraction();
    }

    /**
     * 后置通知（在方法执行之后返回）用于拦截Controller层操作
     * @param joinPoint
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {

        try{
            TbFractionLog tbFractionLog = new TbFractionLog();
            // 日志标题
            tbFractionLog.setName(getControllerMethodDescription(joinPoint));
            // 请求参数
            Map<String, String[]> logParams = request.getParameterMap();
            String id = logParams.get("id")[0];
            String fraction = logParams.get("fraction")[0];
            String operator = logParams.get("operator")[0];
            TbStudent tbStudent = studentService.getStudentById(Long.valueOf(id));
            // 学生用户名
            tbFractionLog.setUsername(tbStudent.getUsername());
            // 学生昵称
            tbFractionLog.setNickname(tbStudent.getNickname());
            // 学生所在班级
            tbFractionLog.setInclass(tbStudent.getInclass());
            // 修改的分数
            tbFractionLog.setFraction(tbStudent.getFraction() + "(" + (Integer.parseInt(fraction)-oldFraction) + ")");
            // 请求用户
            tbFractionLog.setUser(operator);
            // 请求IP
            tbFractionLog.setIp(IPInfoUtil.getIpAddr(request));
            // IP地址
//            tbFractionLog.setIp_info(IPInfoUtil.getIpCityByPut(IPInfoUtil.getIpAddr(request)));
            // 请求开始时间
            Date logStartTime = beginTimeThreadLocal.get();

            long beginTime = beginTimeThreadLocal.get().getTime();
            long endTime = System.currentTimeMillis();
            // 请求耗时
            Long logElapsedTime = endTime - beginTime;
            tbFractionLog.setTime(Math.toIntExact(logElapsedTime));
            tbFractionLog.setCreateDate(logStartTime);

            // 调用线程保存至数据库
            ThreadPoolUtil.getPool().execute(new SaveFractionLogThread(tbFractionLog, fractionService));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class SaveFractionLogThread implements Runnable {

        private TbFractionLog tbFractionLog;
        private FractionService fractionService;

        public SaveFractionLogThread(TbFractionLog tbFractionLog, FractionService fractionService) {
            this.tbFractionLog = tbFractionLog;
            this.fractionService = fractionService;
        }

        @Override
        public void run() {
            fractionService.addLog(tbFractionLog);
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
            description = method.getAnnotation(FractionControllerLog.class).description();
        }
        return description;

    }
}
