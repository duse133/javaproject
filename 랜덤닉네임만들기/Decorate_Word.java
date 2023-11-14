import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

public class Decorate_Word extends Thread{
	private JLabel DecoWords_lbl;
	String [] DecorateFileWords;
    Vector<String> DecorateWords = new Vector<String>();
    int count = 0;
    
	public Decorate_Word(JLabel DecoWords_lbl) {
		this.DecoWords_lbl = DecoWords_lbl;
		File file = new File("./텍스트파일/DecorateWords.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            	DecorateFileWords = line.split(" ");
                for(int i = 0; i<DecorateFileWords.length;i++) {
                	DecorateWords.add(DecorateFileWords[i]);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void run() {
		int red,blue,green;
		DecoWords_lbl.setOpaque(false);
		int rand = (int)(Math.random()*DecorateWords.size());
		DecoWords_lbl.setText(DecorateWords.get(rand));
		DecorateWords.remove(0);
		while(count < 5) {
			count++;
			red = (int)(Math.random()*255);
			blue = (int)(Math.random()*255);
			green = (int)(Math.random()*255);
			rand = (int)(Math.random()*DecorateWords.size());
			DecoWords_lbl.setText(DecorateWords.get(rand));
			DecoWords_lbl.setForeground(new Color(red,blue,green));
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e){
				return;
			}
		}
	}
}
