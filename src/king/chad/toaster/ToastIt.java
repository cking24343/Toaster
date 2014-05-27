package king.chad.toaster;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beardedhen.bbutton.*;
import com.beardedhen.bbutton.FontAwesomeText.AnimationSpeed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import king.chad.toaster.ToastHelper;

public class ToastIt
{
	private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private LinearLayout mRootLayout;
    private TextView mMessageTextView;
    private ImageView mToastImage;
    private FontAwesomeText mToastIcon;
    private View mToastView;
    private Pattern pattern;
    private Matcher matcher;
    private static Typeface font;
    private static Map<String, String> faMap;
    
    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    @SuppressLint("ResourceAsColor")
	public ToastIt(Activity activity)
	{
		if (activity == null) {
			 throw new IllegalArgumentException("ToastIt: Null Activity!!!");
		}

		 this.mActivity = activity;
	     mLayoutInflater = (LayoutInflater) this.mActivity.getLayoutInflater();
	     mToastView = mLayoutInflater.inflate(R.layout.custom_toast, (ViewGroup) this.mActivity.findViewById(R.id.toast_layout_root));
	     mToastView.setBackgroundColor(Color.parseColor("#00ffffff"));
	     mRootLayout =  (LinearLayout) mToastView.findViewById(R.id.toast_layout);
	     mRootLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
	     mMessageTextView = (TextView) mToastView.findViewById(R.id.toastText);
	     mToastImage =  (ImageView) mToastView.findViewById(R.id.toastImage);
	     pattern = Pattern.compile(HEX_PATTERN);
	     
	     
	}
    
    @SuppressLint("ResourceAsColor")
	public ToastIt(Activity activity, boolean useFontAwesome)
	{
		if (activity == null) {
			 throw new IllegalArgumentException("ToastIt: Null Activity!!!");
		}

		 this.mActivity = activity;
		 
		 if(useFontAwesome)
		 {
			//--re-intialize here to test font icon--
			 mLayoutInflater = (LayoutInflater) this.mActivity.getLayoutInflater();
		     mToastView = mLayoutInflater.inflate(R.layout.custom_font_layout, (ViewGroup) this.mActivity.findViewById(R.id.toast_layout_root));
		     mToastView.setBackgroundColor(Color.parseColor("#00ffffff"));
		     mRootLayout =  (LinearLayout) mToastView.findViewById(R.id.toast_layout);
		     mRootLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
		     mMessageTextView = (TextView) mToastView.findViewById(R.id.toastText);
		     mToastIcon =  (FontAwesomeText) mToastView.findViewById(R.id.imgFontAwesome);
		     pattern = Pattern.compile(HEX_PATTERN);
		 }
		 else
		 {
			 mLayoutInflater = (LayoutInflater) this.mActivity.getLayoutInflater();
		     mToastView = mLayoutInflater.inflate(R.layout.custom_toast, (ViewGroup) this.mActivity.findViewById(R.id.toast_layout_root));
		     mToastView.setBackgroundColor(Color.parseColor("#00ffffff"));
		     mRootLayout =  (LinearLayout) mToastView.findViewById(R.id.toast_layout);
		     mRootLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
		     mMessageTextView = (TextView) mToastView.findViewById(R.id.toastText);
		     mToastImage =  (ImageView) mToastView.findViewById(R.id.toastImage);
		     pattern = Pattern.compile(HEX_PATTERN);
		 }

	}
	
