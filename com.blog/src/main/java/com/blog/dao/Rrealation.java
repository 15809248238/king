package com.blog.dao;



import java.util.List;
import java.util.Map;

import com.blog.entity.Realationv1;


public interface Rrealation {
   public void Follow(Map MyRealation);
   public Realationv1 SelectShield(Map MyRealation);
   public int AbolishFollow(Map MyRealation);
   public List<Realationv1> SelectAllFriend(Map myRealation);
   public void InsertFriend(Map MyRealation);
}
