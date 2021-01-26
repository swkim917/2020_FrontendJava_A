package java0923;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberManager extends JFrame {
	
	private JPanel p1, p2, p3, p4, panelWest, contentPane;
	private JTextField tfId, tfPasswd, tfName;
	private JButton btnTotal, btnAdd, btnDel;
	private JTable table;
	
	private MemberDao memberDao = new MemberDao();
	
	public MemberManager() {
		setTitle("간단한 회원관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 배치 GridLayout(3,1)
		panelWest = new JPanel(new GridLayout(3, 1));
		// 패널 p1   아이디  tfId
		p1 = new JPanel();
		p1.add(new JLabel("아이디"));
		p1.add(tfId = new JTextField(10));
		panelWest.add(p1);
		// 패널 p2   패스워드  tfPasswd
		p2 = new JPanel();
		p2.add(new JLabel("패스워드"));
		p2.add(tfPasswd = new JTextField(10));
		panelWest.add(p2);
		// 패널 p3   패스워드  tfPasswd
		p3 = new JPanel();
		p3.add(new JLabel("이름"));
		p3.add(tfName = new JTextField(10));
		panelWest.add(p3);
		
		// 패널 p4 버튼영역 - 전체보기  추가  삭제
		p4 = new JPanel();
		p4.add(btnTotal = new JButton("전체보기"));
		p4.add(btnAdd = new JButton("추가"));
		p4.add(btnDel = new JButton("삭제"));
		
		contentPane = (JPanel) getContentPane();
		contentPane.add(panelWest, BorderLayout.WEST);
		contentPane.add(p4, BorderLayout.SOUTH);
		
		// 버튼 이벤트 연결하기
		btnTotal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAllMembers();
			}
		});
		
		
		// 인터페이스가 가지는 추상메소드가 1개일 경우
		// 람다식으로 축약해서 표현이 가능함.
		btnAdd.addActionListener((event) -> {
			// 입력상자에 입력값이 없으면 null이 아니고 빈문자열("")이다.
			String id = tfId.getText().trim();
			String passwd = tfPasswd.getText().trim();
			String name = tfName.getText().trim();
			
			if (id.length() == 0 
					|| passwd.length() == 0 
					|| name.length() == 0) {
				JOptionPane.showMessageDialog(MemberManager.this, "필수사항을 입력하세요.");
				return;
			}
			
			MemberVo memberVo = new MemberVo(id, passwd, name);
			memberDao.addMember(memberVo);
			
			JOptionPane.showMessageDialog(MemberManager.this, "회원가입 성공!");
			
			showAllMembers();
			
			tfId.setText("");
			tfPasswd.setText("");
			tfName.setText("");
		});
		
		
		btnDel.addActionListener((e) -> {
			
			//사용자 입력 id값 가져오기
			String id = tfId.getText().trim();
			
			// 입력 아이디값 없으면 "삭제할 아이디를 입력하세요" 다이얼로그 띄움
			if (id.length() == 0) { // id.equals("")
				JOptionPane.showMessageDialog(MemberManager.this, "삭제할 아이디를 입력하세요");
				tfId.requestFocus();
				return;
			}
			
			// 존재하지 않는 아이디면 "삭제할 아이디가 존재하지 않습니다" 띄움
			MemberVo memberVo = memberDao.getMemberById(id);
			if (memberVo == null) {
				JOptionPane.showMessageDialog(MemberManager.this, "삭제할 아이디가 존재하지 않습니다");
				return;
			}
			
			// 입력 아이디값 있으면 "정말 삭제하시겠습니까?" 물어보기
			int result = JOptionPane.showConfirmDialog(MemberManager.this, 
					"아이디 " + id + " 회원을 정말 삭제하시겠습니까?",
					"회원 삭제",
					JOptionPane.OK_CANCEL_OPTION);
			
			// 삭제의도 Cancel 이면 종료(return)
			if (result == JOptionPane.CANCEL_OPTION) {
				return;
			} 
			
			// 삭제의도 OK면 해당 아이디 정보 삭제하기
			memberDao.deleteById(id);
			
			// "삭제성공!" 다이얼로그 띄움
			JOptionPane.showMessageDialog(MemberManager.this, "삭제성공!");
			
			showAllMembers();
			
			// 아이디 입력상자 비우기
			tfId.setText("");
		});
		
		
		setLocationByPlatform(true);
		setSize(700, 300);
		setVisible(true);
	} // MemberManager()
	
	
	void showAllMembers() {
		
		// List 구현체 종류 : ArrayList, Vector
		
		// DB로부터 데이터를 List로 조회
		List<MemberVo> list = memberDao.getAllMembers();
		
		Vector<Vector<Object>> datas = getVectorFromList(list);
		
		// 열제목용 Vector객체 준비
		Vector columnNames = new Vector();
		columnNames.add("아이디");
		columnNames.add("패스워드");
		columnNames.add("이름");
		columnNames.add("가입날짜");
		
		table = new JTable(datas, columnNames);
		
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		
		setVisible(true); // 화면갱신해서 보이기
		
	} // showAllMembers()
	
	/*
	Vector<Vector>    List<List>    List<String>
	
	[ [10, 20, 30],
	  [40, 50, 60],
	  [70, 80, 90] ]
	
	
	[ 10, 20, 30 ]
	[ 10, 20, 30 ]
	
	*/
	public Vector<Vector<Object>> getVectorFromList(List<MemberVo> list) {
		Vector<Vector<Object>> vector = new Vector<>();
		
		for (MemberVo memberVo : list) {
			Vector<Object> row = new Vector<>();
			row.add(memberVo.getId());
			row.add(memberVo.getPasswd());
			row.add(memberVo.getName());
			row.add(memberVo.getRegDate());
			
			vector.add(row);
		} // for
		return vector;
	} // getVectorFromList()
	

	public static void main(String[] args) {
		new MemberManager();
	} // main

}
