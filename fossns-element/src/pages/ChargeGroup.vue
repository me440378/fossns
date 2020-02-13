<template>
	<div>
		<el-card style="width: 80%;margin: auto;">
			<div slot="header"><span>圈子资料</span></div>
			<div>
				<el-form label-width="80px">
					<el-form-item label="圈子名称">
				    <span>{{group.name}}</span>
				  </el-form-item>
				  <el-form-item label="圈子简介">
				  	<span>{{group.summary}}</span>
				    <el-button @click="changesummVisible = true">修改简介</el-button>
							<el-dialog title="修改简介" :visible.sync="changesummVisible" width="30%" :before-close="handleClose">
						  	<el-form label-width="80px">
									<el-form-item label="原简介" style="margin: 20px;">
								    <span>{{group.summary}}</span>
								  </el-form-item>
								  <el-form-item label="新简介" style="margin: 20px;">
								    <el-input type="textarea" v-model="form.summary"></el-input>
								  </el-form-item>
								</el-form>
							  <span slot="footer" class="dialog-footer" style="margin: 20px;">
							    <el-button @click="changesummVisible = false">取 消</el-button>
							    <el-button type="primary" @click="handleChangeSumm">确 定</el-button>
							  </span>
							</el-dialog>
				  </el-form-item>
				  <!-- <el-form-item>
				    <el-button type="primary" @click="handleSave">保存资料</el-button>
				  </el-form-item> -->
				</el-form>
			</div>
		</el-card>
	</div>
</template>
<script>
export default{
	data(){
		return{
			changesummVisible:false,
			form:{
				summary:"",
			},
			group:{
				name:"",
				summary:""
			}
		}
	},
	methods:{
		handleChangeSumm:function(){
			var summary_new = this.form.summary
			var group_id = this.$route.params.group_id
			var key = {}
			var me = this
			key.summary_new = summary_new
			key.group_id = group_id
			this.$post("/group/update",key)
        .then(res=>{
          if(res.data.error==0){
            this.$notify({title: '成功',message: "已提交修改申请",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 6000})
          }
      }).catch(function(err){
       console.log(err)
      });
      this.changesummVisible = false
		},
		handleSave:function(){

		},
		handleClose:function(){
			this.changesummVisible = false
		},
		handleAll:function(){
			var group_id = this.$route.params.group_id
			var me = this
			var key={}
			key.group_id=group_id;
			this.$post("/group/get",key)
        .then(res=>{
          me.group=res.data.group
      }).catch(function(err){
       console.log(err)
      });
		}
	},
	mounted:function(){
		this.handleAll();
	}
}
</script>
<style scoped>
</style>