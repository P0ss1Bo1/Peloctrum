package com.oxigenoxide.catfishing;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.oxigenoxide.catfishing.utils.AutoHash;

public class DesktopLauncher {
	public static void main (String[] arg) {


		new AutoHash("C:/Cat Fishing/images/", new AutoHash.IAutoHasher() {

			@Override
			public void OnCreate() {
				{
					System.out.println("lol");
					TexturePacker.Settings settings = new TexturePacker.Settings();
					settings.maxWidth = 1024;
					settings.maxHeight = 1024;
					settings.paddingX = 1;
					settings.paddingY = 1;

					//TexturePacker.process(settings, "../images/", "../desktop/bin/images/", "images");
					TexturePacker.process(settings, "C:/Cat Fishing/images/", "C:/Cat Fishing/android/assets/Images/", "Images");
				}
			}
		});

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS  = 60;
		new LwjglApplication(new MainClass(new AdMobImpl(), new GPGSImpl()), config);
		if(MainClass.portrait) {
			config.width = 108 * 5;
			config.height = 192 * 5;
		}
		else {
			config.width = 192 * 4;
			config.height = 108 * 4;
		}
		config.fullscreen = true;
	}
}
