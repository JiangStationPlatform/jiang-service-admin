package cn.jiang.station.platform.service.admin.service.impl;

import cn.jiang.station.platform.common.domain.TbSysUser;
import cn.jiang.station.platform.service.admin.mapper.TbSysUserMapper;
import cn.jiang.station.platform.service.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    @Transactional(readOnly = false)
    public void register(TbSysUser tbSysUser) {
        //密码加密
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));
        tbSysUserMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        logger.info("开始查询数据库");
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode", loginCode);
        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
        if (tbSysUser == null) {
            return null;
        }
        logger.info("开始验证密码");
        String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
        if (password.equals(tbSysUser.getPassword())) {
            return tbSysUser;
        }
        return null;
    }

}
