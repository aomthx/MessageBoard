package service;

import dao.UserDao;
import entity.User;

public class UserService {
    private UserDao userDao = new UserDao();
    public Integer register(String name, String email, String password){
        Integer result = userDao.addUser(name, email, password);
        return result;
    }
    public User login(String email, String password){
        User user = userDao.queryUserByEmail(email);
        if(user != null){
            boolean b = user.getPassword().equals(password);
            if(!b)
                user = null;
        }
        return user;
    }
    public User queryUserByEmail(String email){
        User user = userDao.queryUserByEmail(email);
        return user;
    }
    public User queryUserById(int id){
        User user = userDao.queryUserById(id);
        return user;
    }
    public Integer setPassword(String email, String password){
        Integer result = userDao.alterPassword(email, password);
        return result;
    }
    public boolean setStatus(int id, int status){
        if(status == 0){
            int b = userDao.alterStatusTrue(id);
            return b > 0;
        }else{
            int b = userDao.alterStatusFalse(id);
            return b > 0;
        }
    }
}
