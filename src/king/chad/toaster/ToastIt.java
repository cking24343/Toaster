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
    private Positions toasterGravity = null;

    
	//--Getters--
    /**
     * Gets the drawable value of the ToastIt object
     * 
     * @return drawable
     */
    public Drawable getToasterDrawable(){
    	return toasterDrawable;
    }
    /**
     * Gets the bitmap value of the ToastIt object
     * 
     * @return bitmap
     */
    public Bitmap getToasterBitmap(){
    	return toasterBitmap;
    }
    /**
     * Gets the font value of the ToastIt object
     * 
     * @return typeface
     */
	public Typeface getToasterFont(){
    	return toasterFont;
    }
	/**
     * Gets the duration value of the ToastIt object
     * 
     * @return integer
     */
	public int getToasterDuration(){
    	return toasterDuration;
    }
	/**
     * Gets the text size value of the ToastIt object
     * 
     * @return integer
     */
	public int getToasterTextSize(){
    	return toasterTextSize;
    }
	/**
     * Gets the boolean value that indicates if the toaster 
     * bootstrap icon of the ToastIt object should flash 
     * 
     * @return bool
     */
	public boolean getToasterFlashIcon(){
    	return toasterFlashIcon;
    }
	/**
     * Gets the string value of the bootstrap font icon of the ToastIt object
     * 
     * @return string
     */
    public String getToasterFontIcon(){
    	return toasterFontIcon;
    }
    /**
     * Gets the string value of the text color of the ToastIt object
     * 
     * @return string
     */
    public String getToasterTextColor(){
    	return toasterTextColor;
    }
    /**
     * Gets the string value of the background color of the ToastIt object
     * 
     * @return string
     */
    public String getToasterBGColor(){
    	return toasterBGColor;
    }
    /**
     * Gets the string value of the ToastIt object
     * 
     * @return string
     */
    public String getToasterMessage(){
    	return toasterMessage;
    }
    /**
     * Gets the animation speed value of the ToastIt object
     * 
     * @return speed
     */
    public Speeds getToasterAnimationSpeed(){
    	return toasterAnimationSpeed;
    }
    /**
     * Gets the Rotation value that indicates in what direction the toaster 
     * bootstrap icon of the ToastIt object should rotate 
     * 
     * @return rotation
     */
    public Rotations getToasterRotation(){
    	return toasterRotation;
    }
    /**
     * Gets the Position value of the ToastIt object
     * 
     * @return position
     */
    public Positions getToasterGravity(){
    	return toasterGravity;
    }
    
    
    //--Setters--
    /**
     * Sets the value for the drawable of the ToastIt object
     *
     * @param drawable {@link android.graphics.drawable.Drawable}
     * 
     */
    public void setToasterDrawable(Drawable drawable){
    	toasterDrawable = drawable;
    }
    /**
     * Sets the value for the bitmap of the ToastIt object
     *
     * @param bitmap {@link android.graphics.Bitmap}
     */
    public void setToasterBitmap(Bitmap bitmap){
    	toasterBitmap = bitmap;
    }
    /**
     * Sets the value for the typeface of the ToastIt object
     *
     * @param typeface {@link android.graphics.Typeface}
     */
	public void setToasterFont(Typeface typeface){
    	toasterFont = typeface;
    }
	 /**
     * Sets the value for the duration of the ToastIt object
     *
     * @param integer {@link Integer}
     */
	public void setToasterDuration(int integer){
    	toasterDuration = integer;
    }
	 /**
     * Sets the value for the text size of the ToastIt object
     *
     * @param integer {@link Integer}
     */
	public void setToasterTextSize(int integer){
		toasterTextSize = integer;
    }
	 /**
     * Sets the value of the ToastIt object indicating if it should flash
     *
     * @param bool {@link boolean}
     */
	public void setToasterFlashIcon(boolean bool){
    	toasterFlashIcon = bool;
    }
	 /**
     * Sets the value for bootstrap font icon of the ToastIt object
     *
     * @param string {@link String}
     */
	public void setToasterFontIcon(String string){
    	toasterFontIcon = string;
    }
	/**
     * Sets the value for text color of the ToastIt object
     *
     * @param string {@link String}
     */
	public void setToasterTextColor(String string){
    	toasterTextColor = string;
    }
	/**
     * Sets the value for background color of the ToastIt object
     *
     * @param string {@link String}
     */
	public void setToasterBGColor(String string){
    	toasterBGColor = string;
    }
	/**
     * Sets the text value of the ToastIt object
     *
     * @param string {@link String}
     */
    public void setToasterMessage(String string){
    	toasterMessage = string;
    }
    /**
     * Sets the value of the ToastIt object indicating its animation speed
     *
     * @param speed {@link king.chad.toaster.ToastIt.Speeds}
     */
    public void setToasterAnimationSpeed(Speeds speed){
    	toasterAnimationSpeed = speed;
    }
    /**
     * Sets the value of the ToastIt object indicating the direction it should rotate
     *
     * @param rotate {@link king.chad.toaster.ToastIt.Rotations}
     */
    public void setToasterRotation(Rotations rotate){
    	toasterRotation = rotate;
    }
    /**
     * Sets the value of the ToastIt object indicating its position when being displayed
     *
     * @param position {@link king.chad.toaster.ToastIt.Positions}
     */
    public void setToasterGravity(Positions position){
    	toasterGravity = position;
    }

    /**
     * This allows for the developer to reset the values back to the original state
     * of the ToastIt object.
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
	* @param activity 
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
   	* @param activity
   	* @param useFontAwesome (bool value indicating if ToastIt object should use an image or icon)
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
     * Basic show() method that uses the ToastIt object 
     * values specified by the developer via its set methods.
     *  
     */
    public void show()
    {
	   	 /**ToastHelper - a modified class that allows for the time duration to be adjusted. 
	   	  * If you declare a duration length, the ToastHelper class will be called over the Toast class
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
	   		 if(toasterGravity != null)
	   			 t.setGravity(Integer.parseInt(convertPosition(toasterGravity)), 0, 0);
	   		 
	   		 t.setView(mToastView);
	   		 t.show();
	   	 }

    }
    
    /**
     * Converts the king.chad.toaster.ToastIt.Positions object to its corresponding 
     * integer value in order to set the Gravity for the Toast object being displayed. 
     */
    public String convertPosition(Positions temp)
    {
    	String current_position = null;
    	
    	if(temp.toString().equals("TOP_CENTER"))
    	{
	    	current_position = String.valueOf(Gravity.CENTER_VERTICAL | Gravity.TOP);
    	}else if(temp.toString().equals("TOP_LEFT"))
    	{
	    	current_position = String.valueOf(Gravity.LEFT | Gravity.TOP);
    	}
    	else if(temp.toString().equals("TOP_RIGHT"))
    	{
	    	current_position = String.valueOf(Gravity.RIGHT | Gravity.TOP);
    	}else if(temp.toString().equals("CENTER"))
    	{
	    	current_position = String.valueOf(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
    	}else if(temp.toString().equals("CENTER_LEFT"))
    	{
	    	current_position = String.valueOf(Gravity.LEFT | Gravity.CENTER_VERTICAL);
    	}
    	else if(temp.toString().equals("CENTER_RIGHT"))
    	{
	    	current_position = String.valueOf(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    	}else if(temp.toString().equals("BOTTOM_CENTER"))
    	{
	    	current_position = String.valueOf(Gravity.CENTER_VERTICAL | Gravity.BOTTOM);
    	}else if(temp.toString().equals("BOTTOM_LEFT"))
    	{
	    	current_position = String.valueOf(Gravity.LEFT | Gravity.BOTTOM);
    	}
    	else if(temp.toString().equals("BOTTOM_RIGHT"))
    	{
	    	current_position = String.valueOf(Gravity.RIGHT | Gravity.BOTTOM);
    	}
    	
    	
    	return current_position;
    }
    
    /*
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
	
 
    /*
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
	
	
	/*
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
	  * ToastIt defined enum variable to set the speed values.
	  */
	 public enum Speeds {
		    FAST,
		    MEDIUM,
		    SLOW
		 }
	 
	 /**
	  * ToastIt defined enum variable to set the rotation values.
	  */
	 public enum Rotations {
		    Clockwise,
		    CounterClockwise
		 }
	 
	 /**
	  * ToastIt defined enum variable to set the position values.
	  */
	 public enum Positions{
		TOP_RIGHT, TOP_CENTER, TOP_LEFT,			//--TOP--
		CENTER_RIGHT, CENTER, CENTER_LEFT,	//--CENTER--
		BOTTOM_RIGHT, BOTTOM_CENTER, BOTTOM_LEFT	//--BOTTOM--
	 }

}
