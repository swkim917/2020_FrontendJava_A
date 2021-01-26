package com.exam.controller.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.exam.dao.CommentDao;
import com.exam.dao.MySqlDao;
import com.exam.vo.CommentVo;
import com.exam.vo.Criteria;
import com.google.gson.Gson;

import lombok.extern.java.Log;

@Log
@WebServlet(urlPatterns = "/comment/*", loadOnStartup = 1)
public class CommentRestServlet extends HttpServlet {
	
	private Gson gson = new Gson();

	private void sendResponse(String strJson, HttpServletResponse response) throws ServletException, IOException {
		// Ŭ���̾�Ʈ���� ���� �ֱ�
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(strJson);
//		out.flush();
		out.close();
	}
	
	// SQL SELECT�� ��û ó��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet ȣ���");
		
		CommentDao commentDao = CommentDao.getInstance();
		
		String category = request.getParameter("category");
		String strJson = "";
		if (category.equals("one")) {
			int cno = Integer.parseInt(request.getParameter("cno"));
			
			CommentVo commentVo = commentDao.getCommentByCno(cno);
			
			Map<String, Object> map = new HashMap<>();
			map.put("commentVo", commentVo);
			map.put("isSuccess", true);
			
			strJson = gson.toJson(map);
		} else if (category.equals("list")) {
			int nno = Integer.parseInt(request.getParameter("nno")); // �Խ��� �۹�ȣ
			int pageNum = Integer.parseInt(request.getParameter("pageNum")); // ��� ��������ȣ
			int numOfRows = Integer.parseInt(request.getParameter("numOfRows")); // �Ѱ��� ����������� ��۰���
			
//			List<CommentVo> commentList = commentDao.getComments(nno);
			
			Criteria cri = new Criteria(pageNum, numOfRows);
			
			List<CommentVo> commentList = commentDao.getCommentsWithPaging(nno, cri);
			
			int totalCount = commentDao.getTotalCountByNno(nno); // �� ��� ����
			
			Map<String, Object> map = new HashMap<>();
			map.put("commentList", commentList);
			map.put("isSuccess", true);
			map.put("totalCount", totalCount);
			
			strJson = gson.toJson(map);
		}
		
		// Ŭ���̾�Ʈ���� ���� �ֱ�
		sendResponse(strJson, response);
	} // doGet

	// SQL INSERT�� ��û ó��
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doPost ȣ���");
		
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		
		// �α��� Ȯ�� ��������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			// Ŭ���̾�Ʈ���� ������ �� JSON ������ �غ�
			Map<String, Object> map = new HashMap<>();
			map.put("isSuccess", false);
			map.put("description", "not login");
			
			String strJson = gson.toJson(map);
			log.info("strJson : " + strJson);
			
			// Ŭ���̾�Ʈ���� ���� �ֱ�
			sendResponse(strJson, response);
			return;
		}
		
		
		MySqlDao mySqlDao = MySqlDao.getInstance();
		CommentDao commentDao = CommentDao.getInstance();
		
		String category = request.getParameter("category");
		log.info(category);
		
		BufferedReader reader = request.getReader(); // http�޽��� body���� JSON ���ڿ� ��������
		CommentVo commentVo = gson.fromJson(reader, CommentVo.class);
		reader.close();  // ����� ��ü �ݱ�
		
		if (category.equals("main")) { // �ִ�� ����ϱ�
			int cno = mySqlDao.getNextNum("comment");
			commentVo.setCno(cno);  // ��� ��ȣ
			commentVo.setReRef(cno); // �ִ���϶��� �۹�ȣ�� �۱׷��ȣ�� ������
			commentVo.setReLev(0);
			commentVo.setReSeq(0);
			commentVo.setId(id); // �α��� ���̵� ����ۼ��� ���̵� ��
			log.info("commentVo : " + commentVo);
			
			commentDao.addComment(commentVo); // �ִ�� ����ϱ� �޼ҵ� ȣ��
			
		} else if (category.equals("reply")) { // ���� ����ϱ�
			int cno = mySqlDao.getNextNum("comment");
			commentVo.setCno(cno);  // ��� ��ȣ
			commentVo.setId(id);
			log.info("���� : " + commentVo.toString());
			
			commentDao.addReplyComment(commentVo); // ���� ����ϱ� �޼ҵ� ȣ��
		}
		
		// Ŭ���̾�Ʈ���� ������ �� JSON ������ �غ�
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", true);
		map.put("comment", commentVo);
		
		String strJson = gson.toJson(map);
		log.info("strJson : " + strJson);
		
		// Ŭ���̾�Ʈ���� ���� �ֱ�
		sendResponse(strJson, response);
	}

	// SQL UPDATE�� ��û ó��
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doPut ȣ���");
		
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		
		BufferedReader reader = request.getReader();
		
		
		CommentVo commentVo = gson.fromJson(reader, CommentVo.class);
		log.info("commentVo : " + commentVo);
		
		CommentDao commentDao = CommentDao.getInstance();
		commentDao.update(commentVo); // DB update ó�� �Ϸ�
		
		// ��ۼ��� �Ϸ�� ��۳��� ��������
		CommentVo commentVoGet = commentDao.getCommentByCno(commentVo.getCno());
		
		// Ŭ���̾�Ʈ���� ������ �� JSON ������ �غ�
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", true);
		map.put("comment", commentVoGet);
		
		String strJson = gson.toJson(map);
		log.info("strJson : " + strJson);
		
		// Ŭ���̾�Ʈ���� ���� �ֱ�
		sendResponse(strJson, response);
	}

	// SQL DELETE�� ��û ó��
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doDelete ȣ���");
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		log.info("���� cno = " + cno);
		
		CommentDao commentDao = CommentDao.getInstance();
		commentDao.deleteByCno(cno); // DB delete ó�� �Ϸ�
		
		Map<String, Object> map = new HashMap<>();
		map.put("isSuccess", true);
		
		String strJson = gson.toJson(map);
		
		// Ŭ���̾�Ʈ���� ���� �ֱ�
		sendResponse(strJson, response);
	}

}
