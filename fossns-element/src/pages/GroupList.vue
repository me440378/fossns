<template>
<div>
	<el-tabs type="card" tab-position="left">
	    <el-tab-pane label="最TOP圈子">
	    	<el-collapse style="text-align: left;" accordion>
			    <el-collapse-item v-for="(item,no) in groupList" :title="handleTitle(no,item.name,item.popularity)">
			    	<el-row>
			    		<el-col :span="6">
								<img :src="item.image" style="width: 100%" />
							</el-col>
							<el-col :span="16">
								<span style="margin-left: 10px;">{{item.summary}}</span>
							</el-col>
							<el-col :span="2">
				    		<el-button type="primary" @click="handleJump(item.group_id)">进入</el-button>
				    	</el-col>
				    </el-row>
			    </el-collapse-item>
				</el-collapse>
	    </el-tab-pane>
	    <el-tab-pane label="创建圈子">
	    	<div style="width:60%;text-align:center;padding: 50px;">
		    	<el-form label-width="80px">
						<el-form-item label="圈子名称">
					    <el-input v-model="form.name"></el-input>
					  </el-form-item>
					  <el-form-item label="圈子简介">
					    <el-input type="textarea" :rows="4" v-model="form.summary"></el-input>
					  </el-form-item>
					  <el-form-item>
					    <el-button type="primary" @click="handleCreate">立即创建</el-button>
					  </el-form-item>
				  </el-form>
			  </div>
	    </el-tab-pane>
  	</el-tabs>
</div>
</template>
<script>
export default{
	data(){
		return{
			form:{
				name:'',
				summary:'',
				image:''
			},
			groupList:[
				// {group_id:1,name:"热门圈子1",popularity:10000,image:"",summary:"这是一则简介",no:1},
				// {group_id:2,name:"热门圈子2",popularity:9000,image:"",summary:"这是一则简介",no:2},
				// {group_id:3,name:"热门圈子3",popularity:8000,image:"",summary:"这是一则简介",no:3},
				// {group_id:4,name:"热门圈子4",popularity:7000,image:"",summary:"这是一则简介",no:4},
				// {group_id:5,name:"热门圈子5",popularity:6000,image:"",summary:"这是一则简介",no:5}
			]
		}
	},
	methods:{
		handleJump: function(group_id){
			this.$router.push("/Group/" + group_id);
		},
		handleCreate: function(){
			var key={}
			key.customer_id=localStorage.customer_id

			if(this.form.name&&this.form.summary){
        key.name=this.form.name
        key.summary=this.form.summary
      } else {
      	this.$notify({title: '错误',message: "请完整填写信息",duration: 3000})
      	return;
      }

      this.$post("/group/create",key)
        .then(res=>{
          if(res.data.error==0){
          	this.$notify({title: '成功',message: "已提交创建申请",duration: 3000})
          } else {
          	this.$notify({title: '失败',message: res.data.message,duration: 3000})
          }
      }).catch(function(err){
       console.log(err)
      });
		},
		handleTitle: function(no,name,popularity){
			var no = no+1
			return 'top' + no + ' '+name + ' 人气：' + popularity
		},
		handleAll: function(){
			var key = {}
			var me = this
			this.$post("/grouplist/all",key)
      .then(res=>{
        me.groupList = res.data.groupList
      }).catch(function(err){
		 		console.log(err)
				});
		}
	},
	mounted: function(){
		this.handleAll();
	}
}
</script>
<style scoped>
</style>