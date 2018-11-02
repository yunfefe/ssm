package com.ly.util;

import com.github.pagehelper.PageInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FeiyeUtil extends SimpleTagSupport {

private String url;
private PageInfo pageInfo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out=this.getJspContext().getOut();
        StringBuffer stringBuffer=new StringBuffer();
        if (url.contains("?")){
            url=url+"&";
        }else{
            url=url+"?";
        }
        stringBuffer.append("<a href='"+url+"index=1&size="+pageInfo.getPageSize()+"'>首页</a>");
        stringBuffer.append("<a href='"+url+"index="+(pageInfo.getPrePage()==0?1:pageInfo.getPrePage())+"&size="+pageInfo.getPageSize()+"'>上一页</a>");
        stringBuffer.append("<a href='"+url+"index="+(pageInfo.getNextPage()==0?pageInfo.getPages():pageInfo.getNextPage())+"&size="+pageInfo.getPageSize()+"'>下一页</a>");
        stringBuffer.append("<a href='"+url+"index="+pageInfo.getPages()+"&size="+pageInfo.getPageSize()+"'>尾页</a>");
        stringBuffer.append("共"+pageInfo.getTotal()+"条");

        out.print(stringBuffer.toString());
    }
}
