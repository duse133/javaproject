import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.event.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class CreateFrame extends JFrame{
	protected ImageIcon HomeButtonIcon = new ImageIcon("로고이미지/home_btn_icon.jpg");
	protected ImageIcon VolumeIcon = new ImageIcon("로고이미지/Volume.png");
	protected ImageIcon VolumeIcon_X = new ImageIcon("로고이미지/Volume_X.jpeg");
	protected int Word_Length = 2;
	protected char Language_Attribute = 'K';
	protected char SetRandomWords = 'Y';
	protected char Frame = 'L';
	
	protected JSlider Wordslength_Slider = new JSlider(JSlider.HORIZONTAL, 2, 8, 2);
	
	protected ButtonGroup languageSelect_radioBtnGroup = new ButtonGroup(); // 한/영 설정 그룹
	protected JRadioButton language_korea_radioBtn = new JRadioButton("한글", true);
	protected JRadioButton language_english_radioBtn = new JRadioButton("영어");
	protected JRadioButton Yes_radioBtn = new JRadioButton("Yes", true);
	protected JRadioButton No_radioBtn = new JRadioButton("No");
	protected ButtonGroup RandomWords_radioBtnGroup = new ButtonGroup(); // 한/영 설정 그룹
	
	protected JLabel Wordslength_lbl = new JLabel("단어의 길이");
	protected JLabel LanguageSelect_lbl = new JLabel("한/영 설정");
	protected JLabel RandomWords_lbl = new JLabel("무작위");
	protected JLabel MainWords_lbl = new JLabel("");
	protected JLabel DecoWords_lbl = new JLabel("");
	
	protected JButton CreateNewWords_btn = new JButton("만들기");
	protected JButton setVolumeLostArk = new JButton("", VolumeIcon);
	protected JButton setVolumeMaplestory = new JButton("", VolumeIcon);
	protected JButton ReturnHome_btn = new JButton("", HomeButtonIcon);
	
	String [] KoreaFileWords;
	String [] EnglishFileWords;

    Vector<String> KoreaWords = new Vector<String>();
    Vector<String> EnglishWords = new Vector<String>();
    
	private static final char[] CHO = 
			/*ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ */
		{0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145,
			0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e};
	private static final char[] JUN = 
			/*ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ*/
		{0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158,
			0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160,	0x3161,	0x3162,
			0x3163};
	
	//메소드
	public void language_korea_readfile() {
		if(Frame == 'L') {
			File file = new File("./텍스트파일/KoreaLostArkText.txt");
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                KoreaFileWords = line.split(" ");
	                for(int i = 0; i<KoreaFileWords.length;i++) {
	                	KoreaWords.add(KoreaFileWords[i]);
	                } 
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		else if(Frame == 'M') {
			File file = new File("./텍스트파일/MapleStoryWord_K.txt");
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                KoreaFileWords = line.split(" ");
	                for(int i = 0; i<KoreaFileWords.length;i++) {
	                	KoreaWords.add(KoreaFileWords[i]);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public void language_english_readfile() {
		if(Frame == 'L') {
			File file = new File("./텍스트파일/EnglishLostArkText.txt");
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                EnglishFileWords = line.split(" ");
	                for(int i = 0; i<EnglishFileWords.length;i++) {
	                	EnglishWords.add(EnglishFileWords[i]);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		else if(Frame == 'M') {
			File file = new File("./텍스트파일/MapleStoryWord_E.txt");
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                EnglishFileWords = line.split(" ");
	                for(int i = 0; i<EnglishFileWords.length;i++) {
	                	EnglishWords.add(EnglishFileWords[i]);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}

	// 리스너 구현
	class MainFrameAction implements ActionListener {
		LostArkFrame lostarkframe;

		MainFrameAction(LostArkFrame lostarkframe) {
			this.lostarkframe = lostarkframe;
		}

		public void actionPerformed(ActionEvent e) {
			lostarkframe.dispose();
			new MainFrame(); 
		}
	}
	
	class ChangeWordLengthListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			Word_Length = Wordslength_Slider.getValue();
		}
	}
	
	class setLanguageListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(language_korea_radioBtn.isSelected()) {
				Language_Attribute = 'K';
			}
			else if(language_english_radioBtn.isSelected()) {
				Language_Attribute = 'E';
			}
		}
	}
	
	class setRandomListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(Yes_radioBtn.isSelected()) {
				SetRandomWords = 'Y';
			}
			else if(No_radioBtn.isSelected()) {
				SetRandomWords = 'N';
			}
		}
	}
	
	class CreateMainWordsAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Decorate_Word DC = new Decorate_Word(DecoWords_lbl);
			DC.start();
			if(SetRandomWords == 'N') {
				if(Language_Attribute == 'K') {
					language_korea_readfile();
					KoreaWords.remove(0);
					int rand = (int)(Math.random()*KoreaWords.size());
					while(KoreaWords.get(rand).length() != Word_Length) {
						rand = (int)(Math.random()*KoreaWords.size());
					}
					MainWords_lbl.setText(KoreaWords.get(rand));
				}
				else if(Language_Attribute == 'E') {
					language_english_readfile();
					EnglishWords.remove(0);
					int rand = (int)(Math.random()*EnglishWords.size());
					while(EnglishWords.get(rand).length() != Word_Length) {
						rand = (int)(Math.random()*EnglishWords.size());
					}
					MainWords_lbl.setText(EnglishWords.get(rand));
				}
			}
			else if (SetRandomWords == 'Y'){
				String words = "";
				if(Language_Attribute == 'K') {
					int Cho = (int)(Math.random()*CHO.length);
					int Jun = (int)(Math.random()*JUN.length);
					for(int i = 0; i<Word_Length;i++) {
						words += (char)(0xAC00 + 28 * 21 *Cho + 28 * Jun );
						Cho = (int)(Math.random()*CHO.length);
						Jun = (int)(Math.random()*JUN.length);
					}
					MainWords_lbl.setText(words);
				}
				else if(Language_Attribute == 'E') {
					for(int i = 0; i<Word_Length;i++) {
						words += (char)((int)(Math.random()*26)+97);
					}
					MainWords_lbl.setText(words);
				}
			}
		}	
	}
	
}
