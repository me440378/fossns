<template>
<div>
	<el-card style="width: 80%;margin: auto;">
		<div slot="header"><span>我的资料</span></div>
		<div>
			<el-form label-width="80px">
				<el-form-item label="用户名称">
			    <span>{{name}}</span>
			  </el-form-item>
			  <el-form-item label="用户昵称">
			    <el-input v-model="nickname"></el-input>
			  </el-form-item>
			  <el-form-item label="用户密码">
			    <el-button @click="changepswdVisible = true">修改密码</el-button>
						<el-dialog title="修改密码" :visible.sync="changepswdVisible" width="30%" :before-close="handleClose">
					  	<el-form label-width="80px">
								<el-form-item label="旧密码" style="margin: 20px;">
							    <el-input v-model="oldpswd"></el-input>
							  </el-form-item>
							  <el-form-item label="新密码" style="margin: 20px;">
							    <el-input v-model="newpswd"></el-input>
							  </el-form-item>
							</el-form>
						  <span slot="footer" class="dialog-footer" style="margin: 20px;">
						    <el-button @click="changepswdVisible = false">取 消</el-button>
						    <el-button type="primary" @click="handleChangePswd">确 定</el-button>
						  </span>
						</el-dialog>
			  </el-form-item>
			  <el-form-item>
			    <el-button type="primary" @click="handleSave">保存资料</el-button>
			  </el-form-item>
			</el-form>
		</div>
	</el-card>
</div>
</template>
<script>
export default{
	data(){
		return{
			changepswdVisible:false,
			name:"",
			nickname:"",	
			oldpswd:"",
			newpswd:""
		}
	},
	methods:{
		handleSave:function(){
			var customer_id=localStorage.customer_id
			var key={}
			var me=this
			if(this.nickname){
				key.nickname=this.nickname
				key.customer_id=customer_id
			} else {
				this.$notify({title: '错误',message: "请输入昵称",duration: 3000})
				return
			}
			this.$post("/customer/update",key)
	        .then(res=>{
	          if(res.data.error==0){
	            this.$notify({title: '成功',message: "保存成功",duration: 3000})
	          } else {
	            this.$notify({title: '失败',message: res.data.message,duration: 3000})
	          }
	      }).catch(function(err){
	       console.log(err)
	      })
		},
		handleChangePswd:function(){
			var customer_id=localStorage.customer_id
			var key={}
			var me=this
			if(this.oldpswd&&this.newpswd){
				key.oldpswd=this.oldpswd
				key.newpswd=this.newpswd
				key.customer_id=customer_id
			} else {
				this.$notify({title: '错误',message: "请完整填写信息",duration: 3000})
				return
			}
			this.$post("/customer/changepswd",key)
	        .then(res=>{
	          if(res.data.error==0){
	            this.$notify({title: '成功',message: res.data.message,duration: 3000})
	          } else {
	            this.$notify({title: '失败',message: res.data.message,duration: 3000})
	          }
	      }).catch(function(err){
	       console.log(err)
	      })
			this.changepswdVisible=false;
		},
		handleClose:function(){
			this.changepswdVisible=false;
		},
		handleAll:function(){
			var customer_id=localStorage.customer_id
			var key={}
			var me=this
			key.customer_id=customer_id
			this.$post("/customer/get",key)
	        .then(res=>{
	          me.name=res.data.name
	          me.nickname=res.data.nickname
	      }).catch(function(err){
	       console.log(err)
	      })
		}
	},
	mounted:function(){
		this.handleAll()
	}
}
</script>
<style scoped>
</style>