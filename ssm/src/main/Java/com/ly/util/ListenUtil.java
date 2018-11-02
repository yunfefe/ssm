package com.ly.util;

import com.ly.bean.UserTb;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class ListenUtil implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                .getRequest().getSession();
        UserTb u1 = (UserTb) session.getAttribute("u1");
        delegateTask.setAssignee(u1.getManager().getUserName());
    }
}
