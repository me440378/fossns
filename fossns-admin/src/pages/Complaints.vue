<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">投诉查询</span>
				</div>
				<div>
					<el-input placeholder="请输入用户名" v-model="search.data.name" :disabled="!search.useName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="用户名" slot="prepend" v-model="search.useName"></el-checkbox>
	    		</el-input>
	    		<el-input placeholder="请输入用户昵称" v-model="search.data.nickname" :disabled="!search.useNickname" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="用户昵称" slot="prepend" v-model="search.useNickname"></el-checkbox>
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
	    		<el-input placeholder="请输入投诉内容涉及" v-model="search.data.content" :disabled="!search.useContent" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="请输入投诉内容涉及" slot="prepend" v-model="search.useContent"></el-checkbox>
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
					<span style="font-family:Microsoft YaHei;font-size: 24px;">投诉列表</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%">
						<el-table-column prop="name" label="用户名" width="180"></el-table-column>
      			<el-table-column prop="nickname" label="用户昵称"  width="150"></el-table-column>
      			<el-table-column prop="created_at" label="投诉时间"  width="350"></el-table-column>
      			<el-table-column  prop="content" label="投诉内容" width="350"></el-table-column>
    			  <el-table-column label="操作" >
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
			    <el-form-item label="用户名：">
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 380px;" >   {{form.data.name}}
			      </span>
			    </el-form-item>
			    <el-form-item label="用户昵称：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 370px;" >   {{form.data.nickname}}</span>
			    </el-form-item>
			     <el-form-item label="投诉时间：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 370px;" >   {{form.data.created_at}}</span>
			    </el-form-item>
			     <el-form-item label="投诉内容：">
			     <el-scrollbar>
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 380px;word-wrap: break-word;height: 150px;" >   {{form.data.content}}
			     </span>
			      </el-scrollbar>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			     <el-button type="primary" v-if="form.data.is_checking==1" @click="handleCheck(form.data.complaint_id,form.data.customer_id)">已 阅</el-button>
			    <el-button @click="form.visible = false">取 消</el-button>
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
					// {id:'1',name:"zhangsan",nickname:"张三",content:"1231212121929291929werwerwerwerwer",created_at:"2018-05-23",is_checking:1},
					// {id:'2',name:"lisi",nickname:"李四",content:"123",created_at:"2018-06-23",is_checking:1},
					// {id:'3',name:"huangwu",nickname:"黄五",content:"123",created_at:"2018-05-26",is_checking:1},
					// {id:'4',name:"chenming",nickname:"陈明",content:"123",created_at:"2018-03-02",is_checking:0}
				]
			},
			   form: {
			        visible:false,
			        title: '',
			        disabled: false,
			        data:{
			        	complaint_id:"",
			        	customer_id:"",
			          name: '',
			          nickname: '',
			          created_at:'',
			          content: '',
			          is_checking:''
			         }
			      },
			search:{
				useName:true,
				useNickname:true,
				useContent:true,
				useIsChecking:true,
				useStartCreated_at:true,
				useEndCreated_at:true,
				data:{
					name:'',
					nickname:'',
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
      this.form.title = "投诉内容审核";
      this.form.disabled = false;
      this.form.data.complaint_id=row.complaint_id
      this.form.data.customer_id=row.customer_id
      this.form.data.name =  row.name;
      this.form.data.nickname = row.nickname;
      this.form.data.created_at = row.created_at;
      this.form.data.content = row.content;
      this.form.data.is_checking=row.is_checking;
      this.form.visible = true;
    },
    handleShow(row) {
     this.form.title = "投诉详情查看";
      this.form.disabled = false;
      this.form.data.complaint_id=row.complaint_id
      this.form.data.customer_id=row.customer_id
      this.form.data.name =  row.name;
      this.form.data.nickname = row.nickname;
      this.form.data.created_at = row.created_at;
      this.form.data.content = row.content;
      this.form.data.is_checking=row.is_checking;
      this.form.visible = true;
    },
    handleCheck:function(complaint_id,customer_id){
    	var key={}
      key.complaint_id=complaint_id
      key.customer_id=customer_id
 			var me=this;
       this.$post("/admin/complaint/check",key)
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
      if(this.search.useName&&this.search.data.name){
        key.name=this.search.data.name;
      }
      if(this.search.useNickname&&this.search.data.nickname){
        key.nickname=this.search.data.nickname;
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
      this.$get("/admin/complaint/search",key)
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

