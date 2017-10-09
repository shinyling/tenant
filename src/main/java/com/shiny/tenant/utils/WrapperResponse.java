package com.shiny.tenant.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WrapperResponse extends HttpServletResponseWrapper{

    private ByteArrayOutputStream buffer;

    private ServletOutputStream servletOutputStream;

    public WrapperResponse(HttpServletResponse response) {
        super(response);
        buffer=new ByteArrayOutputStream();
        servletOutputStream=new WrapperOutputStream(buffer);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletOutputStream;
    }

    @Override
    public void flushBuffer() throws IOException {
        if(servletOutputStream!=null){
            servletOutputStream.flush();
        }
    }

    public byte[] getContent() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }
}
