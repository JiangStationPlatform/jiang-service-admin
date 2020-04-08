package cn.jiang.station.platform.service.admin.controller;

import cn.jiang.station.platform.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


}
