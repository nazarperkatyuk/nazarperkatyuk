package ShopProject.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import ShopProject.FilterService;
import ShopProject.entities.User_Role;

@WebFilter({"/create-product"})
public class AdminFilter implements Filter {

    private FilterService filterService = FilterService.getInstance();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filterService.validateCallForPage(request, response, chain, Arrays.asList(User_Role.ADMIN));
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
