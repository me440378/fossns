<template>
	<div>
		<el-row>
			<el-col :span="2" style="height: 1px"></el-col>
			<el-col :span="20">
				<el-card>
		    	<div slot="header" style="height: 40px;">
		    		<img src="@/assets/05.jpg" class="image">
		    		<span style="float: left;margin: 12px;margin-left: 20px;">{{group.name}}</span>
		    		<el-button style="float: right;" type="primary" @click="handleJump(group.id)">进入圈子</el-button>
		    	</div>
		      <div style="padding: 14px;">
		        <div style="float: left;margin-left: 20px;">
							<span>{{group.summary}}</span>
		        </div>
		      </div>
		    </el-card>
				<el-card>
					<div slot="header" style="height: 30px">
						<span style="float:left;margin: 8px;margin-left: 10px;">{{topic.name}}</span>
						<div style="float: right;">
							<el-button type="warning" @click="reporttopicVisbible = true">举报话题</el-button>
							<el-dialog title="举报话题" :visible.sync="reporttopicVisbible" width="30%" :before-close="handleCloseReportTopic">
		            <el-form label-width="80px">
		              <el-form-item label="举报原因" style="margin: 20px;">
		                <el-input type="textarea" :rows="5" v-model="report.content"></el-input>
		              </el-form-item>
		            </el-form>
		            <span slot="footer" class="dialog-footer" style="margin: 20px;">
		              <el-button @click="reporttopicVisbible = false">取 消</el-button>
		              <el-button type="primary" @click="handleReportTopic">确 定</el-button>
		            </span>
		          </el-dialog>
						</div>
					</div>
					<div style="background-color: #eee;">
						<el-row v-for="(item,no) in detailList" style="margin-top:1px;margin-bottom: 1px;background-color: #fff">
							<el-col :span="4" >
								<div style="height: 20px;text-align: left;"><span style="font-size: 18px;">{{no+1}}</span></div>
								<div style="width: 120px;height: 120px;background-color: #cf5;margin:auto;margin-top: 30px; "></div>
								<div style="margin:20px">
									<span style="font-size: 13px;">{{item.customer_nickname}}</span>
								</div>
							</el-col>
							<el-col :span="20" style="padding: 20px;">
								<div style="text-align: left;"><span>{{item.content}}</span></div>
								<div style="position:absolute;bottom:0;right:0;">
									<el-dialog title="举报" :visible.sync="reportdetailVisible" width="30%" :before-close="handleCloseReportDetail">
				            <el-form label-width="80px">
				              <el-form-item label="举报原因" style="margin: 20px;">
				                <el-input type="textarea" :rows="5" v-model="report.content"></el-input>
				              </el-form-item>
				            </el-form>
				            <span slot="footer" class="dialog-footer" style="margin: 20px;">
				              <el-button @click="reportdetailVisible = false">取 消</el-button>
				              <el-button type="primary" @click="handleReportDetail">确 定</el-button>
				            </span>
				          </el-dialog>
									<el-button type="text" @click="handleShowReportDetail(item.id)">举报</el-button>
									<span style="color: #999;">{{item.created_at}}</span>
								</div>
							</el-col>
						</el-row>
					</div>
					<div>
						<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="table.pageIndex" :page-sizes="[25, 50, 75, 100]" :page-size="table.pageCount" layout="total, sizes, prev, pager, next, jumper" :total="table.total">
         		</el-pagination>
					</div>
				</el-card>
				<!-- 参与讨论 -->
				<el-card style="margin-top: 20px;">
					<div slot="header" style="text-align: left;"><span>发表讨论</span></div>
					<div>
						<el-input type="textarea" rows="5" v-model="form.content"></el-input>
						<el-button type="primary" style="margin-top: 20px;" @click="handleSubmit">提交</el-button>
					</div>
				</el-card>
			</el-col>
			<el-col :span="2" style="height: 1px"></el-col>
		</el-row>
	</div>
