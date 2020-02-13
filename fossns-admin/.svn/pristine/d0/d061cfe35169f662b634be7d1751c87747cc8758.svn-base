<template>
<div>
	<el-row>
		<el-col :span="6">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">圈子查询</span>
				</div>
				<div>
					<el-input placeholder="请输入圈子名称" v-model="search.data.group_name" :disabled="!search.useGroupName" style="margin:10px;">
	      		<el-checkbox label="圈子名称" slot="prepend" v-model="search.useGroupName"></el-checkbox>
	    		</el-input>
	    		<el-input placeholder="请输入创建者用户名" v-model="search.data.creator_name" :disabled="!search.useCreatorName" style="margin:10px;">
	      		<el-checkbox label="创建者用户名" slot="prepend" v-model="search.useCreatorName"></el-checkbox>
	    		</el-input>
	    			<el-row style="margin:10px;">
	    			<el-col :span="9">
	    				<el-checkbox label="更新时间早于" v-model="search.useEndUpdate_at" style="margin-top: 5px"></el-checkbox>
	    			</el-col>
	    			<el-col :span="15">
	    				<el-date-picker v-model="search.data.endUpdate_at" :disabled="!search.useEndUpdate_at" style="width:100%" type="date" placeholder="选择日期"></el-date-picker>
	    			</el-col>
	    		</el-row>
	    		<el-row style="margin:10px;">
	    			<el-col :span="9">
	    				<el-checkbox label="更新时间晚于" v-model="search.useStartUpdate_at" style="margin-top: 5px"></el-checkbox>
	    			</el-col>
	    			<el-col :span="15">
	    				<el-date-picker v-model="search.data.startUpdate_at" :disabled="!search.useStartUpdate_at" style="width:100%" type="date" placeholder="选择日期"></el-date-picker>
	    			</el-col>
	    		</el-row>
	    		<el-button style="margin: 10px;" @click="handleSearch">查询</el-button>
				</div>
	    </el-card>
		</el-col>
		<el-col :span="18">
			<el-card class="box-card" shadow="never">
			<div slot="header" class="clearfix">
					<span style="font-family:Microsoft YaHei;font-size: 24px;">修改圈子审批</span>
				</div>
				<div>
					<el-table :data="table.items" style="width: 100%;text-align: left;">
      			<el-table-column prop="group_name" label="圈子名称"  width="180"></el-table-column>
      			<el-table-column prop="summary" label="现有简介"  width="240"></el-table-column>
      			<el-table-column prop="summary_new" label="新简介"  width="240"></el-table-column>
      			<el-table-column prop="creator_name" label="创建者"  width="180"></el-table-column>
      			<el-table-column prop="update_at" label="更新时间"  width="240"></el-table-column>
      			  <el-table-column label="操作" >
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
            
			  <el-form ref="form" :model="form.data" label-width="110px">

			    <el-form-item label="圈子名称：">
			      <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.group_name}}
			      </span>
			    </el-form-item>
			    <el-form-item label="圈子新简介：">
			     <el-scrollbar>
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 330px;word-wrap: break-word;height: 110px;" >   {{form.data.summary_new}}
			     </span>
			      </el-scrollbar>
			    </el-form-item>
			    <el-form-item label="创建者：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.creator_name}}</span>
			    </el-form-item>
			     <el-form-item label="更新时间：">
			     <span style="display: block;text-align:left;font-size: 16px;font-family:Microsoft YaHei;width: 350px;" >   {{form.data.update_at}}
			      </span>
			    </el-form-item>
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
					// {id:'1',name:"校园爱情",summary_new:'何同学的猫',summary:"何同学与他的猫，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，喵喵喵喵，啦啦啦啦啦，",creator_id:"1",creator:"zhangsan",update_at:"2018-05-27",is_public:1,is_checking:1},
					// {id:'2',name:"古代情缘",summary_new:'萧风晴水',summary:"萧风路见不平，拔刀相助堂主女儿晴水",creator_id:"2",creator:"lisi",update_at:"2018-06-28",is_public:0,is_checking:1},
					// {id:'3',name:"玄幻仙侠",summary_new:'',summary:"长生上山拜师",creator_id:"3",creator:"huangwu",update_at:"2018-05-30",is_public:1,is_checking:1},
					// {id:'4',name:"职场萌新报到",summary_new:'杜小路，加油加油',summary:"杜小路初入职场",creator_id:"4",creator:"chenming",update_at:"2018-04-02",is_public:0,is_checking:1}
				]
			},
			   form: {
			        visible:false,
			        title: '',
			        disabled: false,
			        data: 
			          {
			          group_id:'',
			          group_name: '',
			          summary_new:'',
			          creator_name:'',
			          update_at:''
			         }
			      },
			search:{
				useGroupName:true,
				useCreatorName:true,
				useStartUpdate_at:true,
				useEndUpdate_at:true,
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
	handleSizeChange:function(val) {
      this.table.pageCount = val;
      this.handleSearch();
    },
    handleCurrentChange:function(val) {
      this.table.pageIndex = val;
      this.handleSearch();
    },
    handleShowCheck:function(row) {
      this.form.title = "圈子修改审核";
      this.form.disabled = false;
      this.form.data.group_id =  row.group_id;
      this.form.data.group_name =  row.group_name;
      this.form.data.summary_new = row.summary_new;
      this.form.data.creator_name = row.creator_name;
      this.form.data.creator_id=row.creator_id;
      this.form.data.update_at = row.update_at;
      this.form.visible = true;
    },
    handleCheck:function(group_id,customer_id,pass){
    	var key={}
      key.group_id=group_id
      key.customer_id=customer_id
      key.pass=pass
 			var me=this;
       this.$post("/admin/updategroup/check",key)
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
      if(this.search.useGroupName&&this.search.data.group_name){
        key.group_name=this.search.data.group_name;
      }
      if(this.search.useCreatorName&&this.search.data.creator_name){
        key.creator_name=this.search.data.creator_name;
      }
      if(this.search.useStartUpdate_at&&this.search.data.startUpdate_at){
        key.startUpdate_at=this.$filter(this.search.data.startUpdate_at,'yyyy-mm-dd','0');
      }
      if(this.search.useEndUpdate_at&&this.search.data.endUpdate_at){
        key.endUpdate_at=this.$filter(this.search.data.endUpdate_at,'yyyy-mm-dd','0');
      }

      var me=this;
      this.$get("/admin/updategroup/search",key)
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

