package com.sjapps.testapp.sjdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.button.MaterialButton;
import com.sjapps.testapp.R;
import com.sjapps.testapp.SettingsData;

import java.util.ArrayList;

@SuppressWarnings({"ALL"})
public abstract class SJDialog_Base<T> extends AppCompatActivity {

    String title;

    protected ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    protected ArrayList<EditText> editTexts = new ArrayList<>();

    boolean hasTwoButtons;
    boolean twoButtonsRequired;

    boolean setPreset1;
    boolean setPreset2;
    boolean setPreset3;

    protected boolean setTitle;
    protected boolean setMessage;
    protected boolean oldTheme;
    protected boolean setTxtColor;
    protected boolean setTitleTxtColor;
    protected boolean setMessageTxtColor;
    protected boolean twoBtns;
    protected boolean RedBtn;
    protected boolean RedBtn1;
    protected boolean RedBtn2;
    protected boolean Mat3RedBtn;
    protected boolean Mat3RedBtn1;
    protected boolean Mat3RedBtn2;
    protected boolean appTheme;
    protected boolean theme1;
    protected boolean theme2;
    protected boolean noInsetsTheme;
    protected boolean setAllBtnColorRed;
    protected boolean setAllBtnColorMat3Red;
    protected boolean setBgColor;
    protected boolean setBtnColor;
    protected boolean setBtn1Color;
    protected boolean setBtn2Color;
    protected boolean setAllBtnColor;
    protected boolean setBtnColorTxt;
    protected boolean setBtn1ColorTxt;
    protected boolean setBtn2ColorTxt;
    protected boolean setAllBtnColorTxt;
    protected boolean oneBtnEvent;
    protected boolean BtnEvents;
    protected boolean onShow;
    protected boolean onDismiss;
    protected boolean btnTxt;
    protected boolean btn1Txt;
    protected boolean btn2Txt;
    protected boolean btnRes;
    protected boolean btn1Res;
    protected boolean btn2Res;
    protected boolean allBtnRes;
    protected boolean dialogRes;
    protected boolean setMaxWidth;
    protected boolean setAnimation;
    protected boolean setNullOnTouchListener;
    protected boolean setOnTouchListener;
    protected boolean disableSwipe;
    protected boolean leftInsets;
    protected boolean rightInsets;
    protected boolean bottomInsets;
    protected boolean horizontalInsets;
    protected boolean allInsets;
    protected boolean noneInsets;


    protected LinearLayout rootView;

    MaterialButton createBtn;
    protected EditText titleTxt;
    protected EditText messageTxt;
    protected EditText BgColor;
    protected EditText BtnColor;
    protected EditText Btn1Color;
    protected EditText Btn2Color;
    protected EditText AllBtnColor;
    protected EditText BtnColorTxt;
    protected EditText Btn1ColorTxt;
    protected EditText Btn2ColorTxt;
    protected EditText AllBtnColorTxt;
    protected EditText BtnClickMsgTxt;
    protected EditText Btn1ClickMsgTxt;
    protected EditText Btn2ClickMsgTxt;
    protected EditText BtnTxt;
    protected EditText Btn1Txt;
    protected EditText Btn2Txt;
    protected EditText maxWidth;


    public SJDialog_Base(boolean hasTwoButtons, boolean twoButtonsRequired){
        this(hasTwoButtons,twoButtonsRequired,"");
    }
    public SJDialog_Base(boolean hasTwoButtons, boolean twoButtonsRequired, String title){
        this.hasTwoButtons = hasTwoButtons;
        this.twoButtonsRequired = twoButtonsRequired;
        this.title = title;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjdialog_dialog);
        rootView = findViewById(R.id.root);
        SettingsData.applyWindowInsets(rootView);

        initialize();

    }

    abstract void init();
//    abstract void tests();
//    abstract boolean hasTwoButtons();


    void initialize(){

        createBtn = findViewById(R.id.createDialogBtn);
        createBtn.setOnClickListener(view -> createDialog());
        titleTxt = new EditText(this);
        messageTxt = new EditText(this);

        BgColor = new EditText(this);
        BtnColor = new EditText(this);
        Btn1Color = new EditText(this);
        Btn2Color = new EditText(this);
        AllBtnColor = new EditText(this);
        BtnColorTxt = new EditText(this);
        Btn1ColorTxt = new EditText(this);
        Btn2ColorTxt = new EditText(this);
        AllBtnColorTxt = new EditText(this);
        BtnClickMsgTxt = new EditText(this);
        Btn1ClickMsgTxt = new EditText(this);
        Btn2ClickMsgTxt = new EditText(this);
        BtnTxt = new EditText(this);
        Btn1Txt = new EditText(this);
        Btn2Txt = new EditText(this);
        maxWidth = new EditText(this);

        init();

        if(!title.equals(""))
            addTitle();

        createViews();

//        Button createDialogBtn = new Button(this);
//        createDialogBtn.setText("Create Dialog");
//        createDialogBtn.setOnClickListener(view -> createDialog());
//        rootView.addView(createDialogBtn);
    }

    private void addTitle() {
        TextView titleTv =  new TextView(this);
        titleTv.setText(title);
        titleTv.setTextSize(25);
        titleTv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rootView.addView(titleTv);
        addSpace();
    }

    boolean checkTwoBtns(CompoundButton compoundButton,boolean b,boolean b2){

        if (twoButtonsRequired)
            return b;

        if (b2) {
            compoundButton.setChecked(false);
            return false;
        }
        if (b && !twoBtns) {
            Toast.makeText(this, "Enable Two Buttons!!", Toast.LENGTH_SHORT).show();
            compoundButton.setChecked(false);
            return false;
        }
        return b;
    }

    protected boolean checkBool(CompoundButton compoundButton,boolean b, boolean b2) {
        if (b && b2){
            compoundButton.setChecked(false);
            return false;
        }
        return b;
    }

    protected boolean uncheck(CompoundButton compoundButton, boolean b, boolean b2,int n, boolean b3){
        if (b3 && twoButtonsRequired)
            return b;
        return uncheck(compoundButton, b, b2,n);
    }
    protected boolean uncheck(CompoundButton compoundButton, boolean b, boolean b2,int n){

        if (b2) {
            compoundButton.setChecked(false);
            return false;
        }
        if (!b) return false;
        for (int i = 1; i<= n; i++)
            checkBoxes.get(checkBoxes.indexOf(compoundButton)-i).setChecked(false);

        return true;
    }


    protected void createViews() {


        createMultiCheckBox(
                new CBData("Preset - 1", (compoundButton, b) -> {
                    setPreset1 = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 2).setChecked(false);
                }),new CBData("Preset - 2", (compoundButton, b) -> {
                    setPreset2 = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) + 1).setChecked(false);
                }),new CBData("Preset - 3", (compoundButton, b) -> {
                    setPreset3 = b;
                    if (!b) return;
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 1).setChecked(false);
                    checkBoxes.get(checkBoxes.indexOf(compoundButton) - 2).setChecked(false);
                })
        );

        addSpace();

        createEditTextAndCheckBoxLayout("Set Title",titleTxt,"Title","title",(compoundButton, b) -> setTitle = b);
        createEditTextAndCheckBoxLayout("Set Message",messageTxt,"Message","message",(compoundButton, b) -> setMessage = b);

        if(hasTwoButtons && !twoButtonsRequired)
            createCheckBox("Two Buttons",(compoundButton, b) -> twoBtns = b);
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

        createCheckBox("Set text color",(compoundButton, b) -> setTxtColor = b);
        createCheckBox("Set title text color",(compoundButton, b) -> setTitleTxtColor = b);
        createCheckBox("Set message text color",(compoundButton, b) -> setMessageTxtColor = b);

        if(!twoButtonsRequired)
            createCheckBox("red Btn",(compoundButton, b) -> RedBtn = checkBool(compoundButton,b,RedBtn1));
        if(hasTwoButtons) {
            createCheckBox("Left red Btn", (compoundButton, b) -> RedBtn1 = uncheck(compoundButton, b, setAllBtnColorRed, 1,true));
            createCheckBox("Right red Btn", (compoundButton, b) -> RedBtn2 = checkTwoBtns(compoundButton, b, setAllBtnColorRed));
            createCheckBox("All red Btn", (compoundButton, b) -> setAllBtnColorRed = uncheck(compoundButton, b, false, 2));
        }

        if(!twoButtonsRequired)
            createCheckBox("material3 red Btn",(compoundButton, b) -> Mat3RedBtn = checkBool(compoundButton,b,Mat3RedBtn1));
        if(hasTwoButtons) {
            createCheckBox("Left material3 red Btn", (compoundButton, b) -> Mat3RedBtn1 = uncheck(compoundButton, b, setAllBtnColorMat3Red, 1,true));
            createCheckBox("Right material3 red Btn", (compoundButton, b) -> Mat3RedBtn2 = checkTwoBtns(compoundButton, b, setAllBtnColorMat3Red));
            createCheckBox("All material3 red Btn", (compoundButton, b) -> setAllBtnColorMat3Red = uncheck(compoundButton, b, false, 2));
        }

        createEditTextAndCheckBoxLayout("Set Background Color",BgColor,"#FF006464","colorBg",(compoundButton, b) -> setBgColor = b);
        if(!twoButtonsRequired)
            createEditTextAndCheckBoxLayout("Set Btn Color",BtnColor,"#FF00FF00","Btn color",(compoundButton, b) -> setBtnColor = checkBool(compoundButton,b,setBtn1Color));
        if(hasTwoButtons) {
            createEditTextAndCheckBoxLayout("Set Left Btn Color", Btn1Color, "#FF00FF00", "Left Btn color", (compoundButton, b) -> setBtn1Color = uncheck(compoundButton, b, setAllBtnColor, 1,true));
            createEditTextAndCheckBoxLayout("Set Right Btn Color", Btn2Color, "#FF00FF00", "Right Btn color", (compoundButton, b) -> setBtn2Color = checkTwoBtns(compoundButton, b, setAllBtnColor));
            createEditTextAndCheckBoxLayout("Set All Btns Color",AllBtnColor,"#FF00FF00","All Btns color",(compoundButton, b) -> setAllBtnColor = uncheck(compoundButton,b,false,2));
        }

        if(!twoButtonsRequired)
            createEditTextAndCheckBoxLayout("Set Btn Text",BtnTxt,"BtnTxt","BtnTxt",(compoundButton, b) -> btnTxt = checkBool(compoundButton,b,btn1Txt));
        if(hasTwoButtons) {
            createEditTextAndCheckBoxLayout("Set Left Btn Text", Btn1Txt, "Btn1Txt", "BtnTxt", (compoundButton, b) -> btn1Txt = uncheck(compoundButton, b, false, 1,true));
            createEditTextAndCheckBoxLayout("Set Right Btn Text", Btn2Txt, "Btn2Txt", "BtnTxt", (compoundButton, b) -> btn2Txt = checkTwoBtns(compoundButton, b, false));
        }

        if(!twoButtonsRequired)
            createEditTextAndCheckBoxLayout("Set Button Text Color",BtnColorTxt,"#FF00FF00","Button Text Color",(compoundButton, b) -> setBtnColorTxt = b);
        if(hasTwoButtons) {
            createEditTextAndCheckBoxLayout("Set Left Button Text Color", Btn1ColorTxt, "#FF00FF00", "Left Button Text Color", (compoundButton, b) -> setBtn1ColorTxt = uncheck(compoundButton, b, setAllBtnColorTxt, 1,true));
            createEditTextAndCheckBoxLayout("Set Right Button Text Color", Btn2ColorTxt, "#FF00FF00", "Right Button Text Color", (compoundButton, b) -> setBtn2ColorTxt = checkTwoBtns(compoundButton, b, setAllBtnColorTxt));
            createEditTextAndCheckBoxLayout("Set All Buttons Text Color",AllBtnColorTxt,"#FF00FF00","All Buttons Text Color",(compoundButton, b) -> setAllBtnColorTxt = uncheck(compoundButton,b,false,2));
        }

        createCheckBox("Set onClick btn",(compoundButton, b) -> oneBtnEvent = b);
        if(hasTwoButtons)
            createCheckBox("Set onClick All btns",(compoundButton, b) -> BtnEvents = b);
        createEditTextLayout("Set " + (hasTwoButtons?"Left ":"") + "btn onClick Msg",Btn1ClickMsgTxt,(hasTwoButtons?"Left ":"") +  "Btn msg", (hasTwoButtons?"Left ":"") + "Btn msg");
        if(hasTwoButtons)
            createEditTextLayout("Set Right btn onClick Msg",Btn2ClickMsgTxt,"Right Btn msg","Right Btn msg");

        createCheckBox("Set onShowListener",(compoundButton, b) -> onShow = b);
        createCheckBox("Set onDismissListener",(compoundButton, b) -> onDismiss = b);

        createCheckBox("Set Dialog Background Resource",(compoundButton, b) -> dialogRes = b);

        if(!twoButtonsRequired)
            createCheckBox("Set Button Resource",(compoundButton, b) -> btnRes = checkBool(compoundButton,b,btn1Res));
        if(hasTwoButtons) {
            createCheckBox("Set Left Button Background Resource", (compoundButton, b) -> btn1Res = uncheck(compoundButton, b, allBtnRes, 1,true));
            createCheckBox("Set Right Button Background Resource", (compoundButton, b) -> btn2Res = checkTwoBtns(compoundButton, b, allBtnRes));
            createCheckBox("Set All Buttons Background Resource", (compoundButton, b) -> allBtnRes = uncheck(compoundButton, b, false, 2));
        }

        createEditTextAndCheckBoxLayout("Set Max Dialog Width",maxWidth,"300","width",(compoundButton, b) -> setMaxWidth = b);
        createCheckBox("Set Custom Animation",(compoundButton, b) -> setAnimation = b);
        createCheckBox("Disable swipe to dismiss",(compoundButton, b) -> disableSwipe = b);
        createCheckBox("Null onTouchListener",(compoundButton, b) -> setNullOnTouchListener = b);
        createCheckBox("set example onTouchListener",(compoundButton, b) -> setOnTouchListener = b);

        createMultiCheckBox(
                new CBData("apply left Insets", (compoundButton, b) -> {
                    leftInsets = b;
                    if (!b) return;
                    int startId = checkBoxes.indexOf(compoundButton);
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

    protected void addSpace() {
        View view = new View(this);
        view.setMinimumHeight(50);
        rootView.addView(view);
    }

    protected void createMultiCheckBox(CBData...cbs){
        ArrayList<CheckBox> tmpCB = new ArrayList<>();

        for (CBData cb : cbs) {
            tmpCB.add(createCheckBox(cb.text));
        }
        for (int i = 0; i < cbs.length; i++){
            tmpCB.get(i).setOnCheckedChangeListener(cbs[i].onCheckedChangeListener);
        }
    }

    protected CheckBox createCheckBox(String title) {
        return createCheckBox(title, null, false);
    }
    protected void createCheckBox(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        createCheckBox(title,onCheckedChangeListener,false);
    }
    protected CheckBox createCheckBox(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener,boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
        rootView.addView(checkBox);
        return checkBox;
    }
    protected void createEditTextLayout(String title, EditText editText){
        createEditTextLayout(title,editText,"#FF00FF00","");
    }
    protected void createEditTextLayout(String title, EditText editText,String text,String hint){

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
    protected CheckBox createCheckBoxLL(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        return createCheckBoxLL(title,onCheckedChangeListener,false);
    }
    protected CheckBox createCheckBoxLL(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
//        rootView.addView(checkBox);
        return checkBox;
    }
    protected void createEditTextAndCheckBoxLayout(String title, EditText editText,CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        createEditTextAndCheckBoxLayout(title,editText,"text","",onCheckedChangeListener,false);

    }

    protected void createEditTextAndCheckBoxLayout(String title, EditText editText,CompoundButton.OnCheckedChangeListener onCheckedChangeListener,boolean def){
        createEditTextAndCheckBoxLayout(title,editText,"text","",onCheckedChangeListener,def);

    }
    protected void createEditTextAndCheckBoxLayout(String title, EditText editText,String text,String hint,CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        createEditTextAndCheckBoxLayout(title,editText,text,hint,onCheckedChangeListener,false);

    }
    protected void createEditTextAndCheckBoxLayout(String title, EditText editText,String text,String hint,CompoundButton.OnCheckedChangeListener onCheckedChangeListener,boolean def){

        LinearLayout BtnColorLL = new LinearLayout(this);
        BtnColorLL.setOrientation(LinearLayout.HORIZONTAL);
        editText.setText(text);
        editText.setHint(hint);
        editTexts.add(editText);
        BtnColorLL.addView(createCheckBoxLL(title,onCheckedChangeListener,def));
        BtnColorLL.addView(editText);
        rootView.addView(BtnColorLL);

    }



    abstract void DialogBuilder(T dialog);

    abstract void createDialog();
}
