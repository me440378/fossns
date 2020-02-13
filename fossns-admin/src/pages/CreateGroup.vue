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
	    		<el-button @click="handleSearch">查询</el-button>
				</div>
	    </el-card>
		</el-col>
		<el-col :span="18">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">创建圈子审批</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%;text-align: left;">
      			<el-table-column prop="group_name" label="圈子名称"  width="240"></el-table-column>
      			<el-table-column prop="summary" label="圈子简介"  width="240"></el-table-column>
      			<el-table-column prop="creator_name" label="创建者"  width="240"></el-table-column>
      			<el-table-column prop="created_at" label="创建时间"  width="240"></el-table-column>
      			  <el-table-column label="操作">
                <template slot-scope="scope">
                <el-button size="small" type="primary" @click="handleShowCheck(scope.row)">审核</el-button>
                </template>
                </el-table-column>
    			</el-table>
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="table.pageIndex" :page-sizes="[25, 50, 75, 100]" :page-size="25" layout="total, sizes, prev, pager, next, jumper" :total="table.total">
          </el-pagination>
				</div>
	    </el-card>
        <el-dialog :title="form.title" :visible.sync="form.visible" width="40%" size="tiny" >
            
			  <el-form ref="form" :model="form.data" label-width="150px">

			    <el-form-item label="圈子名称：">
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.group_name}}
			      </span>
			    </el-form-item>
			    <el-form-item label="圈子简介：">
			     <el-scrollbar>
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 330px;word-wrap: break-word;height: 110px;" >   {{form.data.summary}}
			     </span>
			      </el-scrollbar>
			    </el-form-item>
			    <el-form-item label="创建者：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.creator_name}}</span>
			    </el-form-item>
			     <el-form-item label="创建时间：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.created_at}}
			      </span>
			    </el-form-item>
			
			<!--     <el-dialog width="30%" title="    圈子创建不通过" :visible.sync="form.innerVisible" :modal="false" append-to-body>
				       <el-form-item label="">
			              <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 5}" placeholder="请输入不通过原因" v-model="form.data.reason" :disabled="form.disabled" >
			              </el-input>
			            </el-form-item>
			            <div slot="footer" class="dialog-footer">
			            <el-button type="primary" @click="handleDispass()">确 认</el-button>
			            </div>
                </el-dialog> -->
                    </el-form>
			  <div slot="footer" class="dialog-footer">
			     <el-button type="primary" @click="handleCheck(form.data.group_id,form.data.creator_id,1)">通 过</el-button>
			    <el-button @click="handleCheck(form.data.group_id,form.data.creator_id,0)">不通过</el-button>
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
					// {id:'1',name:"校园爱情",summary:"何同学与他的猫，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，",creator_id:"1",creator_name:"zhangsan",created_at:"2018-05-23"},
					// {id:'2',name:"古代情缘",summary:"萧风路见不平，拔刀相助堂主女儿晴水",creator_id:"2",creator:"lisi",created_at:"2018-06-23"},
					// {id:'3',name:"玄幻仙侠",summary:"长生上山拜师",creator_name:"huangwu",creator_id:"3",created_at:"2018-05-26"},
					// {id:'4',name:"职场萌新报到",summary:"杜小路初入职场",creator_name:"chenming",creator_id:"4",created_at:"2018-03-02"}
				]
			},
			   form: {
			        visible:false,
			        innerVisible:false,
			        title: '',
			        disabled: false,
			        data: 
			          {
			          group_id:'',
			          group_name: '',
			          summary:'',
			          creator_name:'',
			          creator_id:'',
			          created_at:''
			         }
			      },
			search:{
				useGroupName:true,
				useCreatorName:true,
				useStartCreated_at:true,
				useEndCreated_at:true,
				data:{
					group_name:'',
					creator_name:'',
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
     handleShowCheck(row) {
      this.form.title = "圈子审核";
      this.form.disabled = false;
      this.form.data.group_id = row.group_id;
      this.form.data.group_name =  row.group_name;
      this.form.data.summary = row.summary;
      this.form.data.creator_id= row.creator_id;
      this.form.data.creator_name = row.creator_name;
      this.form.data.created_at = row.created_at;
      this.form.data.is_public= row.is_public;
      this.form.visible = true;
    },
    handleCheck:function(group_id,customer_id,pass){
    	var key={}
      key.group_id=group_id
      key.customer_id=customer_id
      key.pass=pass
 			var me=this;
       this.$post("/admin/creategroup/check",key)
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
      if(this.search.useCreatorName&&this.search.data.creator_name){
        key.creator_name=this.search.data.creator_name;
      }
     	if(this.search.useStartCreated_at&&this.search.data.startCreated_at){
        key.startCreated_at=this.$filter(this.search.data.startCreated_at,'yyyy-mm-dd','0');
      }
      if(this.search.useEndCreated_at&&this.search.data.endCreated_at){
        key.endCreated_at=this.$filter(this.search.data.endCreated_at,'yyyy-mm-dd','0');
      }

      var me=this;
      this.$get("/admin/creategroup/search",key)
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

