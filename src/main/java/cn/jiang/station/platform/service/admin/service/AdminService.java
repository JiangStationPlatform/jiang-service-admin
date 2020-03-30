package cn.jiang.station.platform.service.admin.service;


import cn.jiang.station.platform.common.domain.TbSysUser;

public interface AdminService {
    /**
     * 注册
     *
     * @param tbSysUser
     */
    public void register(TbSysUser tbSysUser);

    /**
     * 登陆
     *
     * @param loginCode
     * @param plantPassword
     * @return
     */
    public TbSysUser login(String loginCode, String plantPassword);

}
