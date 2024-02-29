package com.sjapps.testapp.sjdialog;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sjapps.library.customdialog.BasicDialog;
import com.sjapps.library.customdialog.DialogButtonEvents;
import com.sjapps.library.customdialog.SJDialog;

import com.sjapps.testapp.R;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class SJDialog_BasicDialog extends SJDialog_Base<BasicDialog> {

    boolean deleteDialog;

    public SJDialog_BasicDialog() {
        super(true, true,"BasicDialog");
    }

    @Override
    void init() {

    }

    @Override
    protected void createViews() {
        createCheckBox("Delete dialog", (compoundButton, b) -> deleteDialog = b);
        super.createViews();
    }

    @Override
    void DialogBuilder(BasicDialog dialog) {
        if (appTheme) {
            if (deleteDialog) {
                dialog.Delete(this, true);
                return;
            }
            dialog.Builder(this, true);
            return;
        }
        if (theme1) {
            if (deleteDialog) {
                dialog.Delete(this, R.style.Test123);
                return;
            }
            dialog.Builder(this, R.style.Test123);
            return;
        }
        if (theme2) {
            if (deleteDialog) {
                dialog.Delete(this, com.google.android.material.R.style.Theme_Material3_DayNight);
                return;
            }
            dialog.Builder(this, com.google.android.material.R.style.Theme_Material3_DayNight);
            return;
        }
        if (noInsetsTheme) {
            if (deleteDialog) {
                dialog.Delete(this, com.sjapps.library.R.style.Theme_SJDialog_NoInsets);
                return;
            }
            dialog.Builder(this, com.sjapps.library.R.style.Theme_SJDialog_NoInsets);
            return;
        }
        if (deleteDialog) {
            dialog.Delete(this);
            return;
        }
        dialog.Builder(this);
    }

    @Override
    void createDialog() {
        try {

            BasicDialog dialog = new BasicDialog();
            DialogBuilder(dialog);

            if (setPreset1)
                dialog.setPresets(Presets.BasicDialogPreset1);
            if (setPreset2)
                dialog.setPresets(Presets.BasicDialogPreset2);
            if (setPreset3)
                dialog.setPresets(Presets.BasicDialogPreset3);

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
            if (RedBtn1)
                dialog.setLeftButtonColor(SJDialog.RED_BUTTON);
            if (RedBtn2)
                dialog.setRightButtonColor(SJDialog.RED_BUTTON);
            if (Mat3RedBtn1)
                dialog.setLeftButtonColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (Mat3RedBtn2)
                dialog.setRightButtonColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (setAllBtnColorRed)
                dialog.setButtonsColor(SJDialog.RED_BUTTON);
            if (setAllBtnColorMat3Red)
                dialog.setButtonsColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (setBtn1Color)
                dialog.setLeftButtonColor((int) Color.parseColor(Btn1Color.getText().toString()));
            if (setBtn2Color)
                dialog.setRightButtonColor((int) Color.parseColor(Btn2Color.getText().toString()));
            if (setBtn1ColorTxt)
                dialog.setLeftButtonTextColor((int) Color.parseColor(Btn1ColorTxt.getText().toString()));
            if (setBtn2ColorTxt)
                dialog.setRightButtonTextColor((int) Color.parseColor(Btn2ColorTxt.getText().toString()));
            if (setAllBtnColor)
                dialog.setButtonsColor((int) Color.parseColor(AllBtnColor.getText().toString()));
            if (setAllBtnColorTxt)
                dialog.setButtonsTextColor((int) Color.parseColor(AllBtnColorTxt.getText().toString()));
            if (oneBtnEvent)
                dialog.onButtonClick(() -> Toast.makeText(this, Btn2ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show());
            if (BtnEvents)
                dialog.onButtonClick(new DialogButtonEvents() {
                    @Override
                    public void onLeftButtonClick() {
                        Toast.makeText(SJDialog_BasicDialog.this, Btn1ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightButtonClick() {
                        Toast.makeText(SJDialog_BasicDialog.this, Btn2ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            if (btn1Txt)
                dialog.setLeftButtonText(Btn1Txt.getText().toString());
            if (btn2Txt)
                dialog.setRightButtonText(Btn2Txt.getText().toString());

            if (onShow)
                dialog.onShowListener(d -> Toast.makeText(this, "Dialog shown", Toast.LENGTH_SHORT).show());
            if (onDismiss)
                dialog.onDismissListener(d -> Toast.makeText(this, "Dialog dismissed", Toast.LENGTH_SHORT).show());
            if (dialogRes)
                dialog.setDialogBackgroundResource(R.drawable.test1234);
            if (setBgColor)
                dialog.setDialogBackgroundColor((int) Color.parseColor(BgColor.getText().toString()));
            if (btn1Res)
                dialog.setLeftButtonBackgroundResource(R.drawable.testbtn);
            if (btn2Res)
                dialog.setRightButtonBackgroundResource(R.drawable.testbtn);
            if (allBtnRes)
                dialog.setButtonsBackgroundResource(R.drawable.testbtn);
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

        } catch (Exception e) {
            ArrayList<ObjData> arr = new ArrayList<>();

            for (EditText editText : editTexts) {
                LogDialog.txt(editText.getHint().toString(), editText.getText().toString());
                arr.add(new ObjData(editText.getHint().toString(), editText.getText().toString()));
            }
            LogDialog.line();
            for (CheckBox checkBox : checkBoxes) {
                LogDialog.bool(checkBox.getText().toString(), checkBox.isChecked());
                arr.add(new ObjData(checkBox.getText().toString(), checkBox.isChecked()));
            }

            throw new RuntimeException(e + "     " + arr);

        }
    }
}