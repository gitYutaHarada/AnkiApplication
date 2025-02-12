package data_transfer_object;

import java.io.Serializable;
import java.util.ArrayList;

import bean.UserBean;

public class UserInformationDTO implements Serializable {
    private ArrayList<UserBean> user_bean_list = new ArrayList<>();

    public UserInformationDTO(){
        user_bean_list = new ArrayList<UserBean>();
    }

    public void add(UserBean user_bean){
        user_bean_list.add(user_bean);
    }

    public UserBean get(int i){
        return user_bean_list.get(i);
    }

    public int size(){
        return user_bean_list.size();
    }
}

