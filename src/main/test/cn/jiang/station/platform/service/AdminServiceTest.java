package cn.jiang.station.platform.service;

import cn.jiang.station.platform.service.admin.ServiceAdminApplication;
import cn.jiang.station.platform.service.admin.domain.TbSysUser;
import cn.jiang.station.platform.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "dev")
@Transactional
@Rollback
public class AdminServiceTest {
    public final static Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);
    @Autowired
    private AdminService adminService;

    @Test
    public void register() {
        TbSysUser tbSysUser = new TbSysUser();
        String userCode = UUID.randomUUID().toString();
        logger.info("userCode:" + userCode);
        tbSysUser.setUserCode(userCode);
        tbSysUser.setLoginCode("Admin@admin.com");
        tbSysUser.setUserName("Admin");
        tbSysUser.setPassword("1234qwer");
        tbSysUser.setUserType("0");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateBy(tbSysUser.getUserCode());
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setUpdateBy("Admin");
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setRemarks(tbSysUser.getUserCode());
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("jiang");
        adminService.register(tbSysUser);
    }

    @Test
    public void login() {
        TbSysUser tbSysUser = adminService.login("Admin@admin.com", "1234qwer");
        Assert.assertNotNull(tbSysUser);

    }
}
