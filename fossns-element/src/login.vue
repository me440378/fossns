<template>
  <div id="app">
    <div style="width: 600px;margin: 200px auto 0 auto;border: 1px solid #e3e3e3;border-radius: 3px; background: white;">
      <el-row style="margin:30px;">
        <div>
          <el-row :gutter="20">
            <el-col :span="24">
              <img src="@/assets/logo.png" style="width: 100%">
              <span class="sm-title">
                <span style="color: gray;font-size:20px">网络社交系统</span>
                &nbsp;&nbsp;
                <span style="font-size:20px" v-if="isLogin">用户登录</span>
                <span style="font-size:20px" v-if="!isLogin">用户注册</span>
              </span>
            </el-col>
          </el-row>
        </div>
        <div>
          <el-row style='margin-top: 30px;'>
            <el-col :span="4"><span>用户名</span></el-col>
            <el-col :span="20">
             <el-input v-model="form.username"></el-input>
            </el-col>
          </el-row>
          <el-row style='margin-top: 30px;'>
            <el-col :span="4"><span>用户密码</span></el-col>
            <el-col :span="20" >
             <el-input type='password' v-model="form.password"></el-input>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20" style="text-align: center;">
             <el-button type="primary" style="margin-top: 30px;width: 200px;padding: 10px;" @click="handleLogin()" v-if="isLogin">登录</el-button>
             <el-button type="primary" style="margin-top: 30px;width: 200px;padding: 10px;" @click="handleRegister()" v-if="!isLogin">注册</el-button>
            </el-col>
            <el-col :span="4">
              <el-button type="text" @click="isLogin = false" v-if="isLogin" style="margin-top: 30px;padding: 10px;">立即注册</el-button>
              <el-button type="text" @click="isLogin = true" v-if="!isLogin" style="margin-top: 30px;padding: 10px;">立即登陆</el-button>
            </el-col>
          </el-row>
         </div>
      </el-row>
    </div>
    <div style="width: 600px;margin: 10px auto 0 auto;">
      <el-row>
        <el-col :span="14" style="color: #c0c0c0;font-size: 0.8em;">
          数据库原理与应用课程设计&copy;2018-06
        </el-col>
         <el-col :span="10" style="color: #c0c0c0;font-size: 0.8em;text-align:right;">
        produce by 何浩嵘与陈诗华小组
        </el-col>
      </el-row>
    </div>
  </div>

</template>
<script>
module.exports = {
  data: function () {
    return {
      isLogin:true,
      form: {
        username: '',
        password:'',
      }
    }
  },
  methods: {
    handleLogin:function() {
      if(!this.form.username){
        this.$notify({
            title: '警告',
            message: '用户名不可为空！',
            type: 'warning'
          });
         return;
      }
      if(!this.form.password){
        this.$notify({
            title: '警告',
            message: '用户密码不可为空！',
            type: 'warning'
          });
         return;
      }

      var me = this;
      this.$get('/login',this.form)
        .then(res=>{
            if(res.data.error==0){
               me.$notify({
                title: '登陆成功',
                message: res.data.message,
                type: 'success'
              });
            } else {
              me.$notify({
                  title: '错误',
                  message: res.data.message,
                  type: 'warning'
                });
                return;
            }
            localStorage.customer_id=res.data.customer_id
            localStorage.username=me.form.username
            me.form.username=""
            me.form.password=""
            window.location.href='#/Home';
            location.reload();
         })
      
    },
    handleInit:function(){
      localStorage.customer_id=""
    },
    handleRegister:function(){
      if(!this.form.username){
        this.$notify({
            title: '警告',
            message: '用户名不可为空！',
            type: 'warning'
          });
         return;
      }
      if(!this.form.password){
        this.$notify({
            title: '警告',
            message: '用户密码不可为空！',
            type: 'warning'
          });
         return;
      }

      var me = this;
      this.$get('/register',this.form)
        .then(res=>{
            if(res.data.error==0){
               me.$notify({
                title: '注册成功',
                message: res.data.message,
                type: 'success'
              });
            } else {
              me.$notify({
                  title: '错误',
                  message: res.data.message,
                  type: 'warning'
                });
                return;
            }
            me.form.username=""
            me.form.password=""
         })
    }
  },
  mounted:function(){
    this.handleInit()
  }
}
</script>
