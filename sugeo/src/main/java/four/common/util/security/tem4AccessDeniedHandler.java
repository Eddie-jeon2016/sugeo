package four.common.util.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Created by Eddie Jeon on 2016-12-19.
 */
public class tem4AccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(tem4AccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        logger.info("Exceiption : {}",e);
        logger.info("LocalizedMessage : {}",e.getLocalizedMessage());
        logger.info("Message : {}",e.getMessage());
        logger.info("StackTrace : {}",e.getStackTrace());

    }
}
