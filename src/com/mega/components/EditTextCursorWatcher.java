package com.mega.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextCursorWatcher extends EditText {

    public EditTextCursorWatcher(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);

    }

    public EditTextCursorWatcher(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public EditTextCursorWatcher(Context context) {
        super(context);

    }


     @Override   
     protected void onSelectionChanged(int selStart, int selEnd) { 
    	 if (getText().length() > 0){
    		 String [] s = getText().toString().split("\\.");
				int lastSelectedPos = 0;
				if (s != null){
					int numParts = s.length;
					if (numParts == 1){
						lastSelectedPos = s[0].length();
					}
					else{
						for (int i=0; i<(numParts-1);i++){
							lastSelectedPos += s[i].length(); 
							lastSelectedPos++;
						}
						lastSelectedPos--; //The last point should not be selected)
					}
				}
				Log.d("EditTextCursorWatcher", "Texto: " + getText() + "_lastSelectedPos: " + lastSelectedPos + "_selStart: " + selStart + "_selEnd: " + selEnd);
				if (selEnd > lastSelectedPos){
					selStart = lastSelectedPos;
					selEnd = lastSelectedPos;
					setSelection(selStart, selEnd);
				}
//    		 Toast.makeText(getContext(), "selStart is " + selStart + "selEnd is " + selEnd, Toast.LENGTH_SHORT).show();
//    		 Toast.makeText(getContext(), "Texto: " + getText(), Toast.LENGTH_LONG).show();
    	 }
     } 
}