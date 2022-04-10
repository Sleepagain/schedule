package com.mywork.controller;//下载控制层，主要负责于前端页面的交互


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mywork.util.CommonUtil;
import com.mywork.util.SysModel;

@Controller 
public class DownLoadController {

	@RequestMapping(value="/download")
	public String download(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		Log log=LogFactory.getLog(DownLoadController.class);
		String filePath=request.getParameter("filePath");
		filePath = CommonUtil.changeEncoding(filePath);
		//"..";
		if(filePath.indexOf("..")!=-1){
			log.debug("download failed ....");
			throw new ServletException("请进行正确操作");
		}
		String path=SysModel.get("uploadRoot").toString()+filePath;
		File f=new File(path);
		if(f==null || !f.exists()){
			//Log.error(path+ " file not exists . ");
			return null;
		}
		FileInputStream in=new FileInputStream(f);
		response.setHeader("Content-disposition","attachment;filename="+f.getName());
		response.setContentLength((int) f.length());
		ServletOutputStream out=response.getOutputStream();
		byte[] b=new byte[1024];
		int i=0;
		while((i=in.read(b))>0){
			out.write(b, 0, i);
		}
		in.close();
		out.close();
		return null;
	}
}
