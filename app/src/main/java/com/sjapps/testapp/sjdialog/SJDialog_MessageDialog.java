package com.sjapps.testapp.sjdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjapps.library.customdialog.MessageDialog;
import com.sjapps.library.customdialog.SJDialog;
import com.sjapps.testapp.R;
import com.sjapps.testapp.SettingsData;

import java.util.ArrayList;
@SuppressWarnings({"SuspiciousMethodCalls","SetTextI18n"})
public class SJDialog_MessageDialog extends AppCompatActivity {

    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();

    boolean setTitle;
    boolean setMessage;
    boolean oldTheme;
    boolean error;
    boolean setTxtColor;
    boolean setTitleTxtColor;
    boolean setMessageTxtColor;
    boolean RedBtn;
    boolean Mat3RedBtn;
    boolean appTheme;
    boolean theme1;
    boolean theme2;
    boolean noInsetsTheme;
    boolean setBgColor;
    boolean setBtnColor;
    boolean setBtnColorTxt;
    boolean BtnEvent;
    boolean btnTxt;
    boolean btnRes;
    boolean dialogRes;
    boolean setMaxWidth;
    boolean setAnimation;
    boolean setNullOnTouchListener;
    boolean setOnTouchListener;
    boolean disableSwipe;
    boolean leftInsets;
    boolean rightInsets;
    boolean bottomInsets;
    boolean horizontalInsets;
    boolean allInsets;
    boolean noneInsets;

    LinearLayout rootView;

