package com.exam.controller.filenotice;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.AttachDao;
import com.exam.dao.JdbcUtils;
import com.exam.dao.NoticeDao;
import com.exam.vo.AttachVo;
import com.exam.vo.NoticeVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileWriteProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileWriteProController......");
		
		// ���� ���ε� ���ؼ� cos.jar ���̺귯���� ������Ʈ �����н��� �߰�.

		//���ε� ��ü �����Ҷ� �ʿ��� ���ڰ�
		//1. request
		//2. ���ε� �� ������ ������ ���
		//3. ���ε� �ִ� ũ�� ����
		//4. ���ϸ� �ѱ�ó�� utf-8
		//5. ���ϸ� �ߺ��ɶ� �̸������Ģ ���� ��ü�� ����

		//���ε� �� ���� ������ ��� ���ϱ�
		ServletContext application = request.getServletContext(); // application ��ü ���� ���ϱ�
		String realPath = application.getRealPath("/upload");
		System.out.println("realPath : " + realPath);

		// ���ó�¥ ����� ������ �����ϴ��� Ȯ���ؼ� ������ �����ϱ�
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(date); // "2020/11/11"

		File dir = new File(realPath, strDate);
		System.out.println("dir : " + dir.getPath());

		if (!dir.exists()) {
			dir.mkdirs();
		}


		//���� ���ε� �ϱ�
		MultipartRequest multi = new MultipartRequest(
				request,
				dir.getPath(),     // 파일 저장 경로
				1024 * 1024 * 100, // 파일업로드 최대크기(바이트 단위) 100MB
				"utf-8",           // 인코딩 유형
				new DefaultFileRenamePolicy()); // 파일명 변경 정책

		//enctype="multipart/form-data" �� ���۹�����
		//�⺻���尴ü�� request���� �Ķ���Ͱ��� �ٷ� ã���� ����!
		//MultipartRequest ��ü�κ��� �Ķ���Ͱ��� ã�ƾ� ��! ������� ������.

		//post �Ķ���Ͱ� �ѱ�ó���� ���� �����ڿ��� ó���ϱ� ������ ���ʿ� ����!
		//request.setCharacterEncoding("utf-8");

		//pageNum �Ķ���Ͱ� ��������
		String pageNum = multi.getParameter("pageNum");


		//DAO ��ü �غ�
		NoticeDao noticeDao = NoticeDao.getInstance();
		AttachDao attachDao = AttachDao.getInstance();

		int nextNum = JdbcUtils.getNextNum("notice");

		//Enumeration�� �ݺ��� ��ü. file�� name�Ӽ����� ������ ����
		Enumeration<String> enu = multi.getFileNames();

		while (enu.hasMoreElements()) {
			// AttachVo ��ü �غ�
			AttachVo attachVo = new AttachVo();
			
			String fname = enu.nextElement();
			// ���� ���ε�� ���ϸ� ��������
			String filename = multi.getFilesystemName(fname);
			System.out.println("�������ϸ� : " + filename);
			
			attachVo.setFilename(filename); // �������ϸ��� VO�� ����
			attachVo.setUploadpath(strDate); // "��/��/��" ��θ� ����
			attachVo.setNoNum(nextNum);  // insert�� �Խ��� �۹�ȣ�� ����
			
			if (filename != null) {
				attachVo.setImage( isImage(filename) ? "I" : "O" );
			}
			
			// attachVo�� attach ���̺� insert�ϱ�
			attachDao.insertAttach(attachVo);
		} // while




		//VO ��ü �غ�
		NoticeVo noticeVo = new NoticeVo();

		//�Ķ���Ͱ� �����ͼ� VO�� ����. MultipartRequest �κ��� ã��.
		noticeVo.setId(multi.getParameter("id"));
		noticeVo.setSubject(multi.getParameter("subject"));
		noticeVo.setContent(multi.getParameter("content"));

		//�۹�ȣ �����ͼ� VO�� ����
		noticeVo.setNum(nextNum);

		//ip  regDate  readcount  �� ����
		noticeVo.setIp(request.getRemoteAddr());
		noticeVo.setRegDate(new Timestamp(System.currentTimeMillis()));
		noticeVo.setReadcount(0);  // ��ȸ��

		//re_ref  re_lev  re_seq
		noticeVo.setReRef(nextNum); // �ֱ��϶��� �۹�ȣ�� �׷��ȣ�� ��
		noticeVo.setReLev(0); // �ֱ��϶��� �鿩���� ������ 0 (�鿩���� ����)
		noticeVo.setReSeq(0); // �ֱ��϶��� �۱׷� ������ ������ 0 (ù��°)


		//�ֱ� noticeVo ����ϱ�
		noticeDao.addNotice(noticeVo);


		//�۳��� �󼼺��� ȭ�� fileContent.jsp�� �̵�
		//response.sendRedirect("fileContent.jsp?num=" + noticeVo.getNum() + "&pageNum=" + pageNum);
		return "redirect:/fileNotice.do";
	}
	
	
	
	
	private boolean isImage(String filename) {
		boolean result = false;
		// ���� Ȯ���� ���ڿ� �����ϱ�
		// aaaa.bbb.ccc.ddd
		int index = filename.lastIndexOf(".");
		String ext = filename.substring(index + 1); // ���� Ȯ���� ���ڿ�
		
		if (ext.equalsIgnoreCase("jpg") 
				|| ext.equalsIgnoreCase("jpeg")
				|| ext.equalsIgnoreCase("gif")
				|| ext.equalsIgnoreCase("png")) {
			result = true;
		}
		return result;
	}

}
