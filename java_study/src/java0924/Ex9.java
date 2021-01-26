package java0924;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ex9 extends JFrame {
	
	private Thread thread;
	
	public Ex9() {
		setTitle("시계");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel clockLable = new JLabel("");
		clockLable.setFont(new Font("Gothic", Font.ITALIC, 80));
		
		Container contentPane = getContentPane();
		contentPane.add(clockLable, BorderLayout.CENTER);
		
		JButton btnStart = new JButton("Start");
		JButton btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		
		// 시계 구동
		btnStart.addActionListener(event -> {
			
			thread = new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						break;
						//return;
					}
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm:ss.SSS");
					String str = sdf.format(date);
					clockLable.setText(str);
				} // while
				
				// 실행문들..
				
			});
			thread.start();
			
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
		});
		
		// 시계 중지
		btnStop.addActionListener(e -> {
			// interrupt() 호출했을때
			// 해당 스레드의 작업코드에 InterruptedException
			// 예외를 발생시킴(여기서는 sleep()메소드에서 발생시킴)
			thread.interrupt();
			
			btnStop.setEnabled(false);
			btnStart.setEnabled(true);
		});
		
		JPanel panel = new JPanel();
		panel.add(btnStart);
		panel.add(btnStop);
		
		contentPane.add(panel, BorderLayout.SOUTH);
		
		setLocationByPlatform(true);
		setSize(700, 300);
		setVisible(true);
	}
	

	public static void main(String[] args) {
		new Ex9();
		
		/*
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm:ss");
				String str = sdf.format(date);
				System.out.println(str);
			}
		}).start();
		*/
		
	} // main

}
