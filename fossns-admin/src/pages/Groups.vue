<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">圈子查询</span>
				</div>
				<div>
					<el-input placeholder="请输入圈子名称" v-model="search.data.group_name" :disabled="!search.useGroupName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="圈子名称" slot="prepend" v-model="search.useGroupName"></el-checkbox>
	    		</el-input>
	    		<el-row style="margin:10px 10px 10px 10px;">
	    			<el-col :span="9">
	    				<el-checkbox label="创建时间早于" v-model="search.useEndCreated_at" style="margin-top: 5px"></el-checkbox>
	    			</el-col>
	    			<el-col :span="15">
	    				<el-date-picker v-model="search.data.endCreated_at" :disabled="!search.useEndCreated_at" style="width:100%" type="date" placeholder="选择日期"></el-date-picker>
	    			</el-col>
	    		</el-row>
	    		<el-row style="margin:10px 10px 10px 10px;">
	    			<el-col :span="9">
	    				<el-checkbox label="创建时间晚于" v-model="search.useStartCreated_at" style="margin-top: 5px"></el-checkbox>
	    			</el-col>
	    			<el-col :span="15">
	    				<el-date-picker v-model="search.data.startCreated_at" :disabled="!search.useStartCreated_at" style="width:100%" type="date" placeholder="选择日期"></el-date-picker>
	    			</el-col>
	    		</el-row>
	    		<el-button style="margin: 10px;" @click="handleSearch">查询</el-button>
				</div>
	    </el-card>
		</el-col>
		<el-col :span="18">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">圈子列表</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%;text-align: left;">
					 	<el-table-column prop="group_name" label="圈子名称" width="300"></el-table-column>
					 	<el-table-column prop="created_at" label="创建时间" width="300"></el-table-column>
					 	<el-table-column prop="creator_name" label="创建者" width="300"></el-table-column>
    			</el-table>
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="table.pageIndex" :page-sizes="[25, 50, 75, 100]" :page-size="table.pageCount" layout="total, sizes, prev, pager, next, jumper" :total="table.total">
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
				//total: 3,
        pageCount: 25,
        pageIndex: 1,
				items:[
					// {name:"zhafngdsan",created_at:"2018-06-08 01:30",creator_name:"zhangsan"},
					// {name:"slidsi",created_at:"2018-06-09 01:45",creator_name:"lisi"},
					// {name:"huadngfwu",created_at:"2018-06-10 15:30",creator_name:"huangwu"}
				]
			},
			search:{
				useGroupName:true,
				useStartCreated_at:true,
				useEndCreated_at:true,
				data:{
					group_name:'',
					startCreated_at:'',
					endCreated_at:''
				}
			},

		}
	},
	methods:{
    handleSizeChange(val) {
      this.table.pageCount = val;
      this.handleSearch();
    },
    handleCurrentChange(val) {
      this.table.pageIndex = val;
      this.handleSearch();
    },
		handleSearch(){
			var key={};
      key.length=this.table.pageCount ;
      key.offset= (this.table.pageIndex-1)*this.table.pageCount;
      if(this.search.useGroupName&&this.search.data.group_name){
        key.group_name=this.search.data.group_name;
      }
      if(this.search.useStartCreated_at&&this.search.data.startCreated_at){
        key.startCreated_at=this.$filter(this.search.data.startCreated_at,'yyyy-mm-dd','0');
      }
      if(this.search.useEndCreated_at&&this.search.data.endCreated_at){
        key.endCreated_at=this.$filter(this.search.data.endCreated_at,'yyyy-mm-dd','0');
      }

      var me=this;
      this.$get("/admin/group/search",key)
			.then(res=>{
				me.table=res.data.table
			})
			.catch(function(err){
		 		console.log(err)
			});
		}
	},
	mounted:function(){
		this.handleSearch()
	}
}
</script>
<style scoped>
</style>