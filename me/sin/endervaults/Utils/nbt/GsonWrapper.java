/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */ import com.google.gson.Gson;

/*    */
/*    */ public class GsonWrapper
/*    */ {
/*  7 */   private static final Gson gson = new Gson();
/*    */   
/*    */   public static String getString(Object obj) {
/* 10 */     return gson.toJson(obj);
/*    */   }
/*    */   
/*    */   public static <T> T deserializeJson(String json, Class<T> type) {
/*    */     try {
/* 15 */       if (json == null) {
/* 16 */         return null;
/*    */       }
/*    */       
/* 19 */       T obj = gson.fromJson(json, type);
/* 20 */       return (T)type.cast(obj);
/*    */     } catch (Exception ex) {
/* 22 */       ex.printStackTrace(); }
/* 23 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\GsonWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */