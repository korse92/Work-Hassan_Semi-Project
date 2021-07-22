package post.post.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post/fileDown")
public class PostFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oName = request.getParameter("oName");
		String rName = request.getParameter("rName");
		
		String saveDir = getServletContext().getRealPath("/upload/post");
		File downFile = new File(saveDir, rName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		response.setContentType("application/octet-stream");
		oName = new String(oName.getBytes("utf-8"), "iso-8859-1");
		System.out.println(oName);
		response.setHeader("Content-Disposition", "attachment;filename=" + oName);
		
		int data = -1;
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		bis.close();
		bos.close();
	}

}
