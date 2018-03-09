package com.easymorse.dialog;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	private Movie mMovie;
	private long mMovieStart;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		setContentView(new CustomGifView(this));
		// Dialog customDialog = new Dialog(this);
		// customDialog.setTitle("test");
		// customDialog.setContentView(new CustomGifView(this));
		// customDialog.show();

	}

	class CustomGifView extends View {

		public CustomGifView(Context context) {
			super(context);
			mMovie = Movie.decodeStream(getResources().openRawResource(
					R.drawable.animation));

		}
		
		public void onDraw(Canvas canvas) {

			long now = android.os.SystemClock.uptimeMillis();
			
			if (mMovieStart == 0) { // first time
				mMovieStart = now;
			}
			if (mMovie != null) {
				
				int dur = mMovie.duration();
				if (dur == 0) {
					dur = 1000;
				}
				int relTime = (int) ((now - mMovieStart) % dur);				
				mMovie.setTime(relTime);
				mMovie.draw(canvas, 0, 0);
				invalidate();
			}
		}

	}

}