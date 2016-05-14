package com.demo.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.demo.common.bean.ResultInfo;
import com.demo.util.LoggerUtils;
import com.demo.util.ServletUtils;

@Controller
public class GlobalExceptionHandler implements HandlerExceptionResolver
{
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception e)
    {
        LoggerUtils.error(request.getRequestURI(), e);
        ModelAndView mv = null;
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
        {
            ResultInfo result = new ResultInfo(-1, e.getMessage());
            ServletUtils.writeJsonToResponse(response, result);
        } else
        {
            mv = new ModelAndView("/common/error.jsp");
            mv.addObject("error", "有错误发生，请联系管理员");
        }
        return mv;
    }
}