/**
 * this class play audio 
 */
package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * class that represent an audio maker
 * 
 * @author
 */
public class AudioMaker {
	Long currentFrame;
	Clip clip;
	Clip clip2;

	/**
	 * create AudioInputStream object
	 */
	AudioInputStream audioInputStream;
	AudioInputStream audioInputStream2;
	static String filePath = "Sameh.wav";
	static String gameover = "Gameover.wav";

	public AudioMaker() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		audioInputStream2 = AudioSystem.getAudioInputStream(new File(gameover).getAbsoluteFile());
		// create clip reference
		clip = AudioSystem.getClip();
		clip2 = AudioSystem.getClip();
		// open audioInputStream to the clip
		clip.open(audioInputStream);
		clip2.open(audioInputStream2);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.stop();
	}

	/**
	 * start the clip
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	public void playMove() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		// create clip reference
		clip = AudioSystem.getClip();

		// open audioInputStream to the clip
		clip.open(audioInputStream);
		clip.start();

	}

	public void playGameover() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (!clip2.isRunning()) {
			audioInputStream2 = AudioSystem.getAudioInputStream(new File(gameover).getAbsoluteFile());
			// create clip reference
			clip2 = AudioSystem.getClip();
			// open audioInputStream to the clip
			clip2.open(audioInputStream2);
			clip2.start();
			return;
		}
	}

	/**
	 * Method to pause the audio
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * 
	 */
	public void pauseMove() {
		clip.setMicrosecondPosition(0);
		clip.stop();
	}

}
