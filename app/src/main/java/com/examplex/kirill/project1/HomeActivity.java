package com.examplex.kirill.project1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import com.examplex.kirill.project1.adapters.MyRecyclerAdapter;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmResults;
import io.realm.RealmSchema;

public class HomeActivity extends AppCompatActivity {

    private RealmList<Hacker> list1 = new RealmList<>();
    public RecyclerView rv;
    private Paint p = new Paint();
    public MyRecyclerAdapter adpter;
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback;
    Realm realm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        realm.init(this);
        realm = Realm.getDefaultInstance();

        initData();
        initRecycler();

    }

    private void initRecycler() {

        rv = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.hasFixedSize();
        adpter = new MyRecyclerAdapter(list1, this);
        rv.setAdapter(adpter);
        enableSwipe();

    }




    private void initData(){
        Toolbar toolbar = findViewById(R.id.drawer);
        list1 = initList();
        setSupportActionBar(toolbar);

        Drawer.Result drawer =  new Drawer()
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

                                break;
                            }
                            case 2:{

                                Intent intent = new Intent(HomeActivity.this, EditItem.class);
                                startActivityForResult(intent,1);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String title = data.getStringExtra("res_title"); // пока возвращаем имя потом передавать нужные данные
        String content = data.getStringExtra("res_content"); // пока возвращаем имя потом передавать нужные данные
        String link = data.getStringExtra("res_link"); // пока возвращаем имя потом передавать нужные данные


        Hacker h = new Hacker();
        Number maxid = realm.where(Hacker.class).max("id");
        int nextID = (maxid == null) ? 1 : maxid.intValue()+1;
        realm.beginTransaction();

        h.setId(nextID);
        h.setNickname(title);
        h.setContent(content);
        h.setLink(link);
        h.setAva(R.drawable.crash);
        realm.insert(h);
        realm.commitTransaction();
        adpter.addItem(h);

    }

    public RealmList<Hacker> initList(){
        RealmList list = new RealmList();
        Realm realm = Realm.getDefaultInstance();
        list.addAll(realm.where(Hacker.class).findAll());
        return list;
    }


    private void enableSwipe () {
        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    final Hacker deletedModel = list1.get(position);
                    final int deletedPosition = position;

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<Hacker> result = realm.where(Hacker.class)
                                    .equalTo(Hacker.HACKER_ID,list1.get(deletedPosition).getId())
                                    .findAll();
                            result.deleteAllFromRealm();
                        }
                    });
                    adpter.removeItem(position);

                    // showing snack bar with Undo option
                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            adpter.restoreItem(deletedModel, deletedPosition);

                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                } else {
                    final Hacker deletedModel = list1.get(position);
                    final int deletedPosition = position;
                    adpter.removeItem(position);
                    // showing snack bar with Undo option
                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            realm.beginTransaction();
                            realm.insert(deletedModel);
                            realm.commitTransaction();
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.razor);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }
    class RealmMigrations implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            final RealmSchema schema = realm.getSchema();

            if (oldVersion == 1) {
                final RealmObjectSchema userSchema = schema.get("UserData");
                userSchema.addField("content", String.class);
                userSchema.addField("link", String.class);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}



