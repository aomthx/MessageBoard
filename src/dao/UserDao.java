package dao;

import DBUtil.C3p0Utils;
import entity.User;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
    public Integer addUser(String username, String email, String password) {
        try {
            return queryRunner.update("insert into user (username,email,password) value (?,?,?)", username, email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public User queryUserById(int id){
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        try {
            return queryRunner.query("select * from user where id = ?", new BeanHandler<>(User.class, processor), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User queryUserByEmail(String email){
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        try {
            return queryRunner.query("select * from user where email = ?", new BeanHandler<>(User.class, processor), email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Integer alterStatusTrue(int id){
        try {
            return queryRunner.update("UPDATE user SET status = 1 where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Integer alterStatusFalse(int id){
        try {
            return queryRunner.update("UPDATE user SET status = 0 where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Integer alterPassword(String email , String password){
        try {
            return queryRunner.update("UPDATE user SET password = ? where email = ?", password, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
