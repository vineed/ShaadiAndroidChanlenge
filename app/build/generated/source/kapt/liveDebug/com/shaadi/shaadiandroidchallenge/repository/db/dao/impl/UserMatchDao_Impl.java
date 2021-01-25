package com.shaadi.shaadiandroidchallenge.repository.db.dao.impl;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.shaadi.shaadiandroidchallenge.repository.db.dao.UserMatchDao;
import com.shaadi.shaadiandroidchallenge.repository.db.entities.UserMatchEntity;
import java.lang.Boolean;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserMatchDao_Impl implements UserMatchDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserMatchEntity> __insertionAdapterOfUserMatchEntity;

  private final EntityDeletionOrUpdateAdapter<UserMatchEntity> __updateAdapterOfUserMatchEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMatchUser;

  public UserMatchDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserMatchEntity = new EntityInsertionAdapter<UserMatchEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `user` (`uuid`,`title`,`first_name`,`last_name`,`age`,`street`,`city`,`state`,`country`,`thumbnail`,`mediumImage`,`largeImage`,`isAccepted`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserMatchEntity value) {
        if (value.getUuid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUuid());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getFirst_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFirst_name());
        }
        if (value.getLast_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLast_name());
        }
        stmt.bindLong(5, value.getAge());
        if (value.getStreet() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStreet());
        }
        if (value.getCity() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getState());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getCountry());
        }
        if (value.getThumbnail() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getThumbnail());
        }
        if (value.getMediumImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getMediumImage());
        }
        if (value.getLargeImage() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getLargeImage());
        }
        final Integer _tmp;
        _tmp = value.isAccepted() == null ? null : (value.isAccepted() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, _tmp);
        }
      }
    };
    this.__updateAdapterOfUserMatchEntity = new EntityDeletionOrUpdateAdapter<UserMatchEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `user` SET `uuid` = ?,`title` = ?,`first_name` = ?,`last_name` = ?,`age` = ?,`street` = ?,`city` = ?,`state` = ?,`country` = ?,`thumbnail` = ?,`mediumImage` = ?,`largeImage` = ?,`isAccepted` = ? WHERE `uuid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserMatchEntity value) {
        if (value.getUuid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUuid());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getFirst_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFirst_name());
        }
        if (value.getLast_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLast_name());
        }
        stmt.bindLong(5, value.getAge());
        if (value.getStreet() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStreet());
        }
        if (value.getCity() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getState());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getCountry());
        }
        if (value.getThumbnail() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getThumbnail());
        }
        if (value.getMediumImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getMediumImage());
        }
        if (value.getLargeImage() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getLargeImage());
        }
        final Integer _tmp;
        _tmp = value.isAccepted() == null ? null : (value.isAccepted() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, _tmp);
        }
        if (value.getUuid() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getUuid());
        }
      }
    };
    this.__preparedStmtOfDeleteAllMatchUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM user";
        return _query;
      }
    };
  }

  @Override
  public Object insertMatchUser(final UserMatchEntity matchEntity,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserMatchEntity.insert(matchEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object updateMatchUser(final UserMatchEntity matchEntity,
      final Continuation<? super Integer> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfUserMatchEntity.handle(matchEntity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object addAllMatchUser(final List<UserMatchEntity> matchEntityList,
      final Continuation<? super Unit> p1) {
    return RoomDatabaseKt.withTransaction(__db, new Function1<Continuation<? super Unit>, Object>() {
      @Override
      public Object invoke(Continuation<? super Unit> __cont) {
        return UserMatchDao.DefaultImpls.addAllMatchUser(UserMatchDao_Impl.this, matchEntityList, __cont);
      }
    }, p1);
  }

  @Override
  public Object deleteAllMatchUser(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMatchUser.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllMatchUser.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getAllMatchUser(final Continuation<? super List<UserMatchEntity>> p0) {
    final String _sql = "SELECT * FROM user";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<List<UserMatchEntity>>() {
      @Override
      public List<UserMatchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfMediumImage = CursorUtil.getColumnIndexOrThrow(_cursor, "mediumImage");
          final int _cursorIndexOfLargeImage = CursorUtil.getColumnIndexOrThrow(_cursor, "largeImage");
          final int _cursorIndexOfIsAccepted = CursorUtil.getColumnIndexOrThrow(_cursor, "isAccepted");
          final List<UserMatchEntity> _result = new ArrayList<UserMatchEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserMatchEntity _item;
            final String _tmpUuid;
            _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpFirst_name;
            _tmpFirst_name = _cursor.getString(_cursorIndexOfFirstName);
            final String _tmpLast_name;
            _tmpLast_name = _cursor.getString(_cursorIndexOfLastName);
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpStreet;
            _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpState;
            _tmpState = _cursor.getString(_cursorIndexOfState);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpThumbnail;
            _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            final String _tmpMediumImage;
            _tmpMediumImage = _cursor.getString(_cursorIndexOfMediumImage);
            final String _tmpLargeImage;
            _tmpLargeImage = _cursor.getString(_cursorIndexOfLargeImage);
            final Boolean _tmpIsAccepted;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfIsAccepted)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfIsAccepted);
            }
            _tmpIsAccepted = _tmp == null ? null : _tmp != 0;
            _item = new UserMatchEntity(_tmpUuid,_tmpTitle,_tmpFirst_name,_tmpLast_name,_tmpAge,_tmpStreet,_tmpCity,_tmpState,_tmpCountry,_tmpThumbnail,_tmpMediumImage,_tmpLargeImage,_tmpIsAccepted);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }
}
