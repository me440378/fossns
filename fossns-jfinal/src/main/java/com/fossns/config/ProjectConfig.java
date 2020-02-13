package com.fossns.config;

import com.fossns.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class ProjectConfig extends JFinalConfig{

	//配置常量
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setJsonFactory(new MixedJsonFactory());
	}

	//配置路由
	public void configRoute(Routes me) {
		me.add(new WebRoutes());
		me.add(new AdminRoutes());
	}

	//配置引擎
	public void configEngine(Engine me) {
		
	}

	//配置插件
	public void configPlugin(Plugins me) {
		DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/fossns_db?characterEncoding=utf8&serverTimezone=GMT%2B8",
				"root", "");
	    me.add(dp);
	    
	    ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
	    me.add(arp);
	    _MappingKit.mapping(arp);
	}

	//配置拦截器
	public void configInterceptor(Interceptors me) {
		
	}

	//配置处理器
	public void configHandler(Handlers me) {
		
	}

}
