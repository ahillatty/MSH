package bl.userbl;

import blservice.userblservice.UserBLInfo;
import blservice.userblservice.UserBLService;
import util.CreditAction;
import util.LoginState;
import util.ResetState;
import util.ResultMessage;
import vo.CreditVO;
import vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Kray on 2016/11/6.
 */
public class UserBLServiceImpl implements UserBLService, UserBLInfo {

    private User user;
    private LoginState loginState;

    protected UserBLServiceImpl(User user, LoginState loginState) {
        this.user = user;
        this.loginState = loginState;
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return 当前登录状态
     */
    public LoginState login(String account, String password) {
        return user.login(account, password);
    }

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    public LoginState logout() {
        if (loginState != LoginState.LOGOUT) {
            return user.logout();
        } else {
            System.out.println("Already Logout");
            return null;
        }
    }

    /**
     * 重置密码
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return 重置密码结果状态
     */
    public ResetState reset(String account, String oldPassword, String newPassword) {
        return user.resetPassword(account, oldPassword, newPassword);
    }

    /**
     * 增加用户
     *
     * @param userVO
     * @return 是否增加成功
     */
    public ResultMessage add(UserVO userVO) {
        return user.add(userVO);
    }

    /**
     * 根据ID查找用户
     *
     * @param userID
     * @return 查到的用户
     */
    public UserVO searchByID(String userID) {
        return user.searchByID(userID);
    }

    /**
     * 更新用户信息
     *
     * @param userVO
     * @return 是否更新成功
     */
    public ResultMessage update(UserVO userVO) {
        return user.update(userVO);
    }

    /**
     * 删除用户
     *
     * @param userVO
     * @return 是否删除成功
     */
    public ResultMessage delete(UserVO userVO) {
        return user.delete(userVO);
    }

    /**
     * 搜索符合关键词的用户列表
     *
     * @param keyword
     * @return 返回的用户列表
     */
    public ArrayList search(String keyword) {
        return user.search(keyword);
    }

    /**
     * 给客户增加信用记录
     *
     * @param clientID
     * @param creditVO
     * @return 增加是否成功
     */
    public ResultMessage addCreditRecord(String clientID, CreditVO creditVO) {
        if (user instanceof Client) {
            Client client = (Client) user;
            return client.addCreditByID(clientID, creditVO);
        } else {
            return null;
        }
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param clientID
     * @return 该客户的信用记录列表
     */
    public ArrayList<CreditVO> searchCreditByID(String clientID) {
        Client client = (Client) user;
        return client.searchCreditByID(clientID);
    }

    /**
     * 根据客户ID获得总信用值
     *
     * @param id
     * @return 该客户的总信用值
     */
    public int getCreditOfID(String id) {
        if (user instanceof Client) {
            return user.getCreditOfID(id);
        } else {
            return -1;
        }
    }

    /**
     * 得到当前登录状态下的客户ID
     *
     * @return 当前登录状态下的客户ID
     */
    public String getCurrentID() {
        return user.getCurrentID();
    }

}