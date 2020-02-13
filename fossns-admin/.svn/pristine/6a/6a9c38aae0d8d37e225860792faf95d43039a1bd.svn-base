<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">用户查询</span>
				</div>
				<div>
					<el-input placeholder="请输入用户名" v-model="search.data.name" :disabled="!search.useName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="用户名" slot="prepend" v-model="search.useName"></el-checkbox>
	    		</el-input>
	    		<el-input placeholder="请输入用户昵称" v-model="search.data.nickname" :disabled="!search.useNickname" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="用户昵称" slot="prepend" v-model="search.useNickname"></el-checkbox>
	    		</el-input>
	    		<el-button style="margin: 10px;" @click="handleSearch">查询</el-button>
				</div>
	    </el-card>
		</el-col>
		<el-col :span="18">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">用户列表</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%">
					 	<el-table-column prop="name" label="用户名" width="300"></el-table-column>
      			<el-table-column prop="nickname" label="用户昵称" width="300"></el-table-column>
    			</el-table>
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="table.pageIndex" :page-sizes="[25, 50, 75, 100]" :page-size="25" layout="total, sizes, prev, pager, next, jumper" :total="table.total">
          </el-pagination>
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
			table:{
				//total: 1,
        pageCount: 25,
        pageIndex: 1,
				items:[
					// {name:"zhangsan",nickname:"张三"},
					// {name:"lisi",nickname:"李四"},
					// {name:"huangwu",nickname:"黄五"}
				]
			},
			search:{
				useName:true,
				useNickname:true,
				data:{
					name:'',
					nickname:''
				}
			},

		}
	},
	methods:{
    handleSizeChange:function(val) {
      this.table.pageCount = val;
      this.handleSearch();
    },
    handleCurrentChange:function(val) {
      this.table.pageIndex = val;
      this.handleSearch();
    },
		handleSearch:function(){
			var key={};
      key.length=this.table.pageCount ;
      key.offset= (this.table.pageIndex-1)*this.table.pageCount;
      if(this.search.useName&&this.search.data.name){
        key.name=this.search.data.name;
      }
      if(this.search.useNickname&&this.search.data.nickname){
        key.nickname=this.search.data.nickname;
      }

      var me=this;
      this.$post("/admin/customer/search",key)
			.then(res=>{
				me.table=res.data.table
			})
			.catch(function(err){
		 		console.log(err)
			});
		}
	},
	mounted:function(){
		this.handleSearch();
	}
}
</script>
<style scoped>
</style>