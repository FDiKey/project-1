

package com.examplex.kirill.project1;

import android.widget.ImageView;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Hacker extends RealmObject



{

    public static final String HACKER_ID = "id";
    public static final String HACKER_NICKNAME = "nickname";
    public static final String HACKER_AVA = "ava";

    @PrimaryKey
    int id;
    String nickname;
    String content;
    String link;
    Integer ava;
    public Hacker(){

    }
    public Hacker(String nickname, Integer ava) {
        this.nickname = nickname;
        this.ava = ava;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAva() {
        return ava;
    }

    public void setAva(Integer ava) {
        this.ava = ava;
    }

    int idNextVal (Realm realm)
    {
        Number maxId = (int) (realm.where(Hacker.class).max("id"));
        int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
        return  nextId;

    }
}

