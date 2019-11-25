package com.example.demo.aspect;

import com.example.demo.annotation.Log;
import com.example.demo.bean.SysLog;
import com.example.demo.dao.SysLogDao;
import com.example.demo.util.HttpContextUtils;
import com.example.demo.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogDao sysLogDao;
    @Pointcut("@annotation(com.example.demo.annotation.Log)")
    public void pointCut(){}
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        Long beginTime = System.currentTimeMillis();
        try {
            //执行方法
            result = point.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
        //执行的时长
        Long time = System.currentTimeMillis() - beginTime;
        saveLog(point,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, Long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if(logAnnotation != null){
            //注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的方法参数值
        Object[] args = point.getArgs();
        //请求方法的参数名
        LocalVariableTableParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramsName = nameDiscoverer.getParameterNames(method);
        if(args != null && paramsName != null){
            String param = "";
            for(int i = 0; i < args.length; i++){
                param += "  " + paramsName[i] + ": " + args[i];
            }
            sysLog.setParams(param);
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置ip地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        //模拟一个用户名
        sysLog.setUsername("sunshinehubery");
        sysLog.setTime(Math.toIntExact(time));
        sysLog.setCreateTime(new Date());
        sysLogDao.saveSysLog(sysLog);
    }
}
