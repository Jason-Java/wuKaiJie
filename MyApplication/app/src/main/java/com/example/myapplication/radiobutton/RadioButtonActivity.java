package com.example.myapplication.radiobutton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;

public class RadioButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);

        RadioGroup radioGroup = findViewById(R.id.radiogroup1);
        //点击radiobutton就跳出提示给
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radioId) {
                RadioButton radioButton = (RadioButton) findViewById(radioId);
                String text = (String) radioButton.getText();
                Toast.makeText(RadioButtonActivity.this,"性别："+text, Toast.LENGTH_LONG).show();
            }
        });

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = findViewById(R.id.radiogroup1);
                for (int i = 0;i < radioGroup.getChildCount();i++){//radioGroup.getChildCount()获取radiogroup中的子组件个数
                    RadioButton radioButton =(RadioButton) radioGroup.getChildAt(i);//获取子组件的id位置
                    if(radioButton.isChecked()){
                        if(radioButton.getText().equals("保密")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RadioButtonActivity.this);//弹出对话框
                            builder.setMessage("保密成功");//写入弹出内容
                            builder.setPositiveButton("确定",null).show();//按确定按钮弹出框消失
                        }else{
                            Toast.makeText(RadioButtonActivity.this,"性别："+radioButton.getText(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    }
                }
            }
        });
    }
}