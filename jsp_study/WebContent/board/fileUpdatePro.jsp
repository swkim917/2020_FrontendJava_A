<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.exam.vo.BoardVo"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파일 업로드 위해서 cos.jar 라이브러리를 프로젝트 빌드패스에 추가.

// 업로드 객체 생성할때 필요한 인자값
// 1. request
// 2. 업로드 할 폴더의 물리적 경로
// 3. 업로드 최대 크기 제한
// 4. 파일명 한글처리 utf-8
// 5. 파일명 중복될때 이름변경규칙 가진 객체를 전달

// 업로드 할 실제 물리적 경로 구하기
String realPath = application.getRealPath("/upload");
System.out.println("realPath : " + realPath);

// 파일 업로드 하기
MultipartRequest multi = new MultipartRequest(
		request,
		realPath,
		1024 * 1024 * 20, // 최대 업로드 20MB로 제한
		"utf-8",
		new DefaultFileRenamePolicy());


// 기존 첨부파일 삭제할 필요 있으면 삭제하기
String delFilename = multi.getParameter("delFilename");
if (delFilename != null) {
	File file = new File(realPath, delFilename);
	
	if (file.exists()) {
		file.delete();
	}
}

// ===============================================================


// pageNum 파라미터값 가져오기
String pageNum = multi.getParameter("pageNum");

// VO 객체 준비
BoardVo boardVo = new BoardVo();

// 파라미터값 가져와서 VO에 저장. MultipartRequest 로부터 찾음.
boardVo.setNum(Integer.parseInt(multi.getParameter("num")));
boardVo.setName(multi.getParameter("name"));
boardVo.setPasswd(multi.getParameter("passwd"));
boardVo.setSubject(multi.getParameter("subject"));
boardVo.setContent(multi.getParameter("content"));

// 업로드한 원본 파일이름 가져오기
System.out.println("원본파일명 : " + multi.getOriginalFileName("filename"));

// 업로드한 실제 파일이름 가져오기
System.out.println("실제파일명 : " + multi.getFilesystemName("filename"));
boardVo.setFile(multi.getFilesystemName("filename"));

// DAO 객체 준비
BoardDao boardDao = BoardDao.getInstance();

// DB 테이블 글정보 수정하기
boardDao.updateBoard(boardVo);

// 수정한 글내용 확인하기 위해 상세보기 페이지인 content.jsp로 이동
response.sendRedirect("content.jsp?num=" + boardVo.getNum() + "&pageNum=" + pageNum);
%>








