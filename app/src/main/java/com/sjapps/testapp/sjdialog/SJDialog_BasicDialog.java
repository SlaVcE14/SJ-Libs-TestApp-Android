package com.sjapps.testapp.sjdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.sjapps.library.customdialog.BasicDialog;
import com.sjapps.library.customdialog.DialogButtonEvents;
import com.sjapps.library.customdialog.SJDialog;

import com.sjapps.testapp.R;
import com.sjapps.testapp.SettingsData;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class SJDialog_BasicDialog extends AppCompatActivity {

    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();

    boolean deleteDialog;
    boolean setTitle;
    boolean setMessage;
    boolean oldTheme;
    boolean RedBtn1;
    boolean Mat3RedBtn1;
    boolean RedBtn2;
    boolean Mat3RedBtn2;
    boolean appTheme;
    boolean theme1;
    boolean theme2;
    boolean noInsetsTheme;
    boolean setAllBtnColorRed;
    boolean setAllBtnColorMat3Red;
    boolean setTxtColor;
    boolean setTitleTxtColor;
    boolean setMessageTxtColor;
    boolean setBgColor;
    boolean setBtn1Color;
    boolean setBtn2Color;
    boolean setAllBtnColor;
    boolean setBtn1ColorTxt;
    boolean setBtn2ColorTxt;
    boolean setAllBtnColorTxt;
    boolean oneBtnEvent;
    boolean BtnEvents;
    boolean btn1Txt;
    boolean btn2Txt;
    boolean btn1Res;
    boolean btn2Res;
    boolean allBtnRes;
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
    EditText Btn1Color;
    EditText Btn2Color;
    EditText AllBtnColor;
    EditText Btn1ColorTxt;
    EditText Btn2ColorTxt;
    EditText AllBtnColorTxt;
    EditText Btn1ClickMsgTxt;
    EditText Btn2ClickMsgTxt;
    EditText Btn1Txt;
    EditText Btn2Txt;
    EditText maxWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjdialog_setup_dialog);
        rootView = findViewById(R.id.root);
        SettingsData.applyWindowInsets(rootView);

        titleTxt = new EditText(this);
        titleTxt.setText("Title");
        titleTxt.setHint("Title");

        BgColor = new EditText(this);
        Btn1Color = new EditText(this);
        Btn2Color = new EditText(this);
        AllBtnColor = new EditText(this);
        Btn1ColorTxt = new EditText(this);
        Btn2ColorTxt = new EditText(this);
        AllBtnColorTxt = new EditText(this);
        Btn1ClickMsgTxt = new EditText(this);
        Btn2ClickMsgTxt = new EditText(this);
        Btn1Txt = new EditText(this);
        Btn2Txt = new EditText(this);
        maxWidth = new EditText(this);

        messageTxt = new EditText(this);
        messageTxt.setText("Message");
        messageTxt.setHint("Message");
        rootView.addView(titleTxt);
        rootView.addView(messageTxt);
        editTexts.add(titleTxt);
        editTexts.add(messageTxt);

        createViews();


        Button createDialogBtn = new Button(this);
        createDialogBtn.setText("Create Dialog");
        createDialogBtn.setOnClickListener(view -> createDialog());
        rootView.addView(createDialogBtn);
    }

    private void createMultiCheckBox(CBData... cbs) {
        ArrayList<CheckBox> tmpCB = new ArrayList<>();

        for (int i = 0; i < cbs.length; i++) {
            tmpCB.add(createCheckBox(cbs[i].text));
        }
        for (int i = 0; i < cbs.length; i++) {
            tmpCB.get(i).setOnCheckedChangeListener(cbs[i].onCheckedChangeListener);
        }
    }

    private void createViews() {


        createCheckBox("Delete dialog", (compoundButton, b) -> deleteDialog = b);
        createCheckBox("Set Title", (compoundButton, b) -> setTitle = b);
        createCheckBox("Set message", (compoundButton, b) -> setMessage = b);
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
//        createCheckBox("Use app theme",(compoundButton, b) -> appTheme = b);
        createCheckBox("Old theme", (compoundButton, b) -> oldTheme = b);
        createCheckBox("Set text color", (compoundButton, b) -> setTxtColor = b);
        createCheckBox("Set title text color", (compoundButton, b) -> setTitleTxtColor = b);
        createCheckBox("Set message text color", (compoundButton, b) -> setMessageTxtColor = b);
        createCheckBox("Left red Btn", (compoundButton, b) -> RedBtn1 = b);
        createCheckBox("Right red Btn", (compoundButton, b) -> RedBtn2 = b);
        createCheckBox("All red Btn", (compoundButton, b) -> {
            setAllBtnColorRed = b;
            if (!b) return;
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
        });
        createCheckBox("Left material3 red Btn", (compoundButton, b) -> Mat3RedBtn1 = b);
        createCheckBox("Right material3 red Btn", (compoundButton, b) -> Mat3RedBtn2 = b);
        createCheckBox("All material3 red Btn", (compoundButton, b) -> {
            setAllBtnColorMat3Red = b;
            if (!b) return;
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
        });
        createEditTextAndCheckBoxLayout("Set Background Color", BgColor, "#FF006464", "colorBg", (compoundButton, b) -> setBgColor = b);
        createEditTextAndCheckBoxLayout("Set Left Btn Color", Btn1Color, "#FF00FF00", "colorLBtb", (compoundButton, b) -> setBtn1Color = b);
        createEditTextAndCheckBoxLayout("Set Right Btn Color", Btn2Color, "#FF00FF00", "colorRBtb", (compoundButton, b) -> setBtn2Color = b);

        createEditTextAndCheckBoxLayout("Set All Btns Color", AllBtnColor, "#FF00FF00", "colorAllBtb", (compoundButton, b) -> {
            setAllBtnColor = b;
            if (!b) return;
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
        });

        createEditTextAndCheckBoxLayout("Set Left Btn Text", Btn1Txt, "Btn1Txt", "BtnTxtL", (compoundButton, b) -> btn1Txt = b);
        createEditTextAndCheckBoxLayout("Set Right Btn Text", Btn2Txt, "Btn2Txt", "BtnTxtR", (compoundButton, b) -> btn2Txt = b);

        createEditTextAndCheckBoxLayout("Set Left Button Text Color", Btn1ColorTxt, "#FF00FF00", "colorLBtnTxt", (compoundButton, b) -> setBtn1ColorTxt = b);
        createEditTextAndCheckBoxLayout("Set Right Button Text Color", Btn2ColorTxt, "#FF00FF00", "colorRBtnTxt", (compoundButton, b) -> setBtn2ColorTxt = b);

        createEditTextAndCheckBoxLayout("Set All Buttons Text Color", AllBtnColorTxt, "#FF00FF00", "colorAllBtnTxt", (compoundButton, b) -> {
            setAllBtnColorTxt = b;
            if (!b) return;
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
        });

        createCheckBox("Set onClick Right btn", (compoundButton, b) -> oneBtnEvent = b, true);
        createCheckBox("Set onClick All btns", (compoundButton, b) -> BtnEvents = b);
        createEditTextLayout("Set Left btn onClick Msg", Btn1ClickMsgTxt, "Left Btn msg", "Left Btn msg");
        createEditTextLayout("Set Right btn onClick Msg", Btn2ClickMsgTxt, "Right Btn msg", "Right Btn msg");


        createCheckBox("Set Dialog Background Resource", (compoundButton, b) -> dialogRes = b);
        createCheckBox("Set Left Button Background Resource", (compoundButton, b) -> btn1Res = b);
        createCheckBox("Set Right Button Background Resource", (compoundButton, b) -> btn2Res = b);
        createCheckBox("Set All Buttons Background Resource", (compoundButton, b) -> {
            allBtnRes = b;
            if (!b) return;
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
        });
        createEditTextAndCheckBoxLayout("Set Max Dialog Width", maxWidth, "300", "width", (compoundButton, b) -> setMaxWidth = b);
        createCheckBox("Set Custom Animation", (compoundButton, b) -> setAnimation = b);
        createCheckBox("Disable swipe to dismiss", (compoundButton, b) -> disableSwipe = b);
        createCheckBox("Null onTouchListener", (compoundButton, b) -> setNullOnTouchListener = b);
        createCheckBox("set example onTouchListener", (compoundButton, b) -> setOnTouchListener = b);

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
        return createCheckBox(title, null, false);
    }

    private void createCheckBox(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        createCheckBox(title, onCheckedChangeListener, false);
    }

    private CheckBox createCheckBox(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
        rootView.addView(checkBox);
        return checkBox;
    }


    private void createEditTextLayout(String title, EditText editText) {
        createEditTextLayout(title, editText, "#FF00FF00", "");
    }

    private void createEditTextLayout(String title, EditText editText, String text, String hint) {

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
        return createCheckBoxLL(title, onCheckedChangeListener, false);
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

    private void createEditTextAndCheckBoxLayout(String title, EditText editText, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        createEditTextAndCheckBoxLayout(title, editText, "text", "", onCheckedChangeListener, false);

    }

    private void createEditTextAndCheckBoxLayout(String title, EditText editText, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {
        createEditTextAndCheckBoxLayout(title, editText, "text", "", onCheckedChangeListener, def);

    }

    private void createEditTextAndCheckBoxLayout(String title, EditText editText, String text, String hint, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        createEditTextAndCheckBoxLayout(title, editText, text, hint, onCheckedChangeListener, false);

    }

    private void createEditTextAndCheckBoxLayout(String title, EditText editText, String text, String hint, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {

        LinearLayout BtnColorLL = new LinearLayout(this);
        BtnColorLL.setOrientation(LinearLayout.HORIZONTAL);
        editText.setText(text);
        editText.setHint(hint);
        editTexts.add(editText);
        BtnColorLL.addView(createCheckBoxLL(title, onCheckedChangeListener, def));
        BtnColorLL.addView(editText);
        rootView.addView(BtnColorLL);

    }

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

    void createDialog() {
        try {

            BasicDialog dialog = new BasicDialog();
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
            ((MaterialButton) dialog.getLeftButton()).setIcon(getDrawable(R.drawable.ic_settings));
            ((MaterialButton) dialog.getRightButton()).setIcon(getDrawable(R.drawable.ic_delete));
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