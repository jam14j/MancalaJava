package sample;

import javafx.scene.paint.Color;

public class SharedPreferences {
	static private String language = "English";
	static private Color bColor = Color.GRAY;
	static private boolean mute = false;

	static public String getLanguage() { return language; }
	static public Color getColor() { return bColor; }
	static public boolean isMute() { return mute; }

	static public void setLanguage(String nl) { language = nl; }
	static public void setColor(Color nc) { bColor = nc; }
	static public void setMute(boolean nm) { mute = nm; }
}
