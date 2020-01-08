import com.vli.controller.client.UserController;
import com.vli.controller.common.CommonUserController;
import com.vli.parameter.UserParameter;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/11/20.
 */
public class TestHttpClient {


    public static void main(String[] args) {
        CommonUserController commonUserController = new CommonUserController();
        UserParameter userParameter = new UserParameter();
        userParameter.setQqNumber("552431558");
        ResultModel qqInformation = commonUserController.getQqInformation(userParameter);
        System.out.println(qqInformation);
    }
}
