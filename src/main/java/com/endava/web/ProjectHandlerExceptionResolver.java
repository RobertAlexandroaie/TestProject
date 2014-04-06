/**
 * 
 */
package com.endava.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.execution.repository.snapshot.SnapshotNotFoundException;

import com.endava.exceptions.ProjectException;

import constants.ProjectConstants;

/**
 * Handle exception when user uses back button.
 *
 */
public class ProjectHandlerExceptionResolver implements HandlerExceptionResolver {

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (ex instanceof ProjectException) {
            return new ModelAndView(ProjectConstants.INVALID_LOCATION_VIEW);
        }
        return null;
    }

}
