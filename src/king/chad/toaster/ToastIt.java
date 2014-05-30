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
	//--Variable declarations--
	private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private LinearLayout mRootLayout;
    private TextView mMessageTextView;
    private ImageView mToastImage;
    private FontAwesomeText mToastIcon;
    private View mToastView;
    private Toast t;
    private ToastHelper toastHelper;
    private Pattern pattern;
    private Matcher matcher;
    private static Typeface font;
    private static Map<String, String> faMap;
    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    
    //--Reusable variables with generic values--
    private Drawable toasterDrawable = null;
    private Bitmap toasterBitmap = null;
    private Typeface toasterFont = null;
    private int toasterDuration = -1;
    private int toasterTextSize = -1;
    private boolean toasterFlashIcon = false;
    private String toasterFontIcon = null;
    private String toasterMessage = "ToastIt!";
    private String toasterTextColor = "#ffffff";
    private String toasterBGColor = "#000000";
    private Speeds toasterAnimationSpeed = null;
    private Rotations toasterRotation = null;

    
	//--Getters--
    public Drawable getToasterDrawable(){
    	return toasterDrawable;
    }
    public Bitmap getToasterBitmap(){
    	return toasterBitmap;
    }
	public Typeface getToasterFont(){
    	return toasterFont;
    }
	public int getToasterDuration(){
    	return toasterDuration;
    }
	public int getToasterTextSize(){
    	return toasterTextSize;
    }
	public boolean getToasterFlashIcon(){
    	return toasterFlashIcon;
    }
    public String getToasterFontIcon(){
    	return toasterFontIcon;
    }
    public String getToasterTextColor(){
    	return toasterTextColor;
    }
    public String getToasterBGColor(){
    	return toasterBGColor;
    }
    public String getToasterMessage(){
    	return toasterMessage;
    }
    public Speeds getToasterAnimationSpeed(){
    	return toasterAnimationSpeed;
    }
    public Rotations getToasterRotation(){
    	return toasterRotation;
    }
    
    
    //--Setters--
    public void setToasterDrawable(Drawable temp){
    	toasterDrawable = temp;
    }
    public void setToasterBitmap(Bitmap temp){
    	toasterBitmap = temp;
    }
	public void setToasterFont(Typeface temp){
    	toasterFont = temp;
    }
	public void setToasterDuration(int temp){
    	toasterDuration = temp;
    }
	public void setToasterTextSize(int temp){
		toasterTextSize = temp;
    }
	public void setToasterFlashIcon(boolean temp){
    	toasterFlashIcon = temp;
    }
	public void setToasterFontIcon(String temp){
    	toasterFontIcon = temp;
    }
	public void setToasterTextColor(String temp){
    	toasterTextColor = temp;
    }
	public void setToasterBGColor(String temp){
    	toasterBGColor = temp;
    }
    public void setToasterMessage(String txt){
    	toasterMessage = txt;
    }
    public void setToasterAnimationSpeed(Speeds temp){
    	toasterAnimationSpeed = temp;
    }
    public void setToasterRotation(Rotations temp){
    	toasterRotation = temp;
    }

    /**
     * This allows for the developer to reset the values back to the original state
     * */
	public void clearValues()
	{
		setToasterDrawable(null);
		setToasterBitmap(null);
		setToasterFont(null);
		setToasterDuration(-1);
		setToasterTextSize(-1);
		setToasterFlashIcon(false);
		setToasterFontIcon(null);
		setToasterTextColor("#ffffff");
		setToasterBGColor("#000000");
		setToasterMessage("ToastIt!");
		setToasterAnimationSpeed(null);
		setToasterRotation(null);
	}
    
    /**
	* Basic constructor:
	* 
	* @param calling activity 
	*/
    @SuppressLint("ResourceAsColor")
	public ToastIt(Activity activity)
	{
		if (activity == null) {
			 throw new IllegalArgumentException("ToastIt: Null Activity!!!");
		}

		 this.mActivity = activity;
		 toastHelper = new ToastHelper(this.mActivity);
		 t = new Toast(this.mActivity);
		 
	     mLayoutInflater = (LayoutInflater) this.mActivity.getLayoutInflater();
	     mToastView = mLayoutInflater.inflate(R.layout.custom_toast, (ViewGroup) this.mActivity.findViewById(R.id.toast_layout_root));
	     mToastView.setBackgroundColor(Color.parseColor("#00ffffff"));
	     mRootLayout =  (LinearLayout) mToastView.findViewById(R.id.toast_layout);
	     mRootLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
	     mMessageTextView = (TextView) mToastView.findViewById(R.id.toastText);
	     pattern = Pattern.compile(HEX_PATTERN);   
	}
    
    /**
   	* Basic constructor with images:
   	* 
   	* @param calling activity
   	* @param boolean use image or icon
   	*/
    @SuppressLint("ResourceAsColor")
	public ToastIt(Activity activity, boolean useFontAwesome)
	{
		if (activity == null) {
			 throw new IllegalArgumentException("ToastIt: Null Activity!!!");
		}

		 this.mActivity = activity;
		 toastHelper = new ToastHelper(this.mActivity);
		 t = new Toast(this.mActivity);
		 
		 //--Check to see if we are using "custom_font_layout" versus the regular "custom_toast"  
		 if(useFontAwesome)
		 {
 			 //--uses one of the bootstrap icons as its image--
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
			//--uses whatever image is passed to it--
			 mLayoutInflater = (LayoutInflater) this.mActivity.getLayoutInflater();
		     mToastView = mLayoutInflater.inflate(R.layout.custom_image_layout, (ViewGroup) this.mActivity.findViewById(R.id.toast_layout_root));
		     mToastView.setBackgroundColor(Color.parseColor("#00ffffff"));
		     mRootLayout =  (LinearLayout) mToastView.findViewById(R.id.toast_layout);
		     mRootLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
		     mMessageTextView = (TextView) mToastView.findViewById(R.id.toastText);
		     mToastImage =  (ImageView) mToastView.findViewById(R.id.toastImage);
		     pattern = Pattern.compile(HEX_PATTERN);
		 }

	}
	
    
    /**
     * If you are using a reusable Toaster object and do not want to call the 
     * various one time show methods below trying to figure out what to pass to them
     * you can simply make a call to just 'show()'. This 'show()' will work with 
     * only the variables that you initialized with the setter methods.  
     */
    public void show()
    {
	   	 /**ToastHelper is a modified class that allows for the time duration to be adjusted. 
	   	  * If you declare a duration length the ToastHelper class will be called over the Toast class
	   	  * when the view gets displayed.  
	   	  */
	   	 boolean useToasterHelper = false;
	   	 
	   	 //--Need to check for null values before assigning--
	   	 if(getToasterDrawable() != null){
	   		 try{
	   			 mToastImage.setBackground(getToasterDrawable());
	   		 }catch(Exception ex){}
	   	 }
	   	 if(getToasterBitmap() != null){
	   		 try{
	   			 mToastImage.setImageBitmap(getToasterBitmap());
	   		 }catch(Exception ex){}
	   	 }
	   	 if(getToasterFont() != null){
	   		 try{
	   			 mMessageTextView.setTypeface(getToasterFont());
	   		 }catch(Exception ex){}
	   	 }
	   	 if(getToasterDuration() > -1){
	   		 try{
	   			 useToasterHelper= true;
	   			 toastHelper.setDuration(getToasterDuration());
	   		 }catch(Exception ex){}
	   	 }
	   	 if(getToasterTextSize() > -1){
	   		 try{
	   			 mMessageTextView.setTextSize(getToasterTextSize());
	   		 }catch(Exception ex){}
	   	 }
	   	 if(getToasterFlashIcon()){
	   		 try{
	   			 
	   			 if(getToasterAnimationSpeed() != null){
	   	    		 try{
	   	    			
	   	    				switch(getToasterAnimationSpeed())
	   	    				{
	   	    					case FAST: mToastIcon.startFlashing(this.mActivity, true,  FontAwesomeText.AnimationSpeed.FAST);
	   	    						break;
	   	    					case SLOW: mToastIcon.startFlashing(this.mActivity, true,  FontAwesomeText.AnimationSpeed.SLOW);
	   	    						break;
	   	    					default : mToastIcon.startFlashing(this.mActivity, true,  FontAwesomeText.AnimationSpeed.MEDIUM);
	   	    						break;
	   	    				}
	   	    		 }catch(Exception ex){}
	   	    	 }
	   			 else //--Medium speed by default--
	   				 mToastIcon.startFlashing(this.mActivity, true, FontAwesomeText.AnimationSpeed.MEDIUM);
	   		 }catch(Exception ex){}
	   	 }
	   	 if(getToasterRotation() != null){
	   		 try{
	   			 if(getToasterRotation() == Rotations.Clockwise){
	   				 if(getToasterAnimationSpeed() != null){
		   				try{
		   	    				switch(getToasterAnimationSpeed())
		   	    				{
		   	    					case FAST: mToastIcon.startRotate(this.mActivity, true, FontAwesomeText.AnimationSpeed.FAST);
		   	    						break;
		   	    					case SLOW: mToastIcon.startRotate(this.mActivity, true, FontAwesomeText.AnimationSpeed.SLOW);
		   	    						break;
		   	    					default : mToastIcon.startRotate(this.mActivity, true, FontAwesomeText.AnimationSpeed.MEDIUM);
		   	    						break;
		   	    				}
		   	    		 }catch(Exception ex){}
	   				 }
	   				 else
	   					mToastIcon.startRotate(this.mActivity, true, AnimationSpeed.MEDIUM);
	   				}else if(getToasterRotation() == Rotations.CounterClockwise){
	   					if(getToasterAnimationSpeed() != null){
	   						try{
		   	    				switch(getToasterAnimationSpeed())
		   	    				{
		   	    					case FAST: mToastIcon.startRotate(this.mActivity, false, FontAwesomeText.AnimationSpeed.FAST);
		   	    						break;
		   	    					case SLOW: mToastIcon.startRotate(this.mActivity, false, FontAwesomeText.AnimationSpeed.SLOW);
		   	    						break;
		   	    					default : mToastIcon.startRotate(this.mActivity, false, FontAwesomeText.AnimationSpeed.MEDIUM);
		   	    						break;
		   	    				}
		   	    		 }catch(Exception ex){}
	      				 }
	      				 else
	   					mToastIcon.startRotate(this.mActivity, false,  AnimationSpeed.MEDIUM);
	   				}
	   		 }catch(Exception ex){}
	   	 } 
	   	 if(getToasterFontIcon() != null){
	   		 try{
	   			 mToastIcon.setIcon(getToasterFontIcon());
	   		 }catch(Exception ex){}
	   	 } 
	   	if(validate(getToasterTextColor())){
	  		 try{
	  			mMessageTextView.setTextColor(Color.parseColor(getToasterTextColor()));
				mToastIcon.setTextColor(Color.parseColor(getToasterTextColor()));
	  		 }catch(Exception ex){}
	  	}
	   	else{
	   		mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
			mToastIcon.setTextColor(Color.parseColor("#ffffff"));
		}
	   	if(validate(getToasterBGColor())){
	 		 try{
	 			mRootLayout.setBackgroundColor(Color.parseColor(getToasterBGColor()));
	 		 }catch(Exception ex){}
	 	}
	  	else{
	  		mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
	   	 
	
	   	 if(useToasterHelper)
	   	 {
	   		 toastHelper.setView(mToastView);
	   		 toastHelper.show();
	   	 }
	   	 else
	   	 {
	   		 t.setView(mToastView);
	   		 t.show();
	   	 }

    }
    
    /**
     * Various basic show methods that are available to call if not passing an image or icon to use:
     */
    public void show(String message, int style)
 	{
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

 		 t.setView(mToastView);
 		 t.setGravity(Gravity.CENTER_VERTICAL, 100, 100);
 		 t.show();
 	}
    
 	public void show(String message, int style, int length)
 	{
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

 		 toastHelper.setDuration(length);
 		 toastHelper.setView(mToastView);
 		 toastHelper.setGravity(Gravity.CENTER_VERTICAL, 100, 100);
 		 toastHelper.show();
 	}
 	
 	public void show(String message, String textColor, String hexCode)
	{
		mMessageTextView.setText(message);

		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}

	    t.setView(mToastView);
	    t.show();
	}
 	
 	public void show(String message, Typeface font, String textColor, String hexCode)
	{
		mMessageTextView.setText(message);
		
		if(font != null){mMessageTextView.setTypeface(font);}

		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
		
	    t.setView(mToastView);
	    t.show();
	}
 		
 	public void show(String message, String textColor, String hexCode, int length)
	{
		mMessageTextView.setText(message);

		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
		
	    toastHelper.setDuration(length);
	    toastHelper.setView(mToastView);
	    toastHelper.show();
	}
 	
 	public void show(String message, Typeface font, String textColor, String hexCode, int length)
	{
		mMessageTextView.setText(message);
		
		if(font != null){mMessageTextView.setTypeface(font);}

		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
		
		toastHelper.setDuration(length);
		toastHelper.setView(mToastView);
		toastHelper.show();
	}
	
 
    /**
    * Various show methods that are available to call if passing an image to be used:
    */
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

		 toastHelper.setDuration(length);
		 toastHelper.setView(mToastView);
		 toastHelper.setGravity(Gravity.CENTER_VERTICAL, 100, 100);
		 toastHelper.show();
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
		
		toastHelper.setDuration(length);
		toastHelper.setView(mToastView);
		toastHelper.show();
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

	    t.setView(mToastView);
	    t.show();
	}
	
	public void show(Drawable icon, String message, Typeface font, String textColor, String hexCode, int length)
	{
		//Animation animation = AnimationUtils.loadAnimation(this.mActivity, R.anim.anim);
		
		mToastImage.setBackground(icon);
		mMessageTextView.setText(message);
		
		if(font != null){mMessageTextView.setTypeface(font);}

		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}
		
		//mRootLayout.setVisibility(View.INVISIBLE);
	    toastHelper.setDuration(length);
	    toastHelper.setView(mToastView);
	    toastHelper.show();
	    //mRootLayout.setVisibility(View.VISIBLE);
	    //mRootLayout.startAnimation(animation);
		
	}
	
	public void show(Bitmap img, String message, String textColor, String hexCode)
	{
		mToastImage.setImageBitmap(img);
		mMessageTextView.setText(message);
		
		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}

	    t.setView(mToastView);
	    t.show();
	}
	
	public void show(Bitmap img, String message, String textColor, String hexCode, int length)
	{
		mToastImage.setImageBitmap(img);
		mMessageTextView.setText(message);
		
		if(validate(hexCode)){
			mRootLayout.setBackgroundColor(Color.parseColor(hexCode));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mRootLayout.setBackgroundColor(Color.parseColor("#444444"));
		}
		
		if(validate(textColor)){
			mMessageTextView.setTextColor(Color.parseColor(textColor));
		}
		else{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
		}

		toastHelper.setDuration(length);
		toastHelper.setView(mToastView);
		toastHelper.show();
	}
	
	
	/**
    * Various show methods that are available to call if using one of the pre-defined bootstrap icons:
    */
	/*
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
	}*/
	
	public void show(String icon, String message, String textColor, String hexCode)
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

	    toastHelper.setDuration(length);
	    toastHelper.setView(mToastView);
	    toastHelper.show();
	   
	}
	
	public void show(String icon, String message, Typeface font, String textColor, String hexCode)
	{
		mToastIcon.setIcon(icon);
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
			mToastIcon.setTextColor(Color.parseColor(textColor));
		}
		else
		{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
			mToastIcon.setTextColor(Color.parseColor("#ffffff"));
		}

	    t.setView(mToastView);
	    t.show();
	   
	}
	
	public void show(String icon, String message, Typeface font, String textColor, String hexCode, int length)
	{
		mToastIcon.setIcon(icon);
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
			mToastIcon.setTextColor(Color.parseColor(textColor));
		}
		else
		{
			mMessageTextView.setText("Hex code not valid, please try another");
			mMessageTextView.setTextColor(Color.parseColor("#ffffff"));
			mToastIcon.setTextColor(Color.parseColor("#ffffff"));
		}

	    toastHelper.setDuration(length);
	    toastHelper.setView(mToastView);
	    toastHelper.show();
	   
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
	 
	 
	 /**
	  * Using enums for various options for a given property
	  */
	 public enum Speeds {
		    FAST,
		    MEDIUM,
		    SLOW
		 }
	 
	 public enum Rotations {
		    Clockwise,
		    CounterClockwise
		 }

}
