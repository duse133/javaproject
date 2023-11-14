import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class LostArkFrame extends CreateFrame {
	private Clip Volume_L;
	private int count = 0;

	public LostArkFrame() {
		Frame = 'L';
		setTitle("LostArk 닉네임 생성기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// -----------------------브금------------------------------------
		try {
			Volume_L = AudioSystem.getClip();
			File audioFile = new File("배경음악/LostArkLogin.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			Volume_L.open(audioStream);

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Volume_L.start();
		Volume_L.loop(Clip.LOOP_CONTINUOUSLY); // 계속 반복
// ---------------------- 패널 -----------------------------------

		LostArkPanel lostarkpanel = new LostArkPanel();
		LostArkResult_Panel result_Panel = new LostArkResult_Panel();

		result_Panel.setLocation(150, 350);
		result_Panel.setSize(450, 100); // 크기 지정
		lostarkpanel.add(result_Panel);

// ------------스윙 컴포넌트 (슬라이더, 라디오 버튼)--------------------

		Wordslength_Slider.setPaintLabels(true);
		Wordslength_Slider.setPaintTicks(true);
		Wordslength_Slider.setPaintTrack(true);
		Wordslength_Slider.setMajorTickSpacing(1);
		Wordslength_Slider.setMinorTickSpacing(1);
		Wordslength_Slider.setLocation(300, 100);
		Wordslength_Slider.setSize(200, 60); // 크기 지정
		Wordslength_Slider.setOpaque(false);
		Wordslength_Slider.setFont(new Font("serif", Font.BOLD, 15));
		Wordslength_Slider.addChangeListener(new ChangeWordLengthListener());
		lostarkpanel.add(Wordslength_Slider);

		language_korea_radioBtn.setLocation(300, 160);
		language_korea_radioBtn.setSize(60, 50); // 크기 지정
		language_english_radioBtn.setLocation(450, 160);
		language_english_radioBtn.setSize(60, 50);
		language_korea_radioBtn.setOpaque(false);
		language_english_radioBtn.setOpaque(false);
		language_korea_radioBtn.setFont(new Font("serif", Font.BOLD, 15));
		language_english_radioBtn.setFont(new Font("serif", Font.BOLD, 15));

		language_korea_radioBtn.addItemListener(new setLanguageListener());
		language_english_radioBtn.addItemListener(new setLanguageListener());

		languageSelect_radioBtnGroup.add(language_korea_radioBtn);
		languageSelect_radioBtnGroup.add(language_english_radioBtn);

		lostarkpanel.add(language_korea_radioBtn);
		lostarkpanel.add(language_english_radioBtn);

		Yes_radioBtn.setLocation(300, 220);
		Yes_radioBtn.setSize(50, 30); // 크기 지정
		No_radioBtn.setLocation(450, 220);
		No_radioBtn.setSize(50, 30);
		Yes_radioBtn.setOpaque(false);
		No_radioBtn.setOpaque(false);
		Yes_radioBtn.setFont(new Font("serif", Font.BOLD, 15));
		No_radioBtn.setFont(new Font("serif", Font.BOLD, 15));

		Yes_radioBtn.addItemListener(new setRandomListener());
		No_radioBtn.addItemListener(new setRandomListener());

		RandomWords_radioBtnGroup.add(Yes_radioBtn);
		RandomWords_radioBtnGroup.add(No_radioBtn);

		lostarkpanel.add(Yes_radioBtn);
		lostarkpanel.add(No_radioBtn);

// --------------------------- Label 그룹 --------------------------      
		Wordslength_lbl.setLocation(150, 90);
		Wordslength_lbl.setSize(200, 50); // 크기 지정
		Wordslength_lbl.setFont(new Font("serif", Font.BOLD, 15));
		lostarkpanel.add(Wordslength_lbl);

		LanguageSelect_lbl.setLocation(150, 150);
		LanguageSelect_lbl.setSize(200, 50); // 크기 지정
		LanguageSelect_lbl.setFont(new Font("serif", Font.BOLD, 15));
		lostarkpanel.add(LanguageSelect_lbl);

		RandomWords_lbl.setLocation(150, 210);
		RandomWords_lbl.setSize(200, 50); // 크기 지정
		RandomWords_lbl.setFont(new Font("serif", Font.BOLD, 15));
		lostarkpanel.add(RandomWords_lbl);

		DecoWords_lbl.setFont(new Font("serif", Font.BOLD, 40));
		MainWords_lbl.setFont(new Font("serif", Font.BOLD, 40));
		result_Panel.add(DecoWords_lbl);
		result_Panel.add(MainWords_lbl);

// -------------------------- Button 그룹 --------------------------      
		CreateNewWords_btn.setLocation(350, 300);
		CreateNewWords_btn.setSize(100, 35);
		CreateNewWords_btn.addActionListener(new CreateMainWordsAction());
		CreateNewWords_btn.setFont(new Font("serif", Font.BOLD, 15));
		lostarkpanel.add(CreateNewWords_btn);

		setVolumeLostArk.setLocation(750, 250);
		setVolumeLostArk.setSize(80, 80);
		lostarkpanel.add(setVolumeLostArk);
		setVolumeLostArk.addActionListener(new LostArkVolumeClick());

		ReturnHome_btn.setLocation(750, 400);
		ReturnHome_btn.setSize(80, 80);
		ReturnHome_btn.addActionListener(new ReturnFrameAction(this));
		lostarkpanel.add(ReturnHome_btn);

		this.add(lostarkpanel);
		lostarkpanel.setLayout(null);
		setSize(900, 600);
		setVisible(true);

	}

	class LostArkPanel extends JPanel {
		private Image LostArkFrameBackground = new ImageIcon("./LostArkFrameBackground.jpg").getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(LostArkFrameBackground, 0, 0, getWidth(), getHeight(), this);
		}
	}

	class LostArkResult_Panel extends JPanel {
		private Image LostArkResultPanel = new ImageIcon("배경이미지/ResultPanel.png").getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(LostArkResultPanel, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Listener 클래스 작성
	class ReturnFrameAction implements ActionListener {
		LostArkFrame lostarkframe;

		ReturnFrameAction(LostArkFrame lostarkframe) {
			this.lostarkframe = lostarkframe;
		}

		public void actionPerformed(ActionEvent e) {
			Volume_L.stop();
			lostarkframe.dispose();
			new MainFrame();
		}
	}

	class LostArkVolumeClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			count++;
			if(count % 2 == 0) {
				setVolumeLostArk.setIcon(VolumeIcon);
				Volume_L.start();
			}
			else if (count % 2 == 1) {
				Volume_L.stop();
				setVolumeLostArk.setIcon(VolumeIcon_X);
			}
		}
	}
}
