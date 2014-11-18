/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.filter;

import edu.mum.waa.beans.LoginBean;
import edu.mum.waa.models.User;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mandal
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @Inject
    private LoginBean loginBean;
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        try 
        {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            String reqURI = req.getRequestURI();
            if (session != null && (User)session.getAttribute("user") != null) 
            {
                if(reqURI.indexOf("login.xhtml") >= 0)
                {
                    res.sendRedirect("index.xhtml");
                }
            }
            else
            if (reqURI.indexOf("/addProduct.xhtml") >= 0) 
            {
                res.sendRedirect("login.xhtml");
            }
            
            chain.doFilter(request, response);
        } 
        catch (IOException | ServletException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

}
