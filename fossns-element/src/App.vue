<template>
  <div id="app">
  	<el-container>
  		<el-header style="background-color: #eee;width:100%;height: 60px;border-width: 2px;">
  		  <el-row>
        <el-col :span="12" style="text-align: left;"">
          <div style="margin:20px;">
            <span style="font-size: 20px;">语C圈子</span>
          </div>
        </el-col>
        <el-col :span="12">
          <div style="text-align: right;margin:10px;">
            <span style="margin-right: 5px;">欢迎您,{{username}}</span>
            <el-button type="text" @click="complaintVisible = true">投诉</el-button>
            <el-button size="mini" @click="handleLogout">退出登录</el-button>
            <el-dialog title="投诉" :visible.sync="complaintVisible" width="30%" :before-close="handleCloseComplaint" style="text-align: center;">
              <el-form label-width="80px">
                <el-form-item label="投诉原因" style="margin: 20px;">
                  <el-input type="textarea" :rows="5" v-model="complaint.content"></el-input>
                </el-form-item>
              </el-form>
              <span slot="footer" class="dialog-footer" style="margin: 20px;">
                <el-button @click="complaintVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleComplaint">确 定</el-button>
              </span>
            </el-dialog>
          </div>
        </el-col>
      </el-row>
  		</el-header>
  		<el-main style="width:100%;">
        <div style="width: 100%;height: 160px;">
          <img src="./assets/logo.png" style="height: 160px;float: left;">
        </div>
  			<el-input placeholder="请输入内容" v-model="select.content" class="input-with-select">
  			    <el-select v-model="select.type" slot="prepend">
  			      <el-option label="圈子" value="1"></el-option>
  			     	<el-option label="话题" value="2"></el-option>
              <el-option label="用户" value="3"></el-option>
  			    </el-select>
  			    <el-button @click="handleSearch" slot="append" icon="el-icon-search"></el-button>
  			 </el-input>
  	 		<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
  	 		  <el-menu-item index="1" @click="handleView('/Home')">网址首页</el-menu-item>
  	 		  <el-menu-item index="2" @click="handleView('/GroupList')">热门圈子</el-menu-item>
  	 		  <el-menu-item index="3" @click="handleView('/TopicList')">热门话题</el-menu-item>
  	 		  <el-menu-item index="4" @click="handleView('/MyFriend')">我的好友</el-menu-item>
  	 		  <el-menu-item index="5" @click="handleView('/MyInformation')">我的信息</el-menu-item>
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
        username:"",
        complaintVisible:false,
        complaint:{
          content:""
        },
        activeIndex: '1',
        input:'',
        select:{
        	type:'1',
        	content:''
        }
      };
    },
  	methods: {
    handleView:function(url){
    	this.$router.push(url);
    },
    handleSearch:function(){
      if(this.select.type==1){
        this.$router.push({
          path:'/SearchGroup',
          query:{
            content:this.select.content
          }
        })
      } else if(this.select.type==2) {
        this.$router.push({
          path:'/SearchTopic',
          query:{
            content:this.select.content
          }
        })
      } else {
        this.$router.push({
          path:'/SearchCustomer',
          query:{
            content:this.select.content
          }
        })
      }
    },
    handleComplaint:function(){
      var customer_id=localStorage.customer_id
      var key={}
      var me=this
      if(this.complaint.content){
        key.content=this.complaint.content
        key.customer_id=customer_id
      } else {
        this.$notify({title: '错误',message: "请输入内容",duration: 3000})
        return
      }
      this.$post("/complaint/create",key)
          .then(res=>{
            if(res.data.error==0){
              this.$notify({title: '成功',message: "已提交投诉",duration: 3000})
            } else {
              this.$notify({title: '失败',message: res.data.message,duration: 3000})
            }
        }).catch(function(err){
         console.log(err)
        })
      this.complaintVisible = false;
    },
    handleCloseComplaint:function(){
      this.complaintVisible = false;
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
  },mounted:function(){
    this.handleCheckCustomer()
  }
}
</script>

<style>
#app {
  font-family: Helvetica, sans-serif;
  text-align: center;
}
.el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
