package com.betterup.codingexercise.daos;

import com.betterup.codingexercise.models.databasemodels.DomainModelFieldNames;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * This is an abstract class that all concrete DAO's can extend to provide CRUD operations for {@link Realm} based databsae objects.
 * <p>
 * Realm is a good fit for this project for following reasons:
 * <p>
 * -In lieu of writing cumbersome table entries and queries for SQL we can treat our datbase models as straight up POJOs
 * -Forming database queries is more straight forward than SQL and primarily relies on providing constraints around fields.
 * -Realm loads all objects lazily which allows the memory foot print to be extremely low. This is especially useful for things like RecyclerView's since the entire content of the list doesn't have to be loaded into memory and we can query data by index position.
 * -Since pagination needed to be supported, Realm can easily cache all of the objects, rely on Lazy loading to reduce the memory foot print, and then also allows the developer to attach a listener to the object for when new data is available.
 */
public abstract class RealmAbstractDAO {
    private Realm realm;
    private boolean success;

    public RealmAbstractDAO() {
        this.realm = Realm.getDefaultInstance();
    }

    public <T extends RealmObject> boolean create(final T domainModel) {
        success = false;

        realm.executeTransaction(realm -> {
            realm.copyToRealm(domainModel);
            success = true;
        });

        return success;
    }

    public <T extends RealmObject> T read(final String docId, final Class<T> type) {
        T model = null;

        RealmQuery<T> query = realm.where(type);
        query.equalTo(DomainModelFieldNames.DocId.getStringValue(), docId);
        model = query.findFirst();

        return model;
    }

    public <T extends RealmObject> List<T> read(final Class<T> type) {
        List<T> model = null;

        RealmQuery<T> query = realm.where(type);
        model = query.findAll();

        return model;
    }

    public <T extends RealmObject> List<T> read(final Class<T> type, final String sortFieldName, final Sort sortOrder) {
        RealmQuery<T> query = realm.where(type);
        RealmResults<T> sortedList = query.findAll();

        sortedList.sort(sortFieldName, sortOrder);
        return sortedList;
    }

    public <T extends RealmObject> boolean update(final T domainModel) {
        success = false;

        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(domainModel);
            success = true;
        });

        return success;
    }

    public <T extends RealmObject> boolean update(final List<T> domainModel) {
        success = false;

        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(domainModel);
            success = true;
        });

        return success;
    }

    public <T extends RealmObject> boolean delete(final String docId, final Class<T> type) {
        success = false;

        realm.executeTransaction(realm -> {
            RealmQuery<T> query = realm.where(type);

            query.equalTo(DomainModelFieldNames.DocId.getStringValue(), docId);

            T model = query.findFirst();

            model.deleteFromRealm();
            success = true;

        });

        return success;
    }

    public <T extends RealmObject> boolean deleteAll(final Class<T> type) {
        success = false;

        realm.executeTransaction(realm -> {
            RealmQuery<T> query = realm.where(type);

            RealmResults<T> result = query.findAll();

            result.deleteAllFromRealm();
            success = true;
        });

        return success;
    }
}
