package cn.jiang.station.platform.service.admin.service.impl;

import cn.jiang.station.platform.common.domain.TbSysUser;
import cn.jiang.station.platform.common.mapper.TbSysUserMapper;
import cn.jiang.station.platform.common.service.impl.BaseServiceImpl;
import cn.jiang.station.platform.service.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

}
