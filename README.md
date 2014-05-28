Toaster
=======

(***Please keep in mind that this is a work in progress***) - A simple custom extension of the existing Android toast class. This will give users a few options to customize a toast notification to become more consistent with your existing application.

How To Use
==============
Create a Toaster Object and Initialize it:
```java
  //--Toaster Object--
  ToastIt toastIt;
	
  //--Initialize our toaster--
  toastIt = new ToastIt(this, true);
  
```

Make a call to show toaster object:
```java
  //--Basic call to toaster object--
  toastIt.show(
	getResources().getDrawable(R.drawable.ic_launcher), //--pass image--
	"Custom Toaster Message",         //--pass message to be displayed--
	font,                             //--pass typeface font file to be used--
	textHexCode,                      //--pass hexcode value for the text color of toaster--
	bgHexCode);                       //--pass hexcode value for the background color of toaster--
```

Sample App can be found here: 
=============================
https://github.com/cking24343/ToasterSample


Github Projects Referenced:
===========================
Boostrap icon implementation (used in Toaster Lib):
https://github.com/Bearded-Hen/Android-Bootstrap

Usage of the HoloColorPicker control:
https://github.com/LarsWerkman/HoloColorPicker
