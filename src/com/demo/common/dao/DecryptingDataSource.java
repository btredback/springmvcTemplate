package com.demo.common.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.InitializingBean;

import com.demo.util.EncryptUtils;


/**
 * 数据源加密
 * @author bt
 *
 */
public class DecryptingDataSource extends BasicDataSource implements InitializingBean
{
    private boolean encrypted;

    public boolean isEncrypted()
    {
        return this.encrypted;
    }

    public void setEncrypted(boolean encrypted)
    {
        this.encrypted = encrypted;
    }

    public void afterPropertiesSet()
    {
        if (this.encrypted)
        {
            String password = EncryptUtils.decryptByDES(getPassword());
            setPassword(password);
        }
    }
}