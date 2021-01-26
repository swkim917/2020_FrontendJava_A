package com.exam.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;

public class WriteFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteFormController......");
		
		// session ��ü ���� ��������
//		HttpSession session = request.getSession();
//		
//		// �α��� ���� Ȯ��
//		String id = (String) session.getAttribute("id");
//		if (id == null) {
//			return "redirect:/notice.do";
//		}
		
		// �Ķ���Ͱ�  pageNum  ��������
		String pageNum = request.getParameter("pageNum");
		
		// ��(jsp)���� �ʿ��� �����͸� request ������ü�� ����
		request.setAttribute("pageNum", pageNum);
		
		return "center/writeForm";
	}

}
