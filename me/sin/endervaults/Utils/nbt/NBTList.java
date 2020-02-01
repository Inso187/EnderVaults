/*     */ package me.sin.endervaults.Utils.nbt;
/*     */ 
/*     */ import java.lang.reflect.Method;

/*     */
/*     */ public class NBTList
/*     */ {
/*     */   private String listname;
/*     */   private NBTCompound parent;
/*     */   private NBTType type;
/*     */   private Object listobject;
/*     */   
/*     */   protected NBTList(NBTCompound owner, String name, NBTType type, Object list) {
/*  13 */     this.parent = owner;
/*  14 */     this.listname = name;
/*  15 */     this.type = type;
/*  16 */     this.listobject = list;
/*  17 */     if ((type != NBTType.NBTTagString) && (type != NBTType.NBTTagCompound)) {
/*  18 */       System.err.println("List types != String/Compound are currently not implemented!");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void save() {
/*  23 */     this.parent.set(this.listname, this.listobject);
/*     */   }
/*     */   
/*     */   public NBTListCompound addCompound() {
/*  27 */     if (this.type != NBTType.NBTTagCompound) {
/*  28 */       new Throwable("Using Compound method on a non Compound list!").printStackTrace();
/*  29 */       return null;
/*     */     }
/*     */     try {
/*  32 */       Method m = this.listobject.getClass().getMethod("add", new Class[] { NBTReflectionUtil.getNBTBase() });
/*  33 */       Object comp = NBTReflectionUtil.getNBTTagCompound().newInstance();
/*  34 */       m.invoke(this.listobject, new Object[] { comp });
/*  35 */       return new NBTListCompound(this, comp);
/*     */     } catch (Exception ex) {
/*  37 */       ex.printStackTrace();
/*     */     }
/*  39 */     return null;
/*     */   }
/*     */   
/*     */   public NBTListCompound getCompound(int id) {
/*  43 */     if (this.type != NBTType.NBTTagCompound) {
/*  44 */       new Throwable("Using Compound method on a non Compound list!").printStackTrace();
/*  45 */       return null;
/*     */     }
/*     */     try {
/*  48 */       Method m = this.listobject.getClass().getMethod("get", new Class[] { Integer.TYPE });
/*  49 */       Object comp = m.invoke(this.listobject, new Object[] { Integer.valueOf(id) });
/*  50 */       return new NBTListCompound(this, comp);
/*     */     } catch (Exception ex) {
/*  52 */       ex.printStackTrace();
/*     */     }
/*  54 */     return null;
/*     */   }
/*     */   
/*     */   public String getString(int i) {
/*  58 */     if (this.type != NBTType.NBTTagString) {
/*  59 */       new Throwable("Using String method on a non String list!").printStackTrace();
/*  60 */       return null;
/*     */     }
/*     */     try {
/*  63 */       Method m = this.listobject.getClass().getMethod("getString", new Class[] { Integer.TYPE });
/*  64 */       return (String)m.invoke(this.listobject, new Object[] { Integer.valueOf(i) });
/*     */     } catch (Exception ex) {
/*  66 */       ex.printStackTrace();
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */   
/*     */   public void addString(String s)
/*     */   {
/*  73 */     if (this.type != NBTType.NBTTagString) {
/*  74 */       new Throwable("Using String method on a non String list!").printStackTrace();
/*  75 */       return;
/*     */     }
/*     */     try {
/*  78 */       Method m = this.listobject.getClass().getMethod("add", new Class[] { NBTReflectionUtil.getNBTBase() });
/*  79 */       m.invoke(this.listobject, new Object[] { NBTReflectionUtil.getNBTTagString().getConstructor(new Class[] { String.class }).newInstance(new Object[] { s }) });
/*  80 */       save();
/*     */     } catch (Exception ex) {
/*  82 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setString(int i, String s)
/*     */   {
/*  88 */     if (this.type != NBTType.NBTTagString) {
/*  89 */       new Throwable("Using String method on a non String list!").printStackTrace();
/*  90 */       return;
/*     */     }
/*     */     try {
/*  93 */       Method m = this.listobject.getClass().getMethod("a", new Class[] { Integer.TYPE, NBTReflectionUtil.getNBTBase() });
/*  94 */       m.invoke(this.listobject, new Object[] { Integer.valueOf(i), NBTReflectionUtil.getNBTTagString().getConstructor(new Class[] { String.class }).newInstance(new Object[] { s }) });
/*  95 */       save();
/*     */     } catch (Exception ex) {
/*  97 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(int i) {
/*     */     try {
/* 103 */       Method m = this.listobject.getClass().getMethod(MethodNames.getremoveMethodName(), new Class[] { Integer.TYPE });
/* 104 */       m.invoke(this.listobject, new Object[] { Integer.valueOf(i) });
/* 105 */       save();
/*     */     } catch (Exception ex) {
/* 107 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public int size() {
/*     */     try {
/* 113 */       Method m = this.listobject.getClass().getMethod("size", new Class[0]);
/* 114 */       return ((Integer)m.invoke(this.listobject, new Object[0])).intValue();
/*     */     } catch (Exception ex) {
/* 116 */       ex.printStackTrace();
/*     */     }
/* 118 */     return -1;
/*     */   }
/*     */   
/*     */   public NBTType getType() {
/* 122 */     return this.type;
/*     */   }
/*     */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */