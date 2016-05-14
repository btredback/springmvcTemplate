package com.demo.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.demo.common.bean.TreeNode;


public abstract interface CommonDao
{
    public abstract List select(String paramString, String[] paramArrayOfString, Class paramClass, Object[] paramArrayOfObject);

    public abstract Object select(String paramString1, String paramString2, Class paramClass, Object paramObject);

    public abstract <T> List<T> select(String paramString, Map<String, String> paramMap, Class<T> paramClass, Map<String, ?> paramMap1);

    public abstract <T> List<T> select(String paramString, Map<String, String> paramMap, Class<T> paramClass);

    public abstract <T> List<T> select(String paramString, Class<T> paramClass);

    public abstract List<Map<String, Object>> select(String paramString1, String paramString2, Object paramObject);

    public abstract List<Map<String, Object>> selectForList(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, Object[] paramArrayOfObject);

    public abstract Map<String, Object> selectForMap(String paramString1, String[] paramArrayOfString, String paramString2, Object[] paramArrayOfObject);

    public abstract Map<String, Object> selectForMap(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, Object[] paramArrayOfObject);

    public abstract String selectForString(String paramString1, String[] paramArrayOfString, String paramString2, Object[] paramArrayOfObject);

    public abstract Object selectForObject(String paramString1, String[] paramArrayOfString, String paramString2, Object[] paramArrayOfObject);

    public abstract List<Map<String, Object>> selectForList(String paramString, Object[] paramArrayOfObject);

    public abstract List<Map<String, Object>> selectForList(String paramString);

    public abstract Integer queryForInt(String paramString1, String paramString2, String[] paramArrayOfString, Object[] paramArrayOfObject);

    public abstract boolean isTableExists(String paramString);

    public abstract int update(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, Object[] paramArrayOfObject);

    public abstract int update(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, Object paramObject);

    public abstract int insert(String paramString, Object[] paramArrayOfObject, String[] paramArrayOfString);

    public abstract List<Map<String, Object>> select(String paramString1, Object[] paramArrayOfObject, String[] paramArrayOfString, String paramString2, boolean paramBoolean);

    public abstract int insert(String paramString, Class paramClass, Object paramObject);

    public abstract int insert(String paramString1, Class paramClass, Object paramObject, String paramString2);

    public abstract int insert(String paramString1, Object[] paramArrayOfObject, String paramString2);

    public abstract int insert(String paramString1, Map paramMap, String paramString2);

    public abstract int update(String paramString1, String paramString2, Class paramClass, Object paramObject);

    public abstract int updateWithIgnoreFields(String paramString1, String paramString2, Class paramClass, Object paramObject, String[] paramArrayOfString);

    public abstract int update(String paramString1, String paramString2, Map paramMap, String paramString3);

    public abstract int update(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, Map paramMap);

    public abstract int update(String paramString1, String paramString2, Object paramObject, String paramString3);

    public abstract int delete(String paramString1, String paramString2, int paramInt);

    public abstract int delete(String paramString1, String paramString2, String paramString3);

    public abstract int delete(String paramString1, Object[] paramArrayOfObject, String paramString2);

    public abstract int delete(String paramString, String[] paramArrayOfString, Map paramMap);

    public abstract int getAvaiableID(String paramString1, String paramString2, boolean paramBoolean);

    public abstract List<TreeNode> makeTree(String paramString);

    public abstract List<TreeNode> makeTree(String paramString, String[] paramArrayOfString);

    public abstract List<TreeNode> makeTree(String paramString1, String[] paramArrayOfString, String paramString2);

    public abstract SqlRowSetMetaData getMetaData(String paramString);

    public abstract void execFilter(List<TreeNode> paramList, int paramInt);

    public abstract void batchUpdate(String paramString, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2);

    public abstract void updateClobAsString(String paramString1, String paramString2, String[] paramArrayOfString, Object[] paramArrayOfObject);

    public abstract int count(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject);

    public abstract int count(String paramString, Object[] paramArrayOfObject);

    public abstract boolean getFilterResult(Integer paramInteger1, Integer paramInteger2);

    public abstract int update(String paramString, Object[] paramArrayOfObject);

    public abstract int getSeqID(String paramString);

    public abstract List<TreeNode> makeTree(String paramString1, String[] paramArrayOfString, String paramString2, boolean paramBoolean);

    public abstract void dropTable(String paramString);
}