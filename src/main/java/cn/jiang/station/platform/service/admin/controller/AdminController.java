package cn.jiang.station.platform.service.admin.controller;

import cn.jiang.station.platform.common.domain.TbSysUser;
import cn.jiang.station.platform.common.dto.BaseResult;
import cn.jiang.station.platform.service.admin.service.AdminService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public BaseResult login(String loginCode, String password) {
        BaseResult baseResult = checkLogen(loginCode, password);
        //检查登陆信息
        if (baseResult != null) {
            return baseResult;
        }
        //登陆业务
        TbSysUser tbSysUser = adminService.login(loginCode, password);

        if (tbSysUser != null) {
            return BaseResult.ok(tbSysUser);
        } else {
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("", "登陆失败")));
        }
    }

    private BaseResult checkLogen(String loginCode, String password) {
        BaseResult baseResult = null;
        if (StringUtils.isBlank(loginCode) || StringUtils.isBlank(password)) {
            baseResult = BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error("loginCode", "登陆账户不能为空"),
                    new BaseResult.Error("password", "密码不能为空")
            ));
        }
        return baseResult;
    }


}
