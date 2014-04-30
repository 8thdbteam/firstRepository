package com.example.planboardv1;


import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;



@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnTouchListener,
OnDragListener {


	int playerNumber=0;
	int flag_color;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CourtViewer vm = new CourtViewer(this);
       
        setContentView(R.layout.activity_main);
        //setContentView(new AnEventMove(this));
        
       // ImageView ball=(ImageView) findViewById(R.id.ball);
        //ball = (ImageView) findViewById(R.id.ball);
        findViewById(R.id.ball).setOnTouchListener(this); // My yellow letter a image
        findViewById(R.id.ball).getRootView().setOnDragListener(this); // My 1st linear layout
		
    

	}
	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	        view.startDrag(null, shadowBuilder, view, 0);
	        view.setVisibility(View.INVISIBLE);
	        return true;
	    } else {
	        return false;
		}
	}

	@Override
	public boolean onDrag(View layoutview, DragEvent event) {

		switch (event.getAction()) {
		case DragEvent.ACTION_DROP:

			float X = event.getX();
			float Y = event.getY();

			// Log.d(LOGCAT, "X " + (int) X + "Y " + (int) Y);
			View view = (View) event.getLocalState();
			view.setX(X-(view.getWidth()/2));
			view.setY(Y-(view.getHeight()/2));
			view.setVisibility(View.VISIBLE);

		default:
			break;
		}

		return true;
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		
		menu.add(0,0,0,"하프코트/풀코드 변경");
		menu.add(0,1,0,"선수추가");
		menu.add(0,2,0,"전술 저장");
		menu.add(0,3,0,"전술 보기");
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		case 0:
			break;
		case 1:
			playerNumber++;
			if(playerNumber<6)
				flag_color=R.drawable.redplayer;
			
			else if(playerNumber>=6 && playerNumber<11)
				flag_color=R.drawable.blueplayer;
			else {
				Toast.makeText(this, "선수는 10명이상 추가 못합니다.",1).show();
				break;
			}
			
			
			LinearLayout linearLayout=(LinearLayout)findViewById(R.id.mainlayout);
			
			
			// ImageView Setup
			ImageView imageView = new ImageView(this);
			// setting image resource
			imageView.setImageResource(flag_color);
			// setting image position
			imageView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			LayoutParams params = (LayoutParams) imageView.getLayoutParams();
			params.width =60;
			params.height=60;
			imageView.setLayoutParams(params);
			// adding view to layout
			linearLayout.addView(imageView);
			// make visible to program
		    
			imageView.setOnTouchListener(this);
			imageView.getRootView().setOnDragListener(this);
			break;
		case 2:
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
