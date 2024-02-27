package com.sjapps.testapp.sjdialog;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;

import com.sjapps.library.customdialog.MessageDialog;
import com.sjapps.library.customdialog.SJDialog;
import com.sjapps.testapp.R;

import java.util.ArrayList;
@SuppressWarnings({"SetTextI18n"})
public class SJDialog_MessageDialog extends SJDialog_Base<MessageDialog> {

    boolean error;

    public SJDialog_MessageDialog(){
        super(false,false,"MessageDialog");
    }

    @Override
    void init() {

    }

    @Override
    protected void createViews() {
        createCheckBox("Error Dialog",(compoundButton, b) -> error = b);
        super.createViews();
    }

    @Override
    void DialogBuilder(MessageDialog dialog) {
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

    @Override
    void createDialog() {
        try {

            MessageDialog dialog = new MessageDialog();

            DialogBuilder(dialog);

            if (setPreset1)
                dialog.setPresets(Presets.MessageDialogPreset1);
            if (setPreset2)
                dialog.setPresets(Presets.MessageDialogPreset2);
            if (setPreset3)
                dialog.setPresets(Presets.MessageDialogPreset3);

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
            if (oneBtnEvent)
                dialog.onButtonClick(() -> Toast.makeText(this, Btn1ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show());
            if (btnTxt)
                dialog.setButtonText(BtnTxt.getText().toString());
            if (onShow)
                dialog.onShowListener(d -> Toast.makeText(this, "Dialog shown", Toast.LENGTH_SHORT).show());
            if (onDismiss)
                dialog.onDismissListener(d -> Toast.makeText(this, "Dialog dismissed", Toast.LENGTH_SHORT).show());
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