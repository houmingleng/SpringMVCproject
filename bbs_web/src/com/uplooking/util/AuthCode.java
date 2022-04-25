package com.uplooking.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class AuthCode {

	//��Сд��ĸ����
	private static int count = 62;
	private static char[] chrs=null;
	private static Random random=null;
	static{
		chrs = new char[count];
		int index = 0;
		for (int i = 48; i < 58; i++) {
			chrs[index++] = (char)i;
		}
		for (int i = 65; i < 91; i++) {
			chrs[index++] = (char)i;
		}
		for (int i = 97; i < 123; i++) {
			chrs[index++] = (char)i;
		}
		random = new Random();
	}
	
	public static void test(){
		System.out.println(Arrays.toString(chrs));
	}
	
	public static String getAuthCodeString(int length){
		StringBuffer strb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			strb.append(chrs[random.nextInt(count)]);
		}
		return strb.toString();
	}
	
	public static void getAuthCodeImage(int width,int height,OutputStream output,String code) throws Exception{
		//�Դ� -> ����һ��  width*height ������ɫͼƬ
		BufferedImage im = new BufferedImage(width, height, 1);
		//���� -> ����ͼ��������
		Graphics graphics = im.getGraphics();
		//������ɫ
		graphics.setColor(new Color(236,236,236));
		//����
		graphics.fillRect(0, 0, width, height);
		//������
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < 5; i++) {
			int x1=random.nextInt(width);
			int y1=random.nextInt(height);
			int x2=random.nextInt(width);
			int y2=random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}
		//��ɫ
		graphics.setColor(new Color(45,30,105));
		//����
		graphics.setFont(new Font("����", 1, 25));
		//����
		graphics.drawString(code, width/6, height);
		//�����ͷ�
		graphics.dispose();
		String formatName ="jpeg";
		ImageIO.write(im, formatName , output);
	}
}
