<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">圈子举报查询</span>
				</div>
				<div>
					<el-input placeholder="请输入举报者用户名" v-model="search.data.customer_name" :disabled="!search.useCustomerName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="举报者" slot="prepend" v-model="search.useCustomerName"></el-checkbox>
	    		</el-input>
	    		<el-input placeholder="请输入圈子名称关键字" v-model="search.data.group_name" :disabled="!search.useGroupName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="被举报圈子名称" slot="prepend" v-model="search.useGroupName"></el-checkbox>
	    		</el-input>
	    			<el-row style="margin:10px 10px 10px 10px;">
	    			<el-col :span="9">
	    				<el-checkbox label="举报时间早于" v-model="search.useEndCreated_at" style="margin-top: 5px"></el-checkbox>
	    			</el-col>
	    			<el-col :span="15">
	    				<el-date-picker v-model="search.data.endCreated_at" :disabled="!search.useEndCreated_at" style="width:100%" type="date" placeholder="选择日期"></el-date-picker>
	    			</el-col>
	    		</el-row>
	    		<el-row style="margin:10px 10px 10px 10px;">
	    			<el-col :span="9">
	    				<el-checkbox label="举报时间晚于" v-model="search.useStartCreated_at" style="margin-top: 5px"></el-checkbox>
	    			</el-col>
	    			<el-col :span="15">
	    				<el-date-picker v-model="search.data.startCreated_at" :disabled="!search.useStartCreated_at" style="width:100%" type="date" placeholder="选择日期"></el-date-picker>
	    			</el-col>
	    		</el-row>
	    		<el-input placeholder="请输入举报内容涉及" v-model="search.data.content" :disabled="!search.useContent" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="请输入举报内容涉及" slot="prepend" v-model="search.useContent"></el-checkbox>
	    		</el-input>
	    		<el-row>
            <el-col :span="7" style="text-align: center">
              <el-checkbox label="审核状态" v-model="search.useIsChecking" style="margin-top: 5px"></el-checkbox>
            </el-col>
            <el-col :span="17">
              <el-select v-model="search.data.is_checking" placeholder="请选择审核状态" :disabled="!search.useIsChecking">
                <el-option label="未审" value="1"></el-option>
                <el-option label="已审" value="0"></el-option>
              </el-select>
            </el-col>
          </el-row>
	    		<el-button style="margin: 10px;" @click="handleSearch">查询</el-button>
				</div>
	    </el-card>
		</el-col>
		<el-col :span="18">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">圈子举报管理</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%;text-align: left;">
						<el-table-column prop="customer_name" label="举报者用户名" width="200"></el-table-column>
      			<el-table-column prop="group_name" label="被举报圈子名称"  width="300"></el-table-column>
      			<el-table-column prop="content" label="举报原因" width="400"></el-table-column>
      		    <el-table-column  prop="created_at" label="举报时间" width="300"></el-table-column>
    			  <el-table-column label="操作" width="150" >
              <template slot-scope="scope">
              <el-button size="small" type="primary" v-if="scope.row.is_checking==1"  @click="handleShowCheck(scope.row)">审核</el-button>
              </template>
            </el-table-column>
    			</el-table>
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="table.pageIndex" :page-sizes="[25, 50, 75, 100]" :page-size="25" layout="total, sizes, prev, pager, next, jumper" :total="table.total">
          </el-pagination>
				</div>
	    </el-card>
        <el-dialog :title="form.title" :visible.sync="form.visible" width="40%" size="tiny" >
			  <el-form ref="form" :model="form.data" label-width="150px">
			    <el-form-item label="举报者用户名：">
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 380px;" >{{form.data.customer_name}}
			      </span>
			    </el-form-item>
			    <el-form-item label="举报圈子名称：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 370px;" >{{form.data.group_name}}</span>
			    </el-form-item>
			     <el-form-item label="举报时间：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 370px;" >{{form.data.created_at}}</span>
			    </el-form-item>
			      <el-form-item label="举报内容：">
			     <el-scrollbar>
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 380px;word-wrap: break-word;height: 150px;" >{{form.data.content}}</span>
			      </el-scrollbar>
			    </el-form-item>
			  </el-form>
				  <div slot="footer" class="dialog-footer">
			     <el-button type="primary" @click="handleCheck(form.data.report_id,form.data.customer_id)">已 阅</el-button>
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
			table:{
				// total: 3,
        pageCount: 25,
        pageIndex: 1,
				items:[
					// {id:'1',creator_name:'limeng',content:'情节太乏味',group_name:'小小小心',created_at:"2018-02-12",is_checking:1},
					// {id:'2',creator_name:'zhangsan',content:'情节太乏味',group_name:'小小心',created_at:"2018-03-12",is_checking:1},
					// {id:'3',creator_name:'zhangxin',content:'情节太乏味',group_name:'小小心心',created_at:"2018-04-12",is_checking:1},
				]
			},
			   form: {
			        visible:false,
			        title: '',
			        disabled: false,
			        data: 
			          {
			          id:"",
			          creator_name: '',
			          report_id:'',
			          group_name:'',
			          is_checking:'',
			          content: '',
			         }
			      },
			search:{
				useCustomerName:true,
				useGroupName:true,
				useContent:true,
				useStartCreated_at:true,
				useEndCreated_at:true,
				useIsChecking:true,
				data:{
					customer_name:'',
					group_name:'',
					startCreated_at:'',
					endCreated_at:'',
					content:'',
					is_checking:''
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
     handleShowCheck(row) {
      this.form.title = "举报内容审核";
      this.form.disabled = false;
      this.form.data.report_id=row.report_id
      this.form.data.customer_id =  row.customer_id;
      this.form.data.customer_name =  row.customer_name;
      this.form.data.group_name = row.group_name;
      this.form.data.created_at = row.created_at;
      this.form.data.content = row.content;
      this.form.visible = true;
    },
    handleCheck:function(report_id,customer_id){
    	var key={}
      key.report_id=report_id
      key.customer_id=customer_id
 			var me=this;
       this.$post("/admin/groupreport/check",key)
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
		handleSearch(){
			var key={};
      key.length=this.table.pageCount ;
      key.offset= (this.table.pageIndex-1)*this.table.pageCount;
      if(this.search.useCustomerName&&this.search.data.customer_name){
        key.customer_name=this.search.data.customer_name;
      }
      if(this.search.useGroupName&&this.search.data.group_name){
        key.group_name=this.search.data.group_name;
      }
      if(this.search.useStartCreated_at&&this.search.data.startCreated_at){
        key.startCreated_at=this.$filter(this.search.data.startCreated_at,'yyyy-mm-dd','0');
      }
      if(this.search.useEndCreated_at&&this.search.data.endCreated_at){
        key.endCreated_at=this.$filter(this.search.data.endCreated_at,'yyyy-mm-dd','0');
      }
      if(this.search.useContent&&this.search.data.content){
        key.content=this.search.data.content;
      }
      if(this.search.useIsChecking&&this.search.data.is_checking){
        key.is_checking=this.search.data.is_checking;
      }
      var me=this;
      this.$get("/admin/groupreport/search",key)
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

