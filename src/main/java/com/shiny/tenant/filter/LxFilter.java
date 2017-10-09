package com.shiny.tenant.filter;

import com.shiny.tenant.utils.WrapperResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "lxFilter",urlPatterns = "/*")
public class LxFilter  implements Filter{

    private static final Logger logger= LogManager.getLogger(LxFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        WrapperResponse wrapperResponse=new WrapperResponse((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest,wrapperResponse);
        byte[] content=wrapperResponse.getContent();
        logger.info("返回值:{}",new String(content));
        ServletOutputStream outputStream=servletResponse.getOutputStream();
        outputStream.write(content);
        outputStream.flush();
    }

    @Override
    public void destroy() {

    }
}
