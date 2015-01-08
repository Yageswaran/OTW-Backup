package com.sai.samples.views;






import com.firebase.client.Firebase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class ImageSwitcherView extends Activity implements ViewFactory {

	Integer pics[] = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
			R.drawable.e };

	ImageSwitcher iSwitcher;
	Button like;
	Button dislike;
	int i = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Firebase.setAndroidContext(this);
	    //Firebase myFirebaseRef = new Firebase("https://dazzling-heat-7789.firebaseio.com/");
		
		like = (Button) findViewById(R.id.button1);
		dislike = (Button) findViewById(R.id.button2);

		iSwitcher = (ImageSwitcher) findViewById(R.id.ImageSwitcher01);
		iSwitcher.setFactory(this);
		iSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		iSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		iSwitcher.setImageResource(pics[i]);

//		Gallery gallery = (Gallery) findViewById(R.id.Gallery01);
//		gallery.setAdapter(new ImageAdapter(this));
//		gallery.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				iSwitcher.setImageResource(pics[arg2]);
//			}
//		});
		
		like.setOnClickListener(new OnClickListener() {

//			

			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				
				
				if (i ==4){
					i = 0;
					iSwitcher.setImageResource(pics[i]);
					
					
				}else{
					
					i++;
					iSwitcher.setImageResource(pics[i]);
					
				}
				Toast.makeText(getApplicationContext(), "Liked", 
		                Toast.LENGTH_SHORT).show();
				Firebase my = new Firebase("https://otw.firebaseio.com/");
				my.child("d").setValue("Liked");
			}
		});
		
		dislike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				if (i ==4){
					i = 0;
					iSwitcher.setImageResource(pics[i]);
					
				}else{
					
					i++;
					iSwitcher.setImageResource(pics[i]);
				}
				Toast.makeText(getApplicationContext(), "Disliked", 
		                Toast.LENGTH_SHORT).show();
				
				}
		});
	}
	
	

	public class ImageAdapter extends BaseAdapter {

		private Context ctx;

		public ImageAdapter(Context c) {
			ctx = c; 
		}

		@Override
		public int getCount() {

			return pics.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			ImageView iView = new ImageView(ctx);
			iView.setImageResource(pics[arg0]);
			iView.setScaleType(ImageView.ScaleType.FIT_XY);
			iView.setLayoutParams(new Gallery.LayoutParams(150, 150));
			return iView;
		}

	}

	@Override
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new 
				ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		iView.setBackgroundColor(0xFF000000);
		return iView;
	}
}