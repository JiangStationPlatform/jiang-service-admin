package cn.jiang.station.platform.service.admin.service.impl;

import cn.jiang.station.platform.service.admin.mapper.TbSysUserMapper;
import cn.jiang.station.platform.service.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private TbSysUserMapper tbSysUserMapper;



}
