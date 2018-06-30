package com.examplex.kirill.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class EditItem extends AppCompatActivity implements View.OnClickListener {

    EditText editTitle;
    EditText editContent;
    EditText editLink;
    Button btn;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_layout);
        toolbar = findViewById(R.id.drawer);
        initToolbar();
        editTitle= (EditText) findViewById(R.id.edit_name);
        editContent= (EditText) findViewById(R.id.edit_content);
        editLink= (EditText) findViewById(R.id.edit_link);
        btn= (Button) findViewById(R.id.edit_btn);
        btn.setOnClickListener(this);





    }

    private void initToolbar() {

        setSupportActionBar(toolbar);

        Drawer.Result drawer1 =  new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(true)
                .withActionBarDrawerToggle(true)
                .addDrawerItems( new PrimaryDrawerItem()
                                .withName(R.string.d_List)
                                .withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.d_add)
                                .withIdentifier(2),
                        new PrimaryDrawerItem()
                                .withName(R.string.d_profile)
                                .withIdentifier(3)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        switch(drawerItem.getIdentifier())
                        {
                            case 1:{
                                Intent intent = new Intent(EditItem.this, HomeActivity.class);
                                startActivityForResult(intent,1);

                                break;
                            }
                            case 2:{



                                break;
                            }
                            case 3:{

                                break;
                            }
                        }
                    }
                })
                .build();


    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("res_title", editTitle.getText().toString() + "");
        intent.putExtra("res_content", editContent.getText().toString() + "");
        intent.putExtra("res_link", editLink.getText().toString() + "");

        setResult(RESULT_OK,intent);
        finish();
    }
}
