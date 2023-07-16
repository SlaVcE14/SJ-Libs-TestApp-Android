package com.sjapps.testapp.sjdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sjapps.library.customdialog.DialogButtonEvents;
import com.sjapps.library.customdialog.ImageListItem;
import com.sjapps.library.customdialog.ListDialog;
import com.sjapps.library.customdialog.ListItemValues;
import com.sjapps.library.customdialog.SJDialog;
import com.sjapps.testapp.R;
import com.sjapps.testapp.SettingsData;

import java.util.ArrayList;

public class SJDialog_ListDialog extends AppCompatActivity {

    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    ArrayList<EditText> editTexts = new ArrayList<>();

    boolean setTitle;
    boolean setMessage;
    boolean oldTheme;
    boolean hideEmptyListTxt;
    boolean setTxtColor;
    boolean setTitleTxtColor;
    boolean setMessageTxtColor;
    boolean twoBtns;
    boolean RedBtn;
    boolean RedBtn1;
    boolean RedBtn2;
    boolean Mat3RedBtn;
    boolean Mat3RedBtn1;
    boolean Mat3RedBtn2;
    boolean appTheme;
    boolean theme1;
    boolean theme2;
    boolean noInsetsTheme;
    boolean setAllBtnColorRed;
    boolean setAllBtnColorMat3Red;
    boolean setBgColor;
    boolean setBtnColor;
    boolean setBtn1Color;
    boolean setBtn2Color;
    boolean setAllBtnColor;
    boolean setBtnColorTxt;
    boolean setBtn1ColorTxt;
    boolean setBtn2ColorTxt;
    boolean setAllBtnColorTxt;
    boolean oneBtnEvent;
    boolean BtnEvents;
    boolean btnTxt;
    boolean btn1Txt;
    boolean btn2Txt;
    boolean btnRes;
    boolean btn1Res;
    boolean btn2Res;
    boolean allBtnRes;
    boolean dialogRes;
    boolean setMaxWidth;
    boolean setAnimation;
    boolean selectable;
    boolean setAdapter;
    boolean listBg;
    boolean listItemBg;
    boolean listItemSelectedBg;
    boolean listItemText;
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
    LinearLayout listPresets;

    EditText titleTxt;
    EditText messageTxt;
    EditText BgColor;
    EditText BtnColor;
    EditText Btn1Color;
    EditText Btn2Color;
    EditText AllBtnColor;
    EditText BtnColorTxt;
    EditText Btn1ColorTxt;
    EditText Btn2ColorTxt;
    EditText AllBtnColorTxt;
    EditText BtnClickMsgTxt;
    EditText Btn1ClickMsgTxt;
    EditText Btn2ClickMsgTxt;
    EditText BtnTxt;
    EditText Btn1Txt;
    EditText Btn2Txt;
    EditText maxWidth;
    EditText iconNumTxt;

    ArrayList<CheckBox> PresetsCB = new ArrayList<>();

