<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">加入圈子申请查询</span>
				</div>
				<div>
					<el-input placeholder="请输入申请者用户名" v-model="search.data.customer_name" :disabled="!search.useCustomerName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="申请者" slot="prepend" v-model="search.useCustomerName"></el-checkbox>
	    		</el-input>
	    		<el-input placeholder="请输入加入圈子名称" v-model="search.data.group_name" :disabled="!search.useGroupName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="加入圈子名称" slot="prepend" v-model="search.useGroupName"></el-checkbox>
	    		</el-input>
	    		  <el-row>
			            <el-col :span="7" style="text-align: center">
			              <el-checkbox label="审核状态" v-model="search.useIsChecking" style="margin-top: 5px"></el-checkbox>
			            </el-col>
			            <el-col :span="17">
			              <el-select v-model="search.data.is_checking" placeholder="请选择审核状态" :disabled="!search.useIsChecking">
			                <el-option label="未审核" value="1"></el-option>
			                <el-option label="已通过" value="0"></el-option>
			              </el-select>
			            </el-col>
          </el-row>
	    		<el-button style="margin:10px;" @click="handleSearch">查询</el-button>
				</div>
	    </el-card>
		</el-col>
		<el-col :span="18">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">入圈审批</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%;text-align: left;">
						<el-table-column prop="customer_name" label="用户名"  width="300"></el-table-column>
      			<el-table-column prop="group_name" label="加入圈子名称"  width="300"></el-table-column>
      			<el-table-column label="操作" width="200">
              <template slot-scope="scope">
              <el-button size="small" type="primary" v-if="scope.row.is_checking==1"  @click="handleShowCheck(scope.row)">审核</el-button>
              <el-button size="small" v-else @click="handleShow(scope.row)">查看</el-button>
              </template>
            </el-table-column>
    			</el-table>
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="table.pageIndex" :page-sizes="[25, 50, 75, 100]" :page-size="25" layout="total, sizes, prev, pager, next, jumper" :total="table.total">
          </el-pagination>
				</div>
	    </el-card>
        <el-dialog :title="form.title" :visible.sync="form.visible" width="40%" size="tiny" >       
			  <el-form ref="form" :model="form.data" label-width="150px">
			    <el-form-item label="申请者：">
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.customer_name}}
			      </span>
			    </el-form-item>
			    <el-form-item label="加入圈子名称：">
			     <el-scrollbar>
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 330px;word-wrap: break-word;" >   {{form.data.group_name}}
			     </span>
			      </el-scrollbar>
			    </el-form-item>
			     <el-form-item label="状态：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" v-if="form.data.is_checking==1">   未审核
			     </span>
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" v-else>   已通过
			     </span>
			    </el-form-item>
                    </el-form>
			  <div slot="footer" class="dialog-footer">
			     <el-button type="primary" v-if="form.data.is_checking==1" @click="handleCheck(form.data.apply_id,form.data.customer_id,1)">通 过</el-button>
			     <el-button v-if="form.data.is_checking==1" @click="handleCheck(form.data.apply_id,form.data.customer_id,0)">不通过</el-button>
			      <el-button type="primary" v-else @click="form.visible = false">确 认</el-button>
			  </div>
		</el-dialog>
		</el-col>
	</el-row>
</div>
</template>
<script>
export default{

	data(){
		return{
			"table":{
				// "total":43,
				pageCount:25,
				pageIndex:1
				// "items":[
				// {"is_checking":0,"group_name":"阴阳师","apply_id":16,"customer_name":"ak47","customer_id":3},
				// {"is_checking":0,"group_name":"阴阳师","apply_id":48,"customer_name":"doubleQ","customer_id":11},
				// {"is_checking":0,"group_name":"阴阳师","apply_id":4,"customer_name":"wing","customer_id":1},
				// {"is_checking":0,"group_name":"阴阳师","apply_id":6,"customer_name":"kimi","customer_id":2},
				// {"is_checking":0,"group_name":"阴阳师","apply_id":33,"customer_name":"cus4","customer_id":4}]
			},
			   form:{
			        visible:false,
			        innerVisible:false,
			        title: '',
			        disabled: false,
			        data: 
			          {
			          // "is_checking":0,
			          // "group_name":"阴阳师",
			          // "apply_id":16,
			          // "customer_name":"ak47",
			          // "customer_id":3
			         }
			      },
			search:{
				useCustomerName:true,
				useGroupName:true,
				useIsChecking:true,
				data:{
					customer_name:'',
					group_name:'',
					is_checking:'',
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
     handleShowCheck:function(row) {
      this.form.title="加入圈子审核";
      this.form.disabled=false;
      this.form.data.apply_id=row.apply_id;
      this.form.data.customer_name=row.customer_name;
      this.form.data.group_name=row.group_name;
      this.form.data.is_checking=row.is_checking;
      this.form.data.customer_id=row.customer_id;
      this.form.visible=true;
    },
    handleShow:function(row) {
      this.form.title = "加入圈子申请";
      this.form.disabled=false;
      this.form.data.apply_id=row.apply_id;
      this.form.data.customer_name=row.customer_name;
      this.form.data.group_name=row.group_name;
      this.form.data.is_checking=row.is_checking;
      this.form.data.customer_id=row.customer_id;
      this.form.visible=true;
    },
    handleCheck:function(apply_id,customer_id,pass){
      var key={}
      key.apply_id=apply_id
      key.customer_id=customer_id
      key.pass=pass
 			var me=this;
       this.$post("/admin/joingroup/check",key)
			.then(res=>{
				if(res.data.error==0){
          this.$notify({title: '成功',message: "审核成功",duration: 3000})
        } else {
          this.$notify({title: '失败',message: res.data.message,duration: 3000})
        }
			})
			.catch(function(err){
		 		console.log(err)
			});
			this.handleSearch()
			this.form.visible=false
    },
		handleSearch:function(){
			var key={};
      key.length=this.table.pageCount ;
      key.offset= (this.table.pageIndex-1)*this.table.pageCount;
      if(this.search.useCustomerName&&this.search.data.customer_name){
        key.customer_name=this.search.data.customer_name;
      }
     	if(this.search.useGroupName&&this.search.data.group_name){
        key.group_name=this.search.data.group_name;
      }
	 		if(this.search.useIsChecking&&this.search.data.is_checking){
        key.is_checking=this.search.data.is_checking;
      }
      var me=this;
      this.$post("/admin/joingroup/search",key)
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

