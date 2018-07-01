package com.github.microkibaco.gomall.db.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.microkibaco.gomall.module.BaseModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据助手类，
 * 三层架构，
 * 应用层调用DBDataHelper,
 * DBDataHelper 调用 DBHelper完成真正的数据存储
 */

@SuppressWarnings("SynchronizeOnNonFinalField")
public final class DBDataHelper {
    private static DBDataHelper dataHelper = null;
    private DBHelper dbHelper = null;
    private static final String SELECT = "select ";
    private static final String FROM = " from ";
    private static final String WHERE = " where ";
    private static final String ORDER_BY = " order by ";

    private DBDataHelper() {
        dbHelper = DBHelper.getInstance();
    }

    public static DBDataHelper getInstance() {
        if (dataHelper == null) {
            dataHelper = new DBDataHelper();
        }
        return dataHelper;
    }

    public ArrayList<BaseModel> select(String tableName,
                                       String showColumns,
                                       String selection,
                                       String selectionArgs,
                                       String orderBy,
                                       Class<?> cls) {

        synchronized (dbHelper) {
            final ArrayList<BaseModel> moduleList = new ArrayList<BaseModel>();
            SQLiteDatabase db = null;
            try {
                db = dbHelper.getReadableDatabase();
                String sql = SELECT;
                sql += showColumns != null ? showColumns : "*";
                sql += FROM + tableName;
                if (selection != null &&
                        selectionArgs != null) {
                    sql += WHERE + selection + " = " + selectionArgs;
                }
                if (orderBy != null) {
                    sql += ORDER_BY + orderBy;
                }
                final Cursor cursor = db.rawQuery(sql, null);
                changeToList(cursor, moduleList, cls);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dbHelper.closeDatabase(db);
            }
            return moduleList;
        }
    }

    private void changeToList(Cursor cursor,
                              List<BaseModel> moduleList,
                              Class<?> cls) {
        // 取出所有的列名
        final int count = cursor.getCount();
        BaseModel module;
        cursor.moveToFirst();
        synchronized (dbHelper) {
            try {
                // 遍历游标
                for (int i = 0; i < count; i++) {
                    // 转化为moduleClass类的一个实例
                    module = changeToModule(cursor, cls);
                    moduleList.add(module);
                    cursor.moveToNext();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }
    }

    private BaseModel changeToModule(Cursor cursor,
                                     Class<?> cls) {
        synchronized (dbHelper) {
            // 取出所有的列名
            final String[] columnNames = cursor.getColumnNames();
            String filedValue;
            final int columnCount = columnNames.length;
            Field field;
            BaseModel module = null;
            try {
                module = (BaseModel) cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 遍历有列
            for (int j = 0; j < columnCount; j++) {
                // 根据列名找到相对应 的字段
                try {

                    field = cls.getField(columnNames[j]);
                    filedValue = cursor.getString(j);
                    if (filedValue != null) {
                        field.set(module, filedValue.trim());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return module;
        }
    }


    /**
     * 根据条件查询数据库表,单条件查询
     */
    public ArrayList<BaseModel> selects(final String tableName,
                                        final String showColumns,
                                        final String selection,
                                        final String selectionArgs,
                                        final String orderBy,
                                        final Class<?> cls) {
        synchronized (dbHelper) {
            final ArrayList<BaseModel> moduleList = new ArrayList<>();
            SQLiteDatabase db = null;
            try {
                db = dbHelper.getReadableDatabase();
                String sql = SELECT;
                sql += showColumns != null ? showColumns : "*";
                sql += FROM + tableName;
                if (selection != null && selectionArgs != null) {
                    sql += WHERE + selection + " = " + selectionArgs;
                }
                if (orderBy != null) {
                    sql += ORDER_BY + orderBy;
                }
                final Cursor cursor = db.rawQuery(sql, null);
                changeToList(cursor, moduleList, cls);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dbHelper.closeDatabase(db);
            }
            return moduleList;
        }
    }

    /**
     * 查找数据表，多条件查询
     */
    public ArrayList<BaseModel> select(final String table,
                                       final String selection,
                                       final String[] selectionArgs,
                                       final String orderby,
                                       final Class<?> moduleClass) {

        SQLiteDatabase database = null;
        Cursor cursor = null;
        final ArrayList<BaseModel> moduleList = new ArrayList<>();
        synchronized (dbHelper) {
            try {
                database = dbHelper.getReadableDatabase();
                // 查询数据
                cursor = database.query(table, null, selection, selectionArgs, null, null, orderby, null);
                // 将结果转换成为数据模型
                changeToList(cursor, moduleList, moduleClass);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dbHelper.closeDatabase(database);
            }
            return moduleList;
        }
    }

    /**
     * 删除数据
     */
    public int delete(final String table,
                      final String whereClause,
                      final String[] whereArgs) {
        synchronized (dbHelper) {
            return dbHelper.delete(table, whereClause, whereArgs);
        }
    }

    /**
     * 向数据库更新数据
     */
    public long update(final String table,
                       final String whereClause,
                       final String[] whereArgs,
                       final BaseModel module) {
        synchronized (dbHelper) {
            final ContentValues values = moduleToContentValues(module);
            return dbHelper.update(table, values, whereClause, whereArgs);
        }
    }

    /**
     * 将实体类型转成数据库可以直接存储的ContentValues
     */
    private ContentValues moduleToContentValues(final BaseModel module) {
        final ContentValues values = new ContentValues();
        final Field[] fields = module.getClass().getFields();
        String fieldName;
        String fieldValue;
        int fieldValueForInt = -1;
        try {
            for (Field field : fields) {
                fieldName = field.getName();
                if (field.get(module) instanceof String) {
                    fieldValue = (String) field.get(module);
                    if (fieldValue != null) {
                        values.put(fieldName, fieldValue.trim());
                    } else {
                        values.put(fieldName, "");
                    }
                } else if (field.get(module) instanceof Integer) {
                    fieldValueForInt = (Integer) field.get(module);
                    if (fieldValueForInt != -1) {
                        values.put(fieldName, fieldValueForInt);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }
}