    String[] strings;
    Integer[] integers;
    ExampleClass[] exampleClasses;
    ArrayList<String> arrayListString;
    ArrayList<ExampleClass> arrayListExampleClass;
    ArrayList<ImageListItem> imageItems;
    TestRecyclerViewAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjdialog_list_dialog);
        rootView = findViewById(R.id.root);
        SettingsData.applyWindowInsets(rootView);
        listPresets = findViewById(R.id.listPresets);
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
        iconNumTxt = new EditText(this);

        createViews();
        Presets();
        addPresets();

        Button createDialogBtn = new Button(this);
        createDialogBtn.setText("Create Dialog");
        createDialogBtn.setOnClickListener(view -> createDialog());
        listPresets.addView(createDialogBtn);

    }

    private void Presets() {
        strings = new String[]{"test1", "test2", "test3", "test4"};
        integers = new Integer[]{1, 2, 3, 4, 5};
        exampleClasses = new ExampleClass[]{new ExampleClass("item1", "value1"), new ExampleClass("item2", "value2"), new ExampleClass("item3", "value3")};
        arrayListExampleClass = new ArrayList<>();
        arrayListExampleClass.add(new ExampleClass("item1", "value1"));
        arrayListExampleClass.add(new ExampleClass("item2", "value2"));
        arrayListExampleClass.add(new ExampleClass("item3", "value3"));

        imageItems = new ArrayList<>();


        ArrayList<TestObj> testObjs = new ArrayList<>();
        testObjs.add(new TestObj("test1", "val1"));
        testObjs.add(new TestObj("test2", "val2"));
        testObjs.add(new TestObj("test3", "val3"));


        testAdapter = new TestRecyclerViewAdapter(testObjs);
    }

    private boolean createImageList() {
        int num;

        try {
            num = Integer.parseInt(iconNumTxt.getText().toString());

            imageItems.clear();
            for (int i = 0; i < num; i++) {

                imageItems.add(new ImageListItem("item" + i, createDrawable(i)));

            }
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "Only numbers", Toast.LENGTH_SHORT).show();

        }
        return false;
    }

    private Drawable createDrawable(int num) {
        //TODO update
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{40, 40, 40, 40, 40, 40, 40, 40});
        shape.setColor(0xFF0064ff);
        return shape;
    }


    boolean checkTwoBtns(CompoundButton compoundButton, boolean b, boolean b2) {

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

    private boolean checkBool(CompoundButton compoundButton, boolean b, boolean b2) {
        if (b && b2) {
            compoundButton.setChecked(false);
            return false;
        }
        return b;
    }

    private boolean uncheck(CompoundButton compoundButton, boolean b, boolean b2, int n) {

        if (b2) {
            compoundButton.setChecked(false);
            return false;
        }
        if (!b) return false;
        for (int i = 1; i <= n; i++)
            checkBoxes.get(checkBoxes.indexOf(compoundButton) - i).setChecked(false);

        return true;
    }



    private void createViews() {

        createEditTextAndCheckBoxLayout("Set Title", titleTxt, "Title", "title", (compoundButton, b) -> setTitle = b);
        createEditTextAndCheckBoxLayout("Set Message", messageTxt, "Message", "message", (compoundButton, b) -> setMessage = b);

        createCheckBox("Two Buttons", (compoundButton, b) -> twoBtns = b);
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
        createCheckBox("Old theme", (compoundButton, b) -> oldTheme = b);

        createCheckBox("Hide 'List is empty' text", (compoundButton, b) -> hideEmptyListTxt = b);
        createCheckBox("Set text color", (compoundButton, b) -> setTxtColor = b);
        createCheckBox("Set title text color", (compoundButton, b) -> setTitleTxtColor = b);
        createCheckBox("Set message text color", (compoundButton, b) -> setMessageTxtColor = b);

        createCheckBox("red Btn", (compoundButton, b) -> RedBtn = checkBool(compoundButton, b, RedBtn1));
        createCheckBox("Left red Btn", (compoundButton, b) -> RedBtn1 = uncheck(compoundButton, b, setAllBtnColorRed, 1));
        createCheckBox("Right red Btn", (compoundButton, b) -> RedBtn2 = checkTwoBtns(compoundButton, b, setAllBtnColorRed));
        createCheckBox("All red Btn", (compoundButton, b) -> setAllBtnColorRed = uncheck(compoundButton, b, false, 2));

        createCheckBox("material3 red Btn", (compoundButton, b) -> Mat3RedBtn = checkBool(compoundButton, b, Mat3RedBtn1));
        createCheckBox("Left material3 red Btn", (compoundButton, b) -> Mat3RedBtn1 = uncheck(compoundButton, b, setAllBtnColorMat3Red, 1));
        createCheckBox("Right material3 red Btn", (compoundButton, b) -> Mat3RedBtn2 = checkTwoBtns(compoundButton, b, setAllBtnColorMat3Red));
        createCheckBox("All material3 red Btn", (compoundButton, b) -> setAllBtnColorMat3Red = uncheck(compoundButton, b, false, 2));

        createEditTextAndCheckBoxLayout("Set Background Color",BgColor,"#FF006464","colorBg",(compoundButton, b) -> setBgColor = b);
        createEditTextAndCheckBoxLayout("Set Btn Color", BtnColor, "#FF00FF00", "colorBtn", (compoundButton, b) -> setBtnColor = checkBool(compoundButton, b, setBtn1Color));
        createEditTextAndCheckBoxLayout("Set Left Btn Color", Btn1Color, "#FF00FF00", "colorLBtn", (compoundButton, b) -> setBtn1Color = uncheck(compoundButton, b, setAllBtnColor, 1));
        createEditTextAndCheckBoxLayout("Set Right Btn Color", Btn2Color, "#FF00FF00", "colorRBtn", (compoundButton, b) -> setBtn2Color = checkTwoBtns(compoundButton, b, setAllBtnColor));

        createEditTextAndCheckBoxLayout("Set All Btns Color", AllBtnColor, "#FF00FF00", "colorAllBtn", (compoundButton, b) -> setAllBtnColor = uncheck(compoundButton, b, false, 2));

        createEditTextAndCheckBoxLayout("Set Btn Text", BtnTxt, "BtnTxt", "BtnTxt", (compoundButton, b) -> btnTxt = checkBool(compoundButton, b, btn1Txt));
        createEditTextAndCheckBoxLayout("Set Left Btn Text", Btn1Txt, "Btn1Txt", "LBtnTxt", (compoundButton, b) -> btn1Txt = uncheck(compoundButton, b, false, 1));
        createEditTextAndCheckBoxLayout("Set Right Btn Text", Btn2Txt, "Btn2Txt", "RBtnTxt", (compoundButton, b) -> btn2Txt = checkTwoBtns(compoundButton, b, false));

        createEditTextAndCheckBoxLayout("Set Button Text Color", BtnColorTxt, "#FF00FF00", "BtnTxtColor", (compoundButton, b) -> setBtnColorTxt = checkBool(compoundButton, b, btn1Txt));
        createEditTextAndCheckBoxLayout("Set Left Button Text Color", Btn1ColorTxt, "#FF00FF00", "LeftBtnTxtColor", (compoundButton, b) -> btn1Txt = uncheck(compoundButton, b, setAllBtnColorTxt, 1));
        createEditTextAndCheckBoxLayout("Set Right Button Text Color", Btn2ColorTxt, "#FF00FF00", "RightBtnTxtColor", (compoundButton, b) -> setBtn2ColorTxt = checkTwoBtns(compoundButton, b, setAllBtnColorTxt));

        createEditTextAndCheckBoxLayout("Set All Buttons Text Color", AllBtnColorTxt, "#FF00FF00", "AllBtsTxtColor", (compoundButton, b) -> setAllBtnColorTxt = uncheck(compoundButton, b, false, 2));

        createCheckBox("Set onClick btn", (compoundButton, b) -> oneBtnEvent = b);
        createCheckBox("Set onClick All btns", (compoundButton, b) -> BtnEvents = b);
        createEditTextLayout("Set Left btn onClick Msg", Btn1ClickMsgTxt, "Left Btn msg", "Left Btn msg");
        createEditTextLayout("Set Right btn onClick Msg", Btn2ClickMsgTxt, "Right Btn msg", "Right Btn msg");


        createCheckBox("Set Dialog Background Resource", (compoundButton, b) -> dialogRes = b);
        createCheckBox("Set Button Resource", (compoundButton, b) -> btnRes = checkBool(compoundButton, b, btn1Res));
        createCheckBox("Set Left Button Background Resource", (compoundButton, b) -> btn1Res = uncheck(compoundButton, b, allBtnRes, 1));
        createCheckBox("Set Right Button Background Resource", (compoundButton, b) -> btn2Res = checkTwoBtns(compoundButton, b, allBtnRes));
        createCheckBox("Set All Buttons Background Resource", (compoundButton, b) -> allBtnRes = uncheck(compoundButton, b, false, 2));
        createEditTextAndCheckBoxLayout("Set Max Dialog Width", maxWidth, "300", "maxWidth", (compoundButton, b) -> setMaxWidth = b);
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

    private void addPresets() {

        for (int i = 0; i < 13; i++)
            PresetsCB.add(new CheckBox(this));

        createCheckBoxList("Set Custom Adapter", (compoundButton, b) -> {
            setAdapter = b;
            uncheckOthers(null);
        }, false);
        createCheckBoxList("Selectable list", (compoundButton, b) -> selectable = b, false);
        createCheckBoxList("Set List Background Resource", (compoundButton, b) -> listBg = b, false);
        createCheckBoxList("Set List Item Background Resource", (compoundButton, b) -> listItemBg = b, false);
        createCheckBoxList("Set List Item Selected Background Resource", (compoundButton, b) -> listItemSelectedBg = b, false);
        createCheckBoxList("Set List Item Text Color", (compoundButton, b) -> listItemText = b, false);

        View view = new View(this);
        view.setMinimumHeight(50);
        listPresets.addView(view);

        createPresets("Array String", PresetsCB.get(0));
        createPresets("Array String with onClick", PresetsCB.get(1));
        createPresets("Array Integer with onClick", PresetsCB.get(2));
        createPresets("Array Object", PresetsCB.get(3));
        createPresets("Array Object with 2 values", PresetsCB.get(4));
        createPresets("Array Object with onClick", PresetsCB.get(5));
        createPresets("Array Object with 2 values and onClick", PresetsCB.get(6));
        createPresets("ArrayList Objects", PresetsCB.get(7));
        createPresets("ArrayList Objects with 2 values", PresetsCB.get(8));
        createPresets("ArrayList Objects  with onClick", PresetsCB.get(9));
        createPresets("ArrayList Objects with 2 values and onClick", PresetsCB.get(10));
        createPresets("Image", PresetsCB.get(11));
        createPresets("Image and onClick", PresetsCB.get(12));

        createEditTextLayout("Number of image items", iconNumTxt, "1", "numberImages", listPresets);
    }

    private void uncheckOthers(CheckBox cb) {
        for (CheckBox checkBox : PresetsCB) {
            if (checkBox != cb)
                checkBox.setChecked(false);
        }
    }

    private void onCheckL(boolean b, CheckBox cb, CheckBoxEvent event) {
        if (b)
            uncheckOthers(cb);
        if (event != null)
            event.onChecked();


    }

    private void createPresets(String title, CheckBox checkBox) {

        checkBox.setText(title);
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> onCheckL(b, checkBox, null));
        listPresets.addView(checkBox);

    }

    private void createPresets(String title, CheckBox checkBox, CheckBoxEvent event) {

        checkBox.setText(title);
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> onCheckL(b, checkBox, event));
        listPresets.addView(checkBox);

    }

    private void createMultiCheckBox(CBData...cbs){
        ArrayList<CheckBox> tmpCB = new ArrayList<>();

        for (int i = 0; i < cbs.length; i++){
            tmpCB.add(createCheckBox(cbs[i].text));
        }
        for (int i = 0; i < cbs.length; i++){
            tmpCB.get(i).setOnCheckedChangeListener(cbs[i].onCheckedChangeListener);
        }
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

    private void createCheckBoxList(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
        listPresets.addView(checkBox);
    }

    private void createEditTextLayout(String title, EditText editText) {
        createEditTextLayout(title, editText, "#FF00FF00", "");
    }

    private void createEditTextLayout(String title, EditText editText, String text, String hint) {
        createEditTextLayout(title, editText, text, hint, rootView);
    }

    private void createEditTextLayout(String title, EditText editText, String text, String hint, LinearLayout view) {

        LinearLayout BtnColorLL = new LinearLayout(this);
        BtnColorLL.setOrientation(LinearLayout.HORIZONTAL);
        TextView titleColor = new TextView(this);
        titleColor.setText(title);
        editText.setText(text);
        editText.setHint(hint);
        editTexts.add(editText);
        BtnColorLL.addView(titleColor);
        BtnColorLL.addView(editText);

        view.addView(BtnColorLL);


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

    void DialogBuilder(ListDialog dialog) {

        if (appTheme) {
            dialog.Builder(this, true);
            return;
        }
        if (theme1){
            dialog.Builder(this, R.style.Test123);
            return;
        }
        if (theme2){
            dialog.Builder(this, com.google.android.material.R.style.Theme_Material3_DayNight);
            return;
        }
        if (noInsetsTheme) {
            dialog.Builder(this, com.sjapps.library.R.style.Theme_SJDialog_NoInsets);
            return;
        }
        dialog.Builder(this);
    }

    void createDialog() {

        try {
            ListDialog dialog = new ListDialog();
            DialogBuilder(dialog);

            if (twoBtns)
                dialog.dialogWithTwoButtons();

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
            if (hideEmptyListTxt)
                dialog.hideEmptyListText();
            if (setTxtColor)
                dialog.setTextColor(Color.MAGENTA);
            if (setTitleTxtColor)
                dialog.setTitleTextColor(Color.MAGENTA);
            if (setMessageTxtColor)
                dialog.setMessageTextColor(Color.MAGENTA);
            if (RedBtn)
                dialog.setButtonColor(SJDialog.RED_BUTTON);
            if (RedBtn1)
                dialog.setLeftButtonColor(SJDialog.RED_BUTTON);
            if (RedBtn2)
                dialog.setRightButtonColor(SJDialog.RED_BUTTON);
            if (Mat3RedBtn)
                dialog.setButtonColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (Mat3RedBtn1)
                dialog.setLeftButtonColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (Mat3RedBtn2)
                dialog.setRightButtonColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (setAllBtnColorRed)
                dialog.setButtonsColor(SJDialog.RED_BUTTON);
            if (setAllBtnColorMat3Red)
                dialog.setButtonsColor(SJDialog.MATERIAL3_RED_BUTTON);
            if (setBtnColor)
                dialog.setButtonColor((int) Color.parseColor(BtnColor.getText().toString()));
            if (setBtn1Color)
                dialog.setLeftButtonColor((int) Color.parseColor(Btn1Color.getText().toString()));
            if (setBtn2Color)
                dialog.setRightButtonColor((int) Color.parseColor(Btn2Color.getText().toString()));
            if (setBtnColorTxt)
                dialog.setLeftButtonTextColor((int) Color.parseColor(BtnColorTxt.getText().toString()));
            if (setBtn1ColorTxt)
                dialog.setLeftButtonTextColor((int) Color.parseColor(Btn1ColorTxt.getText().toString()));
            if (setBtn2ColorTxt)
                dialog.setRightButtonTextColor((int) Color.parseColor(Btn2ColorTxt.getText().toString()));
            if (setAllBtnColor)
                dialog.setButtonsColor((int) Color.parseColor(AllBtnColor.getText().toString()));
            if (setAllBtnColorTxt)
                dialog.setButtonsTextColor((int) Color.parseColor(AllBtnColorTxt.getText().toString()));
            if (oneBtnEvent)
                dialog.onButtonClick(() -> Toast.makeText(this, Btn1ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show());
            if (BtnEvents)
                dialog.onButtonClick(new DialogButtonEvents() {
                    @Override
                    public void onLeftButtonClick() {
                        Toast.makeText(SJDialog_ListDialog.this, Btn1ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightButtonClick() {
                        Toast.makeText(SJDialog_ListDialog.this, Btn2ClickMsgTxt.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            if (btnTxt)
                dialog.setButtonText(BtnTxt.getText().toString());
            if (btn1Txt)
                dialog.setLeftButtonText(Btn1Txt.getText().toString());
            if (btn2Txt)
                dialog.setRightButtonText(Btn2Txt.getText().toString());

            if (dialogRes)
                dialog.setDialogBackgroundResource(R.drawable.test1234);
            if (setBgColor)
                dialog.setDialogBackgroundColor((int) Color.parseColor(BgColor.getText().toString()));
            if (btnRes)
                dialog.setLeftButtonBackgroundResource(R.drawable.testbtn);
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

            if (setAdapter) {
                createAdapter(dialog.getListItemBgRes());
                dialog.setAdapter(testAdapter);
            }
            if (selectable)
                dialog.setSelectableList();


            if (!selectable)
                if (
                        PresetsCB.get(0).isChecked() ||
                                PresetsCB.get(3).isChecked() ||
                                PresetsCB.get(4).isChecked() ||
                                PresetsCB.get(7).isChecked() ||
                                PresetsCB.get(8).isChecked() ||
                                PresetsCB.get(11).isChecked()

                ) {
                    Toast.makeText(this, "Set Selectable list", Toast.LENGTH_SHORT).show();
                    return;
                }

            if (listBg)
                dialog.setListBackgroundResource(R.drawable.test123);
            if (listItemBg)
                dialog.setListItemBackgroundResource(R.drawable.test123);
            if (listItemSelectedBg)
                dialog.setListItemSelectedBackgroundResource(R.drawable.testbtn);
            if (listItemText)
                dialog.setListItemTextColor(0XFFFF0000);


            if (PresetsCB.get(0).isChecked())
                dialog.setItems(strings);
            if (PresetsCB.get(1).isChecked())
                dialog.setItems(strings, this::log);
            if (PresetsCB.get(2).isChecked())
                dialog.setItems(integers, String::valueOf, this::log);
            if (PresetsCB.get(3).isChecked())
                dialog.setItems(exampleClasses, obj -> obj.value1);
            if (PresetsCB.get(4).isChecked())
                dialog.setItems(exampleClasses, new ListItemValues<ExampleClass>() {
                    @Override
                    public String getValue1(ExampleClass obj) {
                        return obj.value1;
                    }

                    @Override
                    public String getValue2(ExampleClass obj) {
                        return obj.value2;
                    }
                });
            if (PresetsCB.get(5).isChecked())
                dialog.setItems(exampleClasses, obj -> obj.value1, this::log);
            if (PresetsCB.get(6).isChecked())
                dialog.setItems(exampleClasses, new ListItemValues<ExampleClass>() {
                    @Override
                    public String getValue1(ExampleClass obj) {
                        return obj.value1;
                    }

                    @Override
                    public String getValue2(ExampleClass obj) {
                        return obj.value2;
                    }
                }, this::log);
            if (PresetsCB.get(7).isChecked())
                dialog.setItems(arrayListExampleClass, obj -> obj.value1);
            if (PresetsCB.get(8).isChecked())
                dialog.setItems(arrayListExampleClass, new ListItemValues<ExampleClass>() {
                    @Override
                    public String getValue1(ExampleClass obj) {
                        return obj.value1;
                    }

                    @Override
                    public String getValue2(ExampleClass obj) {
                        return obj.value2;
                    }
                });
            if (PresetsCB.get(9).isChecked())
                dialog.setItems(arrayListExampleClass, obj -> obj.value1, this::log);
            if (PresetsCB.get(10).isChecked())
                dialog.setItems(arrayListExampleClass, new ListItemValues<ExampleClass>() {
                    @Override
                    public String getValue1(ExampleClass obj) {
                        return obj.value1;
                    }

                    @Override
                    public String getValue2(ExampleClass obj) {
                        return obj.value2;
                    }
                }, this::log);
            if (PresetsCB.get(11).isChecked()) {
                if (createImageList())
                    dialog.setImageItems(imageItems);
            }
            if (PresetsCB.get(12).isChecked()) {
                if (createImageList())
                    dialog.setImageItems(imageItems, this::log);
            }

            if (selectable && twoBtns) {
                dialog.onButtonClick(new DialogButtonEvents() {
                    @Override
                    public void onLeftButtonClick() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onRightButtonClick() {
                        ArrayList<Object> obj = dialog.getSelectedItems();
                        ListDialog dialog2 = new ListDialog();

                        System.out.println(obj);
                        dialog2.Builder(SJDialog_ListDialog.this, true)
                                .setTitle("Selected items")
                                .setSelectableList()
                                .setItems(obj, Object::toString)
                                .show();
                    }
                });
            }

        /*
        dialog.setItems(strings);
        dialog.setItems(strings,(position, value) -> {});
        dialog.setItems(strings,obj -> obj);
        dialog.setItems(strings,obj -> obj,(position, obj) -> {});
        dialog.setItems(exampleClasses,obj -> obj.value1);
        dialog.setItems(exampleClasses,obj -> obj.value1,(position, obj) -> {});
        dialog.setItems(exampleClasses, new ListItemValues<ExampleClass>() {
            @Override
            public String getValue1(ExampleClass obj) {
                return obj.value1;
            }

            @Override
            public String getValue2(ExampleClass obj) {
                return obj.value2;
            }
        });
        dialog.setItems(exampleClasses, new ListItemValues<ExampleClass>() {
            @Override
            public String getValue1(ExampleClass obj) {
                return obj.value1;
            }

            @Override
            public String getValue2(ExampleClass obj) {
                return obj.value2;
            }
        },(position, obj) -> {});
        dialog.setItems(arrayListExampleClass,obj -> obj.value1);
        dialog.setItems(arrayListExampleClass,obj -> obj.value1,(position, obj) -> {});
        dialog.setItems(arrayListExampleClass, new ListItemValues<ExampleClass>() {
            @Override
            public String getValue1(ExampleClass obj) {
                return obj.value1;
            }

            @Override
            public String getValue2(ExampleClass obj) {
                return obj.value2;
            }
        });
        dialog.setItems(arrayListExampleClass, new ListItemValues<ExampleClass>() {
            @Override
            public String getValue1(ExampleClass obj) {
                return obj.value1;
            }

            @Override
            public String getValue2(ExampleClass obj) {
                return obj.value2;
            }
        },(position, obj) -> {});

*/
            dialog.show();
        } catch (Exception e) {
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
            LogDialog.line();
            for (CheckBox checkBox : PresetsCB) {
                LogDialog.bool(checkBox.getText().toString(),checkBox.isChecked());
                arr.add(new ObjData(checkBox.getText().toString(),checkBox.isChecked()));
            }


            throw new RuntimeException(e + "     " + arr);

        }
    }

    private void createAdapter(int bg) {
        ArrayList<TestObj> testObjs = new ArrayList<>();
        testObjs.add(new TestObj("test1", "val1"));
        testObjs.add(new TestObj("test2", "val2"));
        testObjs.add(new TestObj("test3", "val3"));


        testAdapter = new TestRecyclerViewAdapter(testObjs, bg);
    }

    void log(int position, Object obj) {
        Toast.makeText(this, position + " : " + obj, Toast.LENGTH_SHORT).show();
    }
}

interface CheckBoxEvent {
    void onChecked();
}

class ExampleClass {
    String value1;
    String value2;

    public ExampleClass(String value1) {
        this.value1 = value1;
    }

    public ExampleClass(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "ExampleClass{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
}