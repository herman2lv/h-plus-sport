package com.epam.hplus.view.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertDateTagHandler extends SimpleTagSupport {
    private String format = "yyyy-MM-dd HH:mm";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void doTag() throws JspException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        PageContext context = (PageContext) getJspContext();
        context.getOut().write(dateFormat.format(new Date()));
    }
}
