package com.example.testlann;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView myListView;

    //起動時
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //リストビュー
        myListView = findViewById(R.id.listView);

        //db
        DBHelper myOpenHelper = new DBHelper(this);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        //select
        Cursor c = db.rawQuery("select * from myPasstb",null);

        //adapterの準備
        //表示するカラム名
        String[] from = {"_id","name"};

        //バインドするViewリソース
        int[] to = {android.R.id.text1,android.R.id.text2};

        //adapterの生成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,from,to,0);

        //バインドして表示
        myListView.setAdapter(adapter);


        //リストビューをタップした時の各行のデータを取得
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                //各要素を取得
                //_id
                String s1 = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                //name
                //String s2 = ((TextView)view.findViewById(android.R.id.text2)).getText().toString();

                //参照・更新へ
                Intent intent = new Intent(getApplication(),AddDepart.class);

                //モード指定　_idを渡す
                intent.putExtra("KBN",s1);

                //行く
                startActivity(intent);
            }
        });


    }

    //リターン時
    @Override
    protected void onRestart(){
        super.onRestart();
        reload();
    }


    //登録ボタンを押したときは新規登録へ画面を遷移
    public void Register(View view) {
        Intent intent = new Intent(getApplication(), AddDepart.class);

        // モード指定　空は新規
        intent.putExtra("KBN", "");

        //行く
        startActivity(intent);
    }

    //リロード
    public void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }

}

/* Button changeButton = findViewById(R.id.seniadddepartment);
        //ボタン押下時
        changeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //add department Activityに画面遷移
                Intent intent = new Intent(getApplication(), AddDepartment.class);
                startActivity(intent);
            }
        });*/