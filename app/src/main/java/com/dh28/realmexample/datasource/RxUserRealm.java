package com.dh28.realmexample.datasource;

import com.dh28.realmexample.base.RxRealmBase;
import com.dh28.realmexample.model.User;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RxUserRealm extends RxRealmBase {

	public Single<User> getUser(final String name) {
		return execute(realm -> {
			User result = realm
					.where(User.class)
					.equalTo("name", name)
					.findFirst();
			if (result == null) {
				return null;
			}
			return realm.copyFromRealm(result);
		});
	}

	public Completable saveOrUpdateUser(User user) {
		return execute(realm -> {
			realm.copyToRealmOrUpdate(user);
		});
	}

}
