<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">话题查询</span>
				</div>
				<div>
				<el-input placeholder="请输入话题名称关键字" v-model="search.data.topic_name" :disabled="!search.useTopicName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="话题名称" slot="prepend" v-model="search.useTopicName"></el-checkbox>
	    		</el-input>
					<el-input placeholder="请输入圈子名称关键字" v-model="search.data.group_name" :disabled="!search.useGroupName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="圈子名称" slot="prepend" v-model="search.useGroupName"></el-checkbox>
	    		</el-input>
	    		<el-input placeholder="请输入创建者用户名" v-model="search.data.creator_name" :disabled="!search.useCreatorName" style="margin:10px 10px 10px 10px;">
	      		<el-checkbox label="创建者用户名" slot="prepend" v-model="search.useCreatorName"></el-checkbox>
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
					<span style="font-family:Microsoft YaHei;font-size: 24px;">创建话题审批</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%;text-align: left;">
					<el-table-column prop="topic_name" label="话题名称"  width="400"></el-table-column>
      			<el-table-column prop="group_name" label="圈子名称"  width="300"></el-table-column>
      			<el-table-column prop="creator_name" label="创建者"  width="200"></el-table-column>
      			<el-table-column prop="created_at" label="创建时间"  width="200"></el-table-column>
      			  <el-table-column label="操作" >
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
					<el-form-item label="话题名称：">
			     <el-scrollbar>
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 330px;" >   {{form.data.topic_name}}
			     </span>
			      </el-scrollbar>
			    </el-form-item>
			    <el-form-item label="圈子名称：">
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.group_name}}
			      </span>
			    </el-form-item>
			    <el-form-item label="创建者：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.creator_name}}</span>
			    </el-form-item>
			     <el-form-item label="创建时间：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.created_at}}
			      </span>
			    </el-form-item>
			     <el-form-item label="是否公开：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" v-if="form.data.is_public==1">   公开
			     </span>
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" v-else>   不公开
			     </span>
			    </el-form-item>
                    </el-form>
			  <div slot="footer" class="dialog-footer">
			     <el-button type="primary" @click="handleCheck(form.data.topic_id,form.data.creator_id,1)">通 过</el-button>
			    <el-button @click="handleCheck(form.data.topic_id,form.data.creator_id,0)">不通过</el-button>
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
				//total: 3,
        pageCount: 25,
        pageIndex: 1,
				items:[
					// {id:"1",group_name:"校园爱情",name:"topicOne",creator:"zhangming",created_at:"2018-9-23",is_public:1,is_checking:1},
					// {id:"2",group_name:"校园爱情",name:"topicTwo",creator:"xiaoming",created_at:"2018-9-23",is_public:1,is_checking:1},
					// {id:"3",group_name:"校园爱情",name:"topicThree",creator:"xiaoshi",created_at:"2018-9-23",is_public:1,is_checking:1},
					// {id:"4",group_name:"校园爱情",name:"topicFour",creator:"awing",created_at:"2018-9-23",is_public:1,is_checking:1}
				]
			},
			   form: {
			        visible:false,
			        innerVisible:false,
			        title: '',
			        disabled: false,
			        data: 
			          {
			          topic_id:'',
			          group_name:'',
			          topic_name: '',
			          creator_name:'',
			          created_at:''
			         }
			      },
			search:{
				useGroupName:true,
				useTopicName:true,
				useCreatorName:true,
				useIsChecking:true,
				useStartCreated_at:true,
				useEndCreated_at:true,
				data:{
					group_name:'',
					topic_name:'',
					creator_name:'',
					startCreated_at:'',
					endCreated_at:'',
					is_checking:'',
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
      this.form.title = "创建话题审核";
      this.form.disabled = false;
      this.form.data.topic_id = row.topic_id;
      this.form.data.group_name = row.group_name;
      this.form.data.topic_name =  row.topic_name;
      this.form.data.creator_name = row.creator_name;
      this.form.data.creator_id=row.creator_id;
      this.form.data.created_at = row.created_at;
      this.form.visible = true;
    },
    handleCheck:function(topic_id,customer_id,pass){
    	var key={}
      key.topic_id=topic_id
      key.customer_id=customer_id
      key.pass=pass
 			var me=this;
       this.$post("/admin/createtopic/check",key)
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
      if(this.search.useGroupName&&this.search.data.group_name){
        key.group_name=this.search.data.group_name;
      }
      if(this.search.useTopicName&&this.search.data.topic_name){
        key.topic_name=this.search.data.topic_name;
      }
      if(this.search.useCreatorName&&this.search.data.creator_name){
        key.creator_name=this.search.data.creator_name;
      }
      if(this.search.useStartCreated_at&&this.search.data.startCreated_at){
        key.startCreated_at=this.$filter(this.search.data.startCreated_at,'yyyy-mm-dd','0');
      }
      if(this.search.useEndCreated_at&&this.search.data.endCreated_at){
        key.endCreated_at=this.$filter(this.search.data.endCreated_at,'yyyy-mm-dd','0');
      }
      if(this.search.useIsChecking&&this.search.data.is_checking){
        key.is_checking=this.search.data.is_checking;
      }
      var me=this;
      this.$get("/admin/createtopic/search",key)
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

