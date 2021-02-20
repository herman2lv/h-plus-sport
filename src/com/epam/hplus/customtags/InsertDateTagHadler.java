package com.epam.hplus.customtags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class InsertDateTagHadler extends SimpleTagSupport {
	private String format = "YYYY-MM-dd HH:mm";
	
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
