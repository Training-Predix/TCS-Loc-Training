#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * Filter to set headers for Angular requests.
 * @author 212421693
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*"); //${symbol_dollar}NON-NLS-1${symbol_dollar} //${symbol_dollar}NON-NLS-2${symbol_dollar}
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE ,PUT");  //${symbol_dollar}NON-NLS-1${symbol_dollar}//${symbol_dollar}NON-NLS-2${symbol_dollar}
		response.setHeader("Access-Control-Max-Age", "3600"); //${symbol_dollar}NON-NLS-1${symbol_dollar} //${symbol_dollar}NON-NLS-2${symbol_dollar}
		response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");  //${symbol_dollar}NON-NLS-1${symbol_dollar}//${symbol_dollar}NON-NLS-2${symbol_dollar}
		//response.setHeader("Access-Control-Expose-Header", "*");
		chain.doFilter(req, res);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter${symbol_pound}destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter${symbol_pound}init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}