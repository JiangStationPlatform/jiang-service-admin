package cn.jiang.station.platform.service.admin.controller;

import cn.jiang.station.platform.common.domain.TbSysUser;
import cn.jiang.station.platform.common.dto.BaseResult;
import cn.jiang.station.platform.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) TbSysUser tbSysUser) {
        PageInfo pageInfo = adminService.page(pageNum, pageSize, tbSysUser);
        List<TbSysUser> list = pageInfo.getList();
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(pageInfo.getSize());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimt(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }

}