    EditText titleTxt;
    EditText messageTxt;
    EditText BgColor;
    EditText BtnColor;
    EditText BtnColorTxt;
    EditText BtnClickMsgTxt;
    EditText BtnTxt;
    EditText maxWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjdialog_message_dialog);
        rootView = findViewById(R.id.root);
        SettingsData.applyWindowInsets(rootView);
        titleTxt = new EditText(this);
        messageTxt = new EditText(this);

        BgColor = new EditText(this);
        BtnColor = new EditText(this);
        BtnColor = new EditText(this);
        BtnColorTxt = new EditText(this);
        BtnClickMsgTxt = new EditText(this);
        BtnTxt = new EditText(this);
        maxWidth = new EditText(this);


        createViews();

        Button createDialogBtn = new Button(this);
        createDialogBtn.setText("Create Dialog");
        createDialogBtn.setOnClickListener(view -> createDialog());
        rootView.addView(createDialogBtn);
    }

    private void createMultiCheckBox(CBData...cbs){
        ArrayList<CheckBox> tmpCB = new ArrayList<>();

        for (CBData cb : cbs) {
            tmpCB.add(createCheckBox(cb.text));
        }
        for (int i = 0; i < cbs.length; i++){
            tmpCB.get(i).setOnCheckedChangeListener(cbs[i].onCheckedChangeListener);
        }
    }

    private void createViews() {

        createEditTextAndCheckBoxLayout("Set Title",titleTxt,"Title","title",(compoundButton, b) -> setTitle = b);
        createEditTextAndCheckBoxLayout("Set Message",messageTxt,"Message","message",(compoundButton, b) -> setMessage = b);


//        createCheckBox("Set Title",(compoundButton, b) -> setTitle = b);
//        createCheckBox("Set message",(compoundButton, b) -> setMessage = b);
        createMultiCheckBox(
                new CBData("Use app theme", (compoundButton, b) -> {
                    appTheme = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 2).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 3).setChecked(false);
                }),
                new CBData("Use theme1", (compoundButton, b) -> {
                    theme1 = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 2).setChecked(false);
                }),
                new CBData("Use theme2", (compoundButton, b) -> {
                    theme2 = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 1).setChecked(false);
                }),
                new CBData("Use noInsets theme", (compoundButton, b) -> {
                    noInsetsTheme = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 3).setChecked(false);
                })
        );
        createCheckBox("Old theme",(compoundButton, b) -> oldTheme = b);
        createCheckBox("Error Dialog",(compoundButton, b) -> error = b);

        createCheckBox("Set text color",(compoundButton, b) -> setTxtColor = b);
        createCheckBox("Set title text color",(compoundButton, b) -> setTitleTxtColor = b);
        createCheckBox("Set message text color",(compoundButton, b) -> setMessageTxtColor = b);

        createCheckBox("Red Btn",(compoundButton, b) -> RedBtn = b);

        createCheckBox("material3 red Btn",(compoundButton, b) -> Mat3RedBtn = b);

        createEditTextAndCheckBoxLayout("Set Background Color",BgColor,"#FF006464","colorBg",(compoundButton, b) -> setBgColor = b);

        createEditTextAndCheckBoxLayout("Btn Color",BtnColor,"#FF00FF00","color",(compoundButton, b) -> setBtnColor = b);

        createEditTextAndCheckBoxLayout("Set Btn Text",BtnTxt,"BtnTxt","BtnTxt",(compoundButton, b) -> btnTxt = b);

        createEditTextAndCheckBoxLayout("Set Button Text Color",BtnColorTxt,"#FF00FF00","",(compoundButton, b) -> setBtnColorTxt = b);

        createEditTextAndCheckBoxLayout("Set onClick btn",BtnClickMsgTxt,"Btn msg","Left Btn msg",(compoundButton, b) -> BtnEvent = b);

        createCheckBox("Set Dialog Background Resource",(compoundButton, b) -> dialogRes = b);
        createCheckBox("Set Button Background Resource",(compoundButton, b) -> btnRes = b);

        createEditTextAndCheckBoxLayout("Set Max Dialog Width",maxWidth,"300","width",(compoundButton, b) -> setMaxWidth = b);
        createCheckBox("Set Custom Animation",(compoundButton, b) -> setAnimation = b);
        createCheckBox("Disable swipe to dismiss",(compoundButton, b) -> disableSwipe = b);
        createCheckBox("Null onTouchListener",(compoundButton, b) -> setNullOnTouchListener = b);
        createCheckBox("set example onTouchListener",(compoundButton, b) -> setOnTouchListener = b);

        createMultiCheckBox(
                new CBData("apply left Insets", (compoundButton, b) -> {
                    leftInsets = b;
                    if (!b) return;
                    int thisId = checkBoxes.indexOf(compoundButton);
                    int startId = thisId;
                    checkBoxes.get(startId + 3).setChecked(false);
                    checkBoxes.get(startId + 5).setChecked(false);

                }),
                new CBData("apply right Insets", (compoundButton, b) -> {
                    rightInsets = b;
                    if (!b) return;
                    int thisId = checkBoxes.indexOf(compoundButton);
                    int startId = thisId - 1;
                    checkBoxes.get(startId + 3).setChecked(false);
                    checkBoxes.get(startId + 5).setChecked(false);
                }),
                new CBData("apply bottom Insets", (compoundButton, b) -> {
                    bottomInsets = b;
                    if (!b) return;
                    int thisId = checkBoxes.indexOf(compoundButton);
                    int startId = thisId - 2;
                    checkBoxes.get(startId + 5).setChecked(false);
                }),
                new CBData("apply horizontal Insets", (compoundButton, b) -> {
                    horizontalInsets = b;
                    if (!b) return;
                    int thisId = checkBoxes.indexOf(compoundButton);
                    int startId = thisId - 3;
                    checkBoxes.get(startId).setChecked(false);
                    checkBoxes.get(startId + 1).setChecked(false);
                    checkBoxes.get(startId + 5).setChecked(false);
                }),
                new CBData("apply all Insets", (compoundButton, b) -> {
                    allInsets = b;
                    if (!b) return;
                    int thisId = checkBoxes.indexOf(compoundButton);
                    int startId = thisId - 4;
                    for (int i = startId; i < thisId; i++) {
                        checkBoxes.get(i).setChecked(false);
                    }
                    checkBoxes.get(startId + 5).setChecked(false);
                }),
                new CBData("remove all Insets", (compoundButton, b) -> {
                    noneInsets = b;
                    if (!b) return;
                    int thisId = checkBoxes.indexOf(compoundButton);
                    int startId = thisId - 5;
                    for (int i = startId; i < thisId; i++) {
                        checkBoxes.get(i).setChecked(false);
                    }
                })
        );
    }

    private CheckBox createCheckBox(String title) {
        return createCheckBox(title,null,false);
    }

    private void createCheckBox(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        createCheckBox(title,onCheckedChangeListener,false);
    }

    private CheckBox createCheckBox(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener,boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
        rootView.addView(checkBox);
        return checkBox;
    }

    private void createEditTextLayout(String title, EditText editText){
        createEditTextLayout(title,editText,"#FF00FF00","");
    }

    private void createEditTextLayout(String title, EditText editText,String text,String hint){

        LinearLayout BtnColorLL = new LinearLayout(this);
        BtnColorLL.setOrientation(LinearLayout.HORIZONTAL);
        TextView titleColor = new TextView(this);
        titleColor.setText(title);
        editText.setText(text);
        editText.setHint(hint);
        editTexts.add(editText);
        BtnColorLL.addView(titleColor);
        BtnColorLL.addView(editText);

        rootView.addView(BtnColorLL);


    }
    private CheckBox createCheckBoxLL(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        return createCheckBoxLL(title,onCheckedChangeListener,false);
    }

    private CheckBox createCheckBoxLL(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
//        rootView.addView(checkBox);
        return checkBox;
    }

    private void createEditTextAndCheckBoxLayout(String title, EditText editText,CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        createEditTextAndCheckBoxLayout(title,editText,"text","",onCheckedChangeListener,false);

    }

    private void createEditTextAndCheckBoxLayout(String title, EditText editText,CompoundButton.OnCheckedChangeListener onCheckedChangeListener,boolean def){
        createEditTextAndCheckBoxLayout(title,editText,"text","",onCheckedChangeListener,def);

    }
    private void createEditTextAndCheckBoxLayout(String title, EditText editText,String text,String hint,CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        createEditTextAndCheckBoxLayout(title,editText,text,hint,onCheckedChangeListener,false);

    }

    private void createEditTextAndCheckBoxLayout(String title, EditText editText,String text,String hint,CompoundButton.OnCheckedChangeListener onCheckedChangeListener,boolean def){

        LinearLayout BtnColorLL = new LinearLayout(this);
        BtnColorLL.setOrientation(LinearLayout.HORIZONTAL);
        editText.setText(text);
        editText.setHint(hint);
        editTexts.add(editText);
        BtnColorLL.addView(createCheckBoxLL(title,onCheckedChangeListener,def));
        BtnColorLL.addView(editText);
        rootView.addView(BtnColorLL);

    }

    void DialogBuilder(MessageDialog dialog){

        if (appTheme){
            if (error) {
                dialog.ErrorDialogBuilder(this, true);
                return;
            }
            dialog.Builder(this, true);
            return;
        }
        if (theme1){
            if (error){
                dialog.ErrorDialogBuilder(this,R.style.Test123);
                return;
            }
            dialog.Builder(this, R.style.Test123);
            return;
        }
        if (theme2){
            if (error){
                dialog.ErrorDialogBuilder(this, com.google.android.material.R.style.Theme_Material3_DayNight);
                return;
            }
            dialog.Builder(this, com.google.android.material.R.style.Theme_Material3_DayNight);
            return;
        }
        if (noInsetsTheme) {
            if (error) {
                dialog.ErrorDialogBuilder(this, com.sjapps.library.R.style.Theme_SJDialog_NoInsets);
                return;
            }
            dialog.Builder(this, com.sjapps.library.R.style.Theme_SJDialog_NoInsets);
            return;
        }
        if (error){
            dialog.ErrorDialogBuilder(this);
            return;
        }
        dialog.Builder(this);
    }

    void createDialog(){
        try {

            MessageDialog dialog = new MessageDialog();

            DialogBuilder(dialog);

            if (setAnimation)
                dialog.setDialogAnimations(R.style.testAnimation);

            if (disableSwipe)
                dialog.swipeToDismiss(false);
            if (setNullOnTouchListener)
                dialog.setOnTouchListener(null);
            if (setOnTouchListener)
                dialog.setOnTouchListener(new ExampleOnTouchListener(this));

            if (setTitle)
                dialog.setTitle(titleTxt.getText().toString());
            if (setMessage)
                dialog.setMessage(messageTxt.getText().toString());
            if (oldTheme)
                dialog.setOldTheme();
            if (setTxtColor)
                dialog.setTextColor(Color.MAGENTA);
            if (setTitleTxtColor)
                dialog.setTitleTextColor(Color.MAGENTA);
            if (setMessageTxtColor)
                dialog.setMessageTextColor(Color.MAGENTA);
            if (RedBtn)
                dialog.setButtonColor(SJDialog.RED_BUTTON);
            if (Mat3RedBtn)
                dialog.setButtonColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (setBtnColor)
                dialog.setButtonColor((int) Color.parseColor(BtnColor.getText().toString()));
            if (setBtnColorTxt)
                dialog.setButtonTextColor((int) Color.parseColor(BtnColorTxt.getText().toString()));
            if (BtnEvent)
                dialog.onButtonClick(() -> Toast.makeText(this, BtnClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show());
            if (btnTxt)
                dialog.setButtonText(BtnTxt.getText().toString());
            if (dialogRes)
                dialog.setDialogBackgroundResource(R.drawable.test1234);
            if (setBgColor)
                dialog.setDialogBackgroundColor((int) Color.parseColor(BgColor.getText().toString()));
            if (btnRes)
                dialog.setButtonBackgroundResource(R.drawable.testbtn);

            if (setMaxWidth)
                dialog.setMaxDialogWidth(Integer.parseInt(maxWidth.getText().toString()));
            if (leftInsets || rightInsets || bottomInsets || horizontalInsets || allInsets)
                dialog.applyInsets(
                        (leftInsets? SJDialog.INSETS_LEFT : 0) |
//                                (topInsets ? SJDialog.INSETS_TOP : 0) |
                                (rightInsets ? SJDialog.INSETS_RIGHT : 0) |
                                (bottomInsets ? SJDialog.INSETS_BOTTOM : 0) |
//                                (verticleInsets ? SJDialog.INSETS_VERTICAL : 0) |
                                (horizontalInsets ? SJDialog.INSETS_HORIZONTAL : 0) |
                                (allInsets ? SJDialog.INSETS_ALL : 0)
                );
            if (noneInsets)
                dialog.applyInsets(SJDialog.INSETS_NONE);
            dialog.show();

        }catch (Exception e){
            ArrayList<ObjData> arr = new ArrayList<>();

            for (EditText editText : editTexts){
                LogDialog.txt(editText.getHint().toString(),editText.getText().toString());
                arr.add(new ObjData(editText.getHint().toString(),editText.getText().toString()));
            }
            LogDialog.line();
            for (CheckBox checkBox : checkBoxes) {
                LogDialog.bool(checkBox.getText().toString(),checkBox.isChecked());
                arr.add(new ObjData(checkBox.getText().toString(),checkBox.isChecked()));
            }

            throw new RuntimeException(e + "     " + arr);

        }
    }
}