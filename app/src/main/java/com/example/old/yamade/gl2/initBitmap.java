package com.example.old.yamade.gl2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.old.yamade.R;

/**
 * 将图片转成bitmap
 */
public class initBitmap {
 
	public static Bitmap bitmap;
	
	public static void init(Resources res){
		bitmap = BitmapFactory.decodeResource(res, R.drawable.ae_camera_capture_mode_indicator_light) ;
	}
}

