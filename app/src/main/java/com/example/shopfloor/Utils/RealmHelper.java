package com.example.shopfloor.Utils;

import android.util.Log;

import com.example.shopfloor.Models.ServerModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // To save data into database
    public void save(final ServerModel serverModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(ServerModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    serverModel.setId(nextId);
                    ServerModel model = realm.copyToRealm(serverModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // TO get all data from database
    public List<ServerModel> getAllAddress() {
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        return results;
    }

    // To update data from database
    public void update(final String id, final String address) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ServerModel model = realm.where(ServerModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setAddress(address);
            }
        }, new Realm.Transaction.OnSuccess() {
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void delete() {
        final RealmResults<ServerModel> model = realm.where(ServerModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}
