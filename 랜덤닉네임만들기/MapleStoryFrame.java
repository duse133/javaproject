import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapleStoryFrame extends CreateFrame {
	private Clip Volume_M;
	private int count;
	public MapleStoryFrame() {
		count = 0;
		Frame = 'M';
		setTitle("Maplestory 닉네임 생성기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// -----------------------브금------------------------------------
		try {
			Volume_M = AudioSystem.getClip();
			File audioFile = new File("배경음악/MapleLogin.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			Volume_M.open(audioStream);

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Volume_M.start();
		Volume_M.loop(Clip.LOOP_CONTINUOUSLY); // 계속 반복
//----------------------- 폰트 -----------------------------------
		try {
			InputStream inputStream = new BufferedInputStream(new FileInputStream("./글꼴/Maplestory Bold.ttf"));
			Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
// ---------------------- 패널 -----------------------------------

		MapleStoryPanel maplepanel = new MapleStoryPanel();
		MapleStoryResult_Panel result_Panel = new MapleStoryResult_Panel();

		result_Panel.setLocation(150, 350);
		result_Panel.setSize(450, 100); // 크기 지정
		maplepanel.add(result_Panel);

		// ------------스윙 컴포넌트 (슬라이더, 라디오 버튼)--------------------

		Wordslength_Slider.setPaintLabels(true);
		Wordslength_Slider.setPaintTicks(true);
		Wordslength_Slider.setPaintTrack(true);
		Wordslength_Slider.setMajorTickSpacing(1);
		Wordslength_Slider.setMinorTickSpacing(1);
		Wordslength_Slider.setLocation(300, 100);
		Wordslength_Slider.setSize(200, 60); // 크기 지정
		Wordslength_Slider.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		Wordslength_Slider.setOpaque(false);
		Wordslength_Slider.addChangeListener(new ChangeWordLengthListener());
		maplepanel.add(Wordslength_Slider);

		language_korea_radioBtn.setLocation(300, 160);
		language_korea_radioBtn.setSize(70, 30); // 크기 지정
		language_korea_radioBtn.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		language_english_radioBtn.setLocation(450, 160);
		language_english_radioBtn.setSize(70, 30);
		language_english_radioBtn.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		language_korea_radioBtn.setOpaque(false);
		language_english_radioBtn.setOpaque(false);

		language_korea_radioBtn.addItemListener(new setLanguageListener());
		language_english_radioBtn.addItemListener(new setLanguageListener());

		languageSelect_radioBtnGroup.add(language_korea_radioBtn);
		languageSelect_radioBtnGroup.add(language_english_radioBtn);

		maplepanel.add(language_korea_radioBtn);
		maplepanel.add(language_english_radioBtn);

		Yes_radioBtn.setLocation(300, 220);
		Yes_radioBtn.setSize(70, 30); // 크기 지정
		Yes_radioBtn.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		No_radioBtn.setLocation(450, 220);
		No_radioBtn.setSize(50, 30);
		No_radioBtn.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		Yes_radioBtn.setOpaque(false);
		No_radioBtn.setOpaque(false);

		Yes_radioBtn.addItemListener(new setRandomListener());
		No_radioBtn.addItemListener(new setRandomListener());

		RandomWords_radioBtnGroup.add(Yes_radioBtn);
		RandomWords_radioBtnGroup.add(No_radioBtn);

		maplepanel.add(Yes_radioBtn);
		maplepanel.add(No_radioBtn);

		// --------------------------- Label 그룹 --------------------------
		Wordslength_lbl.setLocation(150, 90);
		Wordslength_lbl.setSize(200, 50); // 크기 지정
		Wordslength_lbl.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		maplepanel.add(Wordslength_lbl);

		LanguageSelect_lbl.setLocation(150, 150);
		LanguageSelect_lbl.setSize(200, 50); // 크기 지정
		LanguageSelect_lbl.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		maplepanel.add(LanguageSelect_lbl);

		RandomWords_lbl.setLocation(150, 210);
		RandomWords_lbl.setSize(200, 50); // 크기 지정
		RandomWords_lbl.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		maplepanel.add(RandomWords_lbl);

		DecoWords_lbl.setFont(new Font("Maplestory Bold", Font.BOLD, 40));
		MainWords_lbl.setFont(new Font("Maplestory Bold", Font.BOLD, 40));
		result_Panel.add(DecoWords_lbl);
		result_Panel.add(MainWords_lbl);

		// -------------------------- Button 그룹 --------------------------
		CreateNewWords_btn.setLocation(350, 300);
		CreateNewWords_btn.setSize(100, 35);
		CreateNewWords_btn.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		CreateNewWords_btn.addActionListener(new CreateMainWordsAction());

		maplepanel.add(CreateNewWords_btn);

		setVolumeMaplestory.setLocation(750, 250);
		setVolumeMaplestory.setSize(80, 80);
		setVolumeMaplestory.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		setVolumeMaplestory.addActionListener(new MapleStoryVolumeClick());
		maplepanel.add(setVolumeMaplestory);

		ReturnHome_btn.setLocation(750, 400);
		ReturnHome_btn.setSize(80, 80);
		ReturnHome_btn.setFont(new Font("Maplestory Bold", Font.BOLD, 15));
		ReturnHome_btn.addActionListener(new ReturnFrameAction(this));
		maplepanel.add(ReturnHome_btn);

		this.add(maplepanel);
		maplepanel.setLayout(null);
		setSize(900, 600);
		setVisible(true);
	}

	class MapleStoryPanel extends JPanel {
		private Image MapleStoryFrameBackground = new ImageIcon("배경이미지/MapleStoryFrameBackground.jpg").getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(MapleStoryFrameBackground, 0, 0, getWidth(), getHeight(), this);
		}
	}

	class MapleStoryResult_Panel extends JPanel {
		private Image MapleStoryResultPanel = new ImageIcon("배경이미지/ResultPanel.png").getImage();

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(MapleStoryResultPanel, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Listener 클래스 작성
	class ReturnFrameAction implements ActionListener {
		MapleStoryFrame maplestoryframe;

		ReturnFrameAction(MapleStoryFrame maplestoryframe) {
			this.maplestoryframe = maplestoryframe;
		}

		public void actionPerformed(ActionEvent e) {
			Volume_M.stop();
			maplestoryframe.dispose();
			new MainFrame();
		}
	}
	
	class MapleStoryVolumeClick implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			count++;
			if(count % 2 == 0) {
				setVolumeMaplestory.setIcon(VolumeIcon);
				Volume_M.start();
			}
			else if (count % 2 == 1) {
				Volume_M.stop();
				setVolumeMaplestory.setIcon(VolumeIcon_X);
			}
		}
	}
}