	public void show(Drawable icon, String message, int style, int length)
	{
		mToastImage.setBackground(icon);
		mMessageTextView.setText(message);
		
		switch(style)
      	{
      		case 1: mRootLayout.setBackgroundResource(R.color.stt_holo_red);
      			break;
      		case 2: mRootLayout.setBackgroundResource(R.color.stt_holo_green);
      			break;
      		case 3: mRootLayout.setBackgroundResource(R.color.blue);
      			break;
      	}

		ToastHelper t = new ToastHelper(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.setGravity(Gravity.CENTER_VERTICAL, 100, 100);
	    t.show();
		
	}
	
	public void show(Drawable icon, String message, String hexCode, int length)
	{
		mToastImage.setBackground(icon);
		mMessageTextView.setText(message);
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		ToastHelper t = new ToastHelper(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.show();
		
	}
	
	public void show(Drawable icon, String message, Typeface font, String textColor, String hexCode)
	{
		mToastImage.setBackground(icon);
		mMessageTextView.setText(message);
		if(font != null){mMessageTextView.setTypeface(font);}
		
		
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor))
		{
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
		
		Toast t = new Toast(this.mActivity);
	    t.setView(mToastView);
	    t.show();
	}
	
	public void show(Drawable icon, String message, Typeface font, String textColor, String hexCode, int length)
	{
		Animation animation = AnimationUtils.loadAnimation(this.mActivity, R.anim.anim);
		
		mToastImage.setBackground(icon);
		mMessageTextView.setText(message);
		if(font != null){mMessageTextView.setTypeface(font);}
		
		
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor))
		{
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
		mRootLayout.setVisibility(View.INVISIBLE);
		Toast t = new Toast(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.show();
	    mRootLayout.setVisibility(View.VISIBLE);
	    mRootLayout.startAnimation(animation);
		
	}
	
	public void show(Bitmap img, String message, String textColor, String hexCode, int length)
	{
		mToastImage.setImageBitmap(img);
		mMessageTextView.setText(message);
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor))
		{
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
	
		Toast t = new Toast(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.show();
	}
	
	public void show(String icon, String message, String hexCode, int length)
	{		
		mToastIcon.setIcon(icon);
		mMessageTextView.setText(message);
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		ToastHelper t = new ToastHelper(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.show();
	}
	
	public void show(String icon, String message, String textColor, String hexCode, int length)
	{
		mToastIcon.setIcon(icon);
		mMessageTextView.setText(message);
		
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor))
		{
			mMessageTextView.setTextColor(Color.parseColor(textColor));
			mToastIcon.setTextColor(Color.parseColor(textColor));
		}
		else
		{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
			mToastIcon.setTextColor(Color.parseColor("#ffffff"));
		}

		Toast t = new Toast(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.show();
	   
	}
	
	public void show(String icon, String message, Typeface font, String textColor, String hexCode, int length, boolean flashIcon, boolean rotateIconClockWise, boolean rotateIconCClockWise, String animationSpeed)
	{
		mToastIcon.setIcon(icon);
		mMessageTextView.setText(message);
		if(font != null){mMessageTextView.setTypeface(font);}
		
		AnimationSpeed animateSpeed;
		
		try{
			
			Speeds speed = Speeds.valueOf(animationSpeed);
			switch(speed)
			{
				case FAST: animateSpeed = FontAwesomeText.AnimationSpeed.FAST;
					break;
				case SLOW: animateSpeed = FontAwesomeText.AnimationSpeed.SLOW;
					break;
				default : animateSpeed = FontAwesomeText.AnimationSpeed.MEDIUM;
					break;
			}
		}catch(Exception ex){
			animateSpeed = FontAwesomeText.AnimationSpeed.MEDIUM;
		}
		
		
		if(flashIcon)
		{
			mToastIcon.startFlashing(this.mActivity, true, animateSpeed);
		}
		
		if(rotateIconClockWise){
			mToastIcon.startRotate(this.mActivity, true, animateSpeed);
		}else if(rotateIconCClockWise){
			mToastIcon.startRotate(this.mActivity, true, animateSpeed);
		}
		
		if(validate(hexCode))
		{
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor))
		{
			mMessageTextView.setTextColor(Color.parseColor(textColor));
			mToastIcon.setTextColor(Color.parseColor(textColor));
		}
		else
		{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
			mToastIcon.setTextColor(Color.parseColor("#ffffff"));
		}

		Toast t = new Toast(this.mActivity);
	    t.setDuration(length);
	    t.setView(mToastView);
	    t.show();
	   
	}
	
	
	 /**
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	   public boolean validate(final String hex){
	 
		  matcher = pattern.matcher(hex);
		  return matcher.matches();
	 
	   }
	   
	   public enum Speeds {
		    FAST,
		    MEDIUM,
		    SLOW
		  }

}
