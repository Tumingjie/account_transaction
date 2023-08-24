package com.level.utils.aes;
import com.level.utils.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AESEncryptionHandler implements TypeHandler<String> {

    @Autowired
    AESEncryptionUtils aesEncryptionUtils;

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if(StringUtils.isNotBlank(parameter)){
            String encryptedText = aesEncryptionUtils.encryptEcb(parameter);
            ps.setString(i, encryptedText);
        }else{
            ps.setString(i, parameter);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if(StringUtils.isBlank(result)){
            return result;
        }else{
            return aesEncryptionUtils.decryptEcb(result);
        }
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if(StringUtils.isBlank(result)){
            return result;
        }else{
            return aesEncryptionUtils.decryptEcb(result);
        }
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if(StringUtils.isBlank(result)){
            return result;
        }else{
            return aesEncryptionUtils.decryptEcb(result);
        }
    }
}
