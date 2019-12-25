import com.vli.controller.client.UserController;
import com.vli.parameter.UserParameter;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/11/20.
 */
public class TestHttpClient {


    public static void main(String[] args) {
        UserController userController = new UserController();
        UserParameter userParameter = new UserParameter();
        userParameter.setQqNumber("552431558");
        ResultModel qqInformation = userController.getQqInformation(userParameter);
        System.out.println(qqInformation);
    }
}
