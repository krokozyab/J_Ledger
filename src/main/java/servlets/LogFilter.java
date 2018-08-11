package servlets;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.GregorianCalendar;

/**
 * Created by facet on 13/09/16.
 */
@WebFilter(filterName = "LogFilter", urlPatterns = {"/*"})
public class LogFilter implements Filter {
    private static final Logger log = Logger.getLogger(LogFilter.class);
    private ServletContext context;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        Enumeration<String> params = request.getHeaderNames();
        log.info("-----------------------------------------");
        while(params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getHeader(name);
            log.info("name: "+name+" value: "+value);
        }

        try {
            context=req.getServletContext();
    log.info("Date: " + new GregorianCalendar().getTime());
    log.info("Address: " + req.getRemoteAddr());
}
catch(Exception exception)
        {
            exception.printStackTrace();
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();

    }

}
