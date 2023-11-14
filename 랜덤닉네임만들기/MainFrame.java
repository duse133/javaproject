import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
	public MainFrame() {
		setTitle("무작위 닉네임 생성 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel mainpanel = new MainPanel();
		mainpanel.setLayout(new BorderLayout(30, 0));
		mainpanel.add(new PanelButton(this), BorderLayout.CENTER);
		mainpanel.add(new PanelTitle(), BorderLayout.NORTH);
		this.add(mainpanel);
		this.setSize(600, 400);
		this.setVisible(true);
	}
}

class MainPanel extends JPanel {
	private Image MapleStoryBack = new ImageIcon("배경이미지/mainbackground.jpg").getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(MapleStoryBack, 0, 0, getWidth(), getHeight(), this);
	}
}

class PanelTitle extends JPanel {
	public PanelTitle() {
		setOpaque(false);
		JLabel Main_Title_lbl = new JLabel("닉네임 생성기");
		Main_Title_lbl.setFont(new Font("Maplestory Bold", Font.PLAIN, 40));
		add(Main_Title_lbl);
	}
}


class PanelButton extends JPanel {
	private ImageIcon MapleStoryIcon = new ImageIcon("로고이미지/maplestoryback.jpg");
	private ImageIcon LostArkIcon = new ImageIcon("로고이미지/lostark.jpg");

	public PanelButton(MainFrame mainframe) {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
		JButton MapleStory_btn = new JButton("", MapleStoryIcon);
		JButton LostArk_btn = new JButton("", LostArkIcon);
		MapleStory_btn.setPreferredSize(new Dimension(190, 140));
		LostArk_btn.setPreferredSize(new Dimension(190, 140));
		LostArk_btn.addActionListener(new LostArkCreateFrameAction(mainframe));
		MapleStory_btn.addActionListener(new MapleStoryCreateFrameAction(mainframe));
		add(MapleStory_btn);
		add(LostArk_btn);
	}
}

class LostArkCreateFrameAction implements ActionListener {
	MainFrame mainframe;

	LostArkCreateFrameAction(MainFrame mainframe) {
		this.mainframe = mainframe;
	}

	public void actionPerformed(ActionEvent e) {
		mainframe.dispose();
		new LostArkFrame();
	}
}

class MapleStoryCreateFrameAction implements ActionListener {
	MainFrame mainframe;

	MapleStoryCreateFrameAction(MainFrame mainframe) {
		this.mainframe = mainframe;
	}

	public void actionPerformed(ActionEvent e) {
		mainframe.dispose();
		new MapleStoryFrame();
	}
}