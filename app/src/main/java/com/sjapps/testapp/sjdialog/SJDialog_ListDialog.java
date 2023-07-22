package com.sjapps.testapp.sjdialog;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.sjapps.library.customdialog.DialogButtonEvents;
import com.sjapps.library.customdialog.ImageListItem;
import com.sjapps.library.customdialog.ListDialog;
import com.sjapps.library.customdialog.ListItemValues;
import com.sjapps.library.customdialog.SJDialog;
import com.sjapps.testapp.R;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class SJDialog_ListDialog extends SJDialog_Base<ListDialog> {

    boolean hideEmptyListTxt;
    boolean setEmptyListTxt;

    boolean selectItem;
    boolean selectable;
    boolean setAdapter;
    boolean listBg;
    boolean listItemBg;
    boolean listItemSelectedBg;
    boolean listItemText;
    boolean itemBgColor;
    boolean itemBgColorSelected;


    EditText iconNumTxt;
    EditText emptyListTxt;
    EditText selectItemTxt;
    EditText itemBgColorTxt;
    EditText itemBgColorSelectedTxt;

    ArrayList<CheckBox> PresetsCB = new ArrayList<>();

    String[] strings;
    Integer[] integers;
    ExampleClass[] exampleClasses;
    ArrayList<String> arrayListString;
    ArrayList<ExampleClass> arrayListExampleClass;
    ArrayList<ImageListItem> imageItems;
    TestRecyclerViewAdapter testAdapter;

    public SJDialog_ListDialog(){
        super(true,false,"ListDialog");
    }

    @Override
    void init() {
        iconNumTxt = new EditText(this);
        emptyListTxt = new EditText(this);
        selectItemTxt = new EditText(this);
        itemBgColorTxt = new EditText(this);
        itemBgColorSelectedTxt = new EditText(this);
        Presets();
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

    @Override
    protected void createViews() {
        super.createViews();
        addSpace();
        createCheckBox("Hide 'List is empty' text", (compoundButton, b) -> hideEmptyListTxt = b);
        createEditTextAndCheckBoxLayout("Set empty list text",emptyListTxt,"empty list text","set empty list text", (compoundButton,b) -> setEmptyListTxt = b);
        createEditTextAndCheckBoxLayout("Select item",selectItemTxt,"0","item id", (compoundButton,b) -> selectItem = b);
        addSpace();
        addPresets();
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
        addSpace();
        createCheckBoxList("Set List Item Text Color", (compoundButton, b) -> listItemText = b, false);
        createEditTextAndCheckBoxLayout("List item bg color",itemBgColorTxt,"#FF006464","color",(cb,b) -> itemBgColor = b);
        createEditTextAndCheckBoxLayout("List item selected bg color",itemBgColorSelectedTxt,"#FF003232","color",(cb,b) -> itemBgColorSelected = b);

        addSpace();

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

        createEditTextLayout("Number of image items", iconNumTxt, "1", "numberImages");
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
        rootView.addView(checkBox);

    }

    private void createPresets(String title, CheckBox checkBox, CheckBoxEvent event) {

        checkBox.setText(title);
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> onCheckL(b, checkBox, event));
        rootView.addView(checkBox);

    }


    private void createCheckBoxList(String title, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, boolean def) {

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(title);
        checkBoxes.add(checkBox);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setChecked(def);
        rootView.addView(checkBox);
    }

    @Override
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

    @Override
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
            if (setEmptyListTxt)
                dialog.setEmptyListText(emptyListTxt.getText().toString());
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
            if (itemBgColor)
                dialog.setListItemBackgroundColor(Color.parseColor(itemBgColorTxt.getText().toString()));
            if (itemBgColorSelected)
                dialog.setListItemSelectedBackgroundColor(Color.parseColor(itemBgColorSelectedTxt.getText().toString()));


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
            if(selectItem)
                dialog.selectItem(Integer.parseInt(selectItemTxt.getText().toString()));


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

@SuppressWarnings("ALL")
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

    @NonNull
    @Override
    public String toString() {
        return "ExampleClass{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
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