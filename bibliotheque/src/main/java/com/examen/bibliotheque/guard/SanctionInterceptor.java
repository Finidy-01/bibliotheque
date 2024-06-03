package com.examen.bibliotheque.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.examen.bibliotheque.entity.Membre;
import com.examen.bibliotheque.service.MembreService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SanctionInterceptor implements HandlerInterceptor {
    @Autowired
    MembreService membreService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                                Object handler)throws Exception {
        if(isSanctionned(request)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Vous ne pouvez pas emprunter de livre quand vous êtes sanctionné.");
            // response.sendRedirect("/home");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    public boolean isSanctionned(HttpServletRequest request){
        Membre membre = (Membre) request.getSession().getAttribute("membre");
        return membreService.isSanctionned(membre);
    }
}
