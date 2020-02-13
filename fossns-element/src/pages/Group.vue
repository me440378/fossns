<template>
	<div>
		<el-card>
    	<div slot="header" style="height: 40px;">
    		<img src="@/assets/05.jpg" class="image">
    		<span style="float: left;margin: 12px;margin-left: 20px;">{{group.name}}</span>
        <div style="float: right;">
          <el-dialog title="举报圈子" :visible.sync="reportgroupVisible" width="30%" :before-close="handleCloseReportGroup">
            <el-form label-width="80px">
              <el-form-item label="举报原因" style="margin: 20px;">
                <el-input type="textarea" :rows="5" v-model="report.content"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer" style="margin: 20px;">
              <el-button @click="reportgroupVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleReportGroup">确 定</el-button>
            </span>
          </el-dialog>
          <el-button type="warning" @click="reportgroupVisible = true">举报圈子</el-button>
          <el-button type="success" @click="handleJumpCharge">管理圈子</el-button>
          <el-button v-if="is_joined==0" type="danger" @click="handleJoin">加入圈子</el-button>
        </div>
    	</div>
      <el-row>
        <el-col :span="18" style="text-align: left;">
          <span style="margin-left: 20px;">{{group.summary}}</span>
        </el-col>
        <el-col :span="3" style="text-align: right;">
          <el-button type="primary" @click="createtopicVisible = true" round>创建话题</el-button>
          <el-dialog title="创建话题" :visible.sync="createtopicVisible" width="30%" :before-close="handleCloseCreateTopic" style="text-align: center;">
            <el-form label-width="80px">
              <el-form-item label="话题标题" style="margin: 20px;">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer" style="margin: 20px;">
              <el-button @click="createtopicVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleCreateTopic">确 定</el-button>
            </span>
          </el-dialog>
        </el-col>
      </el-row>
    </el-card>
    <el-row v-for="item in topicList">
			<el-card>
				<div slot="header" style="height: 20px">
					<span style="float: left;margin-left: 15px;">{{item.name}}</span>
					<el-button type="text" style="float: right;" class="button" @click="handleJumpTopic
					(item.id)">进入</el-button>
				</div>
				<div style="margin-bottom: 20px;">
					<span style="position: absolute;left: 20px;">{{item.summary}}</span>
				</div>
			</el-card>
    </el-row>
	</div>
</template>
<script>
export default{
	data(){
		return{
      reportgroupVisible:false,
      createtopicVisible:false,
      report:{
        content:""
      },
      form:{
        name:""
      },
      is_joined:0,
			group:{
        // id:1,
        // name:"圈子111111",
        // summary:"这是一个和谐互助的好圈子",
        // creator_id:1,
      },
			topicList:[
				// {name:"话题1",id:1,summary:"简简介简介简介简介简介简介简介介",image:""},
				// {name:"话题2",id:2,summary:"简简介简介简介简介ss简介简fgs介简介介",image:""},
				// {name:"话题3",id:3,summary:"简简介简简介简介ss简简fgs介简介介",image:""},
				// {name:"话题4",id:4,summary:"简简介简介简介简介ss简简fgs介简介介",image:""}
			],
		}
	},
	methods:{
    handleJumpCharge:function(){
      var customer_id = localStorage.customer_id
      var creator_id = this.group.creator_id;
      if(customer_id==creator_id){
        var group_id = this.group.id
        this.$router.push("/ChargeGroup/" + group_id);
      } else {
        this.$notify({type: 'warning',title: '错误',message: '没有权限',duration: 6000})
      }
    },
		handleJoin: function(){
      var customer_id = localStorage.customer_id
      var group_id = this.$route.params.group_id
      var me = this
      var key={}
      key.customer_id=customer_id
      key.group_id=group_id
      this.$post("/group/join",key)
        .then(res=>{
          if(res.data.error==0){
            this.$notify({title: '成功',message: "已提交加入申请",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 6000})
          }
      }).catch(function(err){
       console.log(err)
      })
		},
		handleJumpTopic: function(topic_id){
      this.$router.push("/Topic/" + topic_id);
		},
    handleAll: function(){
      var customer_id = localStorage.customer_id
      var group_id = this.$route.params.group_id
      var me = this
      var key={}
      key.customer_id=customer_id
      key.group_id=group_id
      this.$post("/group/all",key)
        .then(res=>{
          me.group=res.data.group
          me.topicList=res.data.topicList
          me.is_joined=res.data.is_joined
      }).catch(function(err){
       console.log(err)
      })
    },
    handleCreateTopic:function(){
      if(this.is_joined==0){
        this.$notify({title: '错误',message: "请先加入圈子",duration: 3000})
        return
      }
      var customer_id=localStorage.customer_id
      var group_id=this.$route.params.group_id
      var key={}
      var me=this
      if(this.form.name){
        key.name=this.form.name
      } else {
        this.$notify({title: '错误',message: "请输入名称",duration: 3000})
        return;
      }
      key.group_id=group_id
      key.customer_id=customer_id
      this.$post("/topic/create",key)
        .then(res=>{
          if(res.data.error==0){
            this.$notify({title: '成功',message: "已提交创建申请",duration: 3000})
          } else {
            this.$notify({title: '失败',message: res.data.message,duration: 6000})
          }
      }).catch(function(err){
       console.log(err)
      })

      this.createtopicVisible = false;
    },
    handleCloseCreateTopic:function(){
      this.createtopicVisible = false;
    },
    handleReportGroup:function(){
      var customer_id=localStorage.customer_id
      var group_id=this.$route.params.group_id
      var key={}
      var me=this
      if(this.report.content){
        key.content=this.report.content
        key.customer_id=customer_id
        key.target_id=group_id
        key.type=0
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
      this.reportgroupVisible = false;
    },
    handleCloseReportGroup:function(){
      this.reportgroupVisible = false;
    },
	},
  watch: {
    '$route': 'handleAll'
  },
  mounted: function(){
    this.handleAll()
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
    width: 10%;
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