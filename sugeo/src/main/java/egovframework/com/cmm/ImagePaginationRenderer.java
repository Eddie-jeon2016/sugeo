package egovframework.com.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
/**
 * ImagePaginationRenderer.java 클래스
 *
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 * </pre>
 */
public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public ImagePaginationRenderer() {

	}

	public void initVariables(){
		firstPageLabel    = "<a href=\"?pageIndex={1}\"  class=\"pn\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/sub/page_front.gif\" alt=\"처음\"   border=\"0\"/></a>";
        previousPageLabel = "<a href=\"?pageIndex={1}\" class=\"pn\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/sub/page_prev.gif\"    alt=\"이전\"/></a>";
        currentPageLabel  = "<a href=\"#none\" class=\"on\"/>{0}</a>";
        otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a>";
        nextPageLabel     = "<a href=\"?pageIndex={1}\" class=\"pn\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/sub/page_next.gif\"    alt=\"다음\"/></a>";
        lastPageLabel     = "<a href=\"?pageIndex={1}\" class=\"pn\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/sub/page_back.gif\" alt=\"마지막\" /></a>";
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
