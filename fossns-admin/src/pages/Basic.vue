<template>
<div>
	<el-row :gutter="30">
		<el-col :span="6">
			<el-card class="box-card" style="height: 200px">
				<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 36px;">用户总数</span>
				</div>
				<div class="text item">
					<span style="font-family:Microsoft YaHei;font-size: 72px;">{{customerNum}}</span>
				</div>
			</el-card>
		</el-col>
		<el-col :span="6">
			<el-card class="box-card" style="height: 200px">
				<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 36px;">活跃用户数</span>
				</div>
				<div class="text item">
					<span style="font-family:Microsoft YaHei;font-size: 72px;">{{livelyCustomerNum}}</span>
				</div>
			</el-card>
		</el-col>
		<el-col :span="6">
			<el-card class="box-card" style="height: 200px">
				<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 36px;">圈子总数</span>
				</div>
				<div class="text item">
					<span style="font-family:Microsoft YaHei;font-size: 72px;">{{groupNum}}</span>
				</div>
			</el-card>
		</el-col>
		<el-col :span="6">
			<el-card class="box-card" style="height: 200px">
				<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 36px;">话题总数</span>
				</div>
				<div class="text item">
					<span style="font-family:Microsoft YaHei;font-size: 72px;">{{topicNum}}</span>
				</div>
			</el-card>
		</el-col>
	</el-row>
</div>
</template>
<script>
export default{
	data(){
		return{
			customerNum:0,
			livelyCustomerNum:0,
			groupNum:0,
			topicNum:0
		}
	},
	methods:{
		handleAll:function(){
			var key={}
			var me=this
			this.$post("/admin/basic/all",key)
			.then(res=>{
				me.customerNum=res.data.customerNum
				me.livelyCustomerNum=res.data.livelyCustomerNum
				me.groupNum=res.data.groupNum
				me.topicNum=res.data.topicNum
			})
			.catch(function(err){
		 		console.log(err)
			});
		}
	},
	mounted:function(){
		this.handleAll()
	}
}
</script>
<style scoped>
</style>