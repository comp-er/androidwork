package com.jaybon.mvvmex01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao // (Room이 바라보게 하는) 레파지토리가 되게 하는 어노테이션
public interface UserRepository {

    // select 할때만 쿼리 적고, 나머지 insert, update, delete(전체삭제) 어노테이션걸면됨

    @Query("SELECT * FROM user") // 네이밍 쿼리가 없기 때문에 직접 적어줘야함
    List<User> findAll();

    @Query("SELECT * FROM user WHERE uid = :uid")
    User findByUid(int uid);

    @Insert
    void insert(User user);
}
