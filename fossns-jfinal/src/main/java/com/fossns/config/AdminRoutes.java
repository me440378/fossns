package com.fossns.config;

import com.jfinal.config.Routes;
import com.fossns.controller.admin.*;

public class AdminRoutes extends Routes {

	public void config() {
		add("/admin",BaseController.class);
		add("/admin/customer",CustomerController.class);
		add("/admin/joingroup",JoinGroupController.class);
		add("/admin/group",GroupController.class);
		add("/admin/creategroup",CreateGroupController.class);
		add("/admin/updategroup",UpdateGroupController.class);
		add("/admin/topic",TopicController.class);
		add("/admin/createtopic",CreateTopicController.class);
		add("/admin/complaint",ComplaintController.class);
		add("/admin/groupreport",GroupReportController.class);
		add("/admin/topicreport",TopicReportController.class);
		add("/admin/detailreport",DetailReportController.class);
		add("/admin/banner",BannerController.class);
		
	}

}
