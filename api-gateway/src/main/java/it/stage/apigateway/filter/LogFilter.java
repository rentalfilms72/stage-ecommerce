package it.stage.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilter extends ZuulFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    public String filterType() {
        return "pre"; // "post"
    }

    @Override
    public int filterOrder() {
        return 1; // Order of execution
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        LOGGER.info("+++++++++++++ REQUEST INTERCEPTED : " + request.getContextPath());
        return null;
    }
}