</template>
<script>
export default{
	data(){
		return{
			reporttopicVisbible:false,
			reportdetailVisible:false,
			reportdetailId:"",
			report:{
				content:""
			},
			group:{
				id:1,
				name:"三生三世圈",
				summary:"一个三生三世的uasdasd"
			},
			topic:{
				name:"大家来讨论"
			},
			form:{
				content:""
			},
			table:{
				pageIndex:1,
				pageCount:25,
				total:2
			},
			detailList:[
				{id:1,no:1,customer_icon:"",customer_nickname:"小明",content:"打卡！！！！！！！！！",created_at:"2018-06-15 00:28"},
				{id:2,no:2,customer_icon:"",customer_nickname:"大明",content:"打卡哥哥！！！！！",created_at:"2018-06-15 00:39"}
			]
		}
	},
	methods:{
		handleSizeChange(val) {
      this.table.pageCount = val;
      this.handleAll();
    },
    handleCurrentChange(val) {
      this.table.pageIndex = val;
      this.handleAll();
    },
    handleAll: function(){
    	var topic_id = this.$route.params.topic_id;
      var key={}
      key.topic_id=topic_id
      key.length=this.table.pageCount ;
      key.offset= (this.table.pageIndex-1)*this.table.pageCount;
      var me=this
      this.$post("/topic/all",key)
	        .then(res=>{
		          me.group=res.data.group
		          me.topic=res.data.topic
		          me.table=res.data.table
		          me.detailList=res.data.detailList
		      }).catch(function(err){
		       console.log(err)
		      })
    },
    handleSubmit: function(){
    	var customer_id=localStorage.customer_id
    	var topic_id=this.$route.params.topic_id
    	var key={}
    	if(this.form.content){
    		key.content=this.form.content
    		key.customer_id=customer_id
    		key.topic_id=topic_id
    	} else {
    		this.$notify({title: '错误',message: "内容不能为空",duration: 3000})
    		return
    	}
    	this.$post("/topicdetail/create",key)
	        .then(res=>{
	          if(res.data.error==0){
	            this.$notify({title: '成功',message: "已发送",duration: 3000})
	          } else {
	            this.$notify({title: '失败',message: res.data.message,duration: 6000})
	          }
	      }).catch(function(err){
	       console.log(err)
	      })
      this.form.content=""
    	this.handleAll()
    },
    handleJump: function(group_id){
    	this.$router.push("/Group/" + group_id);
    },
    handleReportTopic:function(){
    	var customer_id=localStorage.customer_id
      var topic_id=this.$route.params.topic_id
      var key={}
      var me=this
      if(this.report.content){
        key.content=this.report.content
        key.customer_id=customer_id
        key.target_id=topic_id
        key.type=1
      } else {
        this.$notify({title: '错误',message: "请输入内容",duration: 6000})
        return;
      }
      this.$post("/report/create",key)
        .then(res=>{
          if(res.data.error==0){
            this.$notify({title: '成功',message: "已提交举报",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 6000})
          }
      }).catch(function(err){
       console.log(err)
      })
    	this.reporttopicVisbible = false;
    },
    handleCloseReportTopic:function(){
    	this.reporttopicVisbible = false;
    },
    handleShowReportDetail:function(detail_id){
    	this.reportdetailVisible = true
    	this.reportdetailId = detail_id
    },
    handleReportDetail:function(){
    	var customer_id=localStorage.customer_id
      var detail_id=this.reportdetailId
      var key={}
      var me=this
      if(this.report.content){
        key.content=this.report.content
        key.customer_id=customer_id
        key.target_id=detail_id
        key.type=2
      } else {
        this.$notify({title: '错误',message: "请输入内容",duration: 6000})
        return;
      }
      this.$post("/report/create",key)
        .then(res=>{
          if(res.data.error==0){
            this.$notify({title: '成功',message: "已提交举报",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 6000})
          }
      }).catch(function(err){
       console.log(err)
      })
      this.reportdetailVisible = false
    },
    handleCloseReportDetail:function(){
    	this.reportdetailVisible = true
    }
	},
	watch:{
		'$route': 'handleAll'
	},
	mounted:function(){
		this.handleAll();
	}
}
</script>
<style scoped>
.popularity {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .image {
    width: 12%;
    display: block;
    float: left;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>