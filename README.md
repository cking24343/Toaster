Toaster
=======

(***Please keep in mind that this is a work in progress***) - A simple custom extension of the existing Android toast class. This will give users a few options to customize a toast notification to become more consistent with your existing application.

How To Use
==============
Create a Toaster Object and Initialize it:
```java
  //--Toaster Object--
  ToastIt toastIt;
  
  //--Initialize our toaster as such--
  toastIt = new ToastIt(this);
  
  //--OR--
	
  //--If using images/icons then initialize our toaster as such--
  toastIt = new ToastIt(this, true);
  
```

Note:
=====
ToastIt has various "show(...)" methods allowing options for any developer to either make one-time calls passing it different variables to customize it when used. If you want to make one Toastit object and make it reusable
ToastIt also supports just a "show()" method that will use the ToastIt object values defined by the user.

Example 1 (recommended for reusable ToastIt objects):
=====================================================
Make a call using just the "show()" method of the toaster object:
```java
  //--Set the values of the toaster object (once declared)--
  toastIt.setToasterMessage("Hello Toaster!!!");
  toastIt.setToasterTextColor("#ffffff");
  toastIt.setToasterBGColor("#000000");
  toastIt.setToasterTextSize(14);
  
  //--Basic call to toaster object--
  toastIt.show();
  
```

Example 2 (recommended for one-time use ToastIt object):
========================================================
Make a call using some of the various "show(...)" methods of the toaster object:
```java
  //--Basic call to toaster object--
  toastIt.show(
	"Custom Toaster Message",         //--pass message to be displayed--
	font,                             //--pass typeface font file to be used--
	textHexCode,                      //--pass hexcode value for the text color of toaster--
	bgHexCode);                       //--pass hexcode value for the background color of toaster--
	
	
  //--OR--
  
  
  /*
  * If passing images you have two options, you can pass either an image file 
  * or you can use the font awesome icons that are included in the Toaster Lib by 
  * simply passing it the name of the icon.
  */
  
  //--Basic call to toaster object if using images--
  toastIt.show(
	getResources().getDrawable(R.drawable.ic_launcher), //--pass image--
	"Custom Toaster Message",         //--pass message to be displayed--
	font,                             //--pass typeface font file to be used--
	textHexCode,                      //--pass hexcode value for the text color of toaster--
	bgHexCode);                       //--pass hexcode value for the background color of toaster--
	
  //--Basic call to toaster object if using font awesome icon--
  toastIt.show(
	"fa-github"),                     //--pass icon name--
	"Custom Toaster Message",         //--pass message to be displayed--
	font,                             //--pass typeface font file to be used--
	textHexCode,                      //--pass hexcode value for the text color of toaster--
	bgHexCode);                       //--pass hexcode value for the background color of toaster--
```


Demo Application
================
A simple demo application can be found on Google Play Store.

<a href="https://play.google.com/store/apps/details?id=king.chad.toastersample">
  <img alt="Google Play Store"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_60.png" />
</a>

Source Code for sample app:
https://github.com/cking24343/ToasterSample


Github Projects Referenced:
===========================
Bootstrap icon implementation (used in Toaster Lib):
https://github.com/Bearded-Hen/Android-Bootstrap

Usage of the HoloColorPicker control:
https://github.com/LarsWerkman/HoloColorPicker
