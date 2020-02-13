<template>
  <div id="app">
  	<el-container>
  		<el-header style="background-color: #eee;width:100%;height: 60px;border-width: 2px;">
        <el-row>
        <el-col :span="12" style="text-align: left;"">
          <div style="margin:20px;">
            <span style="font-size: 20px;">语C圈子后台管理系统</span>
          </div>
        </el-col>
        <el-col :span="12">
          <div style="text-align: right;margin:10px;">
            <span style="margin-right: 5px;">欢迎您,{{username}}</span>
            <el-button size="mini" @click="handleLogout">退出登录</el-button>
          </div>
        </el-col>
      </el-row>
      </el-header>
  		<el-main style="width:100%;">
        <div style="width: 100%;height: 160px;">
          <img src="./assets/logo.png" style="height: 160px;">
        </div>
  	 		<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
  	 		  <el-menu-item index="1" @click="handleView('/Basic')">工作台</el-menu-item>
          <el-submenu index="2">
            <template slot="title">用户管理</template>
            <el-menu-item index="2-1" @click="handleView('/Customers')">用户列表</el-menu-item>
            <el-menu-item index="2-2" @click="handleView('/JoinGroup')">入圈审批</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">圈子管理</template>
            <el-menu-item index="3-1" @click="handleView('/Groups')">圈子列表</el-menu-item>
            <el-menu-item index="3-2" @click="handleView('/CreateGroup')">创建圈子审批</el-menu-item>
            <el-menu-item index="3-3" @click="handleView('/UpdateGroup')">修改圈子审批</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">话题管理</template>
            <el-menu-item index="4-1" @click="handleView('/Topics')">话题列表</el-menu-item>
            <el-menu-item index="4-2" @click="handleView('/CreateTopic')">创建话题审批</el-menu-item>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title">投诉举报管理</template>
            <el-menu-item index="5-1" @click="handleView('/Complaints')">投诉管理</el-menu-item>
            <el-menu-item index="5-2" @click="handleView('/GroupReports')">圈子举报管理</el-menu-item>
            <el-menu-item index="5-3" @click="handleView('/TopicReports')">话题举报管理</el-menu-item>
          </el-submenu>
          <el-menu-item index="6" @click="handleView('/Banners')">轮播图管理</el-menu-item>
  			</el-menu>

			 <router-view></router-view>

  		</el-main>
  		<el-footer style="background-color: #eee;width:100%;height:40px;">
      <p>produce by vue element-ui</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
	data() {
      return {
        activeIndex: '1',
        username:""
      };
    },
  methods: {
    handleView:function(url){
    	this.$router.push(url);
    },
    handleCheckCustomer:function(){
      if(localStorage.customer_id==""){
        window.location.href = '#/login'
        location.reload()
      } 
      this.username=localStorage.username
    },
    handleLogout:function(){
      localStorage.customer_id=""
      localStorage.username=""
      window.location.href = '#/login'
      location.reload()
    }
  },
  mounted:function(){
    this.handleCheckCustomer()
  }
}
</script>

<style>
#app {
  font-family: Helvetica, sans-serif;
  text-align: center;
}
</style>
