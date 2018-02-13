package mall.online.com.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liWensheng on 2018/2/11.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile  {
    @Id(autoincrement = true)
    private Long userId;
    private String name = null;
    private String avater = null;
    private String gender = null;
    private String address = null;
    @Generated(hash = 1628184367)
    public UserProfile(Long userId, String name, String avater, String gender,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avater = avater;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvater() {
        return this.avater;
    }
    public void setAvater(String avater) {
        this.avater = avater;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
